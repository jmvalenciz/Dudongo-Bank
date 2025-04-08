package com.dudongo_bank.cuentas.Adapters;

import com.dudongo_bank.cuentas.Models.Cliente;
import com.dudongo_bank.cuentas.Models.ClienteIdDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ClienteAdapter {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate template;
    @Value("${rabbitmq.queues.clientes.request}")
    private String requestQueue;
    @Value("${rabbitmq.queues.clientes.response}")
    private String responseQueue;
    private final Map<String, MonoSink<Cliente>> pendingRequests;

    public ClienteAdapter(ObjectMapper objectMapper, RabbitTemplate template) {
        this.objectMapper = objectMapper;
        this.template = template;
        this.pendingRequests = new ConcurrentHashMap<>();
    }

    @SneakyThrows
    public Mono<Cliente> fetchCliente(ClienteIdDTO payload) {
        Message message = createMessage(payload, "document");
        return Mono.create(monoSink -> {
            template.send(requestQueue, message);
            pendingRequests.put(message.getMessageProperties().getCorrelationId(), monoSink);
        });
    }

    @SneakyThrows
    @RabbitListener(queues = "${rabbitmq.queues.clientes.response}")
    public void handleResponse(Message message){
        MessageProperties properties = message.getMessageProperties();
        log.info("Trace headers: {}", properties.getHeaders());
        String correlationId = properties.getCorrelationId();
        MonoSink<Cliente> sink = pendingRequests.remove(correlationId);
        if(sink == null){
            return;
        }
        Cliente cliente = objectMapper.readValue(message.getBody(), Cliente.class);
        sink.success(cliente);
    }

    @SneakyThrows
    public Mono<Cliente> fetchCliente(String payload) {
        Message message = createMessage(payload, "id");
        return Mono.create(monoSink -> {
            template.send(requestQueue, message);
            pendingRequests.put(message.getMessageProperties().getCorrelationId(), monoSink);
        });
    }

    @SneakyThrows
    private Message createMessage(Object payload, String getBy){
        byte[] jsonPayload = objectMapper.writeValueAsBytes(payload);
        String correlationId  = UUID.randomUUID().toString();
        MessageProperties properties = MessagePropertiesBuilder.newInstance()
                .setReplyTo(responseQueue)
                .setCorrelationId(correlationId)
                .setHeader("getBy", getBy)
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        return new Message(jsonPayload, properties);
    }
}

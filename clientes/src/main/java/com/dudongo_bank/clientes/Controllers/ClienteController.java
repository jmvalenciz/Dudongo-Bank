package com.dudongo_bank.clientes.Controllers;

import com.dudongo_bank.clientes.Adapters.AmqpResponderAdapter;
import com.dudongo_bank.clientes.Models.Cliente;
import com.dudongo_bank.clientes.Models.ClienteIdDTO;
import com.dudongo_bank.clientes.Services.ClienteService;
import com.dudongo_bank.clientes.Utils.AmqpMessageParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * ClienteController
 */
@Controller
@Slf4j
public class ClienteController {
	private final ClienteService clienteService;
	private final AmqpMessageParser amqpMessageParser;
	private final AmqpResponderAdapter amqpResponderAdapter;

	public ClienteController(ClienteService clienteService, AmqpMessageParser amqpMessageParser, AmqpResponderAdapter amqpResponderAdapter) {
		this.clienteService = clienteService;
		this.amqpMessageParser = amqpMessageParser;
		this.amqpResponderAdapter = amqpResponderAdapter;
	}

	@RabbitListener(queues = "${rabbitmq.queues.clientes.request}")
	public void fetchCliente(Message message) {
		var properties = message.getMessageProperties();
		log.info("Trace headers: {}", properties.getHeaders());
		try{
			Mono<Cliente> response = switch ((String)properties.getHeader("getBy")) {
				case "id" -> {
					String id = amqpMessageParser.postProcessMessage(message, String.class);
					yield clienteService.fetchCliente(id);
				}
				case "document" -> {
					ClienteIdDTO dto = amqpMessageParser.postProcessMessage(message, ClienteIdDTO.class);
					yield clienteService.fetchCliente(dto);
				}
				default -> throw new IllegalArgumentException("getBy header value is not valid");
			};
			response.subscribe(cliente->{
				var replyTo = properties.getReplyTo();
				var correlationId = properties.getCorrelationId();
				amqpResponderAdapter.reply(cliente, replyTo, correlationId);
			});
		} catch (IOException e){
			log.error(e.toString());
		}
	}
}
package com.dudongo_bank.clientes.Adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AmqpResponderAdapter {
	private final RabbitTemplate template;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final Log logger = LogFactory.getLog(AmqpResponderAdapter.class);

	public AmqpResponderAdapter(RabbitTemplate template) {
		this.template = template;
	}

	@SneakyThrows
	public void reply(Object payload, String reply_to, String correlationId){
		Message message = createMessage(payload, correlationId);
		template.send(reply_to, message);
		logger.info("Published payload to "+reply_to+" with correlationId "+correlationId);
	}

	@SneakyThrows
	private Message createMessage(Object payload, String correlationId){
		byte[] jsonPayload = objectMapper.writeValueAsBytes(payload);
		MessageProperties properties = MessagePropertiesBuilder.newInstance()
				.setCorrelationId(correlationId)
				.setContentType(MessageProperties.CONTENT_TYPE_JSON)
				.build();
		return new Message(jsonPayload, properties);
	}
}

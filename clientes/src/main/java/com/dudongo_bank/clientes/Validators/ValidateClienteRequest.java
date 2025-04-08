package com.dudongo_bank.clientes.Validators;

import com.dudongo_bank.clientes.Models.ClienteIdDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidateClienteRequest implements MessagePostProcessor {

	ObjectMapper objectMapper = new ObjectMapper();

	@SneakyThrows
	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		var properties = message.getMessageProperties();
		var correlationId = properties.getCorrelationId();
		var reply_to = properties.getHeader("reply_to").toString();
		if(reply_to.isEmpty()){
			throw new IllegalArgumentException("'reply_to' is missing");
		}
		if(correlationId.isEmpty()) {
			throw new IllegalArgumentException("'correlationId' is missing");
		}
		var dto = objectMapper.readValue(message.getBody(), ClienteIdDTO.class);
		if(dto.getId().isEmpty() || dto.getIdType().isEmpty()){
			throw new IllegalArgumentException("Missing properties in body");
		}
		return new Message(objectMapper.writeValueAsBytes(dto), message.getMessageProperties());
	}
}

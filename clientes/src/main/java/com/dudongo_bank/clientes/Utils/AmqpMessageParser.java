package com.dudongo_bank.clientes.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AmqpMessageParser {
	ObjectMapper objectMapper = new ObjectMapper();

	public <T> T postProcessMessage(Message message, Class<T> type) throws AmqpException, IOException {
		var properties = message.getMessageProperties();
		var correlationId = properties.getCorrelationId();
		var reply_to = properties.getReplyTo();
		if(reply_to.isEmpty()){
			throw new IllegalArgumentException("'reply_to' is missing");
		}
		if(correlationId.isEmpty()) {
			throw new IllegalArgumentException("'correlationId' is missing");
		}
		return objectMapper.readValue(message.getBody(), type);
	}
}

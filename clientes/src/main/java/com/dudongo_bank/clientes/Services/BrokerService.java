package com.dudongo_bank.clientes.Services;

import com.dudongo_bank.clientes.Adapters.AmqpResponderAdapter;
import com.dudongo_bank.clientes.Models.Cliente;
import org.springframework.stereotype.Service;

@Service
public class BrokerService {
	private final AmqpResponderAdapter amqpResponderAdapter;

	public BrokerService(AmqpResponderAdapter amqpResponderAdapter) {
		this.amqpResponderAdapter = amqpResponderAdapter;
	}

	public void sendCliente(Cliente cliente, String reply_to, String correlationId){
		amqpResponderAdapter.reply(cliente, reply_to, correlationId);
	}
}

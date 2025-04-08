package com.dudongo_bank.clientes.Adapters;

import com.dudongo_bank.clientes.Models.Cliente;

import com.dudongo_bank.clientes.Models.ClienteIdDTO;
import reactor.core.publisher.Mono;

/**
 * IClienteAdapter
 */
public interface IClienteAdapter {
	Mono<Cliente> fetchClienteById(String clienteId);
	Mono<Cliente> fetchClienteByDocumento(ClienteIdDTO clienteId);
}

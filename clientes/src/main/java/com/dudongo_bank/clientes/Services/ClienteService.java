package com.dudongo_bank.clientes.Services;

import com.dudongo_bank.clientes.Adapters.CoreClienteAdapter;
import com.dudongo_bank.clientes.Models.Cliente;
import com.dudongo_bank.clientes.Models.ClienteIdDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {
	CoreClienteAdapter clientesAdapter;

	ClienteService(CoreClienteAdapter clientesAdapter){
		this.clientesAdapter = clientesAdapter;
	}

	public Mono<Cliente> fetchCliente(ClienteIdDTO clienteId){
		return this.clientesAdapter.fetchClienteByDocumento(clienteId);
	}

	public Mono<Cliente> fetchCliente(String clienteId){
		return this.clientesAdapter.fetchClienteById(clienteId);
	}
}

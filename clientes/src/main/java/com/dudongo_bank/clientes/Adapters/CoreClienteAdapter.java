package com.dudongo_bank.clientes.Adapters;

import com.dudongo_bank.clientes.Models.Cliente;

import com.dudongo_bank.clientes.Models.ClienteIdDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * CoreClienteAdapter
 */
@Component
@Slf4j
public class CoreClienteAdapter implements IClienteAdapter {
	private final WebClient http;

	public CoreClienteAdapter(@Value("${services.core.baseUrl}") String baseUrl) {
		this.http = WebClient.builder()
				.baseUrl(baseUrl+'/'+"clientes")
				.build();
	}

	@Override
	public Mono<Cliente> fetchClienteById(String clienteId) {
		return http.get()
				.uri("/{clienteId}", clienteId)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Cliente.class);
	}

	@Override
	public Mono<Cliente> fetchClienteByDocumento(ClienteIdDTO clienteId) {
		return http.get()
				.uri(uriBuilder -> uriBuilder.path("/")
						.queryParam("document", clienteId.getId())
						.queryParam("documentType", clienteId.getIdType())
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Cliente.class);
	}


}

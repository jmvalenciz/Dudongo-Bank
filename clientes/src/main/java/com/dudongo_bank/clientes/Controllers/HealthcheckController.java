package com.dudongo_bank.clientes.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/health")
public class HealthcheckController {

	@GetMapping("")
	Mono<Boolean> healthcheck(){
		return Mono.just(true);
	}
}

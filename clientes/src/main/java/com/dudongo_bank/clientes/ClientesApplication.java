package com.dudongo_bank.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClientesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}
}

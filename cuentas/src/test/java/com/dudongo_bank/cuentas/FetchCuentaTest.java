package com.dudongo_bank.cuentas;

import com.dudongo_bank.cuentas.Adapters.ClienteAdapter;
import com.dudongo_bank.cuentas.Controllers.CuentaController;
import com.dudongo_bank.cuentas.Models.Cliente;
import com.dudongo_bank.cuentas.Models.Cuenta;
import com.dudongo_bank.cuentas.Repositories.CuentaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@SpringBootTest
@SpringJUnitConfig
public class FetchCuentaTest {

    @Autowired
    private transient WebTestClient client;
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private RabbitListenerTestHarness harness;
    @Autowired
    private ClienteAdapter clienteAdapter;
    @MockitoBean
    private CuentaRepository cuentaRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fetchCliente(){
        var cliente = Cliente.builder().build();
        client.get().uri("/cuentas/cliente")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Cliente.class).equals(cliente);
    }

    @SneakyThrows
    @Test
    void testClienteAdapter() {
        Cliente c = Cliente.builder().build();
        var message = new Message(objectMapper.writeValueAsBytes(c));
        ClienteAdapter spy = harness.getSpy("cuentas.cliente.response");
        var answer = harness.getLatchAnswerFor("cuentas.cliente.response", 2);
        Mockito.doAnswer(answer).when(spy).handleResponse(message);
    }

    @Test
    void fetchCuentasByCustomerId(){
        var expectedBody = new Cuenta[]{
                Cuenta.builder().build(),
                Cuenta.builder().build(),
        };
        client.get().uri("/cuentas/{}", "123")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Cuenta.class).equals(expectedBody);
    }

    @BeforeEach
    void prepareCuentasRepository(){
        Mockito.when(cuentaRepository.findAllByClienteId("123"))
                .thenReturn( Flux.fromArray(new Cuenta[]{
                        Cuenta.builder().build(),
                        Cuenta.builder().build(),
                }) );
    }
}

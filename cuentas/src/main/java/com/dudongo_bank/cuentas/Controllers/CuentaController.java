package com.dudongo_bank.cuentas.Controllers;

import com.dudongo_bank.cuentas.Adapters.ClienteAdapter;
import com.dudongo_bank.cuentas.Models.Cliente;
import com.dudongo_bank.cuentas.Models.Cuenta;
import com.dudongo_bank.cuentas.Services.CuentaService;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;
    private final ClienteAdapter clienteAdapter;
    private static final Log logger = LogFactory.getLog(CuentaController.class);

    public CuentaController(CuentaService cuentaService, ClienteAdapter clienteAdapter) {
        this.cuentaService = cuentaService;
        this.clienteAdapter = clienteAdapter;
    }

    @GetMapping("/cliente")
    public Mono<Cliente> fetchCliente(){
        var id = "550e8400-e29b-41d4-a716-446655440000";
        return clienteAdapter.fetchCliente(id);
    }

    @GetMapping()
    public Flux<Cuenta> fetchCuentas(@Header @Valid String clienteId){
        return cuentaService.findCuentasByClienteId(clienteId);
    }
}

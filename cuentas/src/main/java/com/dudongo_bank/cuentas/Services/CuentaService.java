package com.dudongo_bank.cuentas.Services;

import com.dudongo_bank.cuentas.Adapters.ClienteAdapter;
import com.dudongo_bank.cuentas.ErrorHandlers.AccountTypeLimitExceededException;
import com.dudongo_bank.cuentas.Models.Cuenta;
import com.dudongo_bank.cuentas.Models.NuevaCuentaDTO;
import com.dudongo_bank.cuentas.Repositories.CuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ClienteAdapter clienteAdapter;

    public CuentaService(CuentaRepository cuentaRepository, ClienteAdapter clienteAdapter) {
        this.cuentaRepository = cuentaRepository;
        this.clienteAdapter = clienteAdapter;
    }

    Mono<Cuenta> fetchCuenta(String id){
        return cuentaRepository.findById(id);
    }

    public Flux<Cuenta> findCuentasByClienteId(String id){
        return cuentaRepository.findAllByClienteId(id);
    }

    public Mono<Cuenta> createCuenta(NuevaCuentaDTO nuevaCuentaDTO){
        var clienteFuture = clienteAdapter.fetchCliente(nuevaCuentaDTO.getIdCliente());
        return clienteFuture.flatMap(cliente -> findCuentasByClienteId(cliente.getId())
            .filter(i->i.getTipoCuenta() == nuevaCuentaDTO.getTipoCuenta())
            .collectList()
            .flatMap(cuentas -> {
                if(cuentas.size() > 5){
                    return Mono.error(new AccountTypeLimitExceededException(cuentas.getFirst().getTipoCuenta()));
                }
                var cuenta = Cuenta.builder()
                        .tipoCuenta(nuevaCuentaDTO.getTipoCuenta())
                        .id(UUID.randomUUID().toString())
                        .clienteId(cliente.getId())
                        .build();
                return cuentaRepository.save(cuenta);
            })
        );
    }
}

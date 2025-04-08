package com.dudongo_bank.cuentas.Repositories;

import com.dudongo_bank.cuentas.Models.Cuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CuentaRepository extends ReactiveCrudRepository<Cuenta, String> {
    Flux<Cuenta> findAllByClienteId(String id);
}

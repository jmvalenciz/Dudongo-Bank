package com.dudongo_bank.cuentas.Models;

import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cuenta {
    @Id
    String id;
    String clienteId;
    @Min(0)
    int saldo;
    @Min(0)
    int maxSaldo;
    TipoCuenta tipoCuenta;
}

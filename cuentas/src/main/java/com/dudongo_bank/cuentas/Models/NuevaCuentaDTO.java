package com.dudongo_bank.cuentas.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NuevaCuentaDTO {
    ClienteIdDTO idCliente;
    TipoCuenta tipoCuenta;
    BigInteger cupoMaximo;
    String divisa;
}

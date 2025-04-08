package com.dudongo_bank.cuentas.Models;

import lombok.*;

/**
 * Cliente
 */
@Builder()
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {
    String id;
    String numeroDocumento;
    String tipoDocumento;
    String nombreCompleto;
    String fechaNacimiento;
    String direccion;
    String telefono;
    String email;
    String fechaRegistro;
    EstadoCuenta estadoCuenta;
}

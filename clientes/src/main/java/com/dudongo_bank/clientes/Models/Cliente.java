package com.dudongo_bank.clientes.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

/**
 * Cliente
 */
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente implements Serializable {
	@JsonAlias("ID")
	String id;
	@JsonAlias("NumeroDocumento")
	String numeroDocumento;
	@JsonAlias("TipoDocumento")
	String tipoDocumento;
	@JsonAlias("NombreCompleto")
	String nombreCompleto;
	@JsonAlias("FechaNacimiento")
	String fechaNacimiento;
	@JsonAlias("Direccion")
	String direccion;
	@JsonAlias("Telefono")
	String telefono;
	@JsonAlias("Email")
	String email;
	@JsonAlias("FechaRegistro")
	String fechaRegistro;
	@JsonIgnore
	String passwordHash;
	@JsonAlias("EstadoCuenta")
	EstadoCuenta estadoCuenta;
}

package com.personal.postgresql.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class EstadoCuentaDTO implements Serializable {

	private Double saldoAnterior;
	private Double saldoNuevo;
	private MaestroValorDTO idTipoTransaccion;
	private Date fecha;
	private Long idRelacionalMovimiento;
}

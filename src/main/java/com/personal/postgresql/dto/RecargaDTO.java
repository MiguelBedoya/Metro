package com.personal.postgresql.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RecargaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private double valor;
	private Date fecRecarga;
	private CuentaDTO idCuenta;
}

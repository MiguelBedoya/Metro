package com.personal.postgresql.dto;

import java.io.Serializable;
import java.util.Date;

import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.Estacion;
import com.personal.postgresql.entities.MaestroValor;

import lombok.Data;

@Data
public class PrestamoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private double valor;
	private MaestroValorDTO estado;
	private Cuenta idCuenta;
	
}

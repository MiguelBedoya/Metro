package com.personal.postgresql.dto;

import java.io.Serializable;

import com.personal.postgresql.entities.Tarifa;

import lombok.Data;

@Data
public class BeneficioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String nombre;
	
	private String descripcion;
	
	private TarifaDTO idTarifa;

}

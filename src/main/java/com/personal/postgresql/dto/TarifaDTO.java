package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TarifaDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;


	private long id;
	
	
	private Double valor;
	
}

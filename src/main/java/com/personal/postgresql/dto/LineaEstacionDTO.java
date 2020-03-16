package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LineaEstacionDTO implements Serializable {
	
	private long id;
	private EstacionDTO idEstacion;
	private LineaDTO idLinea;
}

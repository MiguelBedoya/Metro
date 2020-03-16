package com.personal.postgresql.dto;

import lombok.Data;
@Data
public class RutaEstacionDTO {
	private long id;
	private int orden;
	private EstacionDTO idEstacion;
	private RutaDTO idRuta;
	
}

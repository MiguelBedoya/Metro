package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class VehiculoDTO implements Serializable{

	private long id;
	private int capacidad;
	private String nombre;
	private MaestroValorDTO idTipoVehiculo;
	
}

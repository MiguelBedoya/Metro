package com.personal.postgresql.dto;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@Data
public class LineaDTO implements Serializable{
	
	private long id;
	
	private String nombre;
	
	private String origen;
	
	private String destino;
	
	private MaestroValorDTO tipoLinea;
	
	private ArrayList<VehiculoDTO> vehiculos;
	
	private ArrayList<TarifaDTO> tarifas;
}

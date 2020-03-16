package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class GeolocalizacionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double latitud;
	
	private Double altura;
	
	private Double longitud;
	
}

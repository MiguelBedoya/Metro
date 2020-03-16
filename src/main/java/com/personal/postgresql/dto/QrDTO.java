package com.personal.postgresql.dto;

import java.io.Serializable;

import com.personal.postgresql.entities.EstadoCuenta;

import lombok.Data;

@Data
public class QrDTO implements Serializable{
	
	private long id;
	private String codigo;
	private EstadoCuenta idEstadoCuenta;

}

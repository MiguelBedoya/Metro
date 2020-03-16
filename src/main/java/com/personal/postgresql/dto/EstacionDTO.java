package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class EstacionDTO implements Serializable {
	
	private long id;
	private String nombre;
}

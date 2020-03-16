package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CuentaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private double saldo;
}

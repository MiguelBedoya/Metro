package com.personal.postgresql.dto;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class DatoBiometricoDTO implements Serializable {
	
	private long id;

	private String dato;


}

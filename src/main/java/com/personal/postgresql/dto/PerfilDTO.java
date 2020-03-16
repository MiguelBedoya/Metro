package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PerfilDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String nombre;
	private String descripcion;
	private MaestroValorDTO tipoPerfil;

}

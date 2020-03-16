package com.personal.postgresql.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SolicitudDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private Date fechaInicio;
	private Date fechaFin;
	private MaestroValorDTO idEstadoSolicitud;
	private UsuarioDTO idUsuario;

}

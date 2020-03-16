package com.personal.postgresql.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Getter
public class RespuestaGenerica <T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> listaRespuesta = new ArrayList<T>();
	private T objetoRespuesta = null;
	private String respuesta = "";
	private String variable = "";
	
	public RespuestaGenerica() {
	}

	public RespuestaGenerica(List<T> listaRespuesta, T objetoRespuesta, String respuesta, String variable) {
		super();
		this.listaRespuesta = listaRespuesta;
		this.objetoRespuesta = objetoRespuesta;
		this.respuesta = respuesta;
		this.variable = variable;
	}
	
	public RespuestaGenerica(T objetoRespuesta, String respuesta, String variable) {
		super();
		this.listaRespuesta = new ArrayList<T>();
		this.objetoRespuesta = objetoRespuesta;
		this.respuesta = respuesta;
		this.variable = variable;
	}
	
	public RespuestaGenerica(List<T> listaRespuesta, String respuesta, String variable) {
		super();
		this.listaRespuesta = listaRespuesta;
		this.respuesta = respuesta;
		this.variable = variable;
	}
	
	public RespuestaGenerica(String respuesta, String variable) {
		super();
		this.listaRespuesta = new ArrayList<T>();
		this.respuesta = respuesta;
		this.variable = variable;
	}
	
}

package com.personal.postgresql.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.DatoBiometrico;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Perfil;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String nombres;
	
	private String apellidos;
	
	private String documento;
	
	private String email;
	
	private String telefono;
	
	private String contrasena;	
	
	private String direccion;
	
	private Date fechaNacimiento;
	
	private MaestroValorDTO tipoDocumento;
	
	private MaestroValorDTO tipoSangre;
	
	private DatoBiometricoDTO idDatoBiometrico;
	
	private PerfilDTO idPerfil;

	private CuentaDTO idCuenta;

	
}

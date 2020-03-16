package com.personal.postgresql.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String nombres;
	
	@Column
	private String apellidos;
	
	@Column(unique = true)
	private String documento;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String telefono;
	
	@Column(unique = true)
	private String contrasena;
	
	@Column
	private String direccion;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@ManyToOne
	@JoinColumn(name = "tipo_documento")	
	private MaestroValor tipoDocumento;
	
	@ManyToOne
	@JoinColumn(name = "tipo_sangre")
	private MaestroValor tipoSangre;
	
	@OneToOne
	@JoinColumn(name = "id_dato_biometrico")
	private DatoBiometrico idDatoBiometrico;

	@OneToOne
	@JoinColumn(name = "id_cuenta")
	private Cuenta idCuenta;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil idPerfil;
}

package com.personal.postgresql.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "RUTA_ESTACION")
public class RutaEstacion  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private int orden;
	
	@ManyToOne
	@JoinColumn(name = "id_estacion")
	private Estacion idEstacion;
	
	@ManyToOne
	@JoinColumn(name = "id_ruta")
	private Ruta idRuta;

}

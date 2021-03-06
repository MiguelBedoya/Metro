package com.personal.postgresql.entities;

import java.io.Serializable;

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
@Table(name = "PRESTAMO")
public class Prestamo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "estado")
	private MaestroValor estado;
	
	@OneToOne
	@JoinColumn(name = "id_cuenta")
	private Cuenta idCuenta;

}

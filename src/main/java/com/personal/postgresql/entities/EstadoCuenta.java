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
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ESTADO_CUENTA")
public class EstadoCuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "saldo_anterior")
	private Double saldoAnterior;
	
	@Column(name = "saldo_nuevo")
	private Double saldoNuevo;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_transaccion")
	private MaestroValor idTipoTransaccion;
	
	@Column
	private Date fecha;
	
	@Column
	private Long idRelacionalMovimiento;
	
	

}

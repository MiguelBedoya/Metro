package com.personal.postgresql.entities;

import java.io.Serializable;

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
@Table(name = "PAGO")
public class Pago  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "id_qr_usado")
	private Qr idQrUsado;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_pago")
	private MaestroValor idTipoPago;
	
	@OneToOne
	@JoinColumn(name = "id_estacion")
	private Estacion idEstacion;
	

}

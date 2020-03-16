package com.personal.postgresql.entities;

import java.io.Serializable;

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
@Table(name = "ADJUNTO_SOLICITUD")
public class AdjuntoSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_adjunto")
	private Adjunto idAdjunto;
	
	@ManyToOne
	@JoinColumn(name = "id_solicitud")
	private Solicitud idSolicitud;

}

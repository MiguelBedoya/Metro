package com.personal.postgresql.dto;

import java.io.Serializable;

import com.personal.postgresql.entities.Qr;

import lombok.Data;
@Data
public class PagoDTO implements Serializable{

	private long id;
	private MaestroValorDTO idTipoPago;
	private EstacionDTO idEstacion;
	private QrDTO idQrUsado;
	private CuentaDTO idCuentaVinculada;
	
}

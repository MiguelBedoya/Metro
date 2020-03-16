package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.QrDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.EstadoCuenta;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Qr;
import com.personal.postgresql.entities.Vehiculo;

public class QrMapper {
	
	
	public QrDTO obtenerQrDTO(Qr qrACastear) {

		QrDTO qrARetornar = new QrDTO();
		qrARetornar.setId(qrACastear.getId());
		qrARetornar.setCodigo(qrACastear.getCodigo());
		
		return qrARetornar;
		
	}
	
	public Qr obtenerQrPorEntidad(QrDTO qrACastear) {

		Qr qrARetornar = new Qr();
		qrARetornar.setId(qrACastear.getId());
		qrARetornar.setCodigo(qrACastear.getCodigo());
		
		return qrARetornar;
		
	}

}

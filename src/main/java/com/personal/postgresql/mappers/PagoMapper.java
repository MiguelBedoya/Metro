package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.EstacionDTO;
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.PagoDTO;
import com.personal.postgresql.dto.QrDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.Estacion;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Pago;
import com.personal.postgresql.entities.Qr;
import com.personal.postgresql.entities.Vehiculo;

public class PagoMapper {

	
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();
	private final EstacionMapper estacionrMapper = new EstacionMapper();
	private final QrMapper qrMapper = new QrMapper();
	
	public PagoDTO obtenerPagoDTO(Pago pagoACastear) {
		
		MaestroValor maestroValorACastearPorUnDTO  = pagoACastear.getIdTipoPago();
		Estacion estacionrACastearPorUnDTO = pagoACastear.getIdEstacion();
		Qr qrACastearPorUnDTO = pagoACastear.getIdQrUsado();
		
		MaestroValorDTO maestroARetornar = this.maestroValorMapper.obtenerMaestroValorDTO(maestroValorACastearPorUnDTO);
		EstacionDTO estacionARetornar = this.estacionrMapper.obtenerEstacionDTO(estacionrACastearPorUnDTO);
		QrDTO qrARetornar = this.qrMapper.obtenerQrDTO(qrACastearPorUnDTO);
		
		PagoDTO pagoARetornar = new PagoDTO();
		
		pagoARetornar.setId(pagoACastear.getId());
		pagoARetornar.setIdTipoPago(maestroARetornar);
		pagoARetornar.setIdEstacion(estacionARetornar);
		pagoARetornar.setIdQrUsado(qrARetornar);
		
		return pagoARetornar;
		
	}
	public Pago obtenerPago(PagoDTO pagoDTOACastear) {

		MaestroValorDTO maestroValorACastearPorUnaEntidad = pagoDTOACastear.getIdTipoPago();
		EstacionDTO estacionrACastearPorUnaEntidad = pagoDTOACastear.getIdEstacion();
		QrDTO qrACastearPorEntidad = pagoDTOACastear.getIdQrUsado();
		
		MaestroValor maestroARetornar = this.maestroValorMapper.obtenerMaestroValor(maestroValorACastearPorUnaEntidad);
		Estacion estacionARetornar = this.estacionrMapper.obtenerEstacion(estacionrACastearPorUnaEntidad);
		Qr qrARetornar = this.qrMapper.obtenerQrPorEntidad(qrACastearPorEntidad);

		Pago pagoARetornar = new Pago();
		pagoARetornar.setId(pagoDTOACastear.getId());
		pagoARetornar.setIdTipoPago(maestroARetornar);
		pagoARetornar.setIdEstacion(estacionARetornar);
		pagoARetornar.setIdQrUsado(qrARetornar);
		
		return pagoARetornar;
		
		
		
	}
	
}

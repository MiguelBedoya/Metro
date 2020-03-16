package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.EstacionDTO;
import com.personal.postgresql.dto.RutaDTO;
import com.personal.postgresql.dto.RutaEstacionDTO;
import com.personal.postgresql.entities.Estacion;
import com.personal.postgresql.entities.Ruta;
import com.personal.postgresql.entities.RutaEstacion;

public class RutaEstacionMapper {
	private final EstacionMapper estacionMapper = new EstacionMapper();
	private final RutaMapper rutaMapper = new RutaMapper();
	
	public RutaEstacionDTO obtenerRutaEstacionDTO(RutaEstacion rutaEstacionACastear) {

		Estacion estacionACastearPorUnDTO = rutaEstacionACastear.getIdEstacion();
		Ruta rutaACastearPorUnDTO = rutaEstacionACastear.getIdRuta();
		
		EstacionDTO estacionARetornar = this.estacionMapper.obtenerEstacionDTO(estacionACastearPorUnDTO);
		RutaDTO  rutaARetornar = this.rutaMapper.obtenerRutaDTO(rutaACastearPorUnDTO);
		
		RutaEstacionDTO rutaEstacionARetornar = new RutaEstacionDTO();
		rutaEstacionARetornar.setId(rutaEstacionACastear.getId());
		rutaEstacionARetornar.setOrden(rutaEstacionACastear.getOrden());
		rutaEstacionARetornar.setIdEstacion(estacionARetornar);
		rutaEstacionARetornar.setIdRuta(rutaARetornar);
		return rutaEstacionARetornar;
		
	}
	
	
	public RutaEstacion obtenerRutaEstacion(RutaEstacionDTO rutaEstacionDTOACastear) {

		EstacionDTO estacionACastearPorUnaEntidad = rutaEstacionDTOACastear.getIdEstacion();
		RutaDTO rutaValorACastearPorUnaEntidad = rutaEstacionDTOACastear.getIdRuta();
		Estacion estacionARetornar = this.estacionMapper.obtenerEstacion(estacionACastearPorUnaEntidad);
		Ruta rutaARetornar = this.rutaMapper.obtenerRuta(rutaValorACastearPorUnaEntidad);

		RutaEstacion rutaEstacionARetornar = new RutaEstacion();
		rutaEstacionARetornar.setId(rutaEstacionDTOACastear.getId());
		rutaEstacionARetornar.setOrden(rutaEstacionDTOACastear.getOrden());
		rutaEstacionARetornar.setIdEstacion(estacionARetornar);
		rutaEstacionARetornar.setIdRuta(rutaARetornar);
		return rutaEstacionARetornar;
		
	}

	
}

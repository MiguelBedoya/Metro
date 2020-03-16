package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.EstacionDTO;
import com.personal.postgresql.dto.LineaDTO;
import com.personal.postgresql.dto.LineaEstacionDTO;
import com.personal.postgresql.entities.Estacion;
import com.personal.postgresql.entities.Linea;
import com.personal.postgresql.entities.LineaEstacion;

public class LineaEstacionMapper {
	private final EstacionMapper estacionMapper = new EstacionMapper();
	private final LineaMapper lineaMapper = new LineaMapper();
	
	public LineaEstacionDTO obtenerLineaEstacionDTO(LineaEstacion lineaEstacion) {
		LineaEstacionDTO lineaEstacionDTO = new LineaEstacionDTO();
		
		Estacion estacionACastearPorUnDTO = lineaEstacion.getIdEstacion();
		EstacionDTO estacionDTOARetornar = this.estacionMapper
				.obtenerEstacionDTO(estacionACastearPorUnDTO);
		
		Linea lineaACastearPorUnDTO = lineaEstacion.getIdLinea();
		LineaDTO lineaDTOARetornar = this.lineaMapper
				.obtenerLineaDTO(lineaACastearPorUnDTO, null, null);
		
		lineaEstacionDTO.setId(lineaEstacion.getId());
		lineaEstacionDTO.setIdEstacion(estacionDTOARetornar);
		lineaEstacionDTO.setIdLinea(lineaDTOARetornar);
		return lineaEstacionDTO;
	}
	
	public LineaEstacion obtenerLineaEstacion(LineaEstacionDTO lineaEstacionDTO) {
		LineaEstacion lineaEstacion = new LineaEstacion();
		
		EstacionDTO estacionDTOACastearPorUnaEntidad = lineaEstacionDTO.getIdEstacion();
		Estacion estacionARetornar = this.estacionMapper
				.obtenerEstacion(estacionDTOACastearPorUnaEntidad);
		
		LineaDTO lineaDTOACastearPorUnaEntidad = lineaEstacionDTO.getIdLinea();
		Linea lineaARetornar = this.lineaMapper
				.obtenerLinea(lineaDTOACastearPorUnaEntidad);
		
		lineaEstacion.setIdEstacion(estacionARetornar);
		lineaEstacion.setIdLinea(lineaARetornar);
		lineaEstacion.setId(lineaEstacionDTO.getId());
		return lineaEstacion;
	}
}

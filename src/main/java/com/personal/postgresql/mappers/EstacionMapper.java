package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.EstacionDTO;
import com.personal.postgresql.entities.Estacion;

public class EstacionMapper {
	
	public EstacionDTO obtenerEstacionDTO(Estacion estacion) {
		EstacionDTO estacionDTO = new EstacionDTO();
		estacionDTO.setNombre(estacion.getNombre());
		estacionDTO.setId(estacion.getId());
		return estacionDTO;
	}
	
	public Estacion obtenerEstacion(EstacionDTO estacionDTO) {
		Estacion estacion = new Estacion();
		estacion.setNombre(estacionDTO.getNombre());
		estacion.setId(estacionDTO.getId());
		return estacion;
	}
}

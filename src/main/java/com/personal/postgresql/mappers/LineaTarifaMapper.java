package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.entities.Linea;
import com.personal.postgresql.entities.LineaTarifa;
import com.personal.postgresql.entities.Tarifa;

public class LineaTarifaMapper {
	

	public LineaTarifa obtenerLineaTarifa(TarifaDTO tarifaDTO,Linea linea) {
		LineaTarifa lineaTarifaARetornar = new LineaTarifa();
		lineaTarifaARetornar.setIdLinea(linea);
		Tarifa tarifa = new Tarifa();
		tarifa.setId(tarifaDTO.getId());	
		lineaTarifaARetornar.setIdTarifa(tarifa);
		return lineaTarifaARetornar;
	}

}

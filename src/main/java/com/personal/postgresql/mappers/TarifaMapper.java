package com.personal.postgresql.mappers;


import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.entities.Tarifa;

import lombok.Data;

@Data
public class TarifaMapper {

	
	public TarifaDTO obtenerTarifaDTO(Tarifa tarifaACastear) {
		TarifaDTO geoDtoARetornar = new TarifaDTO();
		geoDtoARetornar.setId(tarifaACastear.getId());
		geoDtoARetornar.setValor(tarifaACastear.getValor());
		return geoDtoARetornar;
	}
	
	public Tarifa obtenerTarifa(TarifaDTO tarifaDTOACastear) {
		Tarifa geoARetornar = new Tarifa();
		geoARetornar.setId(tarifaDTOACastear.getId());
		geoARetornar.setValor(tarifaDTOACastear.getValor());
		return geoARetornar;
	}
}

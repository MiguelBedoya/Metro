package com.personal.postgresql.mappers;

import java.io.Serializable;


import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.entities.MaestroValor;

import lombok.Data;

@Data
public class MaestroValorMapper {

	public MaestroValorDTO obtenerMaestroValorDTO(MaestroValor maestroACastear) {
		MaestroValorDTO maestroValorDTOARetornar =  new MaestroValorDTO();
		maestroValorDTOARetornar.setDato(maestroACastear.getDato());
		maestroValorDTOARetornar.setId(maestroACastear.getId());
		return maestroValorDTOARetornar;
	}
	
	public MaestroValor obtenerMaestroValor(MaestroValorDTO maestroDTOACastear) {
		MaestroValor maestroValorARetornar =  new MaestroValor();
		maestroValorARetornar.setId(maestroDTOACastear.getId());
		return maestroValorARetornar;
	}
	
}

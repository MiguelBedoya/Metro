package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.RutaDTO;
import com.personal.postgresql.entities.Ruta;

public class RutaMapper {
		
	public RutaDTO obtenerRutaDTO(Ruta rutaoACastear) {

		RutaDTO rutaARetornar = new RutaDTO();
		rutaARetornar.setOrigen(rutaoACastear.getOrigen());
		rutaARetornar.setId(rutaoACastear.getId());
		rutaARetornar.setDestino(rutaoACastear.getDestino());

		return rutaARetornar;
		
	}
	
	
	public Ruta obtenerRuta(RutaDTO rutaDTOACastear) {

		Ruta rutaARetornar = new Ruta();
		rutaARetornar.setOrigen(rutaDTOACastear.getOrigen());
		rutaARetornar.setId(rutaDTOACastear.getId());
		rutaARetornar.setDestino(rutaDTOACastear.getDestino());
		
		return rutaARetornar;
		
	}

}

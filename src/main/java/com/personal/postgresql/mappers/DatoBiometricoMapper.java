package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.DatoBiometricoDTO;
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.entities.DatoBiometrico;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Usuario;

public class DatoBiometricoMapper {
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();
	
	public DatoBiometricoDTO obtenerDatoDTO(DatoBiometrico datoACastear) {
		DatoBiometricoDTO datosDtoARetornar = new DatoBiometricoDTO();
		
		datosDtoARetornar.setId(datoACastear.getId());
		datosDtoARetornar.setDato(datoACastear.getDato());

		return datosDtoARetornar;
	}
	
	public DatoBiometrico obtenerDato(DatoBiometricoDTO datoDTOACastear) {
		DatoBiometrico datosARetornar = new DatoBiometrico();
		
		datosARetornar.setId(datoDTOACastear.getId());
		datosARetornar.setDato(datoDTOACastear.getDato());

		return datosARetornar;

	}

}

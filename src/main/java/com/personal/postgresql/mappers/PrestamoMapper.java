package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.PrestamoDTO;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Prestamo;

public class PrestamoMapper {
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();	
	
	public PrestamoDTO obtenerPrestamoDTO(Prestamo prestamoCastear) {
        MaestroValor maestroValorACastearPorUnDTO = prestamoCastear.getEstado();
		
		MaestroValorDTO maestroARetornar = this.maestroValorMapper
				.obtenerMaestroValorDTO(maestroValorACastearPorUnDTO);
		
		
		PrestamoDTO prestamoDTO = new PrestamoDTO();
		prestamoDTO.setId(prestamoCastear.getId());
		prestamoDTO.setValor(prestamoCastear.getValor());
		prestamoDTO.setEstado(maestroARetornar);
		prestamoDTO.setIdCuenta(prestamoCastear.getIdCuenta());
		return prestamoDTO;
	}
	
	public Prestamo obtenerPrestamo(PrestamoDTO prestamoCastear) {
        MaestroValorDTO maestroValorACastearPorUnaEntidad = prestamoCastear.getEstado();
		
		MaestroValor maestroARetornar = this.maestroValorMapper
				.obtenerMaestroValor(maestroValorACastearPorUnaEntidad);
		
		Prestamo prestamo =  new Prestamo();
		prestamo.setId(prestamoCastear.getId());
		prestamo.setValor(prestamoCastear.getValor());
		prestamo.setEstado(maestroARetornar);
		prestamo.setIdCuenta(prestamoCastear.getIdCuenta());
		return prestamo;
	}
	

}

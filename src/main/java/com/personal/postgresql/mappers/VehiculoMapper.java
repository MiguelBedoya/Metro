package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Vehiculo;

import lombok.Data;

@Data
public class VehiculoMapper {

	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();

	public VehiculoDTO obtenerVehiculoDTO(Vehiculo vehiculoACastear) {

		MaestroValor maestroValorACastearPorUnDTO = vehiculoACastear.getIdTipoVehiculo();
		
		MaestroValorDTO maestroARetornar = this.maestroValorMapper
				.obtenerMaestroValorDTO(maestroValorACastearPorUnDTO);

		VehiculoDTO vehiculoARetornar = new VehiculoDTO();
		vehiculoARetornar.setCapacidad(vehiculoACastear.getCapacidad());
		vehiculoARetornar.setId(vehiculoACastear.getId());
		vehiculoARetornar.setNombre(vehiculoACastear.getNombre());
		vehiculoARetornar.setCapacidad(vehiculoACastear.getCapacidad());
		vehiculoARetornar.setIdTipoVehiculo(maestroARetornar);
		
		return vehiculoARetornar;
		
	}
	
	
	public Vehiculo obtenerVehiculo(VehiculoDTO vehiculoDTOACastear) {

		MaestroValorDTO maestroValorACastearPorUnaEntidad = vehiculoDTOACastear.getIdTipoVehiculo();
		
		MaestroValor maestroARetornar = this.maestroValorMapper
				.obtenerMaestroValor(maestroValorACastearPorUnaEntidad);

		Vehiculo vehiculoARetornar = new Vehiculo();
		vehiculoARetornar.setCapacidad(vehiculoDTOACastear.getCapacidad());
		vehiculoARetornar.setId(vehiculoDTOACastear.getId());
		vehiculoARetornar.setNombre(vehiculoDTOACastear.getNombre());
		vehiculoARetornar.setCapacidad(vehiculoDTOACastear.getCapacidad());
		vehiculoARetornar.setIdTipoVehiculo(maestroARetornar);
		
		return vehiculoARetornar;
		
	}

}

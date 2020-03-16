package com.personal.postgresql.mappers;

import java.util.ArrayList;

import com.personal.postgresql.dto.LineaDTO;
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.Linea;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Vehiculo;

public class LineaMapper {
	
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();
	private final VehiculoMapper vehiculoMapper = new VehiculoMapper();

	public LineaDTO obtenerLineaDTO(Linea lineaACastear, ArrayList<VehiculoDTO> vehiculos, ArrayList<TarifaDTO> tarifas) {

		MaestroValor maestroValorACastearPorUnDTO = lineaACastear.getTipoLinea();
		
		MaestroValorDTO maestroARetornar = this.maestroValorMapper
				.obtenerMaestroValorDTO(maestroValorACastearPorUnDTO);
				
		LineaDTO lineaARetornar = new LineaDTO();
		lineaARetornar.setId(lineaACastear.getId());
		lineaARetornar.setNombre(lineaACastear.getNombre());
		lineaARetornar.setDestino(lineaACastear.getDestino());
		lineaARetornar.setOrigen(lineaACastear.getOrigen());
		lineaARetornar.setTipoLinea(maestroARetornar);
		lineaARetornar.setVehiculos(vehiculos);
		lineaARetornar.setTarifas(tarifas);
		
		return lineaARetornar;
		
	}
	
	
	public Linea obtenerLinea(LineaDTO lineaDTOACastear) {

		MaestroValorDTO maestroValorACastearPorUnaEntidad = lineaDTOACastear.getTipoLinea();
		
		MaestroValor maestroARetornar = this.maestroValorMapper
				.obtenerMaestroValor(maestroValorACastearPorUnaEntidad);

		Linea lineaARetornar = new Linea();
		lineaARetornar.setId(lineaDTOACastear.getId());
		lineaARetornar.setNombre(lineaDTOACastear.getNombre());
		lineaARetornar.setOrigen(lineaDTOACastear.getOrigen());
		lineaARetornar.setDestino(lineaDTOACastear.getDestino());
		lineaARetornar.setTipoLinea(maestroARetornar);
		
		return lineaARetornar;
		
	}


}

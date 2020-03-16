package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.Linea;
import com.personal.postgresql.entities.LineaVehiculo;
import com.personal.postgresql.entities.Vehiculo;

public class LineaVehiculoMapper {
	
	public LineaVehiculo obtenerLineaVehiculo(VehiculoDTO vehiculoDTO,Linea linea) {
		LineaVehiculo lineaVehiculoARetornar = new LineaVehiculo();
		lineaVehiculoARetornar.setIdLinea(linea);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setId(vehiculoDTO.getId());
		lineaVehiculoARetornar.setIdVehiculo(vehiculo);
		return lineaVehiculoARetornar;
	}

}

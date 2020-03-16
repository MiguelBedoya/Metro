package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.dto.LineaEstacionDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.LineaEstacionService;

@RestController
@RequestMapping(value = ConstantesApis.LINEA_ESTACION_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class LineaEstacionController {
	
	@Autowired
	private LineaEstacionService lineaEstacionService;
	
	@GetMapping(value = ConstantesApis.LINEA_ESTACION_API_OBTENER_LINEAS, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerLineasEstacion(
				@RequestParam long idEstacion
			){
		return this.lineaEstacionService.obtenerLineasPorIdEstacion(idEstacion);
	}
	
	@GetMapping(value = ConstantesApis.LINEA_ESTACION_API_OBTENER_ESTACIONES, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerEstacionesLinea(
				@RequestParam long idLinea
			){
		return this.lineaEstacionService.obtenerEstacionesPorIdLinea(idLinea);
	}
	
	@PostMapping(value = ConstantesApis.LINEA_ESTACION_API_GUARDAR_O_ACTUALIZAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarLineaEstacion(
				@RequestBody LineaEstacionDTO lineaEstacionDtoAGuardar
			){
		return this.lineaEstacionService.guardarLineaEstacion(lineaEstacionDtoAGuardar);
	}
	
	@DeleteMapping(value = ConstantesApis.LINEA_ESTACION_API_BORRAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarVehiculo(
				@RequestParam long idLineaEstacionBorrar
			){
		return this.lineaEstacionService.eliminarLineaEstacion(idLineaEstacionBorrar);
	}

}

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
import com.personal.postgresql.dto.RutaEstacionDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.RutaEstacionService;
@RestController
@RequestMapping(value = ConstantesApis.RUTAESTACION_API)
public class RutaEstacionController {
	
	@Autowired
	private RutaEstacionService rutaEstacionService;
	
	
	@GetMapping(value = ConstantesApis.RUTAESTACION_API_OBTENER_RUTAS, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerRutasEstacion(
				@RequestParam long idEstacion
			){
		return this.rutaEstacionService.obtenerRutasPorIdEstacion(idEstacion);
	}
	
	@GetMapping(value = ConstantesApis.RUTAESTACION_API_OBTENER_ESTACIONES, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerEstacionesRutas(
				@RequestParam long idRuta
			){
		return this.rutaEstacionService.obtenerEstacionesPorIdRuta(idRuta);
	}
	
	
	@PostMapping(value = ConstantesApis.RUTAESTACION_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarRutaEstacion(
				@RequestBody RutaEstacionDTO rutaEstacionDtoAGuardar
			){
		System.out.println("========================");
		System.out.println(rutaEstacionDtoAGuardar);
		return this.rutaEstacionService.guardarOActualizarRutaEstacion(rutaEstacionDtoAGuardar);
	}
	
	@DeleteMapping(value = ConstantesApis.RUTAESTACION_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarRutaEstacion(
				@RequestParam long idRutaEstacionABorrar
			){
		return this.rutaEstacionService.borrarRutaEstacion(idRutaEstacionABorrar);
	}
}

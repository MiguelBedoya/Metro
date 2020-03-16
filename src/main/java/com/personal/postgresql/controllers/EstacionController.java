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
import com.personal.postgresql.dto.EstacionDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.EstacionService;

@RestController
@RequestMapping(value = ConstantesApis.ESTACION_API)
public class EstacionController {
	
	@Autowired
	EstacionService estacionService;
	
	@GetMapping(value = ConstantesApis.ESTACION_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerEstacion(){
		return this.estacionService.obtenerEstaciones();
	}
	
	@PostMapping(value = ConstantesApis.ESTACION_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarEstacion(
				@RequestBody EstacionDTO estacionDtoAGuardar
			){
		return this.estacionService.guardarOActualizarEstacion(estacionDtoAGuardar);
	}
	
	@DeleteMapping(value = ConstantesApis.ESTACION_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarEstacion(
				@RequestParam long idEstacionABorrar
			){
		return this.estacionService.borrarEstacion(idEstacionABorrar);
	}
}

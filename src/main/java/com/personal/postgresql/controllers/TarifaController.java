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
import com.personal.postgresql.dto.GeolocalizacionDTO;
import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.GeolocalizacionService;
import com.personal.postgresql.services.TarifaService;
@RestController
@RequestMapping(value = ConstantesApis.TARIFA_API)
public class TarifaController {

	@Autowired
	private TarifaService tarifaService;

	@GetMapping(value = ConstantesApis.TARIFA_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerTarifa(
				@RequestParam long idTarifa
			){
		return this.tarifaService.obtenerPorIdTarifa(idTarifa);
	}
	
	@GetMapping(value = ConstantesApis.TARIFA_API_OBTENER_TODOS, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerTarifas(
			){
		return this.tarifaService.obtenerTarifas();
	}
	
	@PostMapping(value = ConstantesApis.TARIFA_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarTarifa(
				@RequestBody TarifaDTO tarifaAGuardar
			){
		return this.tarifaService.guardarTarifa(tarifaAGuardar);
	}
	
	@DeleteMapping(value = ConstantesApis.TARIFA_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarTarifa(
	@RequestParam long idTarifaABorrar
	){
	return this.tarifaService.borrarTarifa(idTarifaABorrar);
	}

	
	
}

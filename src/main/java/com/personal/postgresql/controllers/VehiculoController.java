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
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.VehiculoService;

@RestController
@RequestMapping(value = ConstantesApis.VEHICULO_API)
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoService;
	
	@PostMapping(value = ConstantesApis.VEHICULO_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarVehiculo(
				@RequestBody VehiculoDTO vehiculoDtoAGuardar
			){
		return this.vehiculoService.guardarOActualizarVehiculo(vehiculoDtoAGuardar);
	}
	
	@DeleteMapping(value = ConstantesApis.VEHICULO_API_BORRAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarVehiculo(
				@RequestParam long idVehiculoABorrar
			){
		return this.vehiculoService.borrarVehiculo(idVehiculoABorrar);
	}
	@GetMapping(value = ConstantesApis.VEHICULO_API_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> listarVehiculo(
				
			){
		return this.vehiculoService.listarVehiculo();
	}
	
}

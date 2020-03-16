package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.dto.CuentaDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.CuentaService;

@RestController
@RequestMapping(value = ConstantesApis.CUENTA_API)
public class CuentaController {

	@Autowired
	private CuentaService ctaService;
	
	@GetMapping(value = ConstantesApis.CUENTA_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerCuenta(
			@RequestParam long idCuenta){
		return this.ctaService.obtenerCuenta(idCuenta);
	}
	
	@PostMapping(value = ConstantesApis.CUENTA_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarCuenta(
			@RequestBody CuentaDTO cuentaDto){
		return this.ctaService.actualizarCuenta(cuentaDto);
	}
	
	@PostMapping(value = ConstantesApis.CUENTA_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarCuenta(
			@RequestBody CuentaDTO cuentaDto){
		return this.ctaService.eliminarCuenta(cuentaDto);
	}
}

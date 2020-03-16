package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.dto.PrestamoDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.PrestamoService;

@RestController
@RequestMapping(value = ConstantesApis.PRESTAMO_API)
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	
	@PostMapping(value = ConstantesApis.PRESTAMO_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarPrestamo(
				@RequestBody PrestamoDTO PrestamoDtoAGuardar
			){
		return this.prestamoService.guardarPrestamo(PrestamoDtoAGuardar);
	}	
}

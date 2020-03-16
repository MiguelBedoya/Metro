package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.dto.PagoDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.PagoService;
import com.personal.postgresql.services.VehiculoService;

@RestController
@RequestMapping(value = ConstantesApis.PAGO_API)
public class PagoController {

	@Autowired
	private PagoService pagoService;
	
	@PostMapping(value = ConstantesApis.PAGO_API_GUARDAR_O_ACTUALIZAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarPago(
				@RequestBody PagoDTO pagoDtoAGuardar, @RequestParam double montoPago
			){
		return this.pagoService.guardarOActualizarPago(pagoDtoAGuardar, montoPago);
	}

	
}

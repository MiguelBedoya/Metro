/**
 * 
 */
package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.dto.AdjuntoDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.AdjuntoService;

/**
 * @author serojas
 *
 */
@RestController
@RequestMapping(value = ConstantesApis.ADJUNTO_API)
public class AdjuntoController {
	
	@Autowired
	AdjuntoService adjuntoService;

	@PostMapping(value = ConstantesApis.ADJUNTO_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarAdjunto(
			@RequestBody AdjuntoDTO adjuntoDto){
		return adjuntoService.guardarAdjunto(adjuntoDto);
	}
}

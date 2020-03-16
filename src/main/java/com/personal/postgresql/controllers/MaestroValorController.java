package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.MaestroValorService;

@RestController
@RequestMapping(value = ConstantesApis.MAESTRO_VALOR_API)
public class MaestroValorController {

	@Autowired
	private MaestroValorService maestroValorService;

	@GetMapping(value = ConstantesApis.MAESTRO_VALOR_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerDatosPorTipo(@RequestParam long idTipoMaestroValor) {
		return this.maestroValorService.obtenerDatosPorTipo(idTipoMaestroValor);
	}

}

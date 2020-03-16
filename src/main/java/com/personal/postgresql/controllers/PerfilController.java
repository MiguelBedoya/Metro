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
import com.personal.postgresql.dto.PerfilDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.PerfilService;

@RestController
@RequestMapping(value = ConstantesApis.PERFIL_API)
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@PostMapping(value = ConstantesApis.PERFIL_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarPerfil(@RequestBody PerfilDTO perfilAGuardar) {
		return this.perfilService.guardarPerfil(perfilAGuardar);
	}

	@GetMapping(value = ConstantesApis.PERFIL_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerPerfil(@RequestParam long idPerfil) {
		return this.perfilService.obtenerPerfil(idPerfil);
	}

	@DeleteMapping(value = ConstantesApis.PERFIL_API_BORRAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarPerfil(
				@RequestParam long idPerfil
			){
		return this.perfilService.borrarPerfil(idPerfil);
	}
}

//

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
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.UsuarioService;

@RestController
@RequestMapping(value = ConstantesApis.USUARIO_API)
public class UsuarioController {

	@Autowired
	private UsuarioService usrService;

	@GetMapping(value = ConstantesApis.USUARIO_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerUsuario(
				@RequestParam long idUsuario
			){
		return this.usrService.obtenerUsuario(idUsuario);
	}
	
	@PostMapping(value = ConstantesApis.USUARIO_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarUsuario(
				@RequestBody UsuarioDTO usuarioAGuardar
			){
		return this.usrService.guardarOActualizarUsuario(usuarioAGuardar);
	}

	
	@DeleteMapping(value = ConstantesApis.USUARIO_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarUsuario(
				@RequestParam long usuarioAEliminar
			){
		return this.usrService.eliminarUsuario(usuarioAEliminar);
	}
	
}

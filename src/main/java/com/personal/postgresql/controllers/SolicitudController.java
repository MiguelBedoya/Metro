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
import com.personal.postgresql.dto.SolicitudDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.SolicitudService;

@RestController
@RequestMapping(value = ConstantesApis.SOLICITUD_API)
public class SolicitudController {

	@Autowired
	private SolicitudService solService;
	
	@GetMapping(value = ConstantesApis.SOLICITUD_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerSolicitud(
			@RequestParam long idSolicitud){
		return this.solService.obtenerSolicitud(idSolicitud);
	}
	
	@PostMapping(value = ConstantesApis.SOLICITUD_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarSolicitud(
				@RequestBody SolicitudDTO solicitudAGuardar
			){
		return this.solService.guardarSolicitud(solicitudAGuardar);
	}
	
	@PostMapping(value = ConstantesApis.SOLICITUD_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarSolicitud(
				@RequestParam long solicitudAEliminar
			){
		return this.solService.eliminarSolicitud(solicitudAEliminar);
	}
	
	@PostMapping(value = ConstantesApis.SOLICITUD_API_ACTUALIZAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> actualizarEstadoSolicitud(
				@RequestParam long solicitudAActualizar, @RequestParam long estadoNuevo
			){
		return this.solService.actualizarEstadoSolicitud(solicitudAActualizar, estadoNuevo);
	}
}

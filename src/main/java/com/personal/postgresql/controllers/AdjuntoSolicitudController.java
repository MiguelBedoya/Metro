/**
 * 
 */
package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.AdjuntoSolicitudService;

/**
 * @author serojas
 *
 */
@RestController
@RequestMapping(value = ConstantesApis.ADJUNTO_SOLICITUD_API)
public class AdjuntoSolicitudController {

	@Autowired
	AdjuntoSolicitudService adjuntoSolicitudService;
	
	@PostMapping(value = ConstantesApis.ADJUNTO_SOLICITUD_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  ResponseEntity<RespuestaGenerica> guardarAdjuntoSolicitd(
			@RequestParam long idAdjunto, @RequestParam long idSolicitud){
		return adjuntoSolicitudService.guardarAdjuntoSolicitud(idAdjunto, idSolicitud);	
	}
	
	@GetMapping(value = ConstantesApis.ADJUNTO_SOLCITUD_API_OBTENER_ADJUNTOS,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  ResponseEntity<RespuestaGenerica> guardarAdjuntoSolicitd(@RequestParam long idSolicitud){
		return adjuntoSolicitudService.obtenerAdjuntosSolicitud(idSolicitud);	
	}
}

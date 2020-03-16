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
import com.personal.postgresql.dto.GeolocalizacionDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.GeolocalizacionService;

@RestController
@RequestMapping(value = ConstantesApis.GEOLOCALIZACION_API)
public class GeolocalizacionController {

	@Autowired
	private GeolocalizacionService geolocalizacionService;

	@GetMapping(value = ConstantesApis.GEOLOCALIZACION_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerGeolocalizacionUsuario(
				@RequestParam long idUsuario
			){
		return this.geolocalizacionService.obtenerPorIdUsuario(idUsuario);
	}
	
	@PostMapping(value = ConstantesApis.GEOLOCALIZACION_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarGeolocalizacionUsuario(
				@RequestBody GeolocalizacionDTO geolocalizacionAGuardar, @RequestParam long idUsuarioRelacionado
			){
		return this.geolocalizacionService.guardarGeolocalizacion(geolocalizacionAGuardar, idUsuarioRelacionado);
	}
	
}

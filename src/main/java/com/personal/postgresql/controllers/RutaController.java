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
import com.personal.postgresql.dto.RutaDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.Ruta;
import com.personal.postgresql.repositories.RutaDao;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.RutaService;

@RestController
@RequestMapping(value = ConstantesApis.RUTA_API)
public class RutaController {
	
	@Autowired
	private RutaService rutaService;
	
	@GetMapping(value = ConstantesApis.RUTA_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerRuta(
				@RequestParam long idRuta
			){
		return this.rutaService.obtenerRuta(idRuta);
	}
	
	@PostMapping(value = ConstantesApis.RUTA_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarRuta(
				@RequestBody RutaDTO rutaDtoAGuardar
			){
		return this.rutaService.guardarOActualizarRuta(rutaDtoAGuardar);
	}
	
	
	@DeleteMapping(value = ConstantesApis.RUTA_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarRuta(
				@RequestParam long idRuta
			){
		return this.rutaService.borrarRuta(idRuta);
	}
	
	@GetMapping(value = ConstantesApis.RUTA_API_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerListaRuta(){
		return this.rutaService.obtenerAllRuta();
	}

}

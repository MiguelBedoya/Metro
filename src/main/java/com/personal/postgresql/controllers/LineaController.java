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
import com.personal.postgresql.dto.LineaDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.LineaService;

@RestController
@RequestMapping(value = ConstantesApis.LINEA_API)
public class LineaController {
	
	@Autowired
	private LineaService lineaService;
	
	@GetMapping(value = ConstantesApis.LINEA_API_OBTENER)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerLinea(@RequestParam long id) {
		return this.lineaService.obtener(id);
	}
	
	@PostMapping(value = ConstantesApis.LINEA_API_GUARDAR_O_ACTUALIZAR)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarLinea(@RequestBody LineaDTO lineaDtoAGuardarOActualizar) {
		return this.lineaService.guardarOActualizarLinea(lineaDtoAGuardarOActualizar);
	}
	
	@DeleteMapping(value = ConstantesApis.LINEA_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarLinea(
				@RequestParam long idLineaABorrar
			){
		return this.lineaService.eliminarLinea(idLineaABorrar);
	}

}

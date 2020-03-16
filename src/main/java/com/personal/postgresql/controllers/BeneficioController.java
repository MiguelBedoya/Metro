package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.personal.postgresql.dto.BeneficioDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.BeneficioService;

@RestController
@RequestMapping(value = ConstantesApis.BENEFICIO_API)
public class BeneficioController {
	
	@Autowired
	private BeneficioService beneficioService;
	
	@GetMapping(value = ConstantesApis.BENEFICIO_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerBeneficio(
				@RequestParam long idBeneficio
			){
		return this.beneficioService.obtenerBeneficioPorId(idBeneficio);
	}
	
	@PostMapping(value = ConstantesApis.BENEFICIO_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarBeneficio(
				@RequestBody BeneficioDTO beneficioAGuardar
			){
		return this.beneficioService.guardarOActualizarBeneficio(beneficioAGuardar);
	}
	
	@DeleteMapping(value = ConstantesApis.BENEFICIO_API_ELIMINAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarBeneficio(
				@RequestParam long idBeneficioABorrar
			){
		return this.beneficioService.borrarBeneficio(idBeneficioABorrar);
	}
	
	@GetMapping(value = ConstantesApis.BENEFICIO_API_LISTAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> listarBeneficio(){
		return this.beneficioService.listarBeneficios();
	}

}

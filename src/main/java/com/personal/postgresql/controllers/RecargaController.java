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
import com.personal.postgresql.dto.RecargaDTO;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.GeolocalizacionService;
import com.personal.postgresql.services.RecargaService;

@RestController
@RequestMapping(value = ConstantesApis.RECARGA_API)
public class RecargaController {
	
	// 1. Obtener cuenta a modificar
	// 2. Insertar la transacción (Crear recarga)
	// 3. Saldo Anterios a la transaccion
	// 4. Modificar la cuenta (se toma el nuevo saldo)
	// 5. Se crea un nuevo estado de cuenta (Necesita: el saldo anterios, el tipo de transaccion, el nuevo saldo y el id del tipo transaccion)
	
	@Autowired
	private RecargaService recargaService;

	@GetMapping(value = ConstantesApis.RECARGA_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> consultarRecargasCuenta(
				@RequestParam long idCuenta
			){
		return this.recargaService.consultarRecargaDeCuenta(idCuenta);
	}
	
	@PostMapping(value = ConstantesApis.RECARGA_API_GUARDAR, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> realizarRecargaCuenta(
				@RequestBody RecargaDTO recargaARealizar
			){
		return this.recargaService.recargarCuenta(recargaARealizar);
	}
	
}

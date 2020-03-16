package com.personal.postgresql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.responses.RespuestaGenerica;
import com.personal.postgresql.services.QrService;

@RestController
@RequestMapping(value = ConstantesApis.QR_API)
public class QrController {

	@Autowired
	private QrService qrService;
	
	@PostMapping(value = ConstantesApis.QR_API_OBTENER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RespuestaGenerica> crearYGenerateQRCodeImage() {
		return this.qrService.crearYGenerateQRCodeImage();
	}
	

}

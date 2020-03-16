/**
 * 
 */
package com.personal.postgresql.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.AdjuntoDTO;
import com.personal.postgresql.entities.Adjunto;
import com.personal.postgresql.mappers.AdjuntoMapper;
import com.personal.postgresql.repositories.AdjuntoDao;
import com.personal.postgresql.responses.RespuestaGenerica;

/**
 * @author sebastian Rojas Q.
 *
 */
@Service
public class AdjuntoService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final AdjuntoMapper adjuntoMapper = new AdjuntoMapper();
	
	@Autowired
	AdjuntoDao adjuntoDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarAdjunto(AdjuntoDTO adjunto){
		try {
			Adjunto docAdjunto = adjuntoDao.save(adjuntoMapper.obtenerAdjunto(adjunto));
			RespuestaGenerica respuestaGenerica = new RespuestaGenerica(docAdjunto,ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK); 
		} catch (Exception e) {
			logger.error("Error se presentan problemas al guardar el docuemnto adjunto ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerDocumentoPorId(long idAdjunto){
		try {
			RespuestaGenerica respuestaGenerica;
			if(adjuntoDao.existsById(idAdjunto)) {
				Adjunto docAdjunto = adjuntoDao.findById(idAdjunto).get();
				respuestaGenerica = new RespuestaGenerica(docAdjunto,ConstantesRespuestas.EXITO, "");
			}
			else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "El archivo no se encuentra");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK); 
		} catch (Exception e) {
			logger.error("Error se presentan problemas al obtener el docuemnto adjunto ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

}

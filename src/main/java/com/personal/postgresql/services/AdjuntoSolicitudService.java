/**
 * 
 */
package com.personal.postgresql.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.AdjuntoDTO;
import com.personal.postgresql.dto.AdjuntoSolicitudDTO;
import com.personal.postgresql.entities.Adjunto;
import com.personal.postgresql.entities.AdjuntoSolicitud;
import com.personal.postgresql.entities.Solicitud;
import com.personal.postgresql.mappers.AdjuntoMapper;
import com.personal.postgresql.mappers.AdjuntoSolicitudMapper;
import com.personal.postgresql.repositories.AdjuntoDao;
import com.personal.postgresql.repositories.AdjuntoSolicitudDao;
import com.personal.postgresql.repositories.SolicitudDao;
import com.personal.postgresql.responses.RespuestaGenerica;

/**
 * @author serojas
 *
 */
@Service
public class AdjuntoSolicitudService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final AdjuntoSolicitudMapper adjuntoSolicitudMapper = new AdjuntoSolicitudMapper();
	private final AdjuntoMapper adjuntoMapper = new AdjuntoMapper();

	@Autowired
	AdjuntoSolicitudDao adjuntoSolicitudDao;
	@Autowired
	AdjuntoDao adjuntoDao;
	@Autowired
	SolicitudDao solicitudDao;

	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarAdjuntoSolicitud(
			long idAdjunto, long idSolicitud) {
		try {
			RespuestaGenerica respuestaGenerica;
			if(adjuntoDao.existsById(idAdjunto) && solicitudDao.existsById(idSolicitud)) {
				Adjunto adjunto = adjuntoDao.findById(idAdjunto).get();
				Solicitud solcitud = solicitudDao.findById(idSolicitud).get();
				AdjuntoSolicitud adjuntoSolicitud = new AdjuntoSolicitud();
				adjuntoSolicitud.setIdAdjunto(adjunto);
				adjuntoSolicitud.setIdSolicitud(solcitud);
				adjuntoSolicitud = adjuntoSolicitudDao
						.save(adjuntoSolicitud);
				respuestaGenerica = new RespuestaGenerica<>(adjuntoSolicitud,ConstantesRespuestas.EXITO,"");
			}
			else {
				respuestaGenerica = new RespuestaGenerica<>(ConstantesRespuestas.ERRROR,"");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al guardar la relación entre adjunto y solicitud ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerAdjuntosSolicitud(long idSolicitud){
		try {
			List<AdjuntoDTO> listaAdjuntosDTO = new ArrayList<AdjuntoDTO>();
			List<Adjunto> listaAdjunto = adjuntoSolicitudDao.obtenerAdjuntosSolicitud(idSolicitud);
			listaAdjunto.forEach(adjuntoDto -> listaAdjuntosDTO.add(adjuntoMapper.obtenerAdjuntoDTO(adjuntoDto)));
			RespuestaGenerica respuestaGenerica = new RespuestaGenerica(listaAdjuntosDTO,ConstantesRespuestas.EXITO,"");
			return new ResponseEntity<RespuestaGenerica> (respuestaGenerica,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al obtener la lista de adjuntos de una solicitud ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

}

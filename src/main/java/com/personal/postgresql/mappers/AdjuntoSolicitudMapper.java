/**
 * 
 */
package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.AdjuntoSolicitudDTO;
import com.personal.postgresql.entities.AdjuntoSolicitud;

/**
 * @author serojas
 *
 */
public class AdjuntoSolicitudMapper {
	
	private final AdjuntoMapper adjuntoMapper = new AdjuntoMapper();
	private final SolicitudMapper solicitudMapper = new SolicitudMapper();
	
	public AdjuntoSolicitud obteneAdjuntoSolicitud(AdjuntoSolicitudDTO adjuntoSolicitudDTO) {
		AdjuntoSolicitud adjuntoSolicitud = new AdjuntoSolicitud();
		adjuntoSolicitud.setIdAdjunto(adjuntoMapper.obtenerAdjunto(adjuntoSolicitudDTO.getIdAdjunto()));
		adjuntoSolicitud.setIdSolicitud(solicitudMapper.obtenerSolicitud(adjuntoSolicitudDTO.getIdSolicitud()));
		return adjuntoSolicitud;
	}

}

/**
 * 
 */
package com.personal.postgresql.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author serojas
 *
 */
@Data
public class AdjuntoSolicitudDTO implements Serializable{

	private static final long serialVersionUID = 1590859879590840007L;
	
	private AdjuntoDTO idAdjunto;
	private SolicitudDTO idSolicitud;
}

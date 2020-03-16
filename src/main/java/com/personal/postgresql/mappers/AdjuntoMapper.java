/**
 * 
 */
package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.AdjuntoDTO;
import com.personal.postgresql.entities.Adjunto;

/**
 * @author sebastian Rojas Q.
 *
 */
public class AdjuntoMapper {
	
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();
	
	public Adjunto obtenerAdjunto(AdjuntoDTO adjuntoDTO) {
		Adjunto adjunto = new Adjunto();
		adjunto.setNombre(adjuntoDTO.getNombre());
		adjunto.setRuta(adjuntoDTO.getRuta());
		adjunto.setTipoArchivo(maestroValorMapper.obtenerMaestroValor(adjuntoDTO.getTipoArchivo()));
		return adjunto;
	}
	
	public AdjuntoDTO obtenerAdjuntoDTO (Adjunto adjunto) {
		AdjuntoDTO adjuntoDTO = new AdjuntoDTO();
		adjuntoDTO.setNombre(adjunto.getNombre());
		adjuntoDTO.setRuta(adjunto.getRuta());
		adjuntoDTO.setTipoArchivo(maestroValorMapper.obtenerMaestroValorDTO(adjunto.getTipoArchivo()));
		return adjuntoDTO;
	}

}

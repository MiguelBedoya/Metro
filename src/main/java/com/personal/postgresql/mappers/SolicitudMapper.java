package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.SolicitudDTO;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Solicitud;
import com.personal.postgresql.entities.Usuario;

public class SolicitudMapper {

	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();
	private final UsuarioMapper usuarioMapper = new UsuarioMapper();
	
	public SolicitudDTO obtenerSolicitudDTO(Solicitud solicitud) {
		
		MaestroValor idEstadoSolicitud = solicitud.getIdEstadoSolicitud();
		MaestroValorDTO maestroValorDTO = this.maestroValorMapper.obtenerMaestroValorDTO(idEstadoSolicitud);
		
		Usuario usuario = solicitud.getIdUsuario();
		UsuarioDTO usuarioDTO = this.usuarioMapper.obtenerUsrDTO(usuario);
		
		SolicitudDTO solicitudDTO = new SolicitudDTO();
		solicitudDTO.setId(solicitud.getId());
		solicitudDTO.setFechaInicio(solicitud.getFechaInicio());
		solicitudDTO.setFechaFin(solicitud.getFechaFin());
		solicitudDTO.setIdEstadoSolicitud(maestroValorDTO);
		solicitudDTO.setIdUsuario(usuarioDTO);
		
		return solicitudDTO;
	}
	
	public Solicitud obtenerSolicitud(SolicitudDTO solicitudDTO) {
		MaestroValorDTO maestroValorDTO = solicitudDTO.getIdEstadoSolicitud();
		MaestroValor maestroValor = this.maestroValorMapper.obtenerMaestroValor(maestroValorDTO);
		
		UsuarioDTO UsuarioDTO = solicitudDTO.getIdUsuario();
		Usuario usuario = this.usuarioMapper.obtenerUsr(UsuarioDTO);
		
		Solicitud solicitud = new Solicitud();
		solicitud.setId(solicitudDTO.getId());
		solicitud.setFechaInicio(solicitudDTO.getFechaInicio());
		solicitud.setFechaFin(solicitudDTO.getFechaFin());
		solicitud.setIdEstadoSolicitud(maestroValor);
		solicitud.setIdUsuario(usuario);
		
		return solicitud;
	}
}

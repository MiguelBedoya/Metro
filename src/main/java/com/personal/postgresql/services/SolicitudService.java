package com.personal.postgresql.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesApis;
import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.SolicitudDTO;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.DatoBiometrico;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Solicitud;
import com.personal.postgresql.entities.Usuario;
import com.personal.postgresql.mappers.SolicitudMapper;
import com.personal.postgresql.mappers.UsuarioMapper;
import com.personal.postgresql.repositories.CuentaDao;
import com.personal.postgresql.repositories.SolicitudDao;
import com.personal.postgresql.repositories.UsuarioDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class SolicitudService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final SolicitudMapper solicitudMapper = new SolicitudMapper();
	private final UsuarioMapper usuarioMapper = new UsuarioMapper();
	
	@Autowired
	private SolicitudDao solicitudDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private CuentaDao cuentaDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerSolicitud(long idSolicitud){
		RespuestaGenerica respuestaGenerica;
		try {
			if(solicitudDao.existsById(idSolicitud)) {
				Solicitud solicitud = solicitudDao.findById(idSolicitud).get();
				SolicitudDTO solicitudDTO = this.solicitudMapper.obtenerSolicitudDTO(solicitud);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			}else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Solicitud no existe.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error se presentan problemas al cargar una solicitud ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);	
		}
	}
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarSolicitud(SolicitudDTO solicitudDTO) {
		try {
			//if id == null es Creacion
			RespuestaGenerica respuestaGenerica;
			if (solicitudDTO != null && usuarioDao.existsById(solicitudDTO.getIdUsuario().getId()) ) {
				MaestroValorDTO idEstadoSolicitud = new MaestroValorDTO();
				
				UsuarioDTO usuarioObtenido = usuarioMapper.obtenerUsrDTO(usuarioDao.findById(solicitudDTO.getIdUsuario().getId()).get()); 
				solicitudDTO.setIdUsuario(usuarioObtenido);
				
				idEstadoSolicitud.setId(ConstantesRespuestas.PENDIENTE);
				solicitudDTO.setIdEstadoSolicitud(idEstadoSolicitud);
				
				Solicitud solicitud = this.solicitudMapper.obtenerSolicitud(solicitudDTO);
				
				this.solicitudDao.save(solicitud);
				
				respuestaGenerica	= new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "La solicitud no fue creada.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al crear una solicitud ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> actualizarEstadoSolicitud(long solicitudAActualizar, long estadoNuevo) {
		try {
			//if id == null es Creacion
			RespuestaGenerica respuestaGenerica;
			if (solicitudDao.existsById(solicitudAActualizar)) {
				Solicitud solicitud = solicitudDao.findById(solicitudAActualizar).get();
				MaestroValor idEstadoNuevo = new MaestroValor();
				idEstadoNuevo.setId(estadoNuevo);
				solicitud.setIdEstadoSolicitud(idEstadoNuevo);
				solicitudDao.save(solicitud);
				if(solicitud.getIdEstadoSolicitud().getId()==ConstantesRespuestas.APROBADA){
					Cuenta cuenta = new Cuenta();
					cuenta.setSaldo(0);
					Cuenta cuentaDelUsuario = this.cuentaDao.save(cuenta);
					Usuario usuario = this.usuarioDao.findById(solicitud.getIdUsuario().getId()).get();
					usuario.setIdCuenta(cuentaDelUsuario);
					this.usuarioDao.save(usuario);
					respuestaGenerica	= new RespuestaGenerica(ConstantesRespuestas.EXITO, "SOLICITUD APROBADA");
				}else{
					respuestaGenerica	= new RespuestaGenerica(ConstantesRespuestas.EXITO, "SOLICITUD RECHAZADA");
				}
				
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "El estado de la solicitud no pudo ser actualizado.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al crear una solicitud ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarSolicitud(long idSolicitud) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (solicitudDao.existsById(idSolicitud)) {
				this.solicitudDao.deleteById(idSolicitud);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "Solicitud Eliminada");
				
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Solicitud no encontrada.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar la solicitud ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
}

package com.personal.postgresql.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.entities.DatoBiometrico;
import com.personal.postgresql.entities.Usuario;
import com.personal.postgresql.mappers.DatoBiometricoMapper;
import com.personal.postgresql.mappers.UsuarioMapper;
import com.personal.postgresql.repositories.DatoBiometricoDao;
import com.personal.postgresql.repositories.UsuarioDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class UsuarioService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final UsuarioMapper usuarioMapper = new UsuarioMapper();
	private final DatoBiometricoMapper datoBiometricoMapper = new DatoBiometricoMapper();

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private DatoBiometricoDao datoBiometricoDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerUsuario(long idUsuario) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (usuarioDao.existsById(idUsuario)) {
				Usuario UsuarioConsultado = usuarioDao.findById(idUsuario).get();
				UsuarioDTO UsuarioRetornado = this.usuarioMapper.obtenerUsrDTO(UsuarioConsultado);
			respuestaGenerica = new RespuestaGenerica(UsuarioRetornado,
						ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Usuario no existe.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al obtener el usuario ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarUsuario(UsuarioDTO usuarioAGuardar) {
		try {
			// if id == null es Creacion
			RespuestaGenerica respuestaGenerica;
			if (usuarioAGuardar != null) {
				Usuario usuarioGuardado = this.usuarioMapper.obtenerUsr(usuarioAGuardar);
				DatoBiometrico datoBiometricoCreado = crearDatoBiometrico(
						this.datoBiometricoMapper.obtenerDato(usuarioAGuardar.getIdDatoBiometrico()));
				
				usuarioGuardado.setIdDatoBiometrico(datoBiometricoCreado);
				this.usuarioDao.save(usuarioGuardado);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");

			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Usuario no creado.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar el usuario ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarUsuario(long idUsuario) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (usuarioDao.existsById(idUsuario)) {
				this.usuarioDao.deleteById(idUsuario);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Usuario no existe.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al eliminar el usuario ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public DatoBiometrico crearDatoBiometrico(DatoBiometrico datoAGuardar){
		DatoBiometrico datoBiometricoCreado = datoBiometricoDao.save(datoAGuardar);
		return datoBiometricoCreado;		
	}
}

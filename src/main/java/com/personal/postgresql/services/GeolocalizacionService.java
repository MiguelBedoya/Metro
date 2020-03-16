package com.personal.postgresql.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.GeolocalizacionDTO;
import com.personal.postgresql.entities.Geolocalizacion;
import com.personal.postgresql.mappers.GeolocalizacionMapper;
import com.personal.postgresql.repositories.GeolocalizacionDao;
import com.personal.postgresql.repositories.UsuarioDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class GeolocalizacionService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final GeolocalizacionMapper geolocalizacionMapper = new GeolocalizacionMapper();

	@Autowired
	private GeolocalizacionDao geolocalizacionDao;

	@Autowired
	private UsuarioDao usuarioDao; 

	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerPorIdUsuario(long idUsuario) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (usuarioDao.existsById(idUsuario)) {
				ArrayList<Geolocalizacion> listadoGeoConsultado = this.geolocalizacionDao
						.findByIdUsuario(usuarioDao.findById(idUsuario).get());
				ArrayList<GeolocalizacionDTO> listadoGeoDtoRetornar = new ArrayList<GeolocalizacionDTO>();

				listadoGeoConsultado.forEach((itemGeoACastear) -> {
					listadoGeoDtoRetornar.add(this.geolocalizacionMapper.obtenerGeoDTO(itemGeoACastear));
				});

				respuestaGenerica = new RespuestaGenerica(listadoGeoDtoRetornar, ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Usuario no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar geolocalización ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarGeolocalizacion(
			GeolocalizacionDTO geolocalizacionAGuardar, long idUsuarioRelacionado) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (usuarioDao.existsById(idUsuarioRelacionado)) {
				Geolocalizacion geoAGuardar = this.geolocalizacionMapper.obtenerGeo(geolocalizacionAGuardar,
						idUsuarioRelacionado);
				this.geolocalizacionDao.save(geoAGuardar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Usuario no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar geolocalización ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

}

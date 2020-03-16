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
import com.personal.postgresql.dto.LineaEstacionDTO;
import com.personal.postgresql.dto.RutaEstacionDTO;
import com.personal.postgresql.entities.LineaEstacion;
import com.personal.postgresql.entities.RutaEstacion;
import com.personal.postgresql.mappers.RutaEstacionMapper;
import com.personal.postgresql.repositories.EstacionDao;
import com.personal.postgresql.repositories.RutaDao;
import com.personal.postgresql.repositories.RutaEstacionDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class RutaEstacionService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final RutaEstacionMapper rutaEstacionMapper = new RutaEstacionMapper();
	
	@Autowired
	private RutaEstacionDao rutaestacionDao;
	@Autowired
	private EstacionDao estacionDao;
	@Autowired
	private RutaDao rutaDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarRutaEstacion(RutaEstacionDTO rutaEstacionDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			RutaEstacion rutaEstacionAGuardar = this.rutaEstacionMapper.obtenerRutaEstacion(rutaEstacionDtoAGuardar);
			this.rutaestacionDao.save(rutaEstacionAGuardar);
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar RutaEstacion ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> borrarRutaEstacion(long idRutaEstacionABorrar) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (rutaestacionDao.existsById(idRutaEstacionABorrar)) {
				this.rutaestacionDao.deleteById(idRutaEstacionABorrar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "RutaEstacion no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar geolocalización ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerRutasPorIdEstacion(long idEstacion) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (estacionDao.existsById(idEstacion)) {
				ArrayList<RutaEstacion> listadoRutaConsultado = this.rutaestacionDao
						.findByIdEstacion(estacionDao.findById(idEstacion).get());
				if (listadoRutaConsultado.size() != 0) {
					ArrayList<RutaEstacionDTO> listadoRutaDtoRetornar = new ArrayList<RutaEstacionDTO>();
	
					listadoRutaConsultado.forEach((itemEstacionACastear) -> {
						listadoRutaDtoRetornar.add(this.rutaEstacionMapper.obtenerRutaEstacionDTO(itemEstacionACastear));
					});
	
					respuestaGenerica = new RespuestaGenerica(listadoRutaDtoRetornar, ConstantesRespuestas.EXITO, "");
				} else {
					respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se encuentran rutas relacionadas a esta estación");
				}
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Estación no encontrada");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al obtener rutas por estación ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerEstacionesPorIdRuta(long idRuta) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (rutaDao.existsById(idRuta)) {
				ArrayList<RutaEstacion> listadoEstacionConsultado = this.rutaestacionDao
						.findByIdRuta(rutaDao.findById(idRuta).get());
				if (listadoEstacionConsultado.size() != 0) {
					ArrayList<RutaEstacionDTO> listadoEstacioDtoRetornar = new ArrayList<RutaEstacionDTO>();
	
					listadoEstacionConsultado.forEach((itemLineaACastear) -> {
						listadoEstacioDtoRetornar.add(this.rutaEstacionMapper.obtenerRutaEstacionDTO(itemLineaACastear));
					});
					respuestaGenerica = new RespuestaGenerica(listadoEstacioDtoRetornar, ConstantesRespuestas.EXITO, "");
				} else {
					respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se encuentran estaciones relacionadas a esta ruta");
				}

			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "ruta no encontrada");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al obtener estaciones por ruta");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}

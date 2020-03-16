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
import com.personal.postgresql.dto.EstacionDTO;
import com.personal.postgresql.entities.Estacion;
import com.personal.postgresql.mappers.EstacionMapper;
import com.personal.postgresql.repositories.EstacionDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class EstacionService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final EstacionMapper estacionMapper = new EstacionMapper();
	
	@Autowired
	EstacionDao estacionDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarEstacion(EstacionDTO estacionDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			Estacion estacionAGuardar = this.estacionMapper.obtenerEstacion(estacionDtoAGuardar);
			this.estacionDao.save(estacionAGuardar);
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar estación ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> borrarEstacion(long idEstacionABorrar) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (estacionDao.existsById(idEstacionABorrar)) {
				this.estacionDao.deleteById(idEstacionABorrar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Estación no encontrada");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar estación ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerEstaciones() {
		try {
			RespuestaGenerica respuestaGenerica;
				ArrayList<Estacion> listadoEstacionConsulta = (ArrayList<Estacion>) this.estacionDao.findAll();
				if (listadoEstacionConsulta.size() != 0) {
					ArrayList<EstacionDTO> listadoEstacionDtoRetornar = new ArrayList<EstacionDTO>();
	
					listadoEstacionConsulta.forEach((itemEstadoACastear) -> {
						listadoEstacionDtoRetornar.add(this.estacionMapper.obtenerEstacionDTO(itemEstadoACastear));
					});
	
					respuestaGenerica = new RespuestaGenerica(listadoEstacionDtoRetornar, ConstantesRespuestas.EXITO, "");
				} else {
					respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No hay estaciones disponibles");	
				}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar estaciones ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
}

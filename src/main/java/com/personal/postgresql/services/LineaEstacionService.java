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
import com.personal.postgresql.dto.LineaEstacionDTO;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.entities.Linea;
import com.personal.postgresql.entities.LineaEstacion;
import com.personal.postgresql.mappers.LineaEstacionMapper;
import com.personal.postgresql.repositories.EstacionDao;
import com.personal.postgresql.repositories.LineaDao;
import com.personal.postgresql.repositories.LineaEstacionDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class LineaEstacionService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final LineaEstacionMapper lineaEstacionMapper = new LineaEstacionMapper();
	
	@Autowired
	LineaEstacionDao lineaEstacionDao;
	
	@Autowired
	EstacionDao estacionDao;
	
	@Autowired
	LineaDao lineaDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerLineasPorIdEstacion(long idEstacion) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (estacionDao.existsById(idEstacion)) {
				ArrayList<LineaEstacion> listadoLineaConsultado = this.lineaEstacionDao
						.findByIdEstacion(estacionDao.findById(idEstacion).get());
				if (listadoLineaConsultado.size() != 0) {
					ArrayList<LineaEstacionDTO> listadoLineaDtoRetornar = new ArrayList<LineaEstacionDTO>();
	
					listadoLineaConsultado.forEach((itemEstacionACastear) -> {
						listadoLineaDtoRetornar.add(this.lineaEstacionMapper.obtenerLineaEstacionDTO(itemEstacionACastear));
					});
	
					respuestaGenerica = new RespuestaGenerica(listadoLineaDtoRetornar, ConstantesRespuestas.EXITO, "");
				} else {
					respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se encuentran lineas relacionadas a esta estación");
				}
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Estación no encontrada");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al obtener lineas por estación ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerEstacionesPorIdLinea(long idLinea) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (lineaDao.existsById(idLinea)) {
				ArrayList<LineaEstacion> listadoLineaConsultado = this.lineaEstacionDao
						.findByIdLinea(lineaDao.findById(idLinea).get());
				if (listadoLineaConsultado.size() != 0) {
					ArrayList<LineaEstacionDTO> listadoLineaDtoRetornar = new ArrayList<LineaEstacionDTO>();
	
					listadoLineaConsultado.forEach((itemLineaACastear) -> {
						listadoLineaDtoRetornar.add(this.lineaEstacionMapper.obtenerLineaEstacionDTO(itemLineaACastear));
					});
					respuestaGenerica = new RespuestaGenerica(listadoLineaDtoRetornar, ConstantesRespuestas.EXITO, "");
				} else {
					respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se encuentran estaciones relacionadas a esta linea");
				}

			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Linea no encontrada");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al obtener estaciones por Linea");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarLineaEstacion(LineaEstacionDTO lineaEstacionDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (estacionDao.existsById(lineaEstacionDtoAGuardar.getIdEstacion().getId()) && 
					lineaDao.existsById(lineaEstacionDtoAGuardar.getIdLinea().getId())) {
				LineaEstacion lineaEstacionAGuardar = this.lineaEstacionMapper.obtenerLineaEstacion(lineaEstacionDtoAGuardar);
				this.lineaEstacionDao.save(lineaEstacionAGuardar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Estación o Linea no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al guardar la relación ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarLineaEstacion(long idLineaEstacion) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (lineaEstacionDao.existsById(idLineaEstacion)) {
				this.lineaEstacionDao.deleteById(idLineaEstacion);
				respuestaGenerica	= new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
				
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Relación no existe.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al eliminar La relación ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
}

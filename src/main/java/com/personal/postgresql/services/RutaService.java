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
import com.personal.postgresql.dto.RutaDTO;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.Ruta;
import com.personal.postgresql.entities.Usuario;
import com.personal.postgresql.entities.Vehiculo;
import com.personal.postgresql.mappers.RutaMapper;
import com.personal.postgresql.repositories.RutaDao;
import com.personal.postgresql.responses.RespuestaGenerica;
@Service
public class RutaService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final RutaMapper rutaMapper = new RutaMapper();
	
	@Autowired
	private RutaDao rutaDao;
	
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerRuta(long idRuta) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (rutaDao.existsById(idRuta)) {
				Ruta RutaConsultado = rutaDao.findById(idRuta).get();
				RutaDTO RutaRetornado = this.rutaMapper.obtenerRutaDTO(RutaConsultado);
			respuestaGenerica = new RespuestaGenerica(RutaRetornado,
						ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "La ruta no existe.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar la ruta ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarRuta(RutaDTO rutaDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			Ruta rutaAGuardar = this.rutaMapper.obtenerRuta(rutaDtoAGuardar);
			this.rutaDao.save(rutaAGuardar);
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar ruta ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	public @ResponseBody ResponseEntity<RespuestaGenerica> borrarRuta(long idRuta) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (rutaDao.existsById(idRuta)) {
				this.rutaDao.deleteById(idRuta);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Ruta no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar ruta ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerAllRuta() {
		try {
			RespuestaGenerica respuestaGenerica;
			ArrayList<Ruta> RutaConsultado =(ArrayList<Ruta>) rutaDao.findAll();
			if(RutaConsultado.size()!=0) {
				ArrayList<RutaDTO> listadoRutaDtoRetornar = new ArrayList<RutaDTO>();
				RutaConsultado.forEach((itemEstadoACastear) -> {
					listadoRutaDtoRetornar.add(this.rutaMapper.obtenerRutaDTO(itemEstadoACastear));
				});
			respuestaGenerica = new RespuestaGenerica(listadoRutaDtoRetornar,
						ConstantesRespuestas.EXITO, "");
			}else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No hay estaciones disponibles");
			}
			
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar la ruta ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
}

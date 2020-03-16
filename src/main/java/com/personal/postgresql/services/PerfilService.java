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
import com.personal.postgresql.dto.PerfilDTO;
import com.personal.postgresql.entities.Geolocalizacion;
import com.personal.postgresql.entities.Perfil;
import com.personal.postgresql.entities.Vehiculo;
import com.personal.postgresql.mappers.PerfilMapper;
import com.personal.postgresql.repositories.PerfilDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class PerfilService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final PerfilMapper perfilMapper = new PerfilMapper();

	@Autowired
	private PerfilDao perfilDao;
	

	// Lista el perfil
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerPerfil(long idPerfil) {
		RespuestaGenerica respuestaGenerica;
		try {
			if (perfilDao.existsById(idPerfil)) {
				Perfil perfil = perfilDao.findById(idPerfil).get();
				PerfilDTO perfilDTO = this.perfilMapper.obtenerPerfilDTO(perfil);
				
				
				respuestaGenerica = new RespuestaGenerica(perfilDTO, ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "El perfil no existe.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error, se presentan problemas al cargar un perfil ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	//Lista a todos los perfiles
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerPerfiles() {
		try {
			RespuestaGenerica respuestaGenerica;
			ArrayList<Perfil> perfilesConsultados = (ArrayList<Perfil>) perfilDao.findAll();
			ArrayList<PerfilDTO> perfilARetornar = new ArrayList<PerfilDTO>();

			for (Perfil perfItemC : perfilesConsultados) {
				perfilARetornar.add(this.perfilMapper.obtenerPerfilDTO(perfItemC));
			}
			respuestaGenerica = new RespuestaGenerica(perfilARetornar, ConstantesRespuestas.EXITO, "EXITO");

			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error, se presentan problemas al cargar los perfiles");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	// Crea y actualiza el perfil

	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarPerfil(PerfilDTO perfilDTO) {
		try {
			RespuestaGenerica respuestaGenerica;
			Perfil perfil = this.perfilMapper.obtenerPerfil(perfilDTO);
			this.perfilDao.save(perfil);
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error, se presentan problemas al crear y/o actualizar el perfil ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
		

	// Elimina el perfil

	public @ResponseBody ResponseEntity<RespuestaGenerica> borrarPerfil(long idPerfil) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (perfilDao.existsById(idPerfil)) {
				this.perfilDao.deleteById(idPerfil);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Perfil no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error, se presentan problemas al intentar eliminar el perfil ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

}

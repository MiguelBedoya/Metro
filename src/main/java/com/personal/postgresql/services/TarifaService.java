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
import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.entities.Tarifa;
import com.personal.postgresql.mappers.TarifaMapper;
import com.personal.postgresql.repositories.TarifaDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class TarifaService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final TarifaMapper tarifaMapper = new TarifaMapper();

	@Autowired
	private TarifaDao tarifaDao;

	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerPorIdTarifa(long idTarifa) {
		try {
			RespuestaGenerica respuestaGenerica;
			Tarifa tarifa = tarifaDao.findById(idTarifa).get();
			TarifaDTO tarifaDto = this.tarifaMapper.obtenerTarifaDTO(tarifa);
			respuestaGenerica = new RespuestaGenerica(tarifaDto, ConstantesRespuestas.EXITO, "EXITO");

			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Oe, no esta sirviendo esta vuelta");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerTarifas() {
		try {
			RespuestaGenerica respuestaGenerica;
			ArrayList<Tarifa> tarifasConsultadas = (ArrayList<Tarifa>) tarifaDao.findAll();
			ArrayList<TarifaDTO> tarifasARetornar = new ArrayList<TarifaDTO>();

			for (Tarifa tarifaItemC : tarifasConsultadas) {
				tarifasARetornar.add(this.tarifaMapper.obtenerTarifaDTO(tarifaItemC));
			}
			respuestaGenerica = new RespuestaGenerica(tarifasARetornar, ConstantesRespuestas.EXITO, "EXITO");

			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Oe, no esta sirviendo esta vuelta");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarTarifa(TarifaDTO tarifaAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;

			Tarifa tarAGuardar = this.tarifaMapper.obtenerTarifa(tarifaAGuardar);
			this.tarifaDao.save(tarAGuardar);
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "EXITO");

			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Pailas, se tosto esta tajada");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	public @ResponseBody ResponseEntity<RespuestaGenerica> borrarTarifa(long idTarifaABorrar) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (tarifaDao.existsById(idTarifaABorrar)) {
				this.tarifaDao.deleteById(idTarifaABorrar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR,
						"Que vas a borrar si no hay nada!");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Pailas, se tosto esta tajada");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

}

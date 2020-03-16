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
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.mappers.MaestroValorMapper;
import com.personal.postgresql.repositories.MaestroValorDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class MaestroValorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();

	@Autowired
	private MaestroValorDao maestroValorDao;

	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerDatosPorTipo(long idTipoMaestroValor) {
		try {
			RespuestaGenerica respuestaGenerica;
			ArrayList<MaestroValorDTO> listadoMaestroValorARetornar = new ArrayList<MaestroValorDTO>();
			for (MaestroValor itemMaestroValor : this.maestroValorDao
					.findByIdMaestroValor(this.maestroValorDao.findById(idTipoMaestroValor).get())) {
				listadoMaestroValorARetornar.add(this.maestroValorMapper.obtenerMaestroValorDTO(itemMaestroValor));
			}
			respuestaGenerica = new RespuestaGenerica(listadoMaestroValorARetornar, ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar datos de la tabla maestro valor");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

}

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
import com.personal.postgresql.dto.BeneficioDTO;
import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.entities.Beneficio;
import com.personal.postgresql.mappers.BeneficioMapper;
import com.personal.postgresql.repositories.BeneficioDAO;
import com.personal.postgresql.repositories.TarifaDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class BeneficioService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final BeneficioMapper beneficioMapper = new BeneficioMapper();
	
	@Autowired
	private BeneficioDAO beneficioDAO;
	
	@Autowired
	private TarifaDao tarifaDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerBeneficioPorId(long idBeneficio) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (beneficioDAO.existsById(idBeneficio)) {
				Beneficio beneficio = beneficioDAO.findById(idBeneficio).get();
				BeneficioDTO beneficioRetornado = this.beneficioMapper.obtenerBeneficioDTO(beneficio);
			respuestaGenerica = new RespuestaGenerica(beneficioRetornado, ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No existe el beneficio.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar el beneficio ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarBeneficio(BeneficioDTO beneficioDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			
			if (tarifaDao.existsById(beneficioDtoAGuardar.getIdTarifa().getId())) {	
				
				Beneficio beneficioAGuardar = this.beneficioMapper.obtenerBeneficio(beneficioDtoAGuardar);
				this.beneficioDAO.save(beneficioAGuardar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
				
			}else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se guardo el beneficio");
			}	
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error se presentan problemas al guardar el beneficio ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> borrarBeneficio(long idBeneficioABorrar) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (beneficioDAO.existsById(idBeneficioABorrar)) {
				this.beneficioDAO.deleteById(idBeneficioABorrar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Beneficio no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al borrar el beneficio ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> listarBeneficios() {
		try {
			RespuestaGenerica respuestaGenerica;
			
			ArrayList<Beneficio> listBeneficio = (ArrayList<Beneficio>) beneficioDAO.findAll();
			
			ArrayList<BeneficioDTO> listadoBeneficioDtoRetornar = new ArrayList<BeneficioDTO>();

			listBeneficio.forEach((itemBeneficioACastear) -> {
				listadoBeneficioDtoRetornar.add(this.beneficioMapper.obtenerBeneficioDTO(itemBeneficioACastear));
			});
			respuestaGenerica = new RespuestaGenerica(listadoBeneficioDtoRetornar, ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar el beneficio ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
}

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
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.Beneficio;
import com.personal.postgresql.entities.Vehiculo;
import com.personal.postgresql.mappers.VehiculoMapper;
import com.personal.postgresql.repositories.VehiculoDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class VehiculoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final VehiculoMapper vehiculoMapper = new VehiculoMapper();

	@Autowired
	private VehiculoDao vehiculoDao;

	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarVehiculo(VehiculoDTO vehiculoDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			Vehiculo vehiculoAGuardar = this.vehiculoMapper.obtenerVehiculo(vehiculoDtoAGuardar);
			this.vehiculoDao.save(vehiculoAGuardar);
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al guardar o actualizar vehiculo");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}

	public @ResponseBody ResponseEntity<RespuestaGenerica> borrarVehiculo(long idVehiculoABorrar) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (vehiculoDao.existsById(idVehiculoABorrar)) {
				this.vehiculoDao.deleteById(idVehiculoABorrar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Vehiculo no encontrado");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al borrar vehiculo ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> listarVehiculo() {
		try {
			RespuestaGenerica respuestaGenerica;
			
			ArrayList<Vehiculo> listVehiculo = (ArrayList<Vehiculo>) vehiculoDao.findAll();
			
			ArrayList<VehiculoDTO> listadoVehiculoDtoRetornar = new ArrayList<VehiculoDTO>();

			listVehiculo.forEach((itemVehiculoACastear) -> {
				listadoVehiculoDtoRetornar.add(this.vehiculoMapper.obtenerVehiculoDTO(itemVehiculoACastear));
			});
			respuestaGenerica = new RespuestaGenerica(listadoVehiculoDtoRetornar, ConstantesRespuestas.EXITO, "EXITO");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error al listar vehiculos ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, "ERROR"),
					HttpStatus.BAD_REQUEST);
		}
	}
}

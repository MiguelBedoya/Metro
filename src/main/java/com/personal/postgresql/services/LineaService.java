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
import com.personal.postgresql.dto.LineaDTO;
import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.Linea;
import com.personal.postgresql.entities.LineaVehiculo;
import com.personal.postgresql.entities.Tarifa;
import com.personal.postgresql.entities.Vehiculo;
import com.personal.postgresql.mappers.LineaMapper;
import com.personal.postgresql.mappers.LineaTarifaMapper;
import com.personal.postgresql.mappers.LineaVehiculoMapper;
import com.personal.postgresql.mappers.TarifaMapper;
import com.personal.postgresql.mappers.VehiculoMapper;
import com.personal.postgresql.repositories.LineaDao;
import com.personal.postgresql.repositories.LineaTarifaDao;
import com.personal.postgresql.repositories.LineaVehiculoDao;
import com.personal.postgresql.repositories.TarifaDao;
import com.personal.postgresql.repositories.VehiculoDao;
import com.personal.postgresql.responses.RespuestaGenerica;

import javassist.expr.NewArray;

@Service
public class LineaService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final LineaMapper lineaMapper = new LineaMapper();
	private final VehiculoMapper vehiculoMapper = new VehiculoMapper();
	private final TarifaMapper tarifaMapper = new TarifaMapper();
	private final LineaVehiculoMapper lineaVehiculoMapper = new LineaVehiculoMapper();
	private final LineaTarifaMapper lineaTarifaMapper = new LineaTarifaMapper();
	
	
	@Autowired
	private LineaDao lineaDao;
	
	@Autowired
	private LineaVehiculoDao lineaVehiculoDao;
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Autowired
	private TarifaDao tarifaDao;
	
	@Autowired
	private LineaTarifaDao lineaTarifaDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtener(long id) {
		try {
			RespuestaGenerica respuestaGenerica;
			if (lineaDao.existsById(id)) {
				Linea lineaACastear = lineaDao.findById(id).get();
				ArrayList<Vehiculo> listadoVehiculoConsultado = vehiculoDao.findByIdLinea(lineaACastear.getId());
				
				ArrayList<VehiculoDTO> listadoVehiculoDtoARetornar = new ArrayList<>();
				listadoVehiculoConsultado.forEach((itemVehiculoACastear)->{
					listadoVehiculoDtoARetornar.add(this.vehiculoMapper.obtenerVehiculoDTO(itemVehiculoACastear));
				});
				
				ArrayList<Tarifa> listadoTarifaConsultada = tarifaDao.findByIdLinea(lineaACastear.getId());
				
				ArrayList<TarifaDTO> listadoTarifaDtoARetornar = new ArrayList<>();
				listadoTarifaConsultada.forEach((itemTarifaACastear)->{
					listadoTarifaDtoARetornar.add(this.tarifaMapper.obtenerTarifaDTO(itemTarifaACastear));
				});
				
				respuestaGenerica = new RespuestaGenerica(this.lineaMapper.obtenerLineaDTO(lineaACastear, listadoVehiculoDtoARetornar, listadoTarifaDtoARetornar), ConstantesRespuestas.EXITO, "");
				
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Linea no encontrada");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar la linea ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarLinea(LineaDTO lineaDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			Linea lineaAGuardar = this.lineaMapper.obtenerLinea(lineaDtoAGuardar);
			this.lineaDao.save(lineaAGuardar);
			if(lineaDtoAGuardar.getVehiculos() != null) {
				lineaDtoAGuardar.getVehiculos().forEach((itemVehiculoAGuardar)->{
					this.lineaVehiculoDao.save(this.lineaVehiculoMapper.obtenerLineaVehiculo(itemVehiculoAGuardar, lineaAGuardar));
				});
			}
			if(lineaDtoAGuardar.getTarifas() != null) {
				lineaDtoAGuardar.getTarifas().forEach((itemTarifaAGuardar)->{
					this.lineaTarifaDao.save(this.lineaTarifaMapper.obtenerLineaTarifa(itemTarifaAGuardar, lineaAGuardar));
				});
			}			
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar la linea");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarLinea(long idLineaAEliminar){
		try {
			RespuestaGenerica respuestaGenerica;
			if (lineaDao.existsById(idLineaAEliminar)) {
				this.lineaVehiculoDao.deleteByIdLinea(Long.valueOf(idLineaAEliminar));
				this.lineaTarifaDao.deleteByIdLinea(Long.valueOf(idLineaAEliminar));
				this.lineaDao.deleteById(Long.valueOf(idLineaAEliminar));;
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Linea no encontrada");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar linea ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
}

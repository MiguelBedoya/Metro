package com.personal.postgresql.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.CuentaDTO;
import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.mappers.CuentaMapper;
import com.personal.postgresql.repositories.CuentaDao;
import com.personal.postgresql.repositories.UsuarioDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class CuentaService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final CuentaMapper cuentaMapper = new CuentaMapper();
	
	@Autowired
	private CuentaDao cuentaDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> obtenerCuenta(long idCuenta){
		RespuestaGenerica respuestaGenerica;
		try {
			if(cuentaDao.existsById(idCuenta)) {
				Cuenta cuenta = cuentaDao.findById(idCuenta).get(); 
				CuentaDTO cuentaDTO = this.cuentaMapper.obtenerCuentaDTO(cuenta);
				respuestaGenerica = new RespuestaGenerica<>(cuenta, ConstantesRespuestas.EXITO ,"");
				
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Cuenta no existe.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error se presentan problemas al cargar la cuenta ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);	
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> actualizarCuenta(CuentaDTO cuentaDto){
		RespuestaGenerica respuestaGenerica;
		try {
			if(cuentaDto != null) {
				Cuenta cuenta = this.cuentaMapper.obtenerCuenta(cuentaDto);
				this.cuentaDao.save(cuenta);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se puede crear la cuenta.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error se presentan problemas al crear la cuenta ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> eliminarCuenta(CuentaDTO cuentaDTO){
		RespuestaGenerica respuestaGenerica;
		try {
			if(cuentaDTO != null) {
				this.cuentaDao.deleteById(cuentaDTO.getId());
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se puede eliminar la cuenta.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error se presentan problemas al eliminar la cuenta ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	public Cuenta crearCuenta(CuentaDTO cuentaDto){
		Cuenta cuentaRetorno = null;
		if(cuentaDto != null) {
			Cuenta cuenta = this.cuentaMapper.obtenerCuenta(cuentaDto);
			cuentaRetorno = this.cuentaDao.save(cuenta);
		}
		return cuentaRetorno;
	}
}

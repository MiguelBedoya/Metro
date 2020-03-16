package com.personal.postgresql.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.postgresql.constants.ConstantesRespuestas;
import com.personal.postgresql.dto.RecargaDTO;
import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.EstadoCuenta;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Recarga;
import com.personal.postgresql.mappers.CuentaMapper;
import com.personal.postgresql.mappers.EstadoCuentaMapper;
import com.personal.postgresql.mappers.RecargaMapper;
import com.personal.postgresql.repositories.CuentaDao;
import com.personal.postgresql.repositories.EstadoCuentaDao;
import com.personal.postgresql.repositories.RecargaDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class RecargaService {

	// 1. Obtener cuenta a modificar
	// 2. Insertar la transacción (Crear recarga)
	// 3. Saldo Anterios a la transaccion
	// 4. Modificar la cuenta (se toma el nuevo saldo)
	// 5. Se crea un nuevo estado de cuenta (Necesita: el saldo anterios, el tipo de transaccion, el nuevo saldo y el id del tipo transaccion)
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final RecargaMapper recargaMapper = new RecargaMapper();
	private final CuentaMapper cuentaMapper = new CuentaMapper();
	private final EstadoCuentaMapper estadoCuentaMapper = new EstadoCuentaMapper();
	
	@Autowired
	private RecargaDao recargaDao;
	
	@Autowired
	private CuentaDao cuentaDao;
	
	@Autowired
	private EstadoCuentaDao estadoCuentaDao;
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> consultarRecargaDeCuenta(long idCuenta){
		RespuestaGenerica respuestaGenerica;
		try {
			if(cuentaDao.existsById(idCuenta)) {
				Recarga recarga = recargaDao.findById(idCuenta).get();
				RecargaDTO recargaDTO = this.recargaMapper.consultarRecargasDTO(recarga);
				respuestaGenerica = new RespuestaGenerica<>(recarga, ConstantesRespuestas.EXITO ,"");
				
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "Esta cuenta no tiene recargas");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Se han presentado problemas al consultar las recargas de esta cuenta");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);	
		}
	}
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> recargarCuenta(RecargaDTO recargaDTO){
		RespuestaGenerica respuestaGenerica;
		try {

			Recarga recarga = this.recargaMapper.consultarRecargas(recargaDTO);
			
			if(cuentaDao.existsById(recarga.getIdCuenta().getId())) {				
				Recarga recargaGuardada = this.recargaDao.save(recarga);
				
				Cuenta cuenta = cuentaDao.findById(recarga.getIdCuenta().getId()).get();
				double saldoAnterior = cuenta.getSaldo();
				cuenta.setSaldo(cuenta.getSaldo() + recarga.getValor());
				
				this.cuentaDao.save(cuenta);
				
				EstadoCuenta estadoCuenta = new EstadoCuenta();
				
				estadoCuenta.setSaldoAnterior(saldoAnterior);
				estadoCuenta.setSaldoNuevo(cuenta.getSaldo());
				
				MaestroValor maestroValor = new MaestroValor();
				
				maestroValor.setId(3);
				
				estadoCuenta.setIdTipoTransaccion(maestroValor);
				
				Date fecha = new Date();
				estadoCuenta.setFecha(fecha);
				
				estadoCuenta.setIdRelacionalMovimiento(recargaGuardada.getId());
				
				this.estadoCuentaDao.save(estadoCuenta);
				
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			} else {
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No se pudo generar la recarga.");
			}
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Se ha presentado un problema al registrar la recarga");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
	}
}

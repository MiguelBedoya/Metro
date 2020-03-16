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
import com.personal.postgresql.dto.PrestamoDTO;
import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.EstadoCuenta;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Prestamo;
import com.personal.postgresql.mappers.PrestamoMapper;
import com.personal.postgresql.repositories.CuentaDao;
import com.personal.postgresql.repositories.EstadoCuentaDao;
import com.personal.postgresql.repositories.MaestroValorDao;
import com.personal.postgresql.repositories.PrestamoDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class PrestamoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final PrestamoMapper prestamoMapper = new PrestamoMapper();

	@Autowired
	private PrestamoDao prestamoDao;
	
	@Autowired
	private CuentaDao cuentaDao;
	
	@Autowired
	private EstadoCuentaDao estadoCuentaDao; 
	
	@Autowired
	private MaestroValorDao maestroValorDao; 

	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarPrestamo(PrestamoDTO prestamoDtoAGuardar) {
		try {
			RespuestaGenerica respuestaGenerica;
			Prestamo prestamoAGuardar = this.prestamoMapper.obtenerPrestamo(prestamoDtoAGuardar);
			if(cuentaDao.existsById(prestamoAGuardar.getIdCuenta().getId())) {
			    Cuenta cuenta = this.cuentaDao.findById(prestamoAGuardar.getIdCuenta().getId()).get();
			    EstadoCuenta estadoCuenta = new EstadoCuenta();
			    MaestroValor maestroValor = new MaestroValor();
			    maestroValor.setId(2);			    
				estadoCuenta.setSaldoAnterior(cuenta.getSaldo());
				estadoCuenta.setSaldoNuevo(cuenta.getSaldo() + prestamoAGuardar.getValor());
				estadoCuenta.setFecha(new Date());
				estadoCuenta.setIdTipoTransaccion(maestroValor);
			    cuenta.setSaldo(cuenta.getSaldo() + prestamoAGuardar.getValor());
			    Prestamo prestamoGuardado = this.prestamoDao.save(prestamoAGuardar);
				estadoCuenta.setIdRelacionalMovimiento(prestamoGuardado.getId());
			    this.cuentaDao.save(cuenta);				
			    this.estadoCuentaDao.save(estadoCuenta);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
				return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
			}else {
				this.prestamoDao.save(prestamoAGuardar);
				respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.ERRROR, "");
				return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error("Error se presentan problemas para guardar el prestamo");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, "No existe cuenta"),
					HttpStatus.BAD_REQUEST);
		}
	}	
}

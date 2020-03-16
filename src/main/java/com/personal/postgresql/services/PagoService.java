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
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.PagoDTO;
import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.EstadoCuenta;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Pago;
import com.personal.postgresql.mappers.PagoMapper;
import com.personal.postgresql.repositories.CuentaDao;
import com.personal.postgresql.repositories.EstadoCuentaDao;
import com.personal.postgresql.repositories.MaestroValorDao;
import com.personal.postgresql.repositories.PagoDao;
import com.personal.postgresql.responses.RespuestaGenerica;

@Service
public class PagoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final PagoMapper pagoMapper = new PagoMapper();

	@Autowired
	private PagoDao pagoDao;
	
	@Autowired
	private CuentaDao cuentaDao;
	
	@Autowired
	private EstadoCuentaDao estadoCuentaDao;
	
	
	public @ResponseBody ResponseEntity<RespuestaGenerica> guardarOActualizarPago(PagoDTO pagoDtoAGuardar, double montoPago)
	{
		RespuestaGenerica respuestaGenerica;
		try {
			Pago pagoAGuardar = this.pagoMapper.obtenerPago(pagoDtoAGuardar);
			Pago pagoGuardado = this.pagoDao.save(pagoAGuardar);
			
			Cuenta cuentaVinculada = this.cuentaDao.findById(pagoDtoAGuardar.getIdCuentaVinculada().getId()).get();
			double saldoAnterior = cuentaVinculada.getSaldo();
			
			cuentaVinculada.setSaldo(cuentaVinculada.getSaldo() - montoPago);
			this.cuentaDao.save(cuentaVinculada);
			
			EstadoCuenta estadoCuentaAGuardar =  new EstadoCuenta();
			estadoCuentaAGuardar.setFecha(new Date());
			estadoCuentaAGuardar.setIdRelacionalMovimiento(pagoGuardado.getId());
			
			MaestroValor maestroTipoTransaccion = new MaestroValor();
			maestroTipoTransaccion.setId(4);
			
			estadoCuentaAGuardar.setIdTipoTransaccion(maestroTipoTransaccion);
			estadoCuentaAGuardar.setSaldoAnterior(saldoAnterior);
			estadoCuentaAGuardar.setSaldoNuevo(cuentaVinculada.getSaldo());
			
			this.estadoCuentaDao.save(estadoCuentaAGuardar);
			
			respuestaGenerica = new RespuestaGenerica(ConstantesRespuestas.EXITO, "");
			return new ResponseEntity<RespuestaGenerica>(respuestaGenerica, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error se presentan problemas al cargar geolocalización ");
			e.printStackTrace();
			return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica(ConstantesRespuestas.ERRROR, ""),
					HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
}



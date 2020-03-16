package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.RecargaDTO;
import com.personal.postgresql.entities.Cuenta;
import com.personal.postgresql.entities.EstadoCuenta;
import com.personal.postgresql.entities.Recarga;

public class RecargaMapper {
	
	private final CuentaMapper cuentaMapper = new CuentaMapper();
	
	public RecargaDTO consultarRecargasDTO(Recarga recargaACastear) {
		RecargaDTO recargaDtoARetornar = new RecargaDTO();
		
		recargaDtoARetornar.setValor(recargaACastear.getValor());
		recargaDtoARetornar.setFecRecarga(recargaACastear.getFecRecarga());
		
		return recargaDtoARetornar;
	}
	
	public Recarga consultarRecargas(RecargaDTO recargaDTOACastear) {
		Recarga recargaARetornar = new Recarga();		
		
		recargaARetornar.setValor(recargaDTOACastear.getValor());
		recargaARetornar.setFecRecarga(recargaDTOACastear.getFecRecarga());
		
		Cuenta cuentaConsultarRecarga = this.cuentaMapper.obtenerCuenta(recargaDTOACastear.getIdCuenta());
		recargaARetornar.setIdCuenta(cuentaConsultarRecarga);
		
		return recargaARetornar;
	}	
}

package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.CuentaDTO;
import com.personal.postgresql.entities.Cuenta;

public class CuentaMapper {

	public CuentaDTO obtenerCuentaDTO(Cuenta cuenta) {
		CuentaDTO cuentaDTO = new CuentaDTO();
		cuentaDTO.setId(cuenta.getId());
		cuentaDTO.setSaldo(cuenta.getSaldo());
		
		return cuentaDTO;
	}
	
	public Cuenta obtenerCuenta(CuentaDTO cuentaDTO) {
		Cuenta cuenta =  new Cuenta();
		cuenta.setId(cuentaDTO.getId());
		cuenta.setSaldo(cuentaDTO.getSaldo());
		return cuenta;
	}
}

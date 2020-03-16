package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.EstadoCuentaDTO;
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.entities.EstadoCuenta;
import com.personal.postgresql.entities.MaestroValor;

public class EstadoCuentaMapper {

	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();

	public EstadoCuentaDTO obtenerEstadoCuentaDTO(EstadoCuenta estadoCuentaACastear) {

		MaestroValorDTO maestroValorDToARetornar = this.maestroValorMapper
				.obtenerMaestroValorDTO(estadoCuentaACastear.getIdTipoTransaccion());

		EstadoCuentaDTO estadoCuentaDTOARetornar = new EstadoCuentaDTO();
		estadoCuentaDTOARetornar.setFecha(estadoCuentaACastear.getFecha());
		estadoCuentaDTOARetornar.setIdRelacionalMovimiento(estadoCuentaACastear.getIdRelacionalMovimiento());
		estadoCuentaDTOARetornar.setIdTipoTransaccion(maestroValorDToARetornar);
		estadoCuentaDTOARetornar.setSaldoAnterior(estadoCuentaACastear.getSaldoAnterior());
		estadoCuentaDTOARetornar.setSaldoNuevo(estadoCuentaACastear.getSaldoNuevo());

		return estadoCuentaDTOARetornar;
	}

	public EstadoCuenta obtenerEstadoCuenta(EstadoCuentaDTO estadoCuentaDTOACastear) {
		MaestroValor maestroValorARetornar = this.maestroValorMapper
				.obtenerMaestroValor(estadoCuentaDTOACastear.getIdTipoTransaccion());
		
		EstadoCuenta estadoCuentaARetornar = new EstadoCuenta();
		estadoCuentaARetornar.setFecha(estadoCuentaDTOACastear.getFecha());
		estadoCuentaARetornar.setIdRelacionalMovimiento(estadoCuentaDTOACastear.getIdRelacionalMovimiento());
		estadoCuentaARetornar.setIdTipoTransaccion(maestroValorARetornar);
		estadoCuentaARetornar.setSaldoAnterior(estadoCuentaDTOACastear.getSaldoAnterior());
		estadoCuentaARetornar.setSaldoNuevo(estadoCuentaDTOACastear.getSaldoNuevo());
		
		return estadoCuentaARetornar;
	}

}

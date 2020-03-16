package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.BeneficioDTO;
import com.personal.postgresql.dto.TarifaDTO;
import com.personal.postgresql.entities.Beneficio;
import com.personal.postgresql.entities.Tarifa;

public class BeneficioMapper {
	
	private final TarifaMapper tarifaMapper = new TarifaMapper();
	
	public BeneficioDTO obtenerBeneficioDTO(Beneficio beneficioACastear) {	
		
		Tarifa tarifaACastear = beneficioACastear.getIdTarifa();
		
		TarifaDTO tarifaDTO = tarifaMapper.obtenerTarifaDTO(tarifaACastear);
		
		BeneficioDTO BeneficioARetornar = new BeneficioDTO();
		BeneficioARetornar.setId(beneficioACastear.getId());
		BeneficioARetornar.setNombre(beneficioACastear.getNombre());
		BeneficioARetornar.setDescripcion(beneficioACastear.getDescripcion());
		BeneficioARetornar.setIdTarifa(tarifaDTO);
		return BeneficioARetornar;
	}
	
	public Beneficio obtenerBeneficio(BeneficioDTO beneficioDTOACastear) {
		
		Beneficio beneficioARetornar = new Beneficio();
		beneficioARetornar.setId(beneficioDTOACastear.getId());
		beneficioARetornar.setNombre(beneficioDTOACastear.getNombre());
		beneficioARetornar.setDescripcion(beneficioDTOACastear.getDescripcion());
		Tarifa tarifa = new Tarifa();
		tarifa.setId(beneficioDTOACastear.getIdTarifa().getId());
		beneficioARetornar.setIdTarifa(tarifa);
		
		return beneficioARetornar;
		
	}

}

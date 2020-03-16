package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.PerfilDTO;
import com.personal.postgresql.dto.VehiculoDTO;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Perfil;
import com.personal.postgresql.entities.Vehiculo;

public class PerfilMapper {
	
	
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();

	public PerfilDTO obtenerPerfilDTO(Perfil perfil) {

		MaestroValor idTipoPerfil = perfil.getTipoPerfil();
		MaestroValorDTO maestroValorDTO = this.maestroValorMapper.obtenerMaestroValorDTO(idTipoPerfil);


		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setId(perfil.getId());
		perfilDTO.setNombre(perfil.getNombre());
		perfilDTO.setDescripcion(perfil.getDescripcion());
		perfilDTO.setTipoPerfil(maestroValorDTO);
		;

		return perfilDTO;

	}

	public Perfil obtenerPerfil(PerfilDTO perfilDTO) {

		MaestroValorDTO maestroValorDTO = perfilDTO.getTipoPerfil();

		MaestroValor maestroValor = this.maestroValorMapper.obtenerMaestroValor(maestroValorDTO);


		Perfil perfil = new Perfil();
		perfil.setId(perfilDTO.getId());
		perfil.setNombre(perfilDTO.getNombre());
		perfil.setDescripcion(perfilDTO.getDescripcion());
		perfil.setTipoPerfil(maestroValor);
		

		return perfil;
	}

}

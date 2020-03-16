package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.GeolocalizacionDTO;

import com.personal.postgresql.entities.Geolocalizacion;
import com.personal.postgresql.entities.Usuario;

import lombok.Data;

@Data
public class GeolocalizacionMapper {

	public GeolocalizacionDTO obtenerGeoDTO(Geolocalizacion geolocalizacionACastear) {
		GeolocalizacionDTO geoDtoARetornar = new GeolocalizacionDTO();
		geoDtoARetornar.setAltura(geolocalizacionACastear.getAltura());
		geoDtoARetornar.setLongitud(geolocalizacionACastear.getLongitud());
		geoDtoARetornar.setLatitud(geolocalizacionACastear.getLatitud());
		return geoDtoARetornar;
	}
	
	public Geolocalizacion obtenerGeo(GeolocalizacionDTO geolocalizacionDTOACastear, long idUsuarioRelacionado) {
		Geolocalizacion geoARetornar = new Geolocalizacion();
		geoARetornar.setAltura(geolocalizacionDTOACastear.getAltura());
		geoARetornar.setLongitud(geolocalizacionDTOACastear.getLongitud());
		geoARetornar.setLatitud(geolocalizacionDTOACastear.getLatitud());
		Usuario usuarioRelacionado = new Usuario();
		usuarioRelacionado.setId(idUsuarioRelacionado);
		geoARetornar.setIdUsuario(usuarioRelacionado);
		return geoARetornar;
	}
	
}

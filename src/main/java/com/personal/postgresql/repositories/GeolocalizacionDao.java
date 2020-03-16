package com.personal.postgresql.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Geolocalizacion;
import com.personal.postgresql.entities.Usuario;

@Repository
public interface GeolocalizacionDao extends CrudRepository<Geolocalizacion, Long>{
	
	public ArrayList<Geolocalizacion> findByIdUsuario(Usuario idUsuario);
	
}

package com.personal.postgresql.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Estacion;
import com.personal.postgresql.entities.Ruta;
import com.personal.postgresql.entities.RutaEstacion;

@Repository
public interface RutaEstacionDao extends CrudRepository<RutaEstacion, Long> {
public ArrayList<RutaEstacion> findByIdEstacion(Estacion idEstacion);
	
	public ArrayList<RutaEstacion> findByIdRuta(Ruta idRuta);
}

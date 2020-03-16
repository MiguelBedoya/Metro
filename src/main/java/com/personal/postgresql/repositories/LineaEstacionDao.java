package com.personal.postgresql.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Estacion;
import com.personal.postgresql.entities.Linea;
import com.personal.postgresql.entities.LineaEstacion;

@Repository
public interface LineaEstacionDao extends CrudRepository<LineaEstacion,Long> {
	
	public ArrayList<LineaEstacion> findByIdEstacion(Estacion idEstacion);
	
	public ArrayList<LineaEstacion> findByIdLinea(Linea idLinea);
}

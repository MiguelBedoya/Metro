package com.personal.postgresql.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Vehiculo;

@Repository
public interface VehiculoDao extends CrudRepository<Vehiculo, Long> {

	@Query(nativeQuery = true, value = "select * from vehiculo v inner join linea_vehiculo lv on v.id  = lv.id_vehiculo where id_linea = :idLinea")
	public ArrayList<Vehiculo> findByIdLinea(@Param("idLinea")long idLinea);
	
}

package com.personal.postgresql.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.personal.postgresql.entities.Tarifa;


@Repository
public interface TarifaDao extends CrudRepository<Tarifa, Long>{

	@Query(nativeQuery = true, value = "select * from tarifa t inner join linea_tarifa lt on t.id  = lt.id_tarifa where id_linea = :idLinea")
	ArrayList<Tarifa> findByIdLinea(@Param("idLinea")long idLinea);

}

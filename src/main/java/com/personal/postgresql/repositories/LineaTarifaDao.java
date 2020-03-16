package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.LineaTarifa;

@Repository
public interface LineaTarifaDao extends CrudRepository<LineaTarifa, Long>{
	
	LineaTarifa findByIdLinea(long idLineaAEliminar);

	void deleteByIdLinea(Long idLinea);

}

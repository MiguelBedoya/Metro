package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.LineaVehiculo;

@Repository
public interface LineaVehiculoDao extends CrudRepository<LineaVehiculo, Long>{

	LineaVehiculo findByIdLinea(long idLineaAEliminar);

	void deleteByIdLinea(Long idLinea);

}

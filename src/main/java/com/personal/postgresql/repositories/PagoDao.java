package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Pago;
@Repository
public interface PagoDao extends CrudRepository<Pago, Long> {

	
	
}

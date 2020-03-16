package com.personal.postgresql.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.MaestroValor;

@Repository
public interface MaestroValorDao extends CrudRepository<MaestroValor, Long>{

	public ArrayList<MaestroValor> findByIdMaestroValor(MaestroValor maestroValor);
	
}

package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Linea;

@Repository
public interface LineaDao extends CrudRepository<Linea, Long>{

}

package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Estacion;

@Repository
public interface EstacionDao extends CrudRepository<Estacion, Long> {

}

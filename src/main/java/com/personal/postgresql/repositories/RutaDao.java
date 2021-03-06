package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Ruta;

@Repository
public interface RutaDao extends CrudRepository<Ruta, Long> {

}

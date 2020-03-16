package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;

import com.personal.postgresql.entities.Beneficio;

public interface BeneficioDAO extends CrudRepository<Beneficio, Long> {

}

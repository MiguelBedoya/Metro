package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Prestamo;

@Repository
public interface PrestamoDao extends CrudRepository<Prestamo, Long> {

}

package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.EstadoCuenta;

@Repository
public interface EstadoCuentaDao extends CrudRepository<EstadoCuenta, Long>{

}

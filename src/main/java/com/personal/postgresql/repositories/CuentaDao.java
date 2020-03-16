package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Cuenta;

@Repository
public interface CuentaDao extends CrudRepository<Cuenta, Long>{

}

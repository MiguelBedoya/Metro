package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Recarga;

@Repository
public interface RecargaDao extends CrudRepository<Recarga, Long>{

}

package com.personal.postgresql.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Solicitud;

@Repository
public interface SolicitudDao extends CrudRepository<Solicitud, Long>{
	
}

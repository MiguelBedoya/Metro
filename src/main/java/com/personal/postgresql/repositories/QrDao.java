package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;

import com.personal.postgresql.entities.Qr;

public interface QrDao extends CrudRepository<Qr, Long> {

}

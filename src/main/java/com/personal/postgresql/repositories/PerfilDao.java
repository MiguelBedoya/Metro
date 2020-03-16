package com.personal.postgresql.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Geolocalizacion;
import com.personal.postgresql.entities.Perfil;
import com.personal.postgresql.entities.Usuario;

@Repository
public interface PerfilDao extends CrudRepository<Perfil, Long> {

}

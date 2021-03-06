package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Usuario;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Long>{
 
}

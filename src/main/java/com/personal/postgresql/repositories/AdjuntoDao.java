/**
 * 
 */
package com.personal.postgresql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Adjunto;

/**
 * @author sebastian Rojas Quintero
 *
 */
@Repository
public interface AdjuntoDao extends CrudRepository<Adjunto, Long>{
	
	
}

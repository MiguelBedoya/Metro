package com.personal.postgresql.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.postgresql.entities.Adjunto;
import com.personal.postgresql.entities.AdjuntoSolicitud;

/**
 * @author serojas
 *
 */
@Repository
public interface AdjuntoSolicitudDao extends CrudRepository<AdjuntoSolicitud, Long>{
	
	@Query("select a.idAdjunto from AdjuntoSolicitud a where a.idSolicitud.id = ?1")
	public List<Adjunto> obtenerAdjuntosSolicitud(long idSolicitud);

}

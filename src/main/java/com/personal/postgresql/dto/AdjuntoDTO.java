/**
 * 
 */
package com.personal.postgresql.dto;

import lombok.Data;

/**
 * @author serojas
 *
 */
@Data
public class AdjuntoDTO {
	
	private String nombre;
	private String ruta;
	private MaestroValorDTO tipoArchivo;

}

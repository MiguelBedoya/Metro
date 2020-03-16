package com.personal.postgresql.mappers;

import com.personal.postgresql.dto.DatoBiometricoDTO;
import com.personal.postgresql.dto.MaestroValorDTO;
import com.personal.postgresql.dto.PerfilDTO;
import com.personal.postgresql.dto.UsuarioDTO;
import com.personal.postgresql.entities.DatoBiometrico;
import com.personal.postgresql.entities.MaestroValor;
import com.personal.postgresql.entities.Perfil;
import com.personal.postgresql.entities.Usuario;

import lombok.Data;

@Data
public class UsuarioMapper {
	private final MaestroValorMapper maestroValorMapper = new MaestroValorMapper();

	private final DatoBiometricoMapper datoBiometricoMapper = new DatoBiometricoMapper();
	
	private final PerfilMapper perfilMapper = new PerfilMapper();
	
	
	public UsuarioDTO obtenerUsrDTO(Usuario usrACastear) {
		MaestroValorDTO tipoDoc = this.maestroValorMapper.obtenerMaestroValorDTO(usrACastear.getTipoDocumento());
		MaestroValorDTO tipoSangre = this.maestroValorMapper.obtenerMaestroValorDTO(usrACastear.getTipoSangre());
		DatoBiometricoDTO datoBiometrico = this.datoBiometricoMapper.obtenerDatoDTO(usrACastear.getIdDatoBiometrico());
		PerfilDTO perfil = this.perfilMapper.obtenerPerfilDTO(usrACastear.getIdPerfil());
		
		perfil.setId(usrACastear.getIdPerfil().getId());
		datoBiometrico.setId(usrACastear.getIdDatoBiometrico().getId());
		tipoDoc.setId(usrACastear.getTipoDocumento().getId());
		tipoSangre.setId(usrACastear.getTipoSangre().getId());
		
		
		UsuarioDTO usrDtoARetornar = new UsuarioDTO();
		usrDtoARetornar.setId(usrACastear.getId());
		usrDtoARetornar.setNombres(usrACastear.getNombres());
		usrDtoARetornar.setApellidos(usrACastear.getApellidos());
		usrDtoARetornar.setDireccion(usrACastear.getDireccion());
		usrDtoARetornar.setDocumento(usrACastear.getDocumento());
		usrDtoARetornar.setEmail(usrACastear.getEmail());
		usrDtoARetornar.setFechaNacimiento(usrACastear.getFechaNacimiento());
		usrDtoARetornar.setTelefono(usrACastear.getTelefono());
		usrDtoARetornar.setContrasena(usrACastear.getContrasena());
		usrDtoARetornar.setTipoDocumento(tipoDoc);
		usrDtoARetornar.setTipoSangre(tipoSangre);
		usrDtoARetornar.setIdDatoBiometrico(datoBiometrico);
		usrDtoARetornar.setIdPerfil(perfil);
		// usrDtoARetornar.setIdCuenta(usrACastear.getIdCuenta());

		return usrDtoARetornar;
	}
	
	public Usuario obtenerUsr(UsuarioDTO usrACastear) {
		Usuario usrARetornar = new Usuario();
		MaestroValor tipoDoc = this.maestroValorMapper.obtenerMaestroValor(usrACastear.getTipoDocumento());
		MaestroValor tipoSangre = this.maestroValorMapper.obtenerMaestroValor(usrACastear.getTipoSangre());
		Perfil perfil = this.perfilMapper.obtenerPerfil(usrACastear.getIdPerfil());
		
		perfil.setId(usrACastear.getIdPerfil().getId());
		DatoBiometrico datoBiometrico = this.datoBiometricoMapper.obtenerDato(usrACastear.getIdDatoBiometrico());
		
		datoBiometrico.setId(usrACastear.getIdDatoBiometrico().getId());
		tipoDoc.setId(usrACastear.getTipoDocumento().getId());
		tipoSangre.setId(usrACastear.getTipoSangre().getId());
		usrARetornar.setId(usrACastear.getId());
		usrARetornar.setNombres(usrACastear.getNombres());
		usrARetornar.setApellidos(usrACastear.getApellidos());
		usrARetornar.setDireccion(usrACastear.getDireccion());
		usrARetornar.setDocumento(usrACastear.getDocumento());
		usrARetornar.setEmail(usrACastear.getEmail());
		usrARetornar.setFechaNacimiento(usrACastear.getFechaNacimiento());
		usrARetornar.setTelefono(usrACastear.getTelefono());
		usrARetornar.setContrasena(usrACastear.getContrasena());
		usrARetornar.setTipoDocumento(tipoDoc);
		usrARetornar.setTipoSangre(tipoSangre);
		usrARetornar.setIdDatoBiometrico(datoBiometrico);
		usrARetornar.setIdPerfil(perfil);
		// usrDtoARetornar.setIdCuenta(usrNuevo.getIdCuenta());

		return usrARetornar;
	}

}

package com.mit.fachada;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mit.entitys.Usuario;

public interface IUsuarioFachada {

	List<Usuario> getUsuarios() throws Exception;

	ResponseEntity<String> updateUsuario(Usuario usuario) throws Exception;

	ResponseEntity<String> createUsuario(Usuario usuario) throws Exception;
	
	

}

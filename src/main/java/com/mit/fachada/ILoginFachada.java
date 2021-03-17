package com.mit.fachada;

import java.util.Optional;

import com.mit.entitys.Usuario;

public interface ILoginFachada {
	
	public Usuario getUsuario(String usuario) throws Exception;
	
	public String ejecutarJob() throws Exception;

}

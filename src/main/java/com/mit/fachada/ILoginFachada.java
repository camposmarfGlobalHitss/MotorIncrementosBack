package com.mit.fachada;

import com.mit.entitys.Usuario;

public interface ILoginFachada {
	
	public Usuario getUsuario(String usuario) throws Exception;
	
	public String ejecutarJob() throws Exception;
	
	
	public Usuario actualizarUSuario(Usuario usuario) throws Exception;
	
	public Usuario crearUsuario(Usuario usuario) throws Exception;
	
	public String sendEmail(String email, String subject, String content) throws Exception;
	
	public Usuario verificarUsuario(String code) throws Exception;
	
	public boolean olvidoPass(String correo);

}

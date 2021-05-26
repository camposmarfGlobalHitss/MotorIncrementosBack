package com.mit.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import com.mit.entitys.Usuario;

public interface ILoginDao extends CrudRepository<Usuario, Long> {
	@Transactional(readOnly = true) 
	Usuario findByUsername(String userName);
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario u SET u.estado = 1 WHERE u.id = ?1")
	void activarUsuario(Long id);
	
	@Query("SELECT u FROM  Usuario u WHERE u.codigoverificacion = ?1")
	Usuario verificarUsuario(String code);
	
	@Transactional(readOnly = true)
	Usuario findByCorreo(String correo);
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario u SET u.codigoverificacion = ?1 WHERE u.id = ?2")
	void cambiarCodigoVerificacion(String code,Long id);
	
	
	
}

package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	
	@Query(value="SELECT * FROM IMT_TBL_USUARIOS ORDER BY FEC_INI_USUARIO DESC", nativeQuery = true)
	@Transactional(readOnly = true)
	List<Usuario> getUsuarios();

	
}

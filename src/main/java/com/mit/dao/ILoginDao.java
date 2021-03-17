package com.mit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import com.mit.entitys.Usuario;

public interface ILoginDao extends CrudRepository<Usuario, Long> {
	@Transactional(readOnly = true) 
	Usuario findByUsername(String userName);
}

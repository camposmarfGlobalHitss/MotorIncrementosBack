package com.mit.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.Auditoria;

@Repository
public interface ITarifaUsoDao extends CrudRepository<Auditoria, Long>{
	
	/*
		@Luz.Obredor 10.02.2022
		método que consulta la cantidad de registros en la extracción CS
		No requiere parámetros
	*/
	@Query(value = "SELECT COUNT(*) CANT FROM IMT_TBL_TARIFAS_USO", nativeQuery = true)
	@Transactional(readOnly = true)
	public String obtenerCantidadRegistrosTarifaUso();	
	
}

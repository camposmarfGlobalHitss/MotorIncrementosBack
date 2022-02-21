package com.mit.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.Parametros;

@Repository
public interface IParametrosDao extends CrudRepository<Parametros, Long>{

	
	@Modifying
	@Transactional
	@Query(value = "update IMT_TBL_PARAMETROS set VALUE_PARAM = ?1 WHERE KEY_PARAM = 'Ruta_Archivo_PLM'", nativeQuery = true)
	public void guardarRuta(String ruta);
	
	
	/*
	@Luz.Obredor 14.02.2022
	método que consulta la ruta del archivo PLM
	*/
	@Query(value = "SELECT value_param FROM IMT_TBL_PARAMETROS WHERE key_param = 'Ruta_Archivo_PLM'", nativeQuery = true)
	@Transactional(readOnly = true)
	public String obtenerRutaArchivoPLM();	
	
	

}

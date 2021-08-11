package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mit.entitys.CalculoIncremento;

@Repository
public interface ICalculoIncrementoDao extends CrudRepository<CalculoIncremento, Long> {

	@Query(value = "SELECT NO_REFERENCIA, COD_ID, ID_CLIENTE, ID_TIP_DOC, IDEN_CLIE FROM IMT_TBL_CALCULO_INCREMENTO_V2 "
			+ "WHERE NO_REFERENCIA = ?1 AND ID_CLIENTE = ?2",nativeQuery = true)
	CalculoIncremento consultaEspecial(String NoReferencia, String idCliente);
	
	
	
	

	
	

}

package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.MovilRangosIncremento;
import com.mit.entitys.ParametrosIncrementoFija;

@Repository
public interface IParametrosIncrementosFija extends CrudRepository<ParametrosIncrementoFija, Long>{
	
	@Query(value = "SELECT *\r\n"
			+ "FROM imt_tbl_parametros_incremento\r\n"
			+ "WHERE VIGENCIA IN (\r\n"
			+ "    SELECT MAX(VIGENCIA) FROM imt_tbl_parametros_incremento\r\n"
			+ ")", nativeQuery = true)
	@Transactional(readOnly = true)
	List<ParametrosIncrementoFija> findAll();

}

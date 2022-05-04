package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.MovilRangosIncremento;

@Repository
public interface IMovilRangosIncrementoDao extends CrudRepository<MovilRangosIncremento, Long> {

	@Query(value = "SELECT *\r\n"
			+ "FROM imt_tbl_movil_rangos_incremento\r\n"
			+ "WHERE (to_char(FECHA_DESDE,'YYYY-MM-DD'), to_char(FECHA_HASTA,'YYYY-MM-DD')) IN (\r\n"
			+ "    SELECT to_char(MAX(FECHA_DESDE),'YYYY-MM-DD'), to_char(MAX(FECHA_HASTA),'YYYY-MM-DD') FROM imt_tbl_movil_rangos_incremento\r\n"
			+ ")", nativeQuery = true)
	@Transactional(readOnly = true)
	List<MovilRangosIncremento> findAll();
	
}

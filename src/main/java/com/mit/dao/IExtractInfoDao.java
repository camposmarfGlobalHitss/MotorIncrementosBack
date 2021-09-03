package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.Auditoria;

@Repository
public interface IExtractInfoDao extends CrudRepository<Auditoria, Long> {
	
	@Query(value="SELECT * \r\n" + 
			"FROM imt_tbl_auditoria\r\n" + 
			"WHERE ( nom_objeto, fec_proceso ) IN (SELECT nom_objeto, MAX(fec_proceso) FROM imt_tbl_auditoria WHERE\r\n" + 
			"nom_objeto IN ( 'imt_tbl_producto_oferta', 'imt_tbl_clientes', 'imt_tbl_contratos' ) GROUP BY nom_objeto ) " 
			, nativeQuery = true)
	@Transactional(readOnly = true)
	List<Auditoria> getStatsExtraccion() throws RuntimeException;
	
}

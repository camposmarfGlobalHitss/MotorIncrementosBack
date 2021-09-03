package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.Auditoria;

@Repository
public interface IAuditoriaDao extends CrudRepository<Auditoria, Long>{

	@Query(value="SELECT * FROM IMT_TBL_AUDITORIA ORDER BY FEC_PROCESO DESC", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<Auditoria> buscarAuditorias() throws Exception;

	@Query(value="select distinct nom_objeto from imt_tbl_auditoria au where nom_objeto like 'imt_tbl%'", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<String> getListaTablasAuditoria();

	@Query(value="SELECT * FROM IMT_TBL_AUDITORIA WHERE NOM_OBJETO = ?1 ORDER BY FEC_PROCESO DESC", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<Auditoria> filtroByTablas(String tabla);

	@Query(value = "SELECT * FROM (SELECT FEC_PROCESO FROM IMT_TBL_AUDITORIA WHERE UPPER(NOM_OBJETO) LIKE UPPER('%imt_tbl_movil_producto%') ORDER BY FEC_PROCESO DESC ) WHERE ROWNUM = 1\r\n", 
			nativeQuery = true)
	@Transactional(readOnly = true)
	public String obtenerUltimaActualizacionPSO();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE IMT_TBL_AUDITORIA SET NOM_USUARIO = ?1 WHERE ID_AUDITORIA = (SELECT MAX(ID_AUDITORIA) AS ID_AUDITORIA FROM IMT_TBL_AUDITORIA WHERE UPPER(NOM_OBJETO) LIKE UPPER('%imt_tbl_movil%'))", nativeQuery = true)
	public void actualizarUsuario(String usuario);
	
}

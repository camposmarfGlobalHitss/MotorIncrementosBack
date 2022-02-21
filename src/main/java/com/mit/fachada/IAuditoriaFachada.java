package com.mit.fachada;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mit.entitys.Auditoria;

public interface IAuditoriaFachada {
	
	List<Auditoria> getAuditoria() throws Exception;

	List<String> getTablasAuditora() throws Exception;

	List<Auditoria> filtroByTablas(String tabla) throws Exception;
	
	/*
		@Luz.Obredor 10.02.2022
		Declaración del método para guardar la cantidad de registros procesados de la tabla IMT_TBL_TARIFAS_USO
	 */
	ResponseEntity<Auditoria> guardarAuditoriaCS(String usuario) throws Exception;

}

package com.mit.fachada;

import java.util.List;

import com.mit.entitys.Auditoria;

public interface IAuditoriaFachada {
	
	List<Auditoria> getAuditoria() throws Exception;

	List<String> getTablasAuditora() throws Exception;

	List<Auditoria> filtroByTablas(String tabla) throws Exception;

}

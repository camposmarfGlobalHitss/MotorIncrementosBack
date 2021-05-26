package com.mit.fachada;

import java.util.List;

import com.mit.entitys.Auditoria;


public interface IExtractInfoFachada {

	List<Auditoria> getStatsExtraccion() throws Exception;

	String extractClientes() throws Exception;

	String extractContratos() throws Exception;

	String extractProductoOferta() throws Exception;

	

}

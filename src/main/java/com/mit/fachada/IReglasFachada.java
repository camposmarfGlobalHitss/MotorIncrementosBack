package com.mit.fachada;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mit.entitys.CalculoIncremento;
import com.mit.entitys.Reglas;
import com.sun.el.stream.Optional;

public interface IReglasFachada {

	String condicionesActuales() throws Exception;

	ResponseEntity<String> chequearCondicion(String condicion) throws Exception;

	Reglas crearCondicion(Reglas regla) throws Exception;

	ResponseEntity<List<Reglas>> listarCondiciones() throws Exception;

	ResponseEntity<String> actualizarCondicion(Reglas regla);

	ResponseEntity<String> borrarCondicion(Long id) throws Exception;

	ResponseEntity<String> extraccionCuentas() throws Exception;

	ResponseEntity<List<CalculoIncremento>> traerCuentasPostExtraccion() throws Exception;

	ResponseEntity<List<CalculoIncremento>> validarCondiciones() throws Exception;
	
	ResponseEntity<List<CalculoIncremento>> filtrarCorregidos() throws Exception;

}

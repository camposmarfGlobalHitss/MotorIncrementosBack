package com.mit.fachada;

import org.springframework.http.ResponseEntity;

public interface IInterfaces {

	ResponseEntity<String> generarRepCtasIncremento() throws Exception;

	ResponseEntity<String> genRepCtasNoCumpPolInc() throws Exception;

	ResponseEntity<String> genRepCtasSujetasIncremento() throws Exception;

	ResponseEntity<String> genRepCtasNoSujetasIncremento() throws Exception;

	ResponseEntity<String> generarRepCtrolFijaYMovil() throws Exception;

	ResponseEntity<String> generarInformePreIncremento() throws Exception;
	

}

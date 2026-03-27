package com.mit.fachada;

import java.io.ByteArrayInputStream;

import org.springframework.http.ResponseEntity;

public interface IInterfaces {

	ByteArrayInputStream generarRepCtasIncremento() throws Exception;

	ByteArrayInputStream genRepCtasNoCumpPolInc() throws Exception;

	ResponseEntity<String> genRepCtasSujetasIncremento() throws Exception;

	ByteArrayInputStream genRepCtasNoSujetasIncremento() throws Exception;

	ResponseEntity<String> generarRepCtrolFijaYMovil() throws Exception;

	ByteArrayInputStream generarInformePreIncremento() throws Exception;
	

}

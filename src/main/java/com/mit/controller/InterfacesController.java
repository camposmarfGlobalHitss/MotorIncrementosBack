package com.mit.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.fachada.IInterfaces;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/mit/interfaces")
public class InterfacesController {

	@Autowired
	private IInterfaces interfac;
	
	/*
		@Luz.Obredor 14.03.2022
		Controlador que retorna archivo plano construido de las cuentas con incremento
	 */
	@GetMapping("/genRepCtasIncremento")
	public ResponseEntity<InputStreamResource> genRepCtasIncremento(){
		try {
			String filename = "reporte_cuentas_con_incremento.csv";
		    InputStreamResource file = new InputStreamResource(interfac.generarRepCtasIncremento());
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	/*
		@Luz.Obredor 14.03.2022
		Controlador que retorna archivo plano de las cuentas que no cumplen con politicas de incremento
	 */
	@GetMapping("/genRepCtasNoCumpPolInc")
	public ResponseEntity<InputStreamResource> generarRepCtasNoCumplenPoliticas(){
		try {
			String filename = "reporte_cuentas_no_cumplen_politicas.csv";
		    InputStreamResource file = new InputStreamResource(interfac.genRepCtasNoCumpPolInc());
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/genRepCtasSujInc")
	public ResponseEntity<String> generarRepCtasSujetasIncremento(){
		try {
			return interfac.genRepCtasSujetasIncremento();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
		@Luz.Obredor 14.03.2022
		Controlador que retorna archivo plano de las cuentas no sujetas de incremento
	 */
	@GetMapping("/genRepCtasNoSujInc")
	public ResponseEntity<InputStreamResource> generarRepCtasNoSujetasIncremento(){
		try {
			String filename = "reporte_cuentas_no_sujetas_incremento.csv";
		    InputStreamResource file = new InputStreamResource(interfac.genRepCtasNoSujetasIncremento());
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/genRepCtrlFyM")
	public ResponseEntity<String> generarRepCtrolFijaYMovil(){
		try {
			return interfac.generarRepCtrolFijaYMovil();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
		@Luz.Obredor 14.03.2022
		Controlador que retorna archivo plano de informe de pre-incremento
	 */
	@GetMapping("/GenInfPreInc")
	public ResponseEntity<InputStreamResource> generarInformePreIncremento(){
		try {
			String filename = "reporte_pre_incremento.csv";
		    InputStreamResource file = new InputStreamResource(interfac.generarInformePreIncremento());
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
}
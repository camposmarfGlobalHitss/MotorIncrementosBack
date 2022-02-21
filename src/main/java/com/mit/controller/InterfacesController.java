package com.mit.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@GetMapping("/genRepCtasIncremento")
	public ResponseEntity<String> genRepCtasIncremento(){
		try {
			return interfac.generarRepCtasIncremento();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@GetMapping("/genRepCtasNoCumpPolInc")
	public ResponseEntity<String> generarRepCtasNoCumplenPoliticas(){
		try {
			return interfac.genRepCtasNoCumpPolInc();
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
	
	
	@GetMapping("/genRepCtasNoSujInc")
	public ResponseEntity<String> generarRepCtasNoSujetasIncremento(){
		try {
			return interfac.genRepCtasNoSujetasIncremento();
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
	
	
	@GetMapping("/GenInfPreInc")
	public ResponseEntity<String> generarInformePreIncremento(){
		try {
			return interfac.generarInformePreIncremento();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(InterfacesController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
}

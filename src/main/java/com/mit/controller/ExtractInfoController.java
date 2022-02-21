package com.mit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.entitys.Auditoria;

import com.mit.fachada.IExtractInfoFachada;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/mit/extractInfo")
public class ExtractInfoController {

	@Autowired
	private IExtractInfoFachada extractinfo;
	
	@GetMapping("/statsExtraccion")
	public List<Auditoria> getStatsExtraccion(){
		List<Auditoria> list = new ArrayList<>();
		try {
			list = extractinfo.getStatsExtraccion();
			return list;
			
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		return list;
	}
	
	
	@GetMapping("extractInfoClientes")
	public ResponseEntity<String> EjecutarExtracccionClientes() {
		try {
			String result = extractinfo.extractClientes();
			if(result.equals("OK")) {
				return new ResponseEntity<>("OK", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Error Extraccion Clientes", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
	}
	
	
	@GetMapping("extractInfoContratos")
	public ResponseEntity<String> EjecutarExtracccionContratos() {
		try {
			String result = extractinfo.extractContratos();
			if(result.equals("OK")) {
				return new ResponseEntity<>("OK", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Error Extraccion Contratos", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
	}
	
	
	@GetMapping("extractInfoProductoOFerta")
	public ResponseEntity<String> EjecutarExtracccionProductoOferta() {
		try {
			String result = extractinfo.extractProductoOferta();
			if(result.equals("OK")) {
				return new ResponseEntity<>("OK", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Error Extraccion Producto OFerta", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
	}
	

	/**
	 * <b>Nombre: </b> extractCsOffers </br>
	 * <b>Descripcion:</b> extractCsOffers </br>
	 * <b>Fecha Creacion:</b> 01/10/2020 </br>
	 * <b>Autor:</b> rodriguezorlb </br>
	 * <b>Fecha de ultima Modificacion: </b></br>
	 * <b>Modificado por: </b></br>
	 * <b>Brief: llamado al jar que ejecuta la extraccion de CsOffers</b></br>
	 */
	@GetMapping("extractCsOffers")
	public ResponseEntity<String> EjecutarExtracccionCsOffers() {
		try {
			String result = extractinfo.extractCsOffers();
			if(result.equals("OK")) {
				return new ResponseEntity<>("OK", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Error Extraccion CsOffers", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
	}
	

	
}

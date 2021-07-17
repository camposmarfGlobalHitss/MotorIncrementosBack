package com.mit.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.coyote.Response;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mit.entitys.Reglas;
import com.mit.fachada.IReglasFachada;
import com.sun.el.stream.Optional;

@RestController
@RequestMapping("mit/reglas")
public class ReglasController {

	@Autowired
	private IReglasFachada reglafac;
	
	
	@GetMapping("/condicionesActuales")
	public String getCondicionesActuales() {
		try {
			return reglafac.condicionesActuales();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
			return null;
			
		}
		
	}
	
	@GetMapping("/chequearCondicion")
	public ResponseEntity<String> chequearCondicion(@RequestParam(value = "condicion") String condicion) throws Exception{
		try {
			
			return reglafac.chequearCondicion(condicion);
		} catch (SQLGrammarException e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>("Error Interno",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	@PostMapping("/crearCondicion")
	public ResponseEntity<Reglas> crearCondicion(@RequestBody Reglas regla){
		try {			
			Reglas reglaOpc = reglafac.crearCondicion(regla);
			if(reglaOpc == null) {
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				return new ResponseEntity<Reglas>(reglaOpc,HttpStatus.OK);
			}
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarCondiciones")
	public ResponseEntity<List<Reglas>> listarCondiciones(){
		try {
			return reglafac.listarCondiciones();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/actualizarCondicion")
	public ResponseEntity<String> actualizarCondicion(@RequestBody Reglas regla){
		try {
			return reglafac.actualizarCondicion(regla);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/borrarCondicion")
	public ResponseEntity<String> borrarCondicion(@RequestParam(value = "id") Long id){
		try {
			return reglafac.borrarCondicion(id);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
}

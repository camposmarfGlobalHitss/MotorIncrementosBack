package com.mit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.coyote.Response;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.entitys.CalculoIncremento;
import com.mit.entitys.Exclusiones;
import com.mit.entitys.Reglas;
import com.mit.fachada.IReglasFachada;
import com.sun.el.stream.Optional;

import oracle.jdbc.proxy.annotation.Post;

@RestController
@RequestMapping("mit/reglas")
public class ReglasController {

	@Autowired
	private IReglasFachada reglafac;
	
	List<String> files = new ArrayList<>();
	private final Path rootLocation = Paths.get("_Path_To_Save_The_File");
	
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
	
	
	@GetMapping("/extraccionCuentas")
	public ResponseEntity<String> extraccionCuentas(){
		try {
			return reglafac.extraccionCuentas();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/calculoIncrementoPostExtraccion")
	public ResponseEntity<List<CalculoIncremento>> traerCuentasPostExtraccion(){
		try {
			return reglafac.traerCuentasPostExtraccion();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/validarCondiciones")
	public ResponseEntity<List<CalculoIncremento>> validarCondiciones(){
		try {
			return reglafac.validarCondiciones();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/savefile")
	   public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
	      String message;
	      try {
	         try {
	        	 
	        	 StringBuilder builder = new StringBuilder();
	        	 builder.append(System.getProperty("user.home"));
	        	 builder.append(File.separator);
	        	 builder.append("upload_file_spring_boot");
	        	 builder.append(File.separator);
	        	 builder.append(file.getOriginalFilename());
	        	 Path path = Paths.get(builder.toString());
	            Files.copy(file.getInputStream(), path);
	         } catch (Exception e) {
	            throw new RuntimeException("FAIL!");
	         }
	         files.add(file.getOriginalFilename());

	         message = "Successfully uploaded!";
	         return ResponseEntity.status(HttpStatus.OK).body(message);
	      } catch (Exception e) {
	         message = "Failed to upload!";
	         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	      }
	   }

	
	@GetMapping("/calculoIncrementoCorregido")
	public ResponseEntity<List<CalculoIncremento>> filtrarCorrgidos(){
		try {
			return reglafac.filtrarCorregidos();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(ReglasController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

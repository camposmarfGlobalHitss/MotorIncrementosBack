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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.entitys.Exclusiones;
import com.mit.entitys.MovilRangosIncremento;
import com.mit.entitys.ParametrosIncrementoFija;
import com.mit.entitys.Uvts;
import com.mit.fachada.ICalculoIncremento;
import com.sun.mail.iap.Response;

import java.util.logging.Logger;

@RestController
@RequestMapping("mit/calculo")
public class CalculoIncrementoController {
	
	
	@Autowired
	private ICalculoIncremento calculoFac;

	@PostMapping("/guardarExclusiones/{user}")
	public ResponseEntity<List<Exclusiones>> guardarExclusiones(@RequestParam("file") MultipartFile file, @PathVariable String user) throws IOException {
		try {
			if(file.isEmpty()) {
				return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
			}
			return calculoFac.guardarExclusiones(file, user);			
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
	
	
	@GetMapping("/obtenerMovilRangosIncrementos")
	public ResponseEntity<List<MovilRangosIncremento>> obtenerMovilRangosIncrementos(){
		try {
			return calculoFac.obtenerMovilRangosIncrementos();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
	
	@PostMapping("/guardarMovilRangoIncremento")
	public ResponseEntity<String> guardarMovilRangoIncremento(@RequestBody MovilRangosIncremento mri, @RequestParam(value = "user") String usuario){
		try {
			return calculoFac.guardarMRI(mri,usuario);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/borrarMovilRangoIncremento")
	public ResponseEntity<String> borrarMovilRangoIncremento(@RequestBody MovilRangosIncremento mri){
		try {
			return calculoFac.borrarMRI(mri);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/obtenerParametrosIncrementoFija")
	public ResponseEntity<List<ParametrosIncrementoFija>> obtenerParamentrosIncrementoFija(){
		try {
			return calculoFac.obtenerParamentrosIncrementoFija();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/guardarParametrosIncrementoFija")
	public ResponseEntity<String> guardarParametrosIncrementoFija(@RequestBody ParametrosIncrementoFija pif, @RequestParam(value = "user") String usuario ){
		try {
			return calculoFac.guardarPIF(pif,usuario);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/borrarParametrosIncrementoFija")
	public ResponseEntity<String> borrarParametrosIncrementoFija(@RequestBody ParametrosIncrementoFija pif){
		try {
			return calculoFac.borrarParametrosIncrementoFija(pif);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/obtenerValoresUvts")
	public ResponseEntity<List<Uvts>> obtenerValoresUvts(){
		try {
			return calculoFac.obtenerValoresUvts();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/guardarUvt")
	public ResponseEntity<String> guardarUvt(@RequestBody Uvts uvt, @RequestParam(value = "user") String usuario){
		try {
			return calculoFac.guardarUvt(uvt,usuario);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/borrarValorUvt")
	public ResponseEntity<String> borrarValorUvt(@RequestBody Uvts uvt){
		try {
			return calculoFac.borrarValorUvt(uvt);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/obtenerUltimaActualizacionProductoSubtipoOferta")
	public ResponseEntity<String> ultimaActualizacionProductoSubtipoOferta(){
		try {
			return calculoFac.obtenerUltimaActualizacionPSO();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ActualizacionPSO")
	public ResponseEntity<String> actualizarPso(@RequestParam(name = "user") String usuario){
		try {
			return calculoFac.actualizarPso(usuario);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CalculoIncrementoController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}

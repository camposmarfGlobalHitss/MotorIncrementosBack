package com.mit.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mit.fachada.IFileStorageService;

@RestController
@RequestMapping("/mit/file")
public class FileStorageController {

	private IFileStorageService storageService;
	 
	@Autowired
	public FileStorageController(IFileStorageService storageService) {
		this.storageService = storageService;
	}
	
	/*
		@Luz.Obredor 14.02.2022
		Método que permite la carga del archivo enviado y validado en el front para almacenarlo en el servidor.
		Si devuelve el método retorna true: es posible su carga, si retorna false: no puede cargarse debido 
		a que existe un archivo con el mismo nombre. Nuevamente se hace la validación del nombre del archivo
	*/
	@PostMapping("/upload")
	public ResponseEntity<Boolean> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			Pattern pat = Pattern.compile("^OUT_MTARIFF_BATCH_([0-2][0-9]|3[0-1])(0[1-9]|1[0-2])(\\d{4})([01]?[0-9]|2[0-3])[0-5][0-9]([0-5][0-9])?.csv$");
			Matcher mat = pat.matcher(file.getOriginalFilename());
			if (mat.find()) {
				storageService.save(file);
				return ResponseEntity.status(HttpStatus.OK).body(true);				
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
			}			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	/*
		@Luz.Obredor 15.02.2022
		Método que permite ejecutar el job que actualiza los estados de los contratos en exitoso o rechazado
	*/
	@GetMapping("/executeJob")
	public ResponseEntity<Boolean> executeJob() {		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(storageService.executeJob());	
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}		
	}

}
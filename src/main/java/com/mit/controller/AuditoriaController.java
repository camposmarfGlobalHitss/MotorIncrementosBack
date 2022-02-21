package com.mit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.entitys.Auditoria;
import com.mit.entitys.Exclusiones;
import com.mit.fachada.IAuditoriaFachada;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/mit/auditoria")
public class AuditoriaController{
	
	@Autowired
	private IAuditoriaFachada auditoriaFachada;
	
	@GetMapping("/getAuditoria")
	public List<Auditoria> getAuditoria(){
		List<Auditoria> list = new ArrayList<>();
		try {
			return auditoriaFachada.getAuditoria();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		return list;
		
	}
	
	@GetMapping("/getTablasAuditoria")
	public List<String> getTablasAuditoria(){
		try {
			return auditoriaFachada.getTablasAuditora();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
	}
	
	
	@GetMapping("/getFiltroByTabla")
	public List<Auditoria> getFiltroByTabla(@RequestParam(value = "tabla") String tabla){
		List<Auditoria> list = new ArrayList<>();
		try {
			return auditoriaFachada.filtroByTablas(tabla);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		return list;
	}
	
	/*
		@Luz.Obredor 10.02.2022
		Controller para guardar los registros generados del proceso de Charging System
		Requiere el usuario commo parámetro enviado directamente en la ruta
	 */
	@GetMapping("/guardarAuditoriaCS/{user}")
	public ResponseEntity<Auditoria> guardarAuditoria(@PathVariable String user) throws IOException {
		try {
			return auditoriaFachada.guardarAuditoriaCS(user);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

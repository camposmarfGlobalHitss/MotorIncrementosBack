package com.mit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mit.entitys.Auditoria;
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
	
}

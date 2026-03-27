package com.mit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mit.fachada.IBizFachada;

@RestController
@RequestMapping("/mit/Biz")
public class BizController {
	
	@Autowired
	private IBizFachada biz;
	
	@GetMapping("/getListaIncrementados")
	public List<String> getListaIncrementados(){

		List<String> list = new ArrayList<>();
		try {
			list = biz.getListaIncrementados();
			
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		return list;
	}

}

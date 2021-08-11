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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.entitys.Exclusiones;
import com.mit.fachada.ICalculoIncremento;

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
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
	
}

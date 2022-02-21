package com.mit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.entitys.Usuario;
import com.mit.fachada.IUsuarioFachada;

@RestController
@RequestMapping("/mit/admin/usuarios")
public class UsuarioController {
	
	
	@Autowired
	private IUsuarioFachada usuarioFachada;
	
	@GetMapping("/getusuarios")
	public List<Usuario> getUsuarios(){
		List<Usuario> list = new ArrayList<>();
		try {
			list = usuarioFachada.getUsuarios();
			return list;
		} catch (Exception e) {
			Logger logger = Logger.getLogger(UsuarioController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
		}
		return list;
	}
	
	
	@PostMapping("/updateUsuario")
	public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario) {
		try {
			ResponseEntity<String> resp = usuarioFachada.updateUsuario(usuario);
			return resp;
		} catch (Exception e) {
			Logger logger = Logger.getLogger(UsuarioController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>("Error "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	@PostMapping("/createUsuario")
	public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) {
		try {
			ResponseEntity<String> resp = usuarioFachada.createUsuario(usuario);
			return resp;
		} catch (Exception e) {
			Logger logger = Logger.getLogger(UsuarioController.class.getName());
			logger.log(Level.SEVERE, e.getMessage());
			return new ResponseEntity<>("Error "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

}

package com.mit.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.Controller;

import com.mit.entitys.Usuario;
import com.mit.fachada.ILoginFachada;
import com.mit.utils.EncodeDecodePass;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/mit")
public class LoginController {
	
	@Autowired
	private ILoginFachada loginFachada;
	
	
	
	@GetMapping("/usuarios")
	public ResponseEntity<Usuario> getUsuario(@RequestParam(value = "usuario") String usuario) {
		
		Usuario user = new Usuario();
		EncodeDecodePass utils = new EncodeDecodePass();
		
		try {
			
			user = loginFachada.getUsuario(usuario);
			if(user != null) {
				if(user.getEstado()==1) {
					String contrasenaEncode = user.getContrasena();
					user.setContrasena(utils.desencriptar(contrasenaEncode)); 
					return ResponseEntity.ok(user);
				}else {
					return ResponseEntity.notFound().build();
				}
				
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			
			Logger logger = Logger.getLogger(LoginController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return ResponseEntity.ok(user);
		
		
	}
	
	@PutMapping("/actualizarUsuario")
	public ResponseEntity<Usuario> putCambioClave(@RequestBody Usuario usuario){
		Usuario user = new Usuario();
		try {
			
			user = loginFachada.actualizarUSuario(usuario);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(LoginController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		return ResponseEntity.ok(user);
	}
	
	
	
	@PostMapping("/crearUsuario")
	public ResponseEntity<Usuario> postCrearUSuario(@RequestBody Usuario usuario){
		Usuario user = new Usuario();
		try {
			user = loginFachada.crearUsuario(usuario);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(LoginController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
	}
	
	
	
	
	///servicio que desencripta una cadena de texto AES -- retorna el texto desencriptado 
	@GetMapping("/decode")
	public String desencriptar(@RequestParam(value = "texto") String texto) {
		
		EncodeDecodePass utils = new EncodeDecodePass();
		
		return utils.desencriptar(texto);
	}
	
	///servicio que encripta una cadena de texto con algoritmo AES -- retorna el texto encriptado
	@GetMapping("/encode")
	public String encriptar(@RequestParam(value = "texto") String texto) {
		
		EncodeDecodePass utils = new EncodeDecodePass();		
		return utils.Encriptar(texto);
	}
	
	@GetMapping("/ejecutarJob")
	public String ejecutarjob() {
		String retorno = "";
		try {
			
			retorno=  loginFachada.ejecutarJob();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return retorno;
		
	}
	
	@GetMapping("/sendEmail")
	public String sendEmail(@RequestParam(value = "email") String email,@RequestParam(value = "subject") String subject, @RequestParam(value = "contenido") String content){
		
		try {
			return loginFachada.sendEmail(email,subject,content);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
	}
	
	@GetMapping("/verificarCodigo")
	public ResponseEntity<Usuario> verificarCodigo(@RequestParam(value = "codigo") String code){
		Usuario user = new Usuario();
		try {
			user = loginFachada.verificarUsuario(code);
			if(user == null) {
				return ResponseEntity.notFound().build();
			}else {
				return ResponseEntity.ok(user);
			}
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
		
	}
	
	@GetMapping("/olvidoPass")
	public boolean olvidoPass(@RequestParam(value="correo") String correo) {
		try {
			return loginFachada.olvidoPass(correo);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	

}

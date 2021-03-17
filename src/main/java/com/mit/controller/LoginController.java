package com.mit.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mit.entitys.Usuario;
import com.mit.fachada.ILoginFachada;
import com.mit.utils.EncodeDecodePass;


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
				String contrasenaEncode = user.getContrasena();
				user.setContrasena(utils.Desencriptar(contrasenaEncode)); 
				return ResponseEntity.ok(user);
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage().toString());
		}
		return ResponseEntity.ok(user);
		
		
	}
	
	
	
	
	///servicio que desencripta una cadena de texto AES -- retorna el texto desencriptado 
	@GetMapping("/decode")
	public String desencriptar(@RequestParam(value = "texto") String texto) {
		
		EncodeDecodePass utils = new EncodeDecodePass();
		String decodificada = utils.Desencriptar(texto);
		
		return decodificada;
	}
	
	///servicio que encripta una cadena de texto con algoritmo AES -- retorna el texto encriptado
	@GetMapping("/encode")
	public String encriptar(@RequestParam(value = "texto") String texto) {
		
		EncodeDecodePass utils = new EncodeDecodePass();
		String encode = utils.Encriptar(texto);
		
		return encode;
	}
	
	@GetMapping("/ejecutarJob")
	public String ejecutarjob() {
		String retorno = "";
		try {
			
			retorno=  loginFachada.ejecutarJob();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retorno;
		
	}
	
	
	
	
	
	
	

}

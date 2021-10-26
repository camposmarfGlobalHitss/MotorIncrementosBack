package com.mit.fachadaimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mit.dao.IUsuarioDao;
import com.mit.entitys.Usuario;
import com.mit.fachada.IUsuarioFachada;
import com.mit.utils.Constantes;
import com.mit.utils.EncodeDecodePass;
import com.sun.xml.bind.v2.schemagen.Util;

import net.bytebuddy.utility.RandomString;

@Service
public class UsuarioFachadaImpl implements IUsuarioFachada {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
    private JavaMailSender mailSender;

	
	@Override
	public List<Usuario> getUsuarios() throws Exception {
		List<Usuario> list  = new ArrayList<>();
		list = usuarioDao.getUsuarios();
		return list;
	}

	@Override
	public ResponseEntity<String> updateUsuario(Usuario usuario) throws Exception {
		Usuario user = new Usuario();
		if(usuario.getEstado()==4) {
			usuario.setFecfinusuario(new Date());
			user = usuarioDao.save(usuario);
		}else {
			Optional<Usuario> searchedUser = usuarioDao.findById(usuario.getId());
			if(searchedUser.get().getContrasena().equals(usuario.getContrasena())) {
				usuario.setCodigoverificacion(RandomString.make(64));
				user = usuarioDao.save(usuario);			
			}else {
				EncodeDecodePass util = new EncodeDecodePass();
				usuario.setContrasena(util.Encriptar(usuario.getContrasena()));
				usuario.setCodigoverificacion(RandomString.make(64));
				user = usuarioDao.save(usuario);
			}
			
		}
		
		if(user.getId() != null) {
			return new ResponseEntity<>("ok", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Error servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> createUsuario(Usuario usuario) throws Exception {
		Usuario user = new Usuario();
		EncodeDecodePass utils = new EncodeDecodePass();
		if(usuario != null) {
			usuario.setContrasena(utils.Encriptar(usuario.getContrasena()));
			usuario.setDescUsuario(usuario.getDescUsuario()==null ? "Usuario ST": usuario.getDescUsuario());
			usuario.setFeciniusuario(new Date());
			usuario.setCodigoverificacion(RandomString.make(64));
			String resp =  sendEmailVerification(usuario);
			if(resp.equals("ok")) {
				user = usuarioDao.save(usuario);
				if(user != null) {
					return new ResponseEntity<>("ok", HttpStatus.OK);								
				}else {
					return new ResponseEntity<>("Error al crear usuario", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else {
				return new ResponseEntity<>("Error al enviar email", HttpStatus.INTERNAL_SERVER_ERROR);
			}				
		}else {
			return new ResponseEntity<>("Error servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	public String sendEmailVerification(Usuario user) throws Exception {
		
		String content = "<h1>VERIFICACION CUENTA</h1>";
		content += "<hr>";
		content += "<p>Cordial Saludo "+user.getNombrecompleto()+" </p>";
		content += "<p>Usted se ha registrado al Motor de incrementos para verificar su usuario por favor de clic en el siguiente enlace: </>";
		content += "<h3><a href="+Constantes.getUrlbase()+Constantes.getPuertoAngular()+"/verificarUsuario/"+user.getCodigoverificacion()+">ENLACE VERIFICACION</a></h3>";
		content += "<hr>";
		content += "<p>Cordialmente <br> Equipo IMT<p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("motorincremento@gmail.com","Equipo IMT");
		helper.setTo(user.getCorreo());
		helper.setSubject("Por favor verifique su cuenta");
		helper.setText(content, true);
		
		


        mailSender.send(message);
		return "ok";
	}

}

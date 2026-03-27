package com.mit.fachadaimpl;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mit.controller.AuditoriaController;
import com.mit.dao.ILoginDao;
import com.mit.entitys.Usuario;
import com.mit.fachada.ILoginFachada;
import com.mit.utils.Constantes;
import com.mit.utils.EncodeDecodePass;

import local_project.job_rep_cta_sujetos_incrementos_0_1.Job_Rep_Cta_Sujetos_Incrementos;
import net.bytebuddy.utility.RandomString;


@Service
public class LoginFachadaImpl implements ILoginFachada {
	
	@Autowired
	private ILoginDao loginDao;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public Usuario getUsuario(String usuario) throws Exception {
		Usuario user = new Usuario();
		
		if(usuario == null) {
			return user;
		}
		
		
		user= loginDao.findByUsername(usuario);
		
		return user;
	}

	@Override
	public String ejecutarJob() throws Exception {
		
		Job_Rep_Cta_Sujetos_Incrementos job = new Job_Rep_Cta_Sujetos_Incrementos();
		
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode==0) {
			return "ARCHIVO DE EXCEL GENERADO CORRECTAMENTE";
		}else {
			return "Error en la ejecucion, intenta mas tarde";
		}
		
		

	}

	@Override
	public Usuario actualizarUSuario(Usuario usuario) throws Exception {
		try {
			EncodeDecodePass utils = new EncodeDecodePass();
			usuario.setContrasena(utils.Encriptar(usuario.getContrasena()));
			usuario.setCodigoverificacion(RandomString.make(64));			
			return loginDao.save(usuario);
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		return null;
		
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) throws Exception {
		EncodeDecodePass utils = new EncodeDecodePass();
		usuario.setDescUsuario("Usuario ST");
		usuario.setContrasena(utils.Encriptar(usuario.getContrasena()));
		usuario.setFeciniusuario(new Date());
		usuario.setEstado(2);
		usuario.setCodperfil(2);
		usuario.setCodigoverificacion(RandomString.make(64));
		usuario.setObservaciones("Sin Observacion");
		String resp = sendEmailVerification(usuario);
		if(resp.equals("ok")) {
			return loginDao.save(usuario);			
		}else {
			return null;
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

	@Override
	public String sendEmail(String Toemail, String subject, String content) throws Exception {
		SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(Toemail);
        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);
		return "ok";
	}

	@Override
	public Usuario verificarUsuario(String code) throws Exception {
		Usuario user = new Usuario();
		user = loginDao.verificarUsuario(code);
		
		if(user == null) {
			return null;
		}else {
			String newcode = RandomString.make(64);
			loginDao.cambiarCodigoVerificacion(newcode,user.getId());
			loginDao.activarUsuario(user.getId());
			return user;
		}
	}

	@Override
	public boolean olvidoPass(String correo) {
		
		try {
			Usuario user = new Usuario();
			user = loginDao.findByCorreo(correo);
			if(user.getId() != null) {
				String resp = sendRecoveryEmail(user);
				if(resp.equals("ok")) {
					return true;
				}
			}
		} catch (Exception e) {
			Logger logger = Logger.getLogger(AuditoriaController.class.getName());
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		
		return false;
	}
	
	
	
	public String sendRecoveryEmail(Usuario user) throws Exception  {
		
		String content = "<h1>CAMBIO DE CONTRASEÑA</h1>";
		content += "<hr>";
		content += "<p>Cordial Saludo "+user.getNombrecompleto()+" </p>";
		content += "<p>ah solicitado cambio de contraseña por favor de clic en el siguiente enlace: </>";
		content += "<h3><a href="+Constantes.getUrlbase()+Constantes.getPuertoAngular()+"/olvidoPass/"+user.getCodigoverificacion()+">ENLACE RESTAURACION CONTRASEÑA</a></h3>";
		content += "<br>";
		content += "<p>Tenga en cuenta que si no solicito este cambio de contraseña haga caso omiso a este mensaje, e ingrese a la plataforma y dentro de ella seccion perfil cambie la contraseña por seguridad";
		content += "<hr>";
		content += "<p>Cordialmente <br> Equipo IMT<p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("motorincremento@gmail.com","Equipo IMT - Cambio Contraseña");
		helper.setTo(user.getCorreo());
		helper.setSubject("Correo Automatico Cambio Contraseña");
		helper.setText(content, true);
		
		mailSender.send(message);
		 
		return "ok";
	}
	
	

	

	

}

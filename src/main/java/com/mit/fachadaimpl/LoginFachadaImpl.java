package com.mit.fachadaimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dao.ILoginDao;
import com.mit.entitys.Usuario;
import com.mit.fachada.ILoginFachada;

import local_project.job_rep_cta_sujetos_incrementos_0_1.Job_Rep_Cta_Sujetos_Incrementos;


@Service
public class LoginFachadaImpl implements ILoginFachada {
	
	@Autowired
	private ILoginDao loginDao;
	
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
//		System.out.println("antes de ejecutar la sentencia...");
//		
//		Runtime.getRuntime().exec("cmd /c start C:\\Users\\camposmarf\\Desktop\\AAAA\\Job_Rep_Cta_Sujetos_Incrementos_0.1\\Job_Rep_Cta_Sujetos_Incrementos\\Job_Rep_Cta_Sujetos_Incrementos_run.bat");
//		
		
		Job_Rep_Cta_Sujetos_Incrementos job = new Job_Rep_Cta_Sujetos_Incrementos();
		String[][] retorno = job.runJob(new String[] {});		
		
		if(retorno.length>0) {
			return "REPORTE GENERADO CON EXITO";		
		}else {
			return "Error";
		}
		

	}

	

	

}

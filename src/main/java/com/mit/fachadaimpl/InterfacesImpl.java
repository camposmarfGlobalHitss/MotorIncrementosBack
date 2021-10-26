package com.mit.fachadaimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mit.fachada.IInterfaces;

import local_project.job_informe_pre_incremento_0_1.Job_Informe_Pre_Incremento;
import local_project.job_rep_cta_sujetos_incrementos_0_1.Job_Rep_Cta_Sujetos_Incrementos;
import local_project.job_rep_ctas_incrementos_0_1.Job_Rep_Ctas_Incrementos;
import local_project.job_rep_ctas_no_cumplen_politicas_incremento_0_1.job_Rep_Ctas_No_Cumplen_Politicas_Incremento;
import local_project.job_rep_ctas_no_sujetas_incremento_0_1.Job_Rep_Ctas_No_Sujetas_Incremento;
import local_project.job_repcontrol_fija_movil_0_1.Job_RepControl_Fija_Movil;


@Service
public class InterfacesImpl implements IInterfaces{

	@Override
	public ResponseEntity<String> generarRepCtasIncremento() throws Exception {
		Job_Rep_Ctas_Incrementos job = new Job_Rep_Ctas_Incrementos();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode==0) {
			return new ResponseEntity<String>("Reporte Cuentas Incremento generado correctamente en carpeta descargas",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error en la generacion del reporte",HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> genRepCtasNoCumpPolInc() throws Exception {
		job_Rep_Ctas_No_Cumplen_Politicas_Incremento job = new job_Rep_Ctas_No_Cumplen_Politicas_Incremento();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode==0) {
			return new ResponseEntity<String>("Reporte de Cuentas no Incrementadas generado con exito en carpeta de descargas",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error en la generacion del reporte",HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> genRepCtasSujetasIncremento() throws Exception {
		Job_Rep_Cta_Sujetos_Incrementos job = new Job_Rep_Cta_Sujetos_Incrementos();		
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode==0) {
			return new ResponseEntity<String>("Reporte Cuentas Sujetas a Incremento Generado Correctamente en Descargas",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error en la generacion del reporte",HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> genRepCtasNoSujetasIncremento() throws Exception {
		Job_Rep_Ctas_No_Sujetas_Incremento job = new Job_Rep_Ctas_No_Sujetas_Incremento();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode==0) {
			return new ResponseEntity<String>("Reporte Cuentas No Sujetas a Incremento Generado Correctamente en Descargas",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error en la generacion del reporte",HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> generarRepCtrolFijaYMovil() throws Exception {
		Job_RepControl_Fija_Movil job = new Job_RepControl_Fija_Movil();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode==0) {
			return new ResponseEntity<String>("Reporte Control Fija y Movil Generado Correctamente en Carpeta Descargas",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error en la generacion del reporte",HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> generarInformePreIncremento() throws Exception {
		Job_Informe_Pre_Incremento job = new Job_Informe_Pre_Incremento();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode==0) {
			return new ResponseEntity<String>("Informe Pre Incremento generado correctamente",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error en la generacion del reporte",HttpStatus.OK);
		}
		
	}
	
	

}

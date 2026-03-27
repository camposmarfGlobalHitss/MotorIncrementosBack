package com.mit.fachadaimpl;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mit.dao.ICalculoIncrementoDao;
import com.mit.dao.IParametrosDao;
import com.mit.fachada.IInterfaces;
import com.mit.utils.CSVHelper;

import local_project.job_informe_pre_incremento_0_1.Job_Informe_Pre_Incremento;
import local_project.job_rep_cta_sujetos_incrementos_0_1.Job_Rep_Cta_Sujetos_Incrementos;
import local_project.job_rep_ctas_no_cumplen_politicas_incremento_0_1.job_Rep_Ctas_No_Cumplen_Politicas_Incremento;
import local_project.job_rep_ctas_no_sujetas_incremento_0_1.Job_Rep_Ctas_No_Sujetas_Incremento;
import local_project.job_repcontrol_fija_movil_0_1.Job_RepControl_Fija_Movil;

@Service
public class InterfacesImpl implements IInterfaces {

	@Autowired
	private ICalculoIncrementoDao calculoIncrementoDao;

	@Autowired
	private IParametrosDao parametroDao;
	
	/*
 		@Luz.Obredor 09.03.2022
 		Se modifica logica de construcción de archivo plano del reporte de cuentas con incremento
	 */
	@Override
	public ByteArrayInputStream generarRepCtasIncremento() throws Exception {
		return CSVHelper.queryToCSV(calculoIncrementoDao.consultaCuentasConIncremento());
	}
	
	/*
		@Luz.Obredor 09.03.2022
		Se modifica logica de construcción de archivo plano del reporte de cuentas sin incremento
	 */
	@Override
	public ByteArrayInputStream genRepCtasNoCumpPolInc() throws Exception {
		return CSVHelper.queryToCSV(calculoIncrementoDao.consultaCuentasNoIncrementadas());
	}

	@Override
	public ResponseEntity<String> genRepCtasSujetasIncremento() throws Exception {
		Job_Rep_Cta_Sujetos_Incrementos job = new Job_Rep_Cta_Sujetos_Incrementos();
		int exitCode = job.runJobInTOS(new String[] {});
		if (exitCode == 0) {
			return new ResponseEntity<String>(
					"Reporte Cuentas Sujetas a Incremento Generado Correctamente en Descargas", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error en la generacion del reporte", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
		@Luz.Obredor 09.03.2022
		Se modifica logica de construcción de archivo plano del reporte de cuentas no sujetas a incremento
	 */
	@Override
	public ByteArrayInputStream genRepCtasNoSujetasIncremento() throws Exception {
		return CSVHelper.queryToCSV(calculoIncrementoDao.consultaNoSujetasIncremento());
	}

	@Override
	public ResponseEntity<String> generarRepCtrolFijaYMovil() throws Exception {
		Job_RepControl_Fija_Movil job = new Job_RepControl_Fija_Movil();
		int exitCode = job.runJobInTOS(new String[] {});
		if (exitCode == 0) {
			return new ResponseEntity<String>(
					"Reporte Control Fija y Movil Generado Correctamente en Carpeta Descargas", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error en la generacion del reporte", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
		@Luz.Obredor 09.03.2022
		Se modifica logica de construcción de archivo plano del archivo de pre incremento
	 */
	@Override
	public ByteArrayInputStream generarInformePreIncremento() throws Exception {	
	    return CSVHelper.queryToCSV(calculoIncrementoDao.consultaPreIncremento());
	}

}

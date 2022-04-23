package com.mit.fachada;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.mit.entitys.CalculoIncremento;
import com.mit.entitys.Exclusiones;
import com.mit.entitys.MovilRangosIncremento;
import com.mit.entitys.ParametrosCalculoFija;
import com.mit.entitys.ParametrosCalculoMovil;
import com.mit.entitys.ParametrosIncrementoFija;
import com.mit.entitys.Uvts;
import com.mit.utils.CantidadesEstados;

public interface ICalculoIncremento {

	ResponseEntity<List<Exclusiones>> guardarExclusiones(MultipartFile file, String user) throws IOException;

	ResponseEntity<List<MovilRangosIncremento>> obtenerMovilRangosIncrementos() throws Exception;

	ResponseEntity<String> guardarMRI(MovilRangosIncremento mri, String usuario) throws Exception;

	ResponseEntity<String> borrarMRI(MovilRangosIncremento mri) throws Exception;

	ResponseEntity<List<ParametrosIncrementoFija>> obtenerParamentrosIncrementoFija() throws Exception;

	ResponseEntity<String> borrarParametrosIncrementoFija(ParametrosIncrementoFija pif) throws Exception;

	ResponseEntity<String> guardarPIF(ParametrosIncrementoFija pif, String usuario) throws Exception;

	ResponseEntity<List<Uvts>> obtenerValoresUvts() throws Exception;

	ResponseEntity<String> guardarUvt(Uvts uvt, String usuario) throws Exception;

	ResponseEntity<String> borrarValorUvt(Uvts uvt) throws Exception;

	ResponseEntity<String> obtenerUltimaActualizacionPSO() throws Exception;

	ResponseEntity<String> actualizarPso(String usuario) throws Exception;

	ResponseEntity<String> ejecutarCIM(ParametrosCalculoMovil calculoMovil) throws Exception;

	ResponseEntity<String> ejecutarCIF(ParametrosCalculoFija calculoFija)throws Exception;

	ResponseEntity<String> generarArchivoPLM() throws Exception;

	List<String> prueba2() throws Exception;
	
	ResponseEntity<String> generarArchivoPLMCorregido() throws Exception;
	
	ResponseEntity<List<CantidadesEstados>> calculoEstados() throws Exception;
	
	ResponseEntity<List<CalculoIncremento>> calculoPorEstados(String estado) throws Exception;
	
	ResponseEntity<List<BigDecimal>> estratosCFM() throws Exception;
	
	ResponseEntity<List<CalculoIncremento>> cfmPorEstrato(Integer estrato) throws Exception;
	
	ResponseEntity<List<CantidadesEstados>> cuentasIncNoInc() throws Exception;
	
	ResponseEntity<List<CalculoIncremento>> cuentasIncNoInc(String tipo) throws Exception;
	
	ResponseEntity<List<String>> contratosDANE() throws Exception;
	
	ResponseEntity<List<String>> variacionPreincremento() throws Exception;
	
}

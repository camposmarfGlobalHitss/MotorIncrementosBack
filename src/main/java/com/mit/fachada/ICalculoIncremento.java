package com.mit.fachada;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.mit.entitys.Exclusiones;
import com.mit.entitys.MovilRangosIncremento;
import com.mit.entitys.ParametrosIncrementoFija;
import com.mit.entitys.Uvts;

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
	
	

}

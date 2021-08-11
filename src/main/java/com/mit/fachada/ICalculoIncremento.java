package com.mit.fachada;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.mit.entitys.Exclusiones;

public interface ICalculoIncremento {

	ResponseEntity<List<Exclusiones>> guardarExclusiones(MultipartFile file, String user) throws IOException;
	
	

}

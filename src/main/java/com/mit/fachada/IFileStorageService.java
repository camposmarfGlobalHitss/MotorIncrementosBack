package com.mit.fachada;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/*
	@Luz.Obredor 11.02.2022
	Interfaz de métodos para manejo de archivos
*/
public interface IFileStorageService {
	
	public void init();
	
	public void save(MultipartFile file);
	
	public Resource load(String filename);
	
	public void deleteAll();
	
	public Stream<Path> loadAll();
	
	public Boolean executeJob() throws Exception;
	
}

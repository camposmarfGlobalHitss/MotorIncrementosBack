package com.mit.fachadaimpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mit.dao.IParametrosDao;
import com.mit.fachada.IFileStorageService;

import imt.job_cargue_log_proceso_incremento_0_1.Job_Cargue_Log_Proceso_Incremento;


@Service
public class FileStorageServiceImpl implements IFileStorageService {

	private Path root;

	@Autowired
	private IParametrosDao parametro;

	@PostConstruct
	public void FileSystemStorageService() {
		this.root = Paths.get(parametro.obtenerRutaArchivoPLM().toString());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	/*
	 	@Luz.Obredor 14.02.2022 
	 	Método que almacena el archivo en la ruta dada
	 */
	@Override
	public void save(MultipartFile file) {
		try {
			parametro.actualizarNombreArchivoSalida(file.getOriginalFilename());
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
	
	/*
	 	@Luz.Obredor 15.02.2022 
	 	Método que ejecuta job de talend de incremento
	 */
	@Override
	public Boolean executeJob() throws Exception {
		Job_Cargue_Log_Proceso_Incremento job = new Job_Cargue_Log_Proceso_Incremento();
		int code = job.runJobInTOS(new String[] {});
		if(code == 0) {
			return true;
		}else {
			return false;
		}
		
	}


}
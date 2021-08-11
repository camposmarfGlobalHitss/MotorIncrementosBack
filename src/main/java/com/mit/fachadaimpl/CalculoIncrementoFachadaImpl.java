package com.mit.fachadaimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mit.dao.ICalculoIncrementoDao;
import com.mit.dao.IExclusionesDao;
import com.mit.entitys.Exclusiones;
import com.mit.fachada.ICalculoIncremento;

@Service
public class CalculoIncrementoFachadaImpl implements ICalculoIncremento {
	
	@Autowired
	private IExclusionesDao exclusionesDao;

	@Override
	public ResponseEntity<List<Exclusiones>> guardarExclusiones(MultipartFile file, String user) throws IOException {
		exclusionesDao.deleteAll();
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		Path path = Paths.get(convFile.getPath());
		List<Exclusiones> list = new ArrayList<Exclusiones>();
		Stream<String> streamFile = Files.lines(path);
				list = streamFile.map(linea -> linea.split(",")).filter(p-> !p[0].equalsIgnoreCase("nombre")).map(arreglo->{
					if(!arreglo[0].equalsIgnoreCase("nombre") ) {
						Exclusiones exc = new Exclusiones(arreglo[0].toString(), Integer.parseInt(arreglo[1].toString()), Long.parseLong(arreglo[2].toString()), new Date(), "mcampos");
						return exc;
					}			
					return null;
				}).collect(Collectors.toList());
				for (Exclusiones exclusiones : list) {
					exclusionesDao.save(exclusiones);
				}
		return new ResponseEntity<List<Exclusiones>>(list,HttpStatus.OK);
	}

	
	
}

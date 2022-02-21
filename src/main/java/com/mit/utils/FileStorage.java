package com.mit.utils;

/*
	@Luz.Obredor 11.02.2022
	Clase para la carga de archivos en el servidor de aplicaciones
*/
public class FileStorage {

	private String name;
	private String url;
	
	public FileStorage(String name, String url) {
		this.name = name;
		this.url = url;
	}
  
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return this.url;
	}
  
	public void setUrl(String url) {
		this.url = url;
	}

}

package com.mit.utils;

public class Constantes {

	static final String URLBASE = "http://localhost:";
	static final String PUERTO_ANGULAR = "4200";
	static final String PUERTO_BACK = "8080";
	
	
	private Constantes() {
		throw new IllegalStateException("Utility class");
	}

	
	
	public static String getUrlbase() {
		return URLBASE;
	}



	public static String getPuertoAngular() {
		return PUERTO_ANGULAR;
	}



	public static String getPuertoBack() {
		return PUERTO_BACK;
	}


}

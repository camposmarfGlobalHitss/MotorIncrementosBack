package com.mit.entitys;

public class ParametrosCalculoFija {
	
	
	private double iva_oficial;
	
	private double incremento_maximo;
	
	private int frecuencia_commit;
	
	private String estado_a_calcular;
	
	

	public double getIva_oficial() {
		return iva_oficial;
	}

	public void setIva_oficial(double iva_oficial) {
		this.iva_oficial = iva_oficial;
	}

	public double getIncremento_maximo() {
		return incremento_maximo;
	}

	public void setIncremento_maximo(double incremento_maximo) {
		this.incremento_maximo = incremento_maximo;
	}

	public int getFrecuencia_commit() {
		return frecuencia_commit;
	}

	public void setFrecuencia_commit(int frecuencia_commit) {
		this.frecuencia_commit = frecuencia_commit;
	}

	public String getEstado_a_calcular() {
		return estado_a_calcular;
	}

	public void setEstado_a_calcular(String estado_a_calcular) {
		this.estado_a_calcular = estado_a_calcular;
	}
	
	
	
	

}

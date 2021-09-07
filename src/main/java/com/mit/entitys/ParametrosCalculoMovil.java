package com.mit.entitys;

public class ParametrosCalculoMovil {
	
	private double  iva;
	
	private double  impoconsumo;
	
	private double porcion_datos;
	
	private double porcion_voz;
	
	private int unidad_redondeo;
	
	private int vigencia_uvt;
	
	private double incremento_usos_por_defecto;
	
	private String tipo_redondeo_usos;
	
	private int frecuencia_commit;
	
	private String estado_a_calcular;

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getImpoconsumo() {
		return impoconsumo;
	}

	public void setImpoconsumo(double impoconsumo) {
		this.impoconsumo = impoconsumo;
	}

	public double getPorcion_datos() {
		return porcion_datos;
	}

	public void setPorcion_datos(double porcion_datos) {
		this.porcion_datos = porcion_datos;
	}

	public double getPorcion_voz() {
		return porcion_voz;
	}

	public void setPorcion_voz(double porcion_voz) {
		this.porcion_voz = porcion_voz;
	}

	public int getUnidad_redondeo() {
		return unidad_redondeo;
	}

	public void setUnidad_redondeo(int unidad_redondeo) {
		this.unidad_redondeo = unidad_redondeo;
	}

	public int getVigencia_uvt() {
		return vigencia_uvt;
	}

	public void setVigencia_uvt(int vigencia_uvt) {
		this.vigencia_uvt = vigencia_uvt;
	}

	public double getIncremento_usos_por_defecto() {
		return incremento_usos_por_defecto;
	}

	public void setIncremento_usos_por_defecto(double incremento_usos_por_defecto) {
		this.incremento_usos_por_defecto = incremento_usos_por_defecto;
	}

	public String getTipo_redondeo_usos() {
		return tipo_redondeo_usos;
	}

	public void setTipo_redondeo_usos(String tipo_redondeo_usos) {
		this.tipo_redondeo_usos = tipo_redondeo_usos;
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

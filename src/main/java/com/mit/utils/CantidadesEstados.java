package com.mit.utils;

public class CantidadesEstados {
	
	private long cantidad;
	private String estado;
	private String descripcion;
	
	public CantidadesEstados() {
		super();	
	}
	
	public CantidadesEstados(long cantidad, String estado) {
		super();
		this.cantidad = cantidad;
		this.estado = estado; 
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

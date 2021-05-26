package com.mit.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatsExtraccion {
	
	@Id
	@Column(name="NOM_OBJETO")
	private String nombreObjeto;
	
	@Column(name="NOM_USUARIO")
	private String nombreUsuario;
	
	@Column(name="FEC_PROCESO")
	private Date fechaProceso;


	public String getNombreObjeto() {
		return nombreObjeto;
	}


	public void setNombreObjeto(String nombreObjeto) {
		this.nombreObjeto = nombreObjeto;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public Date getFechaProceso() {
		return fechaProceso;
	}


	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	

}

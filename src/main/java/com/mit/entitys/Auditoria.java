package com.mit.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IMT_TBL_AUDITORIA")
public class Auditoria {

	@Id
	@Column(name="ID_AUDITORIA", nullable = false, length = 15)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOM_ACCION", length = 40, nullable = true)
	private String nombreAccion;
	
	@Column(name="NOM_OBJETO", length = 40, nullable = true)
	private String nombreObjeto;
	
	@Column(name="NOM_USUARIO", length = 40, nullable = true)
	private String nombreUsuario;
	
	@Column(name="FEC_PROCESO", nullable = true)
	private Date fechaProceso;
	
	@Column(name="CNT_REGISTROS", length = 20, nullable = true)
	private String cantidadRegistros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

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

	public String getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(String cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	
	
	
}

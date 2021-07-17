package com.mit.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_CONDICIONES_SQL_EXTRACCION")
public class Reglas {
	
	@Id
	@Column(name = "ID_CONDICIONES_SQL_EXTRACCION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "CONDICION", length = 4000, nullable = true)
	private String condicion;
	
	@Column(name = "REGLA_EXTRACCION", length = 4000, nullable = true)
	private String regla;
	
	@Column(name = "FECHA_CREACION", nullable = true)
	private Date fechaCreacion;
	
	@Column(name = "FECHA_ACTUALIZACION", nullable = true)
	private Date fechaActualizacion;
	
	
	@Column(name = "USUARIO_CREACION", length = 60, nullable = true)
	private String usuarioCreacion;
	
	@Column(name = "USUARIO_ACTUALIZACION", length = 60, nullable = true)	
	private String usuarioActualizacion;
	
	@Column(name = "SERVICIO_AFECTADO", length = 20, nullable = false)
	private String servicioAfectado;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getRegla() {
		return regla;
	}

	public void setRegla(String regla) {
		this.regla = regla;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public String getServicioAfectado() {
		return servicioAfectado;
	}

	public void setServicioAfectado(String servicioAfectado) {
		this.servicioAfectado = servicioAfectado;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reglas other = (Reglas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	
	

}

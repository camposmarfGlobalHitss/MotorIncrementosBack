package com.mit.entitys;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_EXCLUSIONES")
public class Exclusiones {
	
	@Id
	@Column(name = "ID_EXCLUSION", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE", nullable = true, length = 40)
	private String nombre;
	
	@Column(name="TIP_DOC", nullable = true, length = 2)
	private int tipDoc;
	
	@Column(name = "IDEN_CLIE", nullable = true, length = 20)
	private Long idenClie;
	
	@Column(name = "FEC_CREA", nullable = true)
	private Date fecCrea;
	
	@Column(name = "USER_CREA", nullable = true, length = 30)
	private String userCrea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipDoc() {
		return tipDoc;
	}

	public void setTipDoc(int tipDoc) {
		this.tipDoc = tipDoc;
	}

	public Long getIdenClie() {
		return idenClie;
	}

	public void setIdenClie(Long idenClie) {
		this.idenClie = idenClie;
	}

	public Date getFecCrea() {
		return fecCrea;
	}

	public void setFecCrea(Date fecCrea) {
		this.fecCrea = fecCrea;
	}

	public String getUserCrea() {
		return userCrea;
	}

	public void setUserCrea(String userCrea) {
		this.userCrea = userCrea;
	}

	public Exclusiones(String nombre, int tipDoc, Long idenClie, Date fecCrea, String userCrea) {
		super();
		this.nombre = nombre;
		this.tipDoc = tipDoc;
		this.idenClie = idenClie;
		this.fecCrea = fecCrea;
		this.userCrea = userCrea;
	}

	public Exclusiones() {
		super();
	}

	@Override
	public String toString() {
		return "Exclusiones [id=" + id + ", nombre=" + nombre + ", tipDoc=" + tipDoc + ", idenClie=" + idenClie
				+ ", fecCrea=" + fecCrea + ", userCrea=" + userCrea + "]";
	}
	
	
	

}

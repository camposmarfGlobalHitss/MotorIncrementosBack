package com.mit.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_PARAMETROS_INCREMENTO")
public class ParametrosIncrementoFija {
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "ID_PARAMETROS_INCREMENTO")
	private Long id;
	
	
	@Column(name = "NEGOCIO", nullable = true, length = 100)
	private String negocio;
	
	@Column(name = "PAQUETE", nullable = true, length = 100)
	private String paquete;
	
	@Column(name = "SERVICIOS_PAQUETE", nullable = true, length = 100)
	private String servicios_paquete;
	
	
	@Column(name = "SERV_INC_ANTES_IVA", nullable = true)
	private int serv_inc_antes_iva;
	
	@Column(name = "UMBRAL_RENTA", nullable = true)
	private int umbral_renta;
	
	
	@Column(name = "SERVICIO", nullable = true, length = 100)
	private String servicio;
	
	@Column(name = "TIPO_INCREMENTO", nullable = true, length = 100)
	private String tipo_incremento;
	
	
	@Column(name = "VIGENCIA", nullable = true)
	private Date vigencia;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNegocio() {
		return negocio;
	}


	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}


	public String getPaquete() {
		return paquete;
	}


	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}


	public String getServicios_paquete() {
		return servicios_paquete;
	}


	public void setServicios_paquete(String servicios_paquete) {
		this.servicios_paquete = servicios_paquete;
	}


	public int getServ_inc_antes_iva() {
		return serv_inc_antes_iva;
	}


	public void setServ_inc_antes_iva(int serv_inc_antes_iva) {
		this.serv_inc_antes_iva = serv_inc_antes_iva;
	}


	public int getUmbral_renta() {
		return umbral_renta;
	}


	public void setUmbral_renta(int umbral_renta) {
		this.umbral_renta = umbral_renta;
	}


	public String getServicio() {
		return servicio;
	}


	public void setServicio(String servicio) {
		this.servicio = servicio;
	}


	public String getTipo_incremento() {
		return tipo_incremento;
	}


	public void setTipo_incremento(String tipo_incremento) {
		this.tipo_incremento = tipo_incremento;
	}


	public Date getVigencia() {
		return vigencia;
	}


	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}


	public ParametrosIncrementoFija(String negocio, String paquete, String servicios_paquete, int serv_inc_antes_iva,
			int umbral_renta, String servicio, String tipo_incremento, Date vigencia) {
		super();
		this.negocio = negocio;
		this.paquete = paquete;
		this.servicios_paquete = servicios_paquete;
		this.serv_inc_antes_iva = serv_inc_antes_iva;
		this.umbral_renta = umbral_renta;
		this.servicio = servicio;
		this.tipo_incremento = tipo_incremento;
		this.vigencia = vigencia;
	}


	public ParametrosIncrementoFija() {
		super();
	}
	
	

}

package com.mit.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_MOVIL_RANGOS_INCREMENTO")
public class MovilRangosIncremento {
	
	@Id
	@Column( name = "ID_RANGOS_INCREMENTO", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DESDE", nullable = true)
	private Long desde;
	
	@Column(name = "HASTA", nullable = true)
	private Long hasta;
	
	@Column(name = "PORCENTAJE", nullable = true)
	private Double porcentaje;
	
	@Column(name = "VALOR", nullable = true)
	private Long valor;	
	
	@Column(name = "FECHA_DESDE", nullable = true)
	private Date fecha_desde;
	
	@Column(name = "FECHA_HASTA", nullable = true)
	private Date fecha_hasta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDesde() {
		return desde;
	}

	public void setDesde(Long desde) {
		this.desde = desde;
	}

	public Long getHasta() {
		return hasta;
	}

	public void setHasta(Long hasta) {
		this.hasta = hasta;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Date getFecha_desde() {
		return fecha_desde;
	}

	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}

	public Date getFecha_hasta() {
		return fecha_hasta;
	}

	public void setFecha_hasta(Date fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	
	
	
	

}

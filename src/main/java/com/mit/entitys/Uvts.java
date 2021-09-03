package com.mit.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_UVTS")
public class Uvts {
	
	
	@Id
	@Column(name = "VLR_UVT_ACT", nullable = false)
	private Long VLR_UVT_ACT;
	
	
	@Column(name = "ANIO_VIGENCIA", nullable = false)
	private int ANIO_VIGENCIA;
	
	@Column(name = "FEC_INI_VIGENCIA", nullable = false)
	private Date FEC_INI_VIGENCIA;
	
	@Column(name = "FEC_FIN_VIGENCIA", nullable = false)
	private Date FEC_FIN_VIGENCIA;
	
	@Column(name = "USER_CREA", nullable = false, length = 30)
	private String USER_CREA;
	
	@Column(name = "ESTADO", nullable = false)
	private int ESTADO;

	public Long getVLR_UVT_ACT() {
		return VLR_UVT_ACT;
	}

	public void setVLR_UVT_ACT(Long vLR_UVT_ACT) {
		VLR_UVT_ACT = vLR_UVT_ACT;
	}

	public int getANIO_VIGENCIA() {
		return ANIO_VIGENCIA;
	}

	public void setANIO_VIGENCIA(int aNIO_VIGENCIA) {
		ANIO_VIGENCIA = aNIO_VIGENCIA;
	}

	public Date getFEC_INI_VIGENCIA() {
		return FEC_INI_VIGENCIA;
	}

	public void setFEC_INI_VIGENCIA(Date fEC_INI_VIGENCIA) {
		FEC_INI_VIGENCIA = fEC_INI_VIGENCIA;
	}

	public Date getFEC_FIN_VIGENCIA() {
		return FEC_FIN_VIGENCIA;
	}

	public void setFEC_FIN_VIGENCIA(Date fEC_FIN_VIGENCIA) {
		FEC_FIN_VIGENCIA = fEC_FIN_VIGENCIA;
	}

	public String getUSER_CREA() {
		return USER_CREA;
	}

	public void setUSER_CREA(String uSER_CREA) {
		USER_CREA = uSER_CREA;
	}

	public int getESTADO() {
		return ESTADO;
	}

	public void setESTADO(int eSTADO) {
		ESTADO = eSTADO;
	}
	
	

}

package com.mit.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_PARAMETROS")
public class Parametros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PARAM")
	private Long id_param;
	
	@Column(name = "KEY_PARAM")
	private String key_param;
	
	@Column(name = "VALUE_PARAM")
	private String value_param;

	public Long getId_param() {
		return id_param;
	}

	public void setId_param(Long id_param) {
		this.id_param = id_param;
	}

	public String getKey_param() {
		return key_param;
	}

	public void setKey_param(String key_param) {
		this.key_param = key_param;
	}

	public String getValue_param() {
		return value_param;
	}

	public void setValue_param(String value_param) {
		this.value_param = value_param;
	}
	

	
	

}

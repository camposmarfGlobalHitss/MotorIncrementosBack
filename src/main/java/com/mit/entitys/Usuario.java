package com.mit.entitys;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_USUARIOS")
public class Usuario {

	
	@Id
	@Column(name="ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USER_NAME" , nullable = false, length = 60)
	private String username;
	
	@Column(name="DESC_USUARIO" , nullable = false, length = 100)
	private String descUsuario;
	
	@Column(name="CONTRASENA" , nullable = false, length = 180)
	private String contrasena;
	
	@Column(name = "FEC_INI_USUARIO")
	private Date feciniusuario; 
	
	@Column(name="FEC_FIN_USUARIO")
	private Date fecfinusuario;
	
	@Column(name="OBSERVACIONES" , nullable = true, length = 100)
	private String observaciones;
	
	@Column(name="ESTADO" , nullable = false, length = 2)
	private int estado;
	
	@Column(name="COD_PERFIL", nullable = false, length = 2)
	private int codperfil;

	@Column(name="CORREO" , nullable = true, length = 60)
	private String correo;

	@Column(name="NOMBRE_COMPLETO" , nullable = true, length = 60)
	private String nombrecompleto;
	
	@Column(name="CODIGO_VERIFICACION" , nullable = true, length = 66)
	private String codigoverificacion;


	public String getCodigoverificacion() {
		return codigoverificacion;
	}

	public void setCodigoverificacion(String codigoverificacion) {
		this.codigoverificacion = codigoverificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescUsuario() {
		return descUsuario;
	}

	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getFeciniusuario() {
		return feciniusuario;
	}

	public void setFeciniusuario(Date feciniusuario) {
		this.feciniusuario = feciniusuario;
	}

	public Date getFecfinusuario() {
		return fecfinusuario;
	}

	public void setFecfinusuario(Date fecfinusuario) {
		this.fecfinusuario = fecfinusuario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombrecompleto() {
		return nombrecompleto;
	}

	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getCodperfil() {
		return codperfil;
	}

	public void setCodperfil(int codperfil) {
		this.codperfil = codperfil;
	}

	
	
	
	
}

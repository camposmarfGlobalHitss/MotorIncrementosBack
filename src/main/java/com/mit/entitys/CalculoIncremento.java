package com.mit.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_CALCULO_INCREMENTO_V2")
public class CalculoIncremento {
	
	@Id
	@Column(name="ID_CAL_INCRTO", nullable=true, length=15) 
	private Long ID_CAL_INCRTO;

	@Column(name="ID_EJEC_PROC", nullable=true, length=10) 
	private String ID_EJEC_PROC;

	@Column(name="CUMPLIO", nullable=true, length=2) 
	private Long CUMPLIO;

	@Column(name="OBSERVACION", nullable=true, length=50) 
	private String OBSERVACION;

	@Column(name="CFM_SIN_IVA_NVO", nullable=true, length=18) 
	private Long CFM_SIN_IVA_NVO;

	@Column(name="CFM_CON_IVA_NVO", nullable=true, length=18) 
	private Long CFM_CON_IVA_NVO;

	@Column(name="IVA_NVO", nullable=true, length=8) 
	private Long IVA_NVO;

	@Column(name="FEC_CREA_INCRTO", nullable=true) 
	private Date FEC_CREA_INCRTO;

	@Column(name="VLR_INCREMENTO", nullable=true, length=18) 
	private Long VLR_INCREMENTO;

	@Column(name="NO_REFERENCIA", nullable=true, length=30) 
	private String NO_REFERENCIA;

	@Column(name="COD_ID", nullable=true, length=30) 
	private String COD_ID;

	@Column(name="ID_CLIENTE", nullable=true, length=38) 
	private Long ID_CLIENTE;

	@Column(name="ID_TIP_DOC", nullable=true, length=2) 
	private Long ID_TIP_DOC;

	@Column(name="IDEN_CLIE", nullable=true, length=20) 
	private String IDEN_CLIE;

	@Column(name="COD_CICLO", nullable=true, length=2 ) 
	private String COD_CICLO;

	@Column(name="ID_PRODUCTO", nullable=true, length=32) 
	private String ID_PRODUCTO;

	@Column(name="HIST_SERV_ACT", nullable=true, length=38) 
	private Long HIST_SERV_ACT;

	@Column(name="USER_CREA_INCRTO", nullable=true, length=30) 
	private String USER_CREA_INCRTO;

	@Column(name="TIP_INCREMENTO", nullable=true, length=1) 
	private Long TIP_INCREMENTO;

	@Column(name="ESTADO", nullable=true, length=2) 
	private Long ESTADO;

	@Column(name="ID_PROCESO", nullable=true, length=38) 
	private Long ID_PROCESO;

	@Column(name="IMPOCONSUMO_VOZ", nullable=true, length=8) 
	private Long IMPOCONSUMO_VOZ;

	@Column(name="IMPOCONSUMO_DATOS", nullable=true, length=8) 
	private Long IMPOCONSUMO_DATOS;

	@Column(name="CFM_SIN_IVA", nullable=true, length=18) 
	private Long CFM_SIN_IVA;

	@Column(name="PORCENTAJE_INCREMENTO", nullable=true, length=5) 
	private Long PORCENTAJE_INCREMENTO;

	@Column(name="TIPO_PAQUETE_FIJA", nullable=true, length=1) 
	private Long TIPO_PAQUETE_FIJA;

	@Column(name="MULTIPLAY", nullable=true, length=32) 
	private String MULTIPLAY;

	@Column(name="ESTADO_CARGO_RECURRENTE", nullable=true, length=50) 
	private String ESTADO_CARGO_RECURRENTE;

	@Column(name="ESTADO_TARIFA_USO", nullable=true, length=50) 
	private String ESTADO_TARIFA_USO;

	
	
	

	public Long getID_CAL_INCRTO() {
		return ID_CAL_INCRTO;
	}

	public void setID_CAL_INCRTO(Long iD_CAL_INCRTO) {
		ID_CAL_INCRTO = iD_CAL_INCRTO;
	}

	public String getID_EJEC_PROC() {
		return ID_EJEC_PROC;
	}

	public void setID_EJEC_PROC(String iD_EJEC_PROC) {
		ID_EJEC_PROC = iD_EJEC_PROC;
	}

	public Long getCUMPLIO() {
		return CUMPLIO;
	}

	public void setCUMPLIO(Long cUMPLIO) {
		CUMPLIO = cUMPLIO;
	}

	public String getOBSERVACION() {
		return OBSERVACION;
	}

	public void setOBSERVACION(String oBSERVACION) {
		OBSERVACION = oBSERVACION;
	}

	public Long getCFM_SIN_IVA_NVO() {
		return CFM_SIN_IVA_NVO;
	}

	public void setCFM_SIN_IVA_NVO(Long cFM_SIN_IVA_NVO) {
		CFM_SIN_IVA_NVO = cFM_SIN_IVA_NVO;
	}

	public Long getCFM_CON_IVA_NVO() {
		return CFM_CON_IVA_NVO;
	}

	public void setCFM_CON_IVA_NVO(Long cFM_CON_IVA_NVO) {
		CFM_CON_IVA_NVO = cFM_CON_IVA_NVO;
	}

	public Long getIVA_NVO() {
		return IVA_NVO;
	}

	public void setIVA_NVO(Long iVA_NVO) {
		IVA_NVO = iVA_NVO;
	}

	public Date getFEC_CREA_INCRTO() {
		return FEC_CREA_INCRTO;
	}

	public void setFEC_CREA_INCRTO(Date fEC_CREA_INCRTO) {
		FEC_CREA_INCRTO = fEC_CREA_INCRTO;
	}

	public Long getVLR_INCREMENTO() {
		return VLR_INCREMENTO;
	}

	public void setVLR_INCREMENTO(Long vLR_INCREMENTO) {
		VLR_INCREMENTO = vLR_INCREMENTO;
	}

	public String getNO_REFERENCIA() {
		return NO_REFERENCIA;
	}

	public void setNO_REFERENCIA(String nO_REFERENCIA) {
		NO_REFERENCIA = nO_REFERENCIA;
	}

	public String getCOD_ID() {
		return COD_ID;
	}

	public void setCOD_ID(String cOD_ID) {
		COD_ID = cOD_ID;
	}

	public Long getID_CLIENTE() {
		return ID_CLIENTE;
	}

	public void setID_CLIENTE(Long iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}

	public Long getID_TIP_DOC() {
		return ID_TIP_DOC;
	}

	public void setID_TIP_DOC(Long iD_TIP_DOC) {
		ID_TIP_DOC = iD_TIP_DOC;
	}

	public String getIDEN_CLIE() {
		return IDEN_CLIE;
	}

	public void setIDEN_CLIE(String iDEN_CLIE) {
		IDEN_CLIE = iDEN_CLIE;
	}

	public String getCOD_CICLO() {
		return COD_CICLO;
	}

	public void setCOD_CICLO(String cOD_CICLO) {
		COD_CICLO = cOD_CICLO;
	}

	public String getID_PRODUCTO() {
		return ID_PRODUCTO;
	}

	public void setID_PRODUCTO(String iD_PRODUCTO) {
		ID_PRODUCTO = iD_PRODUCTO;
	}

	public Long getHIST_SERV_ACT() {
		return HIST_SERV_ACT;
	}

	public void setHIST_SERV_ACT(Long hIST_SERV_ACT) {
		HIST_SERV_ACT = hIST_SERV_ACT;
	}

	public String getUSER_CREA_INCRTO() {
		return USER_CREA_INCRTO;
	}

	public void setUSER_CREA_INCRTO(String uSER_CREA_INCRTO) {
		USER_CREA_INCRTO = uSER_CREA_INCRTO;
	}

	public Long getTIP_INCREMENTO() {
		return TIP_INCREMENTO;
	}

	public void setTIP_INCREMENTO(Long tIP_INCREMENTO) {
		TIP_INCREMENTO = tIP_INCREMENTO;
	}

	public Long getESTADO() {
		return ESTADO;
	}

	public void setESTADO(Long eSTADO) {
		ESTADO = eSTADO;
	}

	public Long getID_PROCESO() {
		return ID_PROCESO;
	}

	public void setID_PROCESO(Long iD_PROCESO) {
		ID_PROCESO = iD_PROCESO;
	}

	public Long getIMPOCONSUMO_VOZ() {
		return IMPOCONSUMO_VOZ;
	}

	public void setIMPOCONSUMO_VOZ(Long iMPOCONSUMO_VOZ) {
		IMPOCONSUMO_VOZ = iMPOCONSUMO_VOZ;
	}

	public Long getIMPOCONSUMO_DATOS() {
		return IMPOCONSUMO_DATOS;
	}

	public void setIMPOCONSUMO_DATOS(Long iMPOCONSUMO_DATOS) {
		IMPOCONSUMO_DATOS = iMPOCONSUMO_DATOS;
	}

	public Long getCFM_SIN_IVA() {
		return CFM_SIN_IVA;
	}

	public void setCFM_SIN_IVA(Long cFM_SIN_IVA) {
		CFM_SIN_IVA = cFM_SIN_IVA;
	}

	public Long getPORCENTAJE_INCREMENTO() {
		return PORCENTAJE_INCREMENTO;
	}

	public void setPORCENTAJE_INCREMENTO(Long pORCENTAJE_INCREMENTO) {
		PORCENTAJE_INCREMENTO = pORCENTAJE_INCREMENTO;
	}

	public Long getTIPO_PAQUETE_FIJA() {
		return TIPO_PAQUETE_FIJA;
	}

	public void setTIPO_PAQUETE_FIJA(Long tIPO_PAQUETE_FIJA) {
		TIPO_PAQUETE_FIJA = tIPO_PAQUETE_FIJA;
	}

	public String getMULTIPLAY() {
		return MULTIPLAY;
	}

	public void setMULTIPLAY(String mULTIPLAY) {
		MULTIPLAY = mULTIPLAY;
	}

	public String getESTADO_CARGO_RECURRENTE() {
		return ESTADO_CARGO_RECURRENTE;
	}

	public void setESTADO_CARGO_RECURRENTE(String eSTADO_CARGO_RECURRENTE) {
		ESTADO_CARGO_RECURRENTE = eSTADO_CARGO_RECURRENTE;
	}

	public String getESTADO_TARIFA_USO() {
		return ESTADO_TARIFA_USO;
	}

	public void setESTADO_TARIFA_USO(String eSTADO_TARIFA_USO) {
		ESTADO_TARIFA_USO = eSTADO_TARIFA_USO;
	}



	
}

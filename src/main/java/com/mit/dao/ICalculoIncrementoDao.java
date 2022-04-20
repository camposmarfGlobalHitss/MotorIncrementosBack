package com.mit.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.CalculoIncremento;
import com.mit.entitys.Parametros;
import com.mit.utils.CantidadesEstados;

@Repository
public interface ICalculoIncrementoDao extends CrudRepository<CalculoIncremento, Long> {

	@Query(value = "SELECT NO_REFERENCIA, COD_ID, ID_CLIENTE, ID_TIP_DOC, IDEN_CLIE FROM IMT_TBL_CALCULO_INCREMENTO"
			+ "WHERE NO_REFERENCIA = ?1 AND ID_CLIENTE = ?2",nativeQuery = true)
	CalculoIncremento consultaEspecial(String NoReferencia, String idCliente);
	
	/*
		 @Luz.Obredor 09.03.2022
		 Se crea query que llama a vista en BD que arma el contenido del reporte de cuentas de incremento
	*/
	@Query(value="SELECT * FROM IMT_REPORTE_CUENTAS_INCREMENTO_VW", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<String> consultaCuentasConIncremento() throws Exception;
	
	/*
		 @Luz.Obredor 09.03.2022
		 Se crea query que llama a vista en BD que arma el contenido del reporte de cuentas no incrementadas
	 */
	@Query(value="SELECT * FROM IMT_REPORTE_CTAS_NO_CUMPLEN_POLITICAS_INCREMENTO_VW", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<String> consultaCuentasNoIncrementadas() throws Exception;
	
	/*
	 	@Luz.Obredor 09.03.2022
	 	Se crea query que llama a vista en BD que arma el contenido del archivo de preincremento
	 */
	@Query(value="SELECT * FROM IMT_REPORTE_PRE_INCREMENTO_VW", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<String> consultaPreIncremento() throws Exception;
	
	/*
	 	@Luz.Obredor 09.03.2022
	 	Se crea query que llama a vista en BD que arma el contenido del reporte de cuentas no sujetas a incremento
	 */
	@Query(value="SELECT * FROM IMT_REPORTE_CUENTAS_NO_SUJETAS_A_INCREMENTO_VW", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<String> consultaNoSujetasIncremento() throws Exception;
		
	@Query(value="SELECT * FROM IMT_TBL_CALCULO_INCREMENTO WHERE ESTADO_CARGO_RECURRENTE = 'CORREGIDO'", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<CalculoIncremento> filtroPorCorregidos() throws Exception;
	
	@Query(value="SELECT new com.mit.utils.CantidadesEstados(COUNT(c), (CASE WHEN c.ESTADO_CARGO_RECURRENTE is null THEN 'INICIAL' ELSE c.ESTADO_CARGO_RECURRENTE END)) FROM CalculoIncremento c GROUP BY c.ESTADO_CARGO_RECURRENTE")
	public List<CantidadesEstados> calculoEstados() throws Exception;
	
	@Query(value= "SELECT * FROM imt_tbl_calculo_incremento WHERE estado_cargo_recurrente = ?1", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<CalculoIncremento> calculoPorEstados(String estado) throws Exception;
	
	@Query(value= "SELECT * FROM imt_tbl_calculo_incremento WHERE estado_cargo_recurrente IS NULL", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<CalculoIncremento> calculoPorEstados() throws Exception;
	
	@Query(value= "SELECT max(b.cfm_sin_iva) CFM FROM IMT_TBL_CLIENTES a JOIN IMT_TBL_CALCULO_INCREMENTO b ON b.IDEN_CLIE = a.IDEN_CLIE GROUP BY a.estrato ORDER BY a.estrato", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<BigDecimal> estratosCFM() throws Exception;
	
	@Query(value= "SELECT B.* FROM IMT_TBL_CLIENTES a JOIN IMT_TBL_CALCULO_INCREMENTO b ON b.IDEN_CLIE = a.IDEN_CLIE WHERE a.estrato = ?1", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<CalculoIncremento> cfmPorEstrato(Integer estrato) throws Exception;
	
	@Query(value="SELECT new com.mit.utils.CantidadesEstados(COUNT(c), (CASE WHEN c.VLR_INCREMENTO = 0 THEN 'NO_INC' ELSE 'INC' END)) FROM CalculoIncremento c GROUP BY (CASE WHEN c.VLR_INCREMENTO = 0 THEN 'NO_INC' ELSE 'INC' END) ORDER BY (CASE WHEN c.VLR_INCREMENTO = 0 THEN 'NO_INC' ELSE 'INC' END)")
	public List<CantidadesEstados> cuentasIncNoInc() throws Exception;
	
	@Query(value= "SELECT * FROM imt_tbl_calculo_incremento WHERE vlr_incremento != 0", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<CalculoIncremento> cuentasIncrementadas() throws Exception;
	
	@Query(value= "SELECT * FROM imt_tbl_calculo_incremento WHERE vlr_incremento = 0", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<CalculoIncremento> cuentasNoIncrementadas() throws Exception;
	
	@Query(value= "SELECT cantidad, cod_dane, desc_ciudad FROM (SELECT b.cod_dane, b.desc_ciudad, COUNT(*) cantidad FROM IMT_TBL_CONTRATOS a JOIN IMT_TBL_CLIENTES b ON a.id_cliente = b.id_cliente GROUP BY b.cod_dane, b.desc_ciudad ORDER BY COUNT(*) DESC) WHERE ROWNUM <= 5", nativeQuery = true)
	@Transactional(readOnly = true)
	public List<String> contratosDANE() throws Exception;
	
}
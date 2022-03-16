package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mit.entitys.CalculoIncremento;
import com.mit.entitys.Parametros;

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
	
}
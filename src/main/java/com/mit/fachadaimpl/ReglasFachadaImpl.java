package com.mit.fachadaimpl;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mit.dao.ICalculoIncrementoDao;
import com.mit.dao.IReglaDao;
import com.mit.entitys.CalculoIncremento;
import com.mit.entitys.Reglas;
import com.mit.fachada.IReglasFachada;



@Service
public class ReglasFachadaImpl implements IReglasFachada{

	@Autowired
	private IReglaDao reglaDao;
	
	@Autowired
	private ICalculoIncrementoDao calculoDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public String condicionesActuales() throws Exception {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("Sp_sql_condiciones")
				.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT)
				.setParameter(1, "");
		spq.execute();

		
		
		return (String) spq.getOutputParameterValue(2);
		
	}
	

	@Override
	public ResponseEntity<String> chequearCondicion(String condicion) throws Exception {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("IMT_CHEQUEAR_CONDICION_SP")
				.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT)
				.setParameter(1, condicion);
		spq.execute();
		return spq.getOutputParameterValue(2).toString()
				.equalsIgnoreCase("ok") ? new ResponseEntity<String>("CONDICION VALIDADA CORRECTAMENTE",HttpStatus.OK) 
						: new ResponseEntity<String>(spq.getOutputParameterValue(2).toString(),HttpStatus.NOT_ACCEPTABLE);
		
	}

	@Override
	public Reglas crearCondicion(Reglas regla) throws Exception {
		
		Reglas reglas = reglaDao.save(regla);
		StoredProcedureQuery spq = em.createStoredProcedureQuery("IMT_VALIDA_CONDICIONES_SP");
		spq.execute();
		return reglas;
	}

	@Override
	public ResponseEntity<List<Reglas>> listarCondiciones() throws Exception {
		List<Reglas> listReglas =  (List<Reglas>) reglaDao.findAll();
		return ResponseEntity.ok(listReglas);			
	}

	@Override
	public ResponseEntity<String> actualizarCondicion(Reglas regla) {
		Reglas resultRegla = reglaDao.save(regla);
		if(resultRegla!=null) {
			return new ResponseEntity<>("CONDICION ACTUALIZADA CORRECTAMENTE!!", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Error en la operacion solicitada",HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<String> borrarCondicion(Long id) throws Exception {
		Optional<Reglas> result =  reglaDao.findById(id);
		if(result.isPresent()) {
			reglaDao.delete(result.get());
			StoredProcedureQuery spq = em.createStoredProcedureQuery("IMT_VALIDA_CONDICIONES_SP");
			spq.execute();			
			return new ResponseEntity<>("CONDICION BORRADA CORRECTAMENTE",HttpStatus.OK);
		}else {
			
			return new ResponseEntity<>("Error en la operacion solicitada",HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public ResponseEntity<String> extraccionCuentas() throws Exception {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("imt_reglas_pkg.pr_aplicacion_reglas_extraccion");
		spq.execute();
		return new ResponseEntity<String>("EXTRACCION GENERADA CORRECTAMENTE",HttpStatus.OK);
						
	}


	@Override
	public ResponseEntity<List<CalculoIncremento>> traerCuentasPostExtraccion() throws Exception {
		List<CalculoIncremento> list = (List<CalculoIncremento>) calculoDao.findAll();
		return new ResponseEntity<List<CalculoIncremento>>(list, HttpStatus.OK);
		
	}


	@Override
	public ResponseEntity<List<CalculoIncremento>> validarCondiciones() throws Exception {

		List<CalculoIncremento> list = (List<CalculoIncremento>) calculoDao.findAll();
		
		return new ResponseEntity<List<CalculoIncremento>>(list, HttpStatus.OK);
	}

}

package com.mit.fachadaimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mit.dao.IAuditoriaDao;
import com.mit.dao.ITarifaUsoDao;
import com.mit.entitys.Auditoria;
import com.mit.fachada.IAuditoriaFachada;

@Service
public class AuditoriaFachadaImpl implements IAuditoriaFachada{

	private IAuditoriaDao auditoriaDao;
	private ITarifaUsoDao tarifaUsoDao;
	
	/*
	 @Luz.Obredor 10.02.2022
	 Se crea constructor como mejor práctica en lugar de hacerlo por propiedades de inyección de dependencias
	 */
	@Autowired
	public AuditoriaFachadaImpl(IAuditoriaDao auditoriaDao, ITarifaUsoDao tarifaUsoDao) {
		this.auditoriaDao = auditoriaDao;
		this.tarifaUsoDao = tarifaUsoDao;
	}
	
	
	@Override
	public List<Auditoria> getAuditoria() throws Exception {
		List<Auditoria> list = new ArrayList<>();
		list = auditoriaDao.buscarAuditorias();
		return list;
	}


	@Override
	public List<String> getTablasAuditora() throws Exception {
		return auditoriaDao.getListaTablasAuditoria();
	}


	@Override
	public List<Auditoria> filtroByTablas(String tabla) throws Exception {
		List<Auditoria> list = new ArrayList<>();
		list = auditoriaDao.filtroByTablas(tabla);
		return list;
	}

	/*
		@Luz.Obredor 10.02.2022
		Implementación del método dónde se hace el llamado a la cantidad de registros creados en la tabla IMT_TBL_TARIFAS_USO
		Para luego crear el insert del proceso de extracción de CS en la tabla IMT_TBL_AUDITORIA
		Requiere como parámetro el usuario quien ejecuta la extracción.
	*/
	@Override
	public ResponseEntity<Auditoria> guardarAuditoriaCS(String usuario) throws Exception {
		String cantidadRegistros = tarifaUsoDao.obtenerCantidadRegistrosTarifaUso();
		Auditoria aud = new Auditoria("INSERT IMT_TBL_TARIFAS_USO", "imt_tbl_tarifas_uso", usuario, new Date(), cantidadRegistros);
		Auditoria audr = auditoriaDao.save(aud);		
		return new ResponseEntity<Auditoria>(audr,HttpStatus.OK);
	}
}

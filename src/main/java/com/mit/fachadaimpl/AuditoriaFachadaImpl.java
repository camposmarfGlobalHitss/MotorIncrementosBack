package com.mit.fachadaimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dao.IAuditoriaDao;
import com.mit.entitys.Auditoria;
import com.mit.fachada.IAuditoriaFachada;

@Service
public class AuditoriaFachadaImpl implements IAuditoriaFachada{

	@Autowired
	private IAuditoriaDao auditoriaDao;
	
	
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

}

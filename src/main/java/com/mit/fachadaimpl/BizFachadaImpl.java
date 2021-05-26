package com.mit.fachadaimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dao.IBizDao;
import com.mit.fachada.IBizFachada;

@Service
public class BizFachadaImpl implements IBizFachada {

	@Autowired
	private IBizDao bizDao;
	
	@Override
	public List<String> getListaIncrementados() throws Exception {
		List<String> list = new ArrayList<>();
		list =  bizDao.getListaIncrementados("EXITO","EXITO","NO APLICA");
		return list;
	}
	

}

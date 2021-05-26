package com.mit.fachadaimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dao.IExtractInfoDao;
import com.mit.entitys.Auditoria;

import com.mit.fachada.IExtractInfoFachada;

import local_project.etl_extract_clientes_bscs_imt_0_1.ETL_EXTRACT_CLIENTES_BSCS_IMT;
import local_project.etl_extract_contratos_bscs_imt_0_1.ETL_EXTRACT_CONTRATOS_BSCS_IMT;
import local_project.etl_extract_producto_oferta_bscs_imt_0_1.ETL_EXTRACT_PRODUCTO_OFERTA_BSCS_IMT;

@Service
public class ExtractInfoFachadaImpl implements IExtractInfoFachada{

	@Autowired
	private IExtractInfoDao extracInfoDao;
	
		
	@Override
	public List<Auditoria> getStatsExtraccion() throws Exception {
		List<Auditoria> list = new ArrayList<>();
		list = extracInfoDao.getStatsExtraccion();
		return list;
	}


	@Override
	public String extractClientes() throws Exception {
		ETL_EXTRACT_CLIENTES_BSCS_IMT job = new ETL_EXTRACT_CLIENTES_BSCS_IMT();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode == 0 ) {
			return "OK";
		}else {
			return "Error en Extraccion Clientes";
		}
		
	}


	@Override
	public String extractContratos() throws Exception {
		ETL_EXTRACT_CONTRATOS_BSCS_IMT job = new ETL_EXTRACT_CONTRATOS_BSCS_IMT();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode == 0 ) {
			return "OK";
		}else {
			return "Error en Extraccion Contratos";
		}
		
		
	}


	@Override
	public String extractProductoOferta() throws Exception {
		ETL_EXTRACT_PRODUCTO_OFERTA_BSCS_IMT job = new ETL_EXTRACT_PRODUCTO_OFERTA_BSCS_IMT();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode == 0 ) {
			return "OK";
		}else {
			return "Error en Extraccion Producto Oferta";
		}
		
	}
	
	

}

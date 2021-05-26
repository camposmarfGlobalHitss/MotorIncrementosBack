package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mit.entitys.CalculoIncremento;


@Repository
public interface IBizDao extends CrudRepository<CalculoIncremento, Long> {
	
	@Query( 
			value = "SELECT c.ID_CLIENTE FROM CalculoIncremento c WHERE c.ESTADO_CARGO_RECURRENTE LIKE %?1% AND c.ESTADO_TARIFA_USO LIKE %?2% OR c.ESTADO_TARIFA_USO LIKE %?3%" 
			
		)
	List<String> getListaIncrementados(String filtro1, String filtro2, String filtro3);
	
}

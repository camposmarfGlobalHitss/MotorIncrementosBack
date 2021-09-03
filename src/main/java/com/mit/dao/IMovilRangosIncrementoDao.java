package com.mit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mit.entitys.MovilRangosIncremento;

@Repository
public interface IMovilRangosIncrementoDao extends CrudRepository<MovilRangosIncremento, Long> {

	
	
}

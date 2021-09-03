package com.mit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mit.entitys.MovilRangosIncremento;
import com.mit.entitys.ParametrosIncrementoFija;

@Repository
public interface IParametrosIncrementosFija extends CrudRepository<ParametrosIncrementoFija, Long>{

}

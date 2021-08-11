package com.mit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mit.entitys.Exclusiones;

@Repository
public interface IExclusionesDao extends CrudRepository<Exclusiones, Long>{

}

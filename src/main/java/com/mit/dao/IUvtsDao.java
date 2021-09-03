package com.mit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mit.entitys.Uvts;

@Repository
public interface IUvtsDao extends CrudRepository<Uvts, Long>{

}

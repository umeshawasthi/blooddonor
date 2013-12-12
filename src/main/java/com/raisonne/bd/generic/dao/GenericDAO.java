/*
 * Copyright 2010 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.generic.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic Hibernate DAO this DAO is based on generic hibernate DAO pattern
 * This interface is the core interface of the DAO hierarchy
 * This interface is responsible for defining all generic persistence related
 * method which are applicable to whole Blood donor domain.
 * 
 * To impliment this generic DAO,user have to provide type and an
 * identifier argument.
 * make sure that identifier being provided to the DAO should have 
 * implimented {@link Serializable} interface.
 * 
 * @author Umesh Awasthi
 * @version 1.0 03/11/2011
 * @since   1.0
 */
public interface GenericDAO<T,ID extends Serializable> {
	
	T findByUUID(ID id,boolean lock);
	T findByName(ID id,boolean lock,T entity);
	List<T> findAll();
	Long getCount();
	List<T> findByExample(T findbyExample,String[] excludeProperty);
	T makePersistance(T entity) throws Exception;
	T saveOrUpdate(T entity) throws Exception;
	void delete(T entity);
	void flush();
	void clear();
	List<T> getPagableData(int fromIndex,int maxRecors,String orderBy,String... criteriaCondition);
	

}

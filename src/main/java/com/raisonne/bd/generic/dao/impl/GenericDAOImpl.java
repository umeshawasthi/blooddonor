/*
 * Copyright 2010 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.generic.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.raisonne.bd.generic.dao.GenericDAO;

/**
 * A skelton implimentation for <tt>GenericDAO</tt> interface
 * This abstract class is responsible for providing generic implimentation
 * for all the method as defined in the <<tt>GenericDAO</tt>
 * 
 * All other entity DAO are required to extend this abstract class.
 * 
 * @author Umesh Awasthi
 * @version 1.0 03/11/2011
 * @see GenericDAO
 * @since   1.0
 * 
 */
public abstract class GenericDAOImpl<T,ID extends Serializable> implements GenericDAO<T, ID> {

	/* (non-Javadoc)
	 * @see com.raisonne.tr.dao.interfaces.GenericDAO#findByUUID(java.io.Serializable, boolean)
	 * This methos will be responsible for getting the entity based on the ID
	 */
	
	/**
	 * Mthod which is responsible for providing
	 * enity based on the identifier being provided as well 
	 * the lock flag. 
	 * @param id identifier which will be used to identify entity in the underlying database
	 * @param lock flag value
	 * @return T entity based on the identifier.  
	 */
	private static Logger log=Logger.getLogger(GenericDAOImpl.class);
	private SessionFactory sessionFactory;
	private Class<T> persistentClass;
	
	/*
	 * Constructor will be used for getting the information
	 * of the entity being persist using java reflection API
	 * This can also be used for constructor based Session injection
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(){
		// Getting the entity information using reflection
		// Need to investigate the cause of warning.
		log.info("getting the information of the entity class using java reflection");
		this.persistentClass=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * General method being used to get the
	 * entity class information which is being persist.
	 * Persistence class information will be used in various methods
	 * 
	 * @return persistentClass
	 */
	public Class<T> getPersistenceClass(){
		return persistentClass;
	}
	
	
	 /**
	  * This setter method should be used for dependency injection.
	 * @param session Hibernate session instance which will be used for various
	 * persistence operations.
	 */
	 public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	 }
	 
	 /*
	  * Getting the session instance.
	  */
	 public SessionFactory getSessionFactory(){
		 log.info("Returning a refrence to the session instance");
		 if(sessionFactory==null){
			 log.error("Not able to find any associated session");
			 throw new RuntimeException("Not able to find any associated session");
		 }
		 return sessionFactory;
	 }
	
	
	 /**
	  * Method used to get Entity based on the UUID
	  * UUID will be an unique indetifier and this method will work as generic method
	  * to get entity.
	  * 
	  * @param id UUID which will be unique in underlying database
	  * @param lock a boolean value used to indicate if this method need any lock
	  * while fetching information from the database
	  * @return Entity against the UUID 
	  */
	@SuppressWarnings("unchecked")
	public T findByUUID(ID id, boolean lock) {
		log.info("Trying to find the entity based on the UUID: "+id);
		Session session=getSessionFactory().getCurrentSession();
		
		T entity=null;
		try{
		if(lock){
			log.info("Using the lock mode as UPGRADE");
			entity=(T)getSessionFactory().getCurrentSession().load(getPersistenceClass(), id, LockOptions.UPGRADE);
		}
		else{
			log.info("Using no lock mode");
			entity=(T)getSessionFactory().getCurrentSession().load(getPersistenceClass(),id);
			}
		
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
				+ "Transcation will be roll backed", e);
			e.printStackTrace();
			
		} 
		return entity;
	}

	
	/**
	 * Each destination in blood donor domain will also be identified by there
	 * unique name per locale.
	 * This method should only be used if the user knows the accurate
	 * name else this method does not gurantee any valid result
	 */
	@SuppressWarnings("unchecked")
	public T findByName(ID name, boolean lock,T entity) {
		log.info("Fetching instance of "+getPersistenceClass()+ "for "+name);
		Session session=getSessionFactory().getCurrentSession();
		try {
			entity=(T) session.createCriteria(getPersistenceClass()).add(Restrictions.eq("name", name)).uniqueResult();
			
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
					+ "Transcation will be roll backed", e);
			e.printStackTrace();
		} 
		
		return entity;
	}

	/**
	 * This Methos will fetch complete network graph of the specified Entity class
	 * Internally this is delegating the call to findByCriteria() method
	 * which takes an var args argement of {@link Criteria}
	 * @return List of defined <tt>T</tt> 
	 */
	public List<T> findAll() {
	    log.info("findAll delegating call to the findByCriteria method");
		return findByCriteria();
	}

	/**
	 * Multi-criteria search functionalities, which allow combinations of many optional criteria fields
	 * @param findbyExample Enity class for which we need to create the Example instance.
	 * @param excludeProperty String array containing the properties which needs to be exculded while creating Example instance.
	 * @return List<T> {@link List} of provided Entity <tt>T</tt>
	 */
	
	public List<T> findByExample(T findbyExample,String[] excludeProperty) {
		//Criteria criteria=getSession().createCriteria(getPersistenceClass());
		//Example example=criteria.createCriteria(findbyExample);
		return null;
	}

	
    public T makePersistance(T entity) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		log.info("Trying to persist" +entity.getClass().getSimpleName());
		try {
		
			session.save(entity);
			session.flush();
			
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
					+ "Transcation will be roll backed", e);
			e.printStackTrace();
            throw e;
		} 

		return entity;
    }
    
    /**
     * This method will take a generic entity and will
     * either save or update the entity based on its state.
     */
    public T saveOrUpdate(T entity) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		log.info("Trying to save or update" +entity.getClass().getSimpleName());
		try {
			session.saveOrUpdate(entity);
			session.flush();
			
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
					+ "Transcation will be roll backed", e);
			e.printStackTrace();
            throw e;
		} 

		return entity;
    }
	
		
	/**
	 * This generic method is responsible for persisting any persistence eligible class
	 * 
	 * @param entity Entity class which is being persist
	 * @return Entity classes which got persist in to the underlying database.
	 */
	public T update(T entity) {
		log.info("About to update the given Entity instance");
		Session session=getSessionFactory().getCurrentSession();
		log.info("Trying to persist" +entity.getClass().getSimpleName());
		try {
			session.update(entity);
			
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
					+ "Transcation will be roll backed", e);
			e.printStackTrace();

		
		}

		return entity;
	}

	/**
	 * Another generic method which takes an entity refrence as input and will delete the 
	 * provided entity instance from the underlying database as well as remove the instance
	 * from the Hibernate context.
	 */
	public void delete(T entity) {
		log.info("Deleting given persistence instance");
		Session session=getSessionFactory().getCurrentSession();
		log.info("Trying to persist" +entity.getClass().getSimpleName());
		try {
			session.delete(entity);
			
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
					+ "Transcation will be roll backed", e);
			e.printStackTrace();

			
		} 
		
	}

	/**
	 * Method needed to synchronize the JDBC connection's state with the state of objects held in memory.
	 * This method takes no argements and have void return type
	 */
	public void flush() {
		log.info("About to flush the given state object with underlying database state");
		//getSessionFactory().getCurrentSession().flush();
		
	}

	/**
	 * Method used to clear the first level cache object from the Hibernate Context cache.
	 * This method takes no argements and have void return type
	 */
	public void clear() {
	    log.info("Cleraning Hibernate context cache");
	    //getSessionFactory().getCurrentSession().clear();	
	}
	
	/*
	 * This is an internal implimentation of GenericDAOImpl class
	 * This methos is being used to return list of defined entity based on the
	 * Criteria which is being build using the provided Criterion array 
	 * @param array of criteria
	 * @return List<T> of defined entity
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion){
	   log.info("Getting the Criteria instance using hibernate session");
	   List<T> list=null;
	   Session session=getSessionFactory().getCurrentSession();
	   Criteria criteria=null;
	   
	   try {
			criteria=session.createCriteria(getPersistenceClass());
			
			for(Criterion c:criterion){
				   criteria.add(c);
			   }
			list=criteria.list();
			
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
					+ "Transcation will be roll backed", e);
			e.printStackTrace();

		} 
	   
	   log.info("Adding Criterion to obtained criteria instance");
	  
	   return list;
   }
	
	@SuppressWarnings("unchecked")
	public List<T> getPagableData(int fromIndex, int maxRecors, String orderBy ,String... criteriaCondition) {
		List<T> list = null;
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(getPersistenceClass());
			for (String n : criteriaCondition) {
				  if(n!=null){
				  //Criterion destinationUUID=Restrictions.eq("destinationid", n);
	              criteria.add(Restrictions.eq("destinationid", n));
				  }
	          }
			if (orderBy != null) {
				String[] orderByArray = orderBy.split(",");
				for (int i = 0; i < orderByArray.length; i++) {
					criteria.addOrder(Order.asc(orderByArray[i]));
				}
			}
			criteria.setMaxResults(maxRecors).setFirstResult(fromIndex);
			list = criteria.list();
			
		} catch (Exception e) {
			log.error("Unable to complete the transcation"
					+ "Transcation will be roll backed", e);
			e.printStackTrace();

		} 

		log.info("Adding Criterion to obtained criteria instance");

		return Collections.unmodifiableList(list);
	}

	public Long getCount(){
		Session session = getSessionFactory().getCurrentSession();
		String queryString="select count(*) from "+getPersistenceClass().getName();
		long count=0;
		try {
			 count = (Long)session.createQuery(queryString).uniqueResult();
			
		} catch (Exception e) {
			log.info("Transcation will be rolled backed");
			e.printStackTrace();
		
		} 
		return count;
	}
}

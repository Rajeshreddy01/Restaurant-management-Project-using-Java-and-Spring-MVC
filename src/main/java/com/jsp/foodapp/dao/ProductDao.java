package com.jsp.foodapp.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.foodapp.dto.Products;
@Repository
public class ProductDao {

	@Autowired
	EntityManagerFactory emf;
	

    
    public void saveProduct (Products u){
	     EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(u);
		et.commit();
	}
    public List<Products> fetchAllProducts(){
    	EntityManager em=emf.createEntityManager();
    	Query q=em.createQuery("select p from Products p");
    	return q.getResultList();
    }
   public Products findProductById(int id) {
   	EntityManager em=emf.createEntityManager();
	   return em.find(Products.class,id);
   }
  public void updateProduct(Products p) {
	  EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(p);
		et.commit();
  }
  public void deleteProductById(int id) {
		EntityManager em = factory.createEntityManager() ;
		EntityTransaction et = em.getTransaction() ;
		Products product = em.find(Products.class, id) ;
		et.begin();
		em.remove(product) ;
		et.commit();
	}
}
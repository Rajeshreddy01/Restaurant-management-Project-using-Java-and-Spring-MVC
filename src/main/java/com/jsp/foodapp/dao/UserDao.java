package com.jsp.foodapp.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.foodapp.dto.User;



@Repository
public class UserDao {
	
	@Autowired
	EntityManagerFactory emf;
	
	public void saveUser (User u){
	     EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(u);
		et.commit();
	}

	public User fetchUserByEmailAndPassword(String email, String password) {
			try {
			EntityManager entityManager = emf.createEntityManager();
			
			Query query = entityManager.createQuery("select a from User a where a.email=:email and a.password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			return (User)query.getSingleResult();
			}
			catch(NoResultException e) {
				return null;
			}
		
	}
	public void updateUser(User u) {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.merge(u);
		entityTransaction.commit();
	}
}
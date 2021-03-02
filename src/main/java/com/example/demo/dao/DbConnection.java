package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

@Repository
public class DbConnection {
	private static final String persistenceName = "Spring_Chess";
	private static DbConnection con;

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	
	public DbConnection() {
		super();
		
	}
	
	public String getPersistenceName() {
		return persistenceName;
	}
	
	public static DbConnection getInstance(){
		if (con == null)
			con = new DbConnection();
		return con;
	}

	private void init() {
		if (emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory(persistenceName);
		}
	}
	
	public EntityManager getEntityManager() {
		init();
		if (em == null)
			em = emf.createEntityManager();
		return em;
	}
	
	public void close() {
		if (emf != null && emf.isOpen()) {
			em.close();
			emf.close();
		}
	}
	

}

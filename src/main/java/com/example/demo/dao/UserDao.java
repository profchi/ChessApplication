package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserDao {
	
	@Autowired
	private DbConnection con;
	
	public UserDao(){
		super();
	
	}
	
	public DbConnection getCon() {
		return con;
	}

	public void setCon(DbConnection con) {
		this.con = con;
	}
	
	public User addUser(User user){
		EntityManager em = con.getEntityManager();
		
		TypedQuery<User> q = em.createNamedQuery("findByUsername", User.class);
		q.setParameter("uUsernameParam", user.getUsername());
		
		User foundUser;
		
		try{
			foundUser =  (User)q.getSingleResult();
		}
		catch (Exception e){
			foundUser = null;
		}
		
		if (foundUser != null)
			return null;
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}
	
	public User loginUser(User user){
		
		User foundUser = getUserByUsername(user.getUsername());
		
		if (foundUser == null || !foundUser.getPassword().equals(user.getPassword()))
			return null;
		else 
			return foundUser;
	}
	
	
	public User getUserByUsername(String name){
		EntityManager em = con.getEntityManager();
		
		TypedQuery<User> q = em.createNamedQuery("findByUsername", User.class);
		q.setParameter("uUsernameParam", name);
		
		User foundUser;
		
		try{
			foundUser =  (User)q.getSingleResult();
		}
		catch (Exception e){
			foundUser = null;
		}
		
		return foundUser;
	}
	
}

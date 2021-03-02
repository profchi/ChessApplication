package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SavedGame;
import com.example.demo.model.User;

@Repository
public class SavedGameDao {
	
	@Autowired
	private DbConnection con;

	
	
	public DbConnection getCon() {
		return con;
	}

	public void setCon(DbConnection con) {
		this.con = con;
	}

	public SavedGameDao() {
		super();
	}

	public void save(SavedGame savedgame) {
		// TODO Auto-generated method stub
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(savedgame);
		em.getTransaction().commit();
		
	}

	public List<SavedGame> loadgames(User user) {
		// TODO Auto-generated method stub
		Query dynamicQuery;
		EntityManager em = con.getEntityManager();
		
		dynamicQuery = em.createQuery("SELECT game FROM SavedGame game WHERE "
				+ "(game.player1 = :user AND game.playedAs = :white ) OR "
				+ "(game.player2 = :user AND game.playedAs = :black) OR "
				+ "(game.playedAs = :both AND game.player1 = :user)");
		
		dynamicQuery.setParameter("user", user);
		dynamicQuery.setParameter("white", "white");
		dynamicQuery.setParameter("black", "black");
		dynamicQuery.setParameter("both", "both");
		
		return dynamicQuery.getResultList();
		
	}
	
	
	

}

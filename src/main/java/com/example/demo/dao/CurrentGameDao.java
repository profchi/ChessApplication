package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CurrentGame;
import com.example.demo.model.User;

@Repository
public class CurrentGameDao {
	
	@Autowired
	private DbConnection con;

	public DbConnection getCon() {
		return con;
	}

	public void setCon(DbConnection con) {
		this.con = con;
	}

	public CurrentGameDao() {
		super();
	}
	
	
	public CurrentGame gameSetupSinglePlayer(User player){
		CurrentGame currentGame = new CurrentGame(player, player);
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(currentGame);
		em.getTransaction().commit();
		
		
		return currentGame;
	}
	
	
	
	public CurrentGame gameSetupDoublePlayer(User player1 , User player2){
		
		CurrentGame currentGame = new CurrentGame(player1, player2);
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(currentGame);
		em.getTransaction().commit();
		
		return currentGame;
		
	}
	
	
	public CurrentGame acceptGameDoublePlayer(User player1, User player2){
		Query dynamicQuery;
		CurrentGame game;
		EntityManager em = con.getEntityManager();
		
		if (player2 == null){
			dynamicQuery = em.createQuery("SELECT game FROM CurrentGame game WHERE game.gameStarted = :false"
					+ " AND game.player2.id = null AND game.completed = :false AND game.abandoned = :false");
			//dynamicQuery.setParameter("player2", null);
		}
		else{
			dynamicQuery = em.createQuery("SELECT game FROM CurrentGame game WHERE game.gameStarted = :false"
					+ " AND game.player1.username = :username1"
					+ " AND game.player2.username = :username2"
					+ " AND game.completed = :false "
					+ "AND game.abandoned = :false");
			dynamicQuery.setParameter("username1", player2.getUsername());
			dynamicQuery.setParameter("username2", player1.getUsername());
		}
		dynamicQuery.setParameter("false", false);
		
		try{
			game = (CurrentGame) dynamicQuery.getResultList().get(0);
		}
		catch (Exception e){
			return null;
		}
		
		if (game != null){
			em.getTransaction().begin();
			game.setGameStarted(true);
			game.setPlayer2(player1);
			em.getTransaction().commit();
		}
		
		
		
		return game;
	}

	public void deleteGame(CurrentGame game) {
		// TODO Auto-generated method stub
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
		em.remove(game);
		em.getTransaction().commit();
	}
	
	
	
	
	
	

}

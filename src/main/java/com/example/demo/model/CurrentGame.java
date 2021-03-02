package com.example.demo.model;

import java.util.Stack;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.controller.game.GameLogic;

@Entity
@Table (name = "Chess_Current_Game")
@SequenceGenerator(name = "currentgame_seq", initialValue = 1, allocationSize = 1)

@NamedQueries({
	@NamedQuery(name = "findByCurrentGameID", query = "SELECT cg FROM CurrentGame cg WHERE cg.id = :cgIdParam")
})

public class CurrentGame {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO , generator = "currentgame_seq")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Resource
	private User player1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User player2;
	
	private int lastPlayed;
	
	private String lastMove;
	
	@Column (columnDefinition = "Number(1)")
	private boolean gameStarted;
	private boolean completed;
	private boolean abandoned;
	
	@Transient
	public GameLogic gamelogic;
	
	@Transient
	private Stack<String> moves;
	
	
	public CurrentGame(){
		super();
	}
	
	public CurrentGame( User player1, User player2){
		this.player1 = player1;
		this.player2 = player2;
		gameStarted = false;
		completed = false;
		abandoned = false;
		gamelogic = new GameLogic();
		gamelogic.init();
		moves = new Stack<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getPlayer1() {
		return player1;
	}

	public void setPlayer1(User player1) {
		this.player1 = player1;
	}

	public User getPlayer2() {
		return player2;
	}

	public void setPlayer2(User player2) {
		this.player2 = player2;
	}

	public int getLastPlayed() {
		return lastPlayed;
	}

	public void setLastPlayed(int lastPlayed) {
		this.lastPlayed = lastPlayed;
	}

	public String getLastMove() {
		return lastMove;
	}

	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isAbandoned() {
		return abandoned;
	}

	public void setAbandoned(boolean abandoned) {
		this.abandoned = abandoned;
	}
	
	public GameLogic getGamelogic() {
		return gamelogic;
	}

	public void setGamelogic(GameLogic gamelogic) {
		this.gamelogic = gamelogic;
	}

	public Stack<String> getMoves() {
		return moves;
	}

	public void setMoves(Stack<String> moves) {
		this.moves = moves;
	}

	
}

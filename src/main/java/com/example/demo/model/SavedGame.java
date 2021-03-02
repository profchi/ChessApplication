package com.example.demo.model;

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

@Entity
@Table (name = "Chess_Saved_Game")
@SequenceGenerator(name = "savedgame_seq", initialValue = 1, allocationSize = 1)
public class SavedGame {
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "savedgame_seq")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User player1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User player2;
	
	private String moves;
	
	private String playedAs;
	
	
	public SavedGame(){
		super();
	}
	
	public SavedGame(User player1, User player2, String moves, String playedAs){
		this.player1 = player1;
		this.player2 = player2;
		this.moves = moves;
		this.playedAs = playedAs;
		
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


	public String getMoves() {
		return moves;
	}


	public void setMoves(String moves) {
		this.moves = moves;
	}


	public String getPlayedAs() {
		return playedAs;
	}


	public void setPlayedAs(String playedAs) {
		this.playedAs = playedAs;
	}
	
	
}

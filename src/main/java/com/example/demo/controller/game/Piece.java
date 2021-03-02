package com.example.demo.controller.game;

import java.util.List;

public class Piece {
	
	protected char color;
	protected char type;
	protected int movesCount = 0;
	
	protected int score;
	
	public int getMovesCount() {
		return movesCount;
	}
	
	public void incrementMovesCount(){
		movesCount++;
	}
	
	public char getColor() {
		return color;
	}
	
	public int getScore() {
		return score;
	}

	public void setColor(char color) {
		this.color = color;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public List<Integer> getLegalMoves(Piece [][] board, int row, int  col){
		return null;
	}

	@Override
	public String toString() {
		
		return "" + color + type;
	}
	
	
}

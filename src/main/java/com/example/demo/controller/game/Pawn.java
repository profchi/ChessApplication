package com.example.demo.controller.game;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
	public Pawn(char color){
		this.color = color;
		this.type = 'P';
		this.score = 1;
	}
	
	
	
	
	@Override
	public List<Integer> getLegalMoves(Piece [][] board, int row, int  col){
		List<Integer> moves = new ArrayList<>();
		if (color == 'W'){
			if(movesCount == 0 && board[row + 2][col] == null)
				moves.add((row + 2) *10 + col);
			if(board[row + 1][col] == null)
				moves.add((row + 1) *10 + col);
			if( col + 1 <= 7 && board[row + 1][col + 1] != null && board[row + 1][col + 1].getColor() != color )
				moves.add((row + 1) *10 + col + 1);
			if(col - 1 >= 0 && board[row + 1][col - 1] != null && board[row + 1][col - 1].getColor() != color )
				moves.add((row + 1) *10 + col - 1);
		}
		else{
			if(movesCount == 0 && board[row - 2][col] == null)
				moves.add((row - 2) *10 + col);
			if(board[row - 1][col] == null)
				moves.add((row - 1) *10 + col);
			if(col + 1 <= 7 && board[row - 1][col + 1] != null && board[row - 1][col + 1].getColor() != color )
				moves.add((row - 1) *10 + col + 1);
			if(col - 1 >= 0 && board[row - 1][col - 1] != null && board[row - 1][col - 1].getColor() != color )
				moves.add((row - 1) *10 + col - 1);
		}
		return moves;
	}
}

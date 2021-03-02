package com.example.demo.controller.game;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
	public Knight(char color){
		this.color = color;
		this.type = 'N';
		this.score = 3;
	}
	
	
	
	@Override
	public List<Integer> getLegalMoves(Piece [][] board, int row, int  col){
		List<Integer> moves = new ArrayList<>();
		
		if (row + 2 <= 7 && col + 1 <= 7 && (board[row + 2][col + 1] == null  
				|| board[row + 2][col + 1].getColor() != color))
			moves.add((row + 2)*10 + col + 1);
		
		if (row - 2 >= 0 && col + 1 <= 7 && (board[row - 2][col + 1] == null  
				|| board[row - 2][col + 1].getColor() != color))
			moves.add((row - 2)*10 + col + 1);
		
		if (row - 2 >= 0 && col - 1 >= 0 && (board[row - 2][col - 1] == null  
				|| board[row - 2][col - 1].getColor() != color))
			moves.add((row - 2)*10 + col - 1);
		
		if (row + 2 <= 7 && col - 1 >= 0 && (board[row + 2][col - 1] == null  
				|| board[row + 2][col - 1].getColor() != color))
			moves.add((row + 2)*10 + col - 1);
		
		
		
		if (row + 1 <= 7 && col + 2 <= 7 && (board[row + 1][col + 2] == null  
				|| board[row + 1][col + 2].getColor() != color))
			moves.add((row + 1)*10 + col + 2);
		
		if (row - 1 >= 0 && col + 2 <= 7 && (board[row - 1][col + 2] == null  
				|| board[row - 1][col + 2].getColor() != color))
			moves.add((row - 1)*10 + col + 2);
		
		if (row - 1 >= 0 && col - 2 >= 0 && (board[row - 1][col - 2] == null  
				|| board[row - 1][col - 2].getColor() != color))
			moves.add((row - 1)*10 + col - 2);
		
		if (row + 1 <= 7 && col - 2 >= 0 && (board[row + 1][col - 2] == null  
				|| board[row + 1][col - 2].getColor() != color))
			moves.add((row + 1)*10 + col - 2);
		
		return moves;
	}
}

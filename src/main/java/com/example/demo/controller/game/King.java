package com.example.demo.controller.game;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
	
	public King(char color){
		this.color = color;
		this.type = 'K';
		this.score = 0;
	}
	
	
	
	@Override
	public List<Integer> getLegalMoves(Piece [][] board, int row, int  col){
		List<Integer> moves = new ArrayList<>();
		//Move left
		if ( col - 1 >= 0 && (board[row][col-1] == null || board[row][col-1].getColor() != color))
			moves.add(row*10+ col - 1);
		
		//Move right
		if ( col + 1 <= 7 && (board[row][col+1] == null || board[row][col+1].getColor() != color))
			moves.add(row*10+ col + 1);
		
		//Move down
		if ( row + 1 <= 7 && (board[row + 1][col] == null || board[row + 1][col].getColor() != color))
			moves.add((row+ 1)*10+ col);
		
		//Move up
		if ( row - 1 >= 0 && (board[row - 1][col] == null || board[row - 1][col].getColor() != color))
			moves.add((row - 1)*10+ col);
		
		//Move up and left
		if ( col - 1 >= 0 && row - 1 >= 0 && (board[row - 1][col-1] == null 
				|| board[row - 1][col-1].getColor() != color))
			moves.add((row - 1)*10+ col - 1);
		
		// Move up and right
		if ( col + 1 <= 7 && row - 1>= 0 && (board[row - 1][col+1] == null 
				|| board[row - 1][col+1].getColor() != color))
			moves.add((row - 1)*10+ col + 1);
		
		//Move down and right
		if ( col + 1 <= 7 && row + 1 <= 7 && (board[row + 1][col+1] == null 
				|| board[row + 1][col+1].getColor() != color))
			moves.add((row + 1)*10+ col + 1);
		
		// Move down and left
		if ( col - 1 >= 0 && row + 1 <= 7 && (board[row + 1][col - 1] == null 
				|| board[row + 1][col - 1].getColor() != color) )
			moves.add((row + 1)*10+ col - 1);
		
		return moves;
	}
	
	public List<Integer> castlingMove(Piece [][] board, int row, int col){
		List<Integer> moves = new ArrayList<>();
		GameLogic gamelogic = new GameLogic(board);
		if (color == 'W'){
			if(row == 0 && board[row][col].getMovesCount() == 0 && board[row][0] != null
					&& board[row][0].getMovesCount() == 0 && board[row][3] == null
					&& board[row][2] == null &&  board[row][1] == null
					&& !gamelogic.getAllCellsAttacked('B').contains(4)
					&& !gamelogic.getAllCellsAttacked('B').contains(3)
					&& !gamelogic.getAllCellsAttacked('B').contains(2)
					&& !gamelogic.getAllCellsAttacked('B').contains(1)
					&& !gamelogic.getAllCellsAttacked('B').contains(0)
					)
				moves.add(2);
			
			if(row == 0 && board[row][col].getMovesCount() == 0 && board[row][7] != null
					&& board[row][7].getMovesCount() == 0 && board[row][5] == null
					&& board[row][6] == null 
					&& !gamelogic.getAllCellsAttacked('B').contains(4)
					&& !gamelogic.getAllCellsAttacked('B').contains(5)
					&& !gamelogic.getAllCellsAttacked('B').contains(6)
					&& !gamelogic.getAllCellsAttacked('B').contains(7)
					)
				moves.add(6);
		}
		else{
			if(row == 7 && board[row][col].getMovesCount() == 0 && board[row][0] != null
					&& board[row][0].getMovesCount() == 0 && board[row][3] == null
					&& board[row][2] == null &&  board[row][1] == null
					&& !gamelogic.getAllCellsAttacked('W').contains(74)
					&& !gamelogic.getAllCellsAttacked('W').contains(73)
					&& !gamelogic.getAllCellsAttacked('W').contains(72)
					&& !gamelogic.getAllCellsAttacked('W').contains(71)
					&& !gamelogic.getAllCellsAttacked('W').contains(70)
					)
				moves.add(72);
			
			if(row == 7 && board[row][col].getMovesCount() == 0 && board[row][7] != null
					&& board[row][7].getMovesCount() == 0 && board[row][5] == null
					&& board[row][6] == null 
					&& !gamelogic.getAllCellsAttacked('W').contains(74)
					&& !gamelogic.getAllCellsAttacked('W').contains(75)
					&& !gamelogic.getAllCellsAttacked('W').contains(76)
					&& !gamelogic.getAllCellsAttacked('W').contains(77)
					)
				moves.add(76);
		}
		return moves;
	}  
}

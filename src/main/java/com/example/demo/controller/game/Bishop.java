package com.example.demo.controller.game;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
	public Bishop(char color){
		this.color = color;
		this.type = 'B';
		this.score = 3;
	}
	
	
	
	@Override
	public List<Integer> getLegalMoves(Piece [][] board, int row, int  col){
		List<Integer> moves = new ArrayList<>();
		
		for (int i = 1; i < 8 ; ++i ){
			if(row + i <= 7 && col + i <= 7  ){
				if (board[row + i] [col + i] == null)
					moves.add((row + i) * 10 + col + i);
				else if (board[row + i] [col + i].getColor() != color){
					moves.add((row + i) * 10 + col + i);
					break;
				}
				else
					break;
			}
		}
		
		
		for (int i = 1; i<8; ++i ){
			if(row - i >= 0 && col + i <= 7  ){
				if (board[row - i] [col + i] == null)
					moves.add((row - i) * 10 + col + i);
				else if (board[row - i] [col + i].getColor() != color){
					moves.add((row - i) * 10 + col + i);
					break;
				}
				else
					break;
			}
		}
		
		for (int i = 1;i<8; ++i ){
			if(row - i >= 0 && col - i >= 0  ){
				if (board[row - i] [col - i] == null)
					moves.add((row - i) * 10 + col - i);
				else if (board[row - i] [col - i].getColor() != color){
					moves.add((row - i) * 10 + col - i);
					break;
				}
				else
					break;
			}
		}
		
		for (int i = 1; i < 8; ++i ){
			if(row + i <= 7 && col - i >= 0  ){
				if (board[row + i] [col - i] == null)
					moves.add((row + i) * 10 + col - i);
				else if (board[row + i] [col - i].getColor() != color){
					moves.add((row + i) * 10 + col - i);
					break;
				}
				else
					break;
			}
		}
		
		return moves;
	}
}

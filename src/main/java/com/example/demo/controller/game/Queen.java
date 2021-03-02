package com.example.demo.controller.game;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
	public Queen (char color){
		this.color = color;
		this.type = 'Q';
		this.score = 9;
	}
	
	
	
	@Override
	public List<Integer> getLegalMoves(Piece [][] board, int row, int  col){
		List<Integer> moves = new ArrayList<>();
		
		for (int i = 1; i < 8 ; ++i ){
			if(row + i <= 7 ){
				if (board[row + i] [col] == null)
					moves.add((row + i) * 10 + col);
				else if (board[row + i] [col].getColor() != color){
					moves.add((row + i) * 10 + col);
					break;
				}
				else
					break;
			}
		}
		
		
		for (int i = 1; i<8; ++i ){
			if(row - i >= 0  ){
				if (board[row - i] [col] == null)
					moves.add((row - i) * 10 + col);
				else if (board[row - i] [col].getColor() != color){
					moves.add((row - i) * 10 + col);
					break;
				}
				else
					break;
			}
		}
		
		for (int i = 1;i<8; ++i ){
			if( col + i <= 7  ){
				if (board[row] [col + i] == null)
					moves.add((row) * 10 + col + i);
				else if (board[row ] [col + i].getColor() != color){
					moves.add((row) * 10 + col + i);
					break;
				}
				else
					break;
			}
		}
		
		for (int i = 1; i < 8; ++i ){
			if( col - i >= 0  ){
				if (board[row] [col - i] == null)
					moves.add((row ) * 10 + col - i);
				else if (board[row] [col - i].getColor() != color){
					moves.add((row) * 10 + col - i);
					break;
				}
				else
					break;
			}
		}
		
		
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

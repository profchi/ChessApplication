package com.example.demo.controller.game;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {
	
	private Scanner scan = new Scanner (System.in);
	private boolean whiteturn = false;
	
	private Piece [][] board;
	
	
	
	public GameLogic(){
		board = new Piece[8][8];
		whiteturn = true;
	}
	
	public GameLogic(Piece [][] board){
		this.board = board;
	}
	
	public void setBoard(Piece [][] board){
		for(int i = 0; i < 8; ++ i){
			this.board[i] =  board[i].clone();
		}
	}
	public Piece [][] getBoard(){
		return board;
	}
	
	public void init(){
		board[0][0] = new Rook('W');
		board[0][1] = new Knight('W');
		board[0][2] = new Bishop('W');
		board[0][3] = new Queen('W');
		board[0][4] = new King('W');
		board[0][5] = new Bishop('W');
		board[0][6] = new Knight('W');
		board[0][7] = new Rook('W');
		
		board[1][0] = new Pawn ('W');
		board[1][1] = new Pawn ('W');
		board[1][2] = new Pawn ('W');
		board[1][3] = new Pawn ('W');
		board[1][4] = new Pawn ('W');
		board[1][5] = new Pawn ('W');
		board[1][6] = new Pawn ('W');
		board[1][7] = new Pawn ('W');
		
		for (int row = 2; row < 6; ++row){
			for (int col = 0; col < 8 ; ++ col){
				board[row][col] = null;
			}
		}
		
		board[6][0] = new Pawn ('B');
		board[6][1] = new Pawn ('B');
		board[6][2] = new Pawn ('B');
		board[6][3] = new Pawn ('B');
		board[6][4] = new Pawn ('B');
		board[6][5] = new Pawn ('B');
		board[6][6] = new Pawn ('B');
		board[6][7] = new Pawn ('B');
		
		board[7][0] = new Rook('B');
		board[7][1] = new Knight('B');
		board[7][2] = new Bishop('B');
		board[7][3] = new Queen('B');
		board[7][4] = new King('B');
		board[7][5] = new Bishop('B');
		board[7][6] = new Knight('B');
		board[7][7] = new Rook('B');
		

	}

	public boolean getWhiteturn(){
		return whiteturn;
	}
	
	public void setWhiteturn( boolean turn){
		whiteturn = turn;
	}
	
	public int [] getMove(){
		int [] positions = null;
		String input;
		do{
			input = scan.nextLine();
			if (input.length() != 4)
				continue;
			positions = new int[4];
			for (int i = 0; i < 4 ; ++ i){
				positions[i] = input.charAt(i) - 48;
			}
		}while (positions == null);
		
		return positions;
	}
	
	public Set<Integer> getAllCellsAttacked(char color){
		Set<Integer> cellsAttacked = new HashSet<>();
		for(int row = 0; row < 8 ; ++ row){
			for (int col = 0; col < 8; ++ col){
				if(board [row][col] != null && board[row][col].getColor() == color)
					cellsAttacked.addAll(board[row][col].getLegalMoves(board, row, col));
			}
		}
		
		return cellsAttacked;
	}
	
	public boolean InCheck( char color){
		int kingrow = 0, kingcol = 0;
		char oppColor = color == 'W'? 'B': 'W';
		int kingCell;
		for(int row = 0; row < 8 ; ++ row){
			for (int col = 0; col < 8; ++ col){
				if (board[row][col] != null && board[row][col].getColor() == color 
						&& board[row][col].getType() == 'K'){
					kingrow = row;
					kingcol = col;
					break;
				}
					
			}
		}
		
		kingCell = kingrow*10 + kingcol;
		return (getAllCellsAttacked(oppColor).contains(kingCell));
		
	}
	
	public boolean checkIfValid(int [] positions){
		Piece temp;
		int row = positions[0];
		int col = positions[1];
		int drow = positions[2];
		int dcol = positions[3];
		int dest = drow*10 + dcol;
		
		if( board[row][col] == null )
			return false;
		
		boolean result = false;
		char color = board[row][col].getColor();
		
		if(whiteturn == true && color == 'B' )
			return false;
		else if(whiteturn == false && color == 'W' )
			return false;
		
		if(
				!(
						board[row][col].getLegalMoves(board, row, col).contains(dest)
						|| (board[row][col].getType() == 'K' 
						&& ((King)board[row][col]).castlingMove(board, row, col).contains(dest))
						)
						)
			return false;
		
		
		temp = board[drow][dcol];
		board[drow][dcol] = board[row][col];
		board[row][col] = null;
		
		if (!InCheck(color))
			result = true;
		
		board[row][col] = board[drow][dcol];
		board[drow][dcol] = temp;
		
		return result;
		
	}
	
	public boolean adjustboard(int [] positions){
		int row = positions[0];
		int col = positions[1];
		int drow = positions[2];
		int dcol = positions[3];
		
		boolean returnResult = false;
		
		// Adjust for castling
		if(board[row][col].getType() == 'K' && Math.abs(dcol - col) == 2){
			if(dcol > col){
				board[row][5] = board[row][7];
				board[row][7] = null;
			}
			else{
				board[row][3] = board[row][0];
				board[row][0] = null;		
			}
			returnResult = true;
		}
		
		
		
		board[drow][dcol] = board[row][col];
		board[drow][dcol].incrementMovesCount();
		board[row][col] = null;
		
		//Pawn Promotion
		if(board[drow][dcol].getType() == 'P'){
			if ( drow == 7 && board[drow][dcol].getColor() == 'W' )
				board[drow][dcol] = new Queen ('W');
			if ( drow == 0 && board[drow][dcol].getColor() == 'B' )
				board[drow][dcol] = new Queen ('B');
		}
		
		whiteturn = !whiteturn;
		return returnResult;
	}
	
	public boolean checkForWinner(){
		char color = whiteturn? 'W': 'B';
		int [] positions = new int [4];
		for(int row = 0; row < 8 ; ++ row){
			positions[0] = row;
			for (int col = 0; col < 8; ++ col){
				positions[1] = col;
				if (board[row][col] != null && board[row][col].getColor() == color)
					
					for(int move : board[row][col].getLegalMoves(board, row, col)){
						positions[2] = move /10;
						positions[3] = move % 10;
						
						if (checkIfValid(positions))
							return false || checkForNoPossibleWins();
					}
			}
		}
		
		return true;
	}
	
	public boolean checkForNoPossibleWins(){
		int blackscore = 0, whitescore = 0;
		
		for (int row = 0; row < 8 ; ++ row){
			for (int col = 0; col < 8; ++ col){
				if (board[row][col] != null && board[row][col].getType() == 'P' )
					return false;
				else if (board [row][col] != null){
					if (board[row][col].getColor() == 'W')
						whitescore += board[row][col].getScore();
					else
						blackscore += board[row][col].getScore();
				}
					
			}
		}
		
		return whitescore < 5 && blackscore < 5;
		
	}
} 

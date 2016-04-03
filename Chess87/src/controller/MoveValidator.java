package controller;

import model.Bishop;
import model.Board;
import model.King;
import model.Knight;
import model.Pawn;
import model.Queen;
import model.Rook;
/**
 * 
 * MoveValidator is a class with static methods to inspect board for check and checkmate scenarios
 * 
 * @author Danny Choi
 * @author Steven Barrios
 * 
 * 
 */
public class MoveValidator {
	
	/**
	 * Checks board to see if any opposing color's piece can directly attack the player's King.
	 * 
	 * @param board game board
	 * @param attackingColor attacking piece's color
	 * @return true if in check, false if not in check
	 */
	public static Boolean checkChecker(Board board, String attackingColor){
		Board tmpBoard = new Board();
		for(int i = 0; i<8; i++){
			for(int k = 0; k<8; k++){
				tmpBoard.board[i][k] = board.board[i][k];
			}
		}
		String kingColor;
		int kingRank= 0;
		int kingFile = 0;
		
		if(attackingColor.equals("white")){
			kingColor = "black";
		}else {
			kingColor = "white";
		}
		
		for(int i = 0; i<8; i++){
			for(int k = 0; k<8; k++){
				if(tmpBoard.board[i][k] == null){
					continue;
				}else if((tmpBoard.board[i][k] instanceof King)&&(tmpBoard.board[i][k].color.equals(kingColor))){
					kingRank = i;
					kingFile = k;
				}
			}
	}
		
		
		for(int i = 0; i<8; i++){
				for(int k = 0; k<8; k++){
					if(tmpBoard.board[i][k] == null){
						continue;
					}else if(tmpBoard.board[i][k].color.equals(attackingColor)){
						//check if this piece can reach the this.colors king
						if( tmpBoard.board[i][k].canMove(i, k, kingRank, kingFile, tmpBoard) ){
							//king is in check
							return true;
						}
					}
				}
		}
		
		return false;
	}
	
	/**
	 * Checks board to see if checkmate exists.
	 * 
	 * @param board game board
	 * @param defendingColor defending piece's color
	 * @return true if in checkmate, false if not in checkmate
	 */
	public static Boolean checkmateChecker(Board board, String defendingColor) {

		Board tmpBoard = new Board();
		for(int i = 0; i<8; i++){
			for(int k = 0; k<8; k++){
				tmpBoard.board[i][k] = board.board[i][k];
			}
		}

		for(int i = 0; i<8; i++){
			for(int k = 0; k<8; k++){
				if(tmpBoard.board[i][k] == null){
					continue;
				}else if(tmpBoard.board[i][k].color.equals(defendingColor)){
					for(int n = 0; n<8; n++){
						for(int m = 0; m<8; m++){
							if(	tmpBoard.board[i][k].validMove(i, k, n, m, tmpBoard) ) {
								
								return false;
							}
							
						}
					}
				}
			}
		}
		
		return true;
	}
	
	
}

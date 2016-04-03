package model;

import controller.MoveValidator;
/**
 * 
 * 
 * Class for Rook that extends the abstract class Piece. 
 * 
 * @author Danny Choi
 * @author Steven Barrios 
 *
 */
public class Rook extends Piece{
	/**
	 * Main constructor for Rook.
	 * 
	 * @param color The color of this Rook
	 */
	public Rook(String color){
		this.color = color;
		if(this.color.equals("white")){
			this.pieceSymbol = "wR";
		}else{
			this.pieceSymbol = "bR";
		}
		this.hasMoved = false;
	}

	@Override
	public Boolean validMove(int oldFile, int oldRank, int newFile, int newRank, Board board) {
		Board tmpBoard = new Board();
		for(int i = 0; i<8; i++){
			for(int k = 0; k<8; k++){
				tmpBoard.board[i][k] = board.board[i][k];
			}
		}
		String attackingColor;
		if(this.color.equals("white")) {
			attackingColor = "black";
		} else {
			attackingColor = "white";
		}
		
		int x = Math.abs(newFile-oldFile);
		int y = Math.abs(newRank-oldRank);
		if(x!=0 && y!=0) {
			return false;
		}
		

		if(board.board[newFile][newRank] != null && board.board[newFile][newRank].color == this.color) {
			return false;
		}
		
		
		if(oldFile < newFile) {// up
			for(int i = 1; i < x; i++) {
				if(board.board[oldFile+i][oldRank] != null) {
					return false;
				}
			}
			tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
			tmpBoard.board[oldFile][oldRank] = null;
			if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
				return false;
			}
			return true;
		}
		
		if(oldFile > newFile) {// down
			for(int i = 1; i < x; i++) {
				if(board.board[oldFile-i][oldRank] != null) {
					return false;
				}
			}
			tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
			tmpBoard.board[oldFile][oldRank] = null;
			if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
				return false;
			}
			return true;
		}
		
		if(oldRank < newRank) {// right
			for(int i = 1; i < y; i++) {
				if(board.board[oldFile][oldRank+1] != null) {
					return false;
				}
			}
			tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
			tmpBoard.board[oldFile][oldRank] = null;
			if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
				return false;
			}
			return true;		
		}
		
		if(oldRank > newRank) {// left
			for(int i = 1; i < y; i++) {
				if(board.board[oldFile][oldRank-1] != null) {
					return false;
				}
			}
			tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
			tmpBoard.board[oldFile][oldRank] = null;
			if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
				return false;
			}
			return true;
		}
		
		return false;
	}


	@Override
	public Boolean canMove(int oldFile, int oldRank, int newFile, int newRank, Board board) {

		int x = Math.abs(newFile-oldFile);
		int y = Math.abs(newRank-oldRank);
		if(x!=0 && y!=0) {
			return false;
		}
		

		if(board.board[newFile][newRank] != null && board.board[newFile][newRank].color == this.color) {
			return false;
		}
		
		
		if(oldFile < newFile) {// up
			for(int i = 1; i < x; i++) {
				if(board.board[oldFile+i][oldRank] != null) {
					return false;
				}
			}
			return true;
		}
		
		if(oldFile > newFile) {// down
			for(int i = 1; i < x; i++) {
				if(board.board[oldFile-i][oldRank] != null) {
					return false;
				}
			}
			return true;
		}
		
		if(oldRank < newRank) {// right
			for(int i = 1; i < y; i++) {
				if(board.board[oldFile][oldRank+1] != null) {
					return false;
				}
			}
			return true;			
		}
		
		if(oldRank > newRank) {// left
			for(int i = 1; i < y; i++) {
				if(board.board[oldFile][oldRank-1] != null) {
					return false;
				}
			}
			return true;			
		}
		
		return false;
	}
}

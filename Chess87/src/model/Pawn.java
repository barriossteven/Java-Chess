package model;

import controller.MoveValidator;
/**
 * Class for Pawn that extends the abstract class Piece. 
 *
 * @author Danny Choi
 * @author Steven Barrios
 *
 */
public class Pawn extends Piece{
	/**
	 * Main constructor for Pawn.
	 * 
	 * @param color The color of this Pawn
	 */
	public Pawn(String color){
		this.color = color;
		if(this.color.equals("white")){
			this.pieceSymbol = "wp";
		}else{
			this.pieceSymbol = "bp";
		}
		this.hasMoved = false;
	}

	@Override
	public Boolean validMove(int oldFile, int oldRank, int newFile, int newRank, Board board) {

		if(oldFile == newFile && oldRank == newRank) {
			return false;
		}
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

		if(board.board[newFile][newRank] != null && board.board[newFile][newRank].color == this.color) {
			return false;
		}
		
		//make sure it is going in the right direction
		if(this.color.equals("white")){
			//System.out.println(oldFile + " " + oldRank + " " + newFile + " " + newRank + "");
			if(oldFile <= newFile) {	//cant go backwards
				return false;
			}
			
			if(oldFile-newFile == 2 && oldRank == newRank) {
				if(board.board[oldFile-1][oldRank] == null && board.board[oldFile-2][oldRank] == null && !hasMoved) {
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					inEnPassant = true;
					return true;
				}
			}
			
			if(oldFile-newFile == 1) {
				if(oldRank == newRank && board.board[oldFile-1][oldRank] == null) {	// moving forward to a blank square
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					return true;
				}
				if(newRank == oldRank+1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("black")) {
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					return true;
				}
				if(newRank == oldRank-1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("black")) {
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					return true;
				}
/*******************************************************************************/
				int newR = newFile;
				int newF = newRank;
				int oldR = oldFile;
				int oldF = oldRank;
				if((newF == oldF-1) && board.board[newR][newF] == null ){
					if(board.board[newR+1][newF] != null ){
						if(board.board[newR+1][newF].color.equals("black") && board.board[newR+1][newF] instanceof Pawn && board.board[newR+1][newF].inEnPassant){
						//move enpassant
							
						tmpBoard.board[newR][newF] = tmpBoard.board[oldR][oldF];
						tmpBoard.board[oldR][oldF] = null;
						if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
							return false;
						}
						board.board[newR+1][newF] = null;
						return true;
						}
		
					}
				}
				if((newF == oldF+1) && board.board[newR][newF] == null ){
					if(board.board[newR+1][newF] != null ){
						if(board.board[newR+1][newF].color.equals("black") && board.board[newR+1][newF] instanceof Pawn && board.board[newR+1][newF].inEnPassant){
						//move enpassant
						tmpBoard.board[newR][newF] = tmpBoard.board[oldR][oldF];
						tmpBoard.board[oldR][oldF] = null;
						if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
							return false;
						}
						board.board[newR+1][newF] = null;
						return true;
						}
		
					}
				}
			}
		}

		if(this.color.equals("black")){
			if(oldFile >= newFile) {
				return false;
			}
			
			if(newFile-oldFile == 2 && oldRank == newRank) {
				if(board.board[oldFile+1][oldRank] == null && board.board[oldFile+2][oldRank] == null && !hasMoved) {
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					inEnPassant = true;
					return true;
				}
			}
			
			if(newFile-oldFile == 1) {
				if(oldRank == newRank && board.board[oldFile+1][oldRank] == null) {	// moving forward to a blank square
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					return true;
				}
				if(newRank == oldRank+1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("white")) {
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					return true;
				}
				if(newRank == oldRank-1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("white")) {
					tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
					tmpBoard.board[oldFile][oldRank] = null;
					if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
						return false;
					}
					return true;
				}
/*******************************************/
				int newR = newFile;
				int newF = newRank;
				int oldR = oldFile;
				int oldF = oldRank;
				if((newF == oldF-1) && board.board[newR][newF] == null ){
					if(board.board[newR-1][newF] != null ){
						if(board.board[newR-1][newF].color.equals("white") && board.board[newR-1][newF] instanceof Pawn && board.board[newR-1][newF].inEnPassant){
						//move enpassant
						tmpBoard.board[newR][newF] = tmpBoard.board[oldR][oldF];
						tmpBoard.board[oldR][oldF] = null;
						if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
							return false;
						}
						board.board[newR-1][newF] = null;
						return true;
						}
		
					}
				}
				if((newF == oldF+1) && board.board[newR][newF] == null ){
					if(board.board[newR-1][newF] != null ){
						if(board.board[newR-1][newF].color.equals("white") && board.board[newR-1][newF] instanceof Pawn && board.board[newR-1][newF].inEnPassant){
						//move enpassant
						tmpBoard.board[newR][newF] = tmpBoard.board[oldR][oldF];
						tmpBoard.board[oldR][oldF] = null;
						if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
							return false;
						}
						board.board[newR-1][newF] = null;
						return true;
						}
		
					}
				}
			}
		}
		
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Boolean canMove(int oldFile, int oldRank, int newFile, int newRank, Board board) {
		
		if(board.board[newFile][newRank] != null && board.board[newFile][newRank].color == this.color) {
			return false;
		}
		
		//make sure it is going in the right direction
		if(this.color.equals("white")){
			//System.out.println(oldFile + " " + oldRank + " " + newFile + " " + newRank + "");
			if(oldFile <= newFile) {	//cant go backwards
				return false;
			}
			
			if(oldFile-newFile == 2 && oldRank == newRank) {
				if(board.board[oldFile-1][oldRank] == null && board.board[oldFile-2][oldRank] == null && !hasMoved) {
					return true;
				}
			}
			
			if(oldFile-newFile == 1) {
				if(oldRank == newRank && board.board[oldFile-1][oldRank] == null) {	// moving forward to a blank square
					return true;
				}
				if(newRank == oldRank+1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("black")) {
					return true;
				}
				if(newRank == oldRank-1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("black")) {
					return true;
				}
			}
		}

		if(this.color.equals("black")){
			if(oldFile >= newFile) {
				return false;
			}
			
			if(newFile-oldFile == 2 && oldRank == newRank) {
				if(board.board[oldFile+1][oldRank] == null && board.board[oldFile+2][oldRank] == null && !hasMoved) {
					return true;
				}
			}
			
			if(newFile-oldFile == 1) {
				if(oldRank == newRank && board.board[oldFile+1][oldRank] == null) {	// moving forward to a blank square
					return true;
				}
				if(newRank == oldRank+1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("white")) {
					return true;
				}
				if(newRank == oldRank-1 && board.board[newFile][newRank] != null && board.board[newFile][newRank].color.equals("white")) {
					return true;
				}
			}
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	
}

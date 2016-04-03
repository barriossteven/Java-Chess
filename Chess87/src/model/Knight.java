package model;

import controller.MoveValidator;

/**
 * 
 * Class for Knight that extends the abstract class Piece.
 * 
 * @author Danny Choi
 * @author Steven Barrios 
 *
 */
public class Knight extends Piece {
	/**
	 * Main constructor for Knight.
	 * 
	 * @param color The color of this Knight
	 */
	public Knight(String color){
		this.color = color;
		if(this.color.equals("white")){
			this.pieceSymbol = "wN";
		}else{
			this.pieceSymbol = "bN";
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
		
		// TODO Auto-generated method stub
		//x has to differ by 2 and y by 1
		//x has to differ by 1 and y by 2
		int x = Math.abs(newFile-oldFile);
		int y = Math.abs(newRank-oldRank);
		
		if(((x==2)&&(y==1))||((x==1)&&(y==2))){
			if(board.board[newFile][newRank] == null || board.board[newFile][newRank].color != this.color) {
				tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
				tmpBoard.board[oldFile][oldRank] = null;
				if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
					return false;
				}
				return true;
			}
		}

		return false;
	}


	@Override
	public Boolean canMove(int oldFile, int oldRank, int newFile, int newRank, Board board) {
		// TODO Auto-generated method stub
		//x has to differ by 2 and y by 1
		//x has to differ by 1 and y by 2
		int x = Math.abs(newFile-oldFile);
		int y = Math.abs(newRank-oldRank);
		
		if(((x==2)&&(y==1))||((x==1)&&(y==2))){
			if(board.board[newFile][newRank] == null || board.board[newFile][newRank].color != this.color) {
				return true;
			}
		}

		return false;
	}

}

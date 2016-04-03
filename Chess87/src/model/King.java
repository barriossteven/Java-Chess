package model;

import controller.MoveValidator;

/**
 *  
 * Class for King that extends the abstract class Piece. 
 *
 * @author Danny Choi
 * @author Steven Barrios
 *
 */
public class King extends Piece{
	/**
	 * Main constructor for King.
	 * 
	 * @param color The color of this King
	 */
	public King(String color){
		this.color = color;
		if(this.color.equals("white")){
			this.pieceSymbol = "wK";
		}else{
			this.pieceSymbol = "bK";
		}
		this.hasMoved = false;
	}

	@Override
	public Boolean validMove(int oldFile, int oldRank, int newFile, int newRank, Board board) {
		Board tmpBoard = new Board();
		Board attackedSquare1 = new Board();
		Board attackedSquare2 = new Board();
		
		for(int i = 0; i<8; i++){
			for(int k = 0; k<8; k++){
				attackedSquare1.board[i][k] = board.board[i][k];
				attackedSquare2.board[i][k] = board.board[i][k];
			}
		}
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

		if(board.board[newFile][newRank] != null && board.board[newFile][newRank].color == this.color) {
			return false;
		}
		
		if (x <= 1 && y <= 1 ) {
			tmpBoard.board[newFile][newRank] = tmpBoard.board[oldFile][oldRank];
			tmpBoard.board[oldFile][oldRank] = null;
			if(MoveValidator.checkChecker(tmpBoard, attackingColor)) {
				return false;
			}
			return true;
		}

		if(this.color == "black" && this.hasMoved == false && newFile == 0 && newRank == 2) { //black queenside
			if (board.board[0][0] != null && board.board[0][0] instanceof Rook && board.board[0][0].hasMoved == false) {	//has rook there
				if (board.board[0][1] == null && board.board[0][2] == null && board.board[0][3] == null) { // no pieces in between
					attackedSquare1.board[0][2] = attackedSquare1.board[0][4];
					attackedSquare2.board[0][3] = attackedSquare2.board[0][4];
					
					attackedSquare1.board[0][4] = null;
					attackedSquare2.board[0][4] = null;
					
					if (MoveValidator.checkChecker(board, "white") || MoveValidator.checkChecker(attackedSquare1, "white") || MoveValidator.checkChecker(attackedSquare2, "white")) {	//if any of the squares are under attack return false
						return false;
					}
					board.board[0][3] = board.board[0][0];
					board.board[0][0] = null;
					return true;
				}
			}
		}
		if(this.color == "black" && this.hasMoved == false && newFile == 0 && newRank == 6) { //black kingside
			if (board.board[0][7] != null && board.board[0][7] instanceof Rook && board.board[0][7].hasMoved == false) {	//has rook there
				if (board.board[0][5] == null && board.board[0][6] == null) { // no pieces in between
					attackedSquare1.board[0][5] = attackedSquare1.board[0][4];
					attackedSquare2.board[0][6] = attackedSquare2.board[0][4];
					
					attackedSquare1.board[0][4] = null;
					attackedSquare2.board[0][4] = null;
					
					if (MoveValidator.checkChecker(board, "white") || MoveValidator.checkChecker(attackedSquare1, "white") || MoveValidator.checkChecker(attackedSquare2, "white")) {	//if any of the squares are under attack return false
						return false;
					}
					board.board[0][5] = board.board[0][7];
					board.board[0][7] = null;
					return true;
				}
			}
			
		}
		
		
		if(this.color == "white" && this.hasMoved == false && newFile == 7 && newRank == 2) { //white queenside
			if (board.board[7][0] != null && board.board[7][0] instanceof Rook && board.board[7][0].hasMoved == false) {	//has rook there
				if (board.board[7][1] == null && board.board[7][2] == null && board.board[7][3] == null) { // no pieces in between
					attackedSquare1.board[7][2] = attackedSquare1.board[7][4];
					attackedSquare2.board[7][3] = attackedSquare2.board[7][4];
					
					attackedSquare1.board[7][4] = null;
					attackedSquare2.board[7][4] = null;
					
					if (MoveValidator.checkChecker(board, "black") || MoveValidator.checkChecker(attackedSquare1, "black") || MoveValidator.checkChecker(attackedSquare2, "black")) {	//if any of the squares are under attack return false
						return false;
					}
					board.board[7][3] = board.board[7][0];
					board.board[7][0] = null;
					return true;
				}
			}
		}
		if(this.color == "white" && this.hasMoved == false && newFile == 7 && newRank == 6) { //white kingside
			if (board.board[7][7] != null && board.board[7][7] instanceof Rook && board.board[7][7].hasMoved == false) {	//has rook there
				if (board.board[7][5] == null && board.board[7][6] == null) { // no pieces in between
					attackedSquare1.board[7][5] = attackedSquare1.board[7][4];
					attackedSquare2.board[7][6] = attackedSquare2.board[7][4];
					
					attackedSquare1.board[7][4] = null;
					attackedSquare2.board[7][4] = null;
					
					if (MoveValidator.checkChecker(board, "black") || MoveValidator.checkChecker(attackedSquare1, "black") || MoveValidator.checkChecker(attackedSquare2, "black")) {	//if any of the squares are under attack return false
						return false;
					}
					board.board[7][5] = board.board[7][7];
					board.board[7][7] = null;
					return true;
				}
			}
			
		}
		
		
		return false;
	}


	@Override
	public Boolean canMove(int oldFile, int oldRank, int newFile, int newRank, Board board) {

		int x = Math.abs(newFile-oldFile);
		int y = Math.abs(newRank-oldRank);
		

		if(oldFile == newFile && oldRank == newRank) {
			return false;
		}
		
		if(board.board[newFile][newRank] != null && board.board[newFile][newRank].color == this.color) {
			return false;
		}
		
		if (x <= 1 && y <= 1 ) {
			return true;
		}

		
		return false;
	}

}

package model;

/**
 * 
 * Board class contains 2d array of Pieces symbolizing the game board
 *
 * @author Danny Choi
 * @author Steven Barrios
 * 
 */
public class Board {
	/**
	 * Piece [][] board is a 2d array of pieces
	 */
	public Piece[][] board;
	
	/**
	 * main constructor initializing board instance
	 */
	public Board(){
		initializeBoard();
	}
	/**
	 * sets all the pieces in their starting positions
	 */
	public void initializeBoard() {
		board = new Piece [8][8];
		
		board[0][0] = new Rook("black");
		board[0][1] = new Knight("black");
		board[0][2] = new Bishop("black");
		board[0][3] = new Queen("black");
		board[0][4] = new King("black");
		board[0][5] = new Bishop("black");		
		board[0][6] = new Knight("black");
		board[0][7] = new Rook("black");
		for(int i = 0;i < board.length; i++){
			board[1][i] = new Pawn("black");
		}
	
		board[7][0] = new Rook("white");
		board[7][1] = new Knight("white");
		board[7][2] = new Bishop("white");
		board[7][3] = new Queen("white");
		board[7][4] = new King("white");
		board[7][5] = new Bishop("white");		
		board[7][6] = new Knight("white");
		board[7][7] = new Rook("white");
		for(int i = 0;i < board.length; i++){
			board[6][i] = new Pawn("white");
		}
	}
	
	/**
	 * prints board with file, rank, black and white squares, and pieces
	 */
	public void printBoard() {
		String[][] tmpBoard = new String[8][8];
		for (int i = 0; i < tmpBoard.length; i++) {
			for (int k = 0; k < tmpBoard[i].length; k++) {
				if ((i + k) % 2 == 0) {
					tmpBoard[i][k] = "   ";
				} else {
					tmpBoard[i][k] = "## ";
				}
			}
		}
		for (int i = 0; i < tmpBoard.length; i++) {
			for (int k = 0; k < tmpBoard[i].length; k++) {
				if(board[i][k] != null){
					tmpBoard [i][k] = board[i][k].pieceSymbol + " ";
				}
			}
		}
		for (int i = 0; i < tmpBoard.length; i++) {
			for (int k = 0; k < tmpBoard[i].length; k++) {
				System.out.print(tmpBoard[i][k]);
			}
			System.out.print(8 - i);
			System.out.println();
		}
		System.out.println(" a  b  c  d  e  f  g  h  ");
	}

}

package model;
/**
 * 
 * abstract class for Piece
 *
 * @author Danny Choi
 * @author Steven Barrios
 * 
 */
public abstract class Piece {
	/**
	 * color of piece
	 */
	public String color;
	/**
	 * symbol that represents this piece which is printed on board
	 */
	public String pieceSymbol;
	/**
	 * indicates whether piece has moved
	 */
	public boolean hasMoved;
	/**
	 * indicates whether Pawn is in en Passant
	 */
	public Boolean inEnPassant = false;
	
	/**
	 * checks to see if move from current position to new position is valid
	 * 
	 * @param oldRank current rank of Piece
	 * @param oldFile current file of Piece
	 * @param newRank new rank of Piece
	 * @param newFile new file of Piece
	 * @param board gameboard
	 * @return true if move is valid, false if move is not valid
	 */
	public abstract Boolean validMove(int oldRank, int oldFile, int newRank, int newFile , Board board);

	public abstract Boolean canMove(int oldRank, int oldFile, int newRank, int newFile , Board board);
}

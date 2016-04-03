package chess;

import java.io.IOException;
import java.util.Scanner;

import controller.MoveValidator;
import model.Bishop;
import model.Board;
import model.Knight;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;
/**
 *  Main Chess class that runs the chess game
 *  
 * @author Danny Choi
 * @author Steven Barrios
 *
 */
public class Chess {
	/**
	 * current input is valid
	 */
	static Boolean validInput = true;
	/**
	 * default promotion character is q for queen
	 */
	static char promotionChar = 'q';
	/**
	 * current game is active
	 */
	static Boolean gameIsActive = true;
	/**
	 * white's turn
	 */
	static Boolean whiteTurn = true;
	/**
	 * asking for draw
	 */
	static Boolean askingDraw= false;
	/**
	 * creates new Board instance
	 */
	static Board board = new Board();
	/**
	 * currently asking for draw
	 */
	static Boolean askforDraw = false;
	/**
	 * if player is in check
	 */
	static Boolean checkFlag = false;
	
	public static void main(String[] args) throws IOException{
		//prints out new board and asks for input from player
		board.printBoard();
		System.out.println();
		Scanner scan = new Scanner(System.in);
		
	while(gameIsActive){
		do{
			if(whiteTurn){
				System.out.print("White's move: ");
			}else{
				System.out.print("Black's move: ");
			}
	
			String s = scan.nextLine();
			s = s.trim();
				readInput(s);
			}while(validInput== false);
		
			if(whiteTurn){
				whiteTurn = false;
			}else{
				whiteTurn = true;
			}
			//prints out board with the move
			System.out.println();
			board.printBoard();
			System.out.println();
			
			if(checkFlag) {
				System.out.println("Check");
				checkFlag = false;
			}

	}
	}	
	
	/**
	 * Parses the input and checks if it is valid input/move
	 * 
	 * @param s String that is inputed
	 */
	public static void readInput(String s){
		s=s.toLowerCase();
		s= s.trim();
		if(s.length()== 0){
			validInput = false;
			return;
		}
		if(s.length() > 11){
			System.out.println("invalid input");
			validInput = false;
			return;
		}
		if(s.equals("resign")){
			resign();
			validInput = true;
			return;
		}
		if(s.equals("draw") && askforDraw == true){
			System.out.println("Draw");
			System.exit(0);
			askforDraw = false;
		}
		
		//checks if move is between a-h and 1-8 .....letter is rank number is file
		if( ( s.charAt(0) >= 'a' && s.charAt(0)<= 'h') &&
				(s.charAt(3) >= 'a' && s.charAt(3)<= 'h')&&
				(s.charAt(1) >= '1' && s.charAt(1)<= '8')&&
				(s.charAt(4) >= '1' && s.charAt(4)<= '8')&&
				(s.charAt(2) == ' ')
				){
				int oldcol = Character.getNumericValue(s.charAt(0))-10;
				int oldrow = Math.abs(Character.getNumericValue(s.charAt(1))-8);
				int newcol = Character.getNumericValue(s.charAt(3))-10;
				int newrow = Math.abs(Character.getNumericValue(s.charAt(4))-8);
							
				if(s.length() == 5){
					//if input is e4 e4 then invalid because cant have new position same as old position
					if((s.charAt(0) ==s.charAt(3))&& (s.charAt(1)==s.charAt(4)) ){
						System.out.println("invalid input");
						validInput = false;
						askforDraw = false;
						return;
					}
					// since input is length 5 we know it is in form of e2 e4
					//send input to movepiece to see if input is a valid move
					if(movePiece(oldrow, oldcol, newrow, newcol, promotionChar)){
						validInput = true;
						askforDraw = false;
						return;
					}else{
						System.out.println("Illegal move, try again");
						validInput = false;
						askforDraw = false;
						return;
					}
				}else if((s.length()== 7)&&
						s.charAt(5)== ' ' &&
						(s.charAt(6)== 'n' || 
						s.charAt(6)== 'q' ||
						s.charAt(6)== 'b' ||
						s.charAt(6)=='r'))
				{
					//input in form of e2 e4 N
					System.out.println("input with promotion");
					if(movePiece(oldrow, oldcol, newrow, newcol, s.charAt(6))){
						validInput = true;
						askforDraw = false;
						return;
					}else{
						System.out.println("Illegal move, try again");
						validInput = false;
						askforDraw = false;
						return;
					}
				}else if(s.length()==11 && s.indexOf("draw?")!=-1){
					//input in for of e4 e3 draw?
					if(movePiece(oldrow, oldcol, newrow, newcol, promotionChar)){
						validInput = true;
						System.out.println("asking for draw");
						askforDraw = true;
						//ask for draw
						return;
					}else{
						System.out.println("Illegal move, try again");
						validInput = false;
						askforDraw = false;
						return;
					}
				}
		}
		System.out.println("invalid input");
		validInput = false;
		askforDraw = false;
		
	}
	/**
	 * If white player entered "resign" then black wins,
	 *  if black player entered "resign" then white wins.
	 * 
	 */
	public static void resign(){
		gameIsActive = false;
		if(whiteTurn){
			System.out.println("Black wins");
			System.exit(0);
		}else{
			System.out.println("White wins");
			System.exit(0);
		}
		
	}
	/**
	 * Takes in current position and new position and check if it is a valid move. Path must be clear, move must obey piece's rule set, and can not move into check for move to be valid.
	 * 
	 * @param oldRank Piece's current rank
	 * @param oldFile Piece's current file
	 * @param newRank Piece's new rank
	 * @param newFile Piece's new file
	 * @param promotionChar if Piece is a pawn and promotion is valid then this character describes the piece you promote pawn to, Q by default
	 * @return true if valid move, false if invalid move
	 */
	public static Boolean movePiece(int oldRank, int oldFile, int newRank, int newFile, char promotionChar ) {
		if(board.board[oldRank][oldFile]== null){
			return false;
		}
		
		String defendingColor;
		String attackingColor;
		
		if(!whiteTurn) {
			defendingColor = "white";
			attackingColor = "black";
		} else {
			defendingColor = "black";
			attackingColor = "white";
		}
		
		Piece yourPiece = board.board[oldRank][oldFile];
		if((yourPiece.color.equals("white")&& whiteTurn)||(yourPiece.color.equals("black")&&!whiteTurn)) {
			Boolean movevalidity = yourPiece.validMove(oldRank, oldFile, newRank, newFile, board);
			if(movevalidity && ((yourPiece instanceof Pawn)&&((oldRank==6&&newRank==7)||(oldRank==1 && newRank==0))) ){
				System.out.println("pawn to be promoted to: " + promotionChar );
				board.board[newRank][newFile] =  pawnPromotion(promotionChar);
				board.board[oldRank][oldFile] = null;
				removeEnPassant();
				return true;
			}else if(movevalidity){
				board.board[newRank][newFile] =  board.board[oldRank][oldFile];
				board.board[newRank][newFile].hasMoved = true;
				board.board[oldRank][oldFile] = null;
				
				/* check for check and checkmate */
				if(MoveValidator.checkChecker(board, attackingColor)) {
					//change this for checkmate
					//System.out.println("Check");

					if(MoveValidator.checkmateChecker(board, defendingColor)) {
						System.out.println("CheckMate");
						if(whiteTurn){
							System.out.println("White wins");
							System.exit(0);
						}else{
							System.out.println("Black wins");
							System.exit(0);
						}
					}
//					System.out.println("Check");
					checkFlag = true;
				}
				
				else if(MoveValidator.checkmateChecker(board, defendingColor)) {
					System.out.println("Stalemate");
					System.exit(0);
				}
				removeEnPassant();
				return true;
			}
		}
		return false;
	}
	/**
	 * Removes en Passant flags in each pawn after 1 turn indicating those pieces are no longer in en Passant.
	 */
	public static void removeEnPassant(){
		String color = null;
		if(whiteTurn){
			color = "black";
		}else {
			color = "white";
		}
		
		for(int i =0; i<8;i++){
			for(int k=0; k<8 ;k++){
				if(board.board[i][k] != null){
					if(board.board[i][k].color.equals(color)){
						board.board[i][k].inEnPassant= false;
					}
				}
			}
		}
	}
	
	/**
	 * PawnPromotion checks what the promotion character is and will return the piece corresponding to the character.
	 * r = Rook, q = Queen, n = Knight , b = Bishop
	 * 
	 * @param promotionChar indicates what the piece the player wants their Pawn to be promoted to.
	 *  
	 * @return the piece that will replace the promoted Pawn
	 */
	public static Piece pawnPromotion(char promotionChar){
		String color;
		if(whiteTurn){
			color = "white";
		} else{
			color = "black";
		}
		if(promotionChar == 'q'){
			return new Queen(color);
		}else if(promotionChar == 'n'){
			return new Knight(color);
		}else if(promotionChar == 'b'){
			return new Bishop(color);
		}else if(promotionChar == 'r'){
			return new Rook(color);
		}
		
		return null;
	}
		
	
}

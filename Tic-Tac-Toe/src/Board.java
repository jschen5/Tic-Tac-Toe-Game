import java.util.ArrayList;

public class Board {
	
	//board representation char[x][y]
	//valid symbols: x, o, n (n = none)
	
	public static final int dim = 3;	//dimension of square board
	private char[][] board;
	private ArrayList<Move> possibleMoves;	//all moves in possibleMoves will have mark 'n'
	private ArrayList<Move> pastMoves;	//all moves in pastMoves will have marks corresponding to the move maker
	
	
	public Board() {
		board = new char[dim][dim];
		possibleMoves = new ArrayList<Move>();
		pastMoves = new ArrayList<Move>();
		resetBoard();
	}
	
	public void resetBoard() {
		fillBoardWith(board, 'n');
		//fill availableMoves with all possible moves
		possibleMoves.clear();
		pastMoves.clear();
		for (int i = 0; i < Board.dim; i++) {
			for (int k = 0; k < Board.dim; k++) {
				possibleMoves.add(new Move(i, k));
			}
		}
	}
	
	public Board clone() {
		Board clone = new Board();
		for (int i = 0; i < Board.dim; i++) {
			for (int k = 0; k < Board.dim; k++) {
				clone.board[i][k] = this.board[i][k];
			}
		}
		clone.possibleMoves.clear();
		for (int i = 0; i < this.possibleMoves.size(); i++) {
			clone.possibleMoves.add(this.possibleMoves.get(i).clone());
		}
		for (int i = 0; i < this.pastMoves.size(); i++) {
			clone.pastMoves.add(this.pastMoves.get(i).clone());
		}
		
		return clone;
	}
	
	public int update(Move mv) {
		//can probably remove this check since only available/valid moves will be in arraylist
		if (canMove(mv)) {
			char origMark = mv.getMark();
			board[mv.getX()][mv.getY()] = origMark;
			pastMoves.add(mv);
			mv.setMark('n');
			possibleMoves.remove(mv);
			mv.setMark(origMark);	//set mark back to original player mark for undoMove(), otherwise move never used again
			return 1;
		}
		return 0;
	}
	
	public void undoMove(Move mv) {
		board[mv.getX()][mv.getY()] = 'n';
		pastMoves.remove(mv);
		mv.setMark('n');
		possibleMoves.add(mv);
	}
	
	public char victConfig() {
		
		char[][] boardT = transpose(board);
		
		for (int i = 0; i < dim; i++) {
			String column = new String(board[i]);
			String row = new String(boardT[i]);
			if (column.equals("xxx") || column.equals("ooo")) {
				return column.charAt(0);
			}
			if (row.equals("xxx") || row.equals("ooo")) {
				return row.charAt(0);
			}
		}
		
		if (((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) || 
			((board[2][0] == board[1][1]) && (board[1][1] == board[0][2]))) {
			if ((board[1][1] == 'x') || (board[1][1] == 'o')) {
				return board[1][1];
			}
		}
		
		return 'n';
	}
	
	public boolean equals(Object obj) {	//two boards with the same 2D array representation must be the same board
		Board bd = (Board) obj;
		for (int i = 0; i < Board.dim; i++) {
			for (int k = 0; k < Board.dim; k++) {
				if (this.board[i][k] != bd.board[i][k]) {
					return false;
				}
			}
		}
		for (int i = 0; i < this.possibleMoves.size(); i++) {
			if (!this.possibleMoves.contains(bd.possibleMoves.get(i))) {
				return false;
			}
		}
		for (int i = 0; i < this.pastMoves.size(); i++) {
			if (!this.pastMoves.contains(bd.pastMoves.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	
	////////////////////////////
	///////Getter Methods///////
	////////////////////////////
	
	public char[][] getBoard() {
		return board;
	}
	
	public ArrayList<Move> getPossibleMoves() {
		return possibleMoves;
	}
	
	public ArrayList<Move> getPastMoves() {
		return pastMoves;
	}
	
	
	////////////////////////////
	///////Helper Methods///////
	////////////////////////////
	
	public static void fillBoardWith(char[][] board, char symbol) {
		for (int i = 0; i < dim; i++) {
			for (int k = 0; k < dim; k++) {
				board[i][k] = symbol;
			}
		}
	}
	
	private boolean canMove(Move mv) {
		if (board[mv.getX()][mv.getY()] == 'n') {
			return true;
		}
		return false;
	}
	
	public static char[][] transpose(char[][] mtrx) {
		char[][] result = new char[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int k = 0; k < dim; k++) {
				result[k][i] = mtrx[i][k];
			}
		}
		return result;
	}
	
}	//end of Board class

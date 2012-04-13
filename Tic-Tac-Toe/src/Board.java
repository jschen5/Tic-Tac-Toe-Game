public class Board {
	
	//board representation char[x][y]
	//valid symbols: x, o, n (n = none)
	
	public static final int dim = 3;	//dimension of square board
	private char[][] board;
	
	public Board() {
		board = new char[dim][dim];
		resetBoard();
	}
	
	public void resetBoard() {
		fillBoardWith(board, 'n');
	}
	
	public int update(Move mv) {
		if (canMove(mv)) {
			board[mv.getX()][mv.getY()] = mv.getMark();
			return 1;
		}
		return 0;
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
	
	
	
	////////////////////////////
	///////Getter Methods///////
	////////////////////////////
	
	public char[][] getBoard() {
		return board;
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

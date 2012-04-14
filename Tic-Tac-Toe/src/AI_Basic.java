import java.util.ArrayList;


public class AI_Basic {
	
	protected String name;
	protected char symbol;
	protected Board gameBrd;
	protected ArrayList<Move> possibleMoves;
	
	public AI_Basic(String randGenName, char mark, Board brd) {
		name = randGenName;
		symbol = mark;
		gameBrd = brd;
	}
	
	public Move nextMove() {
		Move nextMv = gameBrd.availableMoves.get((int) Math.random()*4);
		nextMv.setMark(symbol);
		return nextMv;
	}
	
	
	
	////////////////////////////
	///////Getter Methods///////
	////////////////////////////
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public Board getBrd() {
		return gameBrd;
	}
	
}

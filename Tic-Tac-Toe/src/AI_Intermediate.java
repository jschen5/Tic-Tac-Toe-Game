import java.util.ArrayList;


public class AI_Intermediate extends AI_Basic {
	
	private ArrayList<Move> winningMoves;
	private ArrayList<Move> blockingMoves;
	
	public AI_Intermediate(String randGenName, char mark, Board brd) {
		super(randGenName, mark, brd);
	}
	
	public Move nextMove() {
		
		return null;	//not yet implemented
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
	
	
	/////////////////////////////
	///////Helper Methods////////
	/////////////////////////////
	
}

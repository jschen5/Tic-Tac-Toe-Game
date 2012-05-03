import java.util.ArrayList;


public class AI_Basic {
	
	protected String name;
	protected char symbolAI;
	protected char symbolPlayer;
	protected Board gameBrd;
	protected ArrayList<Move> availableMoves;
	protected ArrayList<Move> madeMoves;
	
	public AI_Basic(String randGenName, char mark, char oppMark, Board brd) {
		name = randGenName;
		symbolAI = mark;
		symbolPlayer = oppMark;
		gameBrd = brd;
		availableMoves = gameBrd.getPossibleMoves();
		madeMoves = new ArrayList<Move>();
	}
	
	public Move nextMove() {
		if (availableMoves.isEmpty()) {
			return null;
		}
		Move nextMv = availableMoves.get((int) (Math.random()*(availableMoves.size())));
		nextMv.setMark(symbolAI);	//this will also change the mark of the move in availableMoves, since they are the same
									//should be immediately removed from availableMoves after this method by call to update(...)
		madeMoves.add(nextMv);
		return nextMv;
	}
	
	
	
	////////////////////////////
	///////Getter Methods///////
	////////////////////////////
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbolAI;
	}
	
	public Board getBrd() {
		return gameBrd;
	}
	
	public ArrayList<Move> getAvailableMoves() {
		return availableMoves;
	}
	
}

import java.util.ArrayList;


public class AI_Intermediate extends AI_Basic {
	
	protected ArrayList<Move> winningMoves;
	protected ArrayList<Move> blockingMoves;
	
	public AI_Intermediate(String randGenName, char mark, char oppMark, Board brd) {
		super(randGenName, mark, oppMark, brd);
		winningMoves = new ArrayList<Move>();
		blockingMoves = new ArrayList<Move>();
	}
	
	public Move nextMove() {
		generateWinningMoves();
		if (!winningMoves.isEmpty()) {
			return winningMoves.get((int) (Math.random()*(winningMoves.size())));
		}
		generateBlockingMoves();
		if (!blockingMoves.isEmpty()) {
			return blockingMoves.get((int) (Math.random()*(blockingMoves.size())));
		}
		return super.nextMove();
	}
	
	
	
	/////////////////////////////
	///////Helper Methods////////
	/////////////////////////////
	
	protected void generateWinningMoves() {
		Board copyBd = gameBrd.clone();
		winningMoves.clear();
		for (int i = 0; i < availableMoves.size(); i++) {
			Move nextTry = availableMoves.get(i);
			nextTry.setMark(symbolAI);
			copyBd.update(nextTry);
			if (copyBd.victConfig() == symbolAI) {
				winningMoves.add(nextTry.clone());
			}
			copyBd.undoMove(nextTry);
		}
	}
	
	protected void generateBlockingMoves() {
		Board copyBd = gameBrd.clone();
		blockingMoves.clear();
		for (int i = 0; i < availableMoves.size(); i++) {
			Move nextTry = availableMoves.get(i);
			nextTry.setMark(symbolPlayer);
			copyBd.update(nextTry);
			if (copyBd.victConfig() == symbolPlayer) {
				Move blockMv = nextTry.clone();
				blockMv.setMark(symbolAI);
				blockingMoves.add(blockMv);
			}
			copyBd.undoMove(nextTry);
		}
	}
}

import java.util.ArrayList;


public class AI_Experienced extends AI_Intermediate {

	boolean firstTurn;
	protected ArrayList<Move> cornerMoves;

	public AI_Experienced (String randGenName, char mark, char oppMark, Board brd) {
		super(randGenName, mark, oppMark, brd);
		cornerMoves = new ArrayList<Move>();
		firstTurn = true;
	}

	public Move nextMove() {

		int firstMove;	//middle = 0, corner = 1

		generateWinningMoves();
		if (!winningMoves.isEmpty()) {
			return winningMoves.get((int) (Math.random()*(winningMoves.size())));
		}

		generateBlockingMoves();
		generateCornerMoves();
		if (!blockingMoves.isEmpty()) {
			if (!cornerMoves.isEmpty()) {
				ArrayList<Move> blockingAndCornerMoves = new ArrayList<Move>();
				for (int i = 0; i < cornerMoves.size(); i++) {
					if (blockingMoves.contains(cornerMoves.get(i))) {
						blockingAndCornerMoves.add(cornerMoves.get(i));
					}
				}
				if (!blockingAndCornerMoves.isEmpty()) {
					return blockingAndCornerMoves.get((int) (Math.random()*(blockingAndCornerMoves.size())));
				}
			}
			return blockingMoves.get((int) (Math.random()*(blockingMoves.size())));
		}

		if (firstTurn) {
			firstMove = (int) Math.random()*2;
			if (firstMove == 0) {	//try middle first
				if (gameBrd.getBoard()[1][1] == 'n') {
					firstTurn = false;
					return new Move(symbolAI, 1, 1);
				} else {	//this means AI goes second
					firstTurn = false;
					return cornerMoves.get((int) (Math.random()*(cornerMoves.size())));
				}
			} else {	//try corner first
				//no need to check for corner moves availability since either first or second turn
				firstTurn = false;
				return cornerMoves.get((int) (Math.random()*(cornerMoves.size())));
				//don't need case where no corner moves
				//there are always corner moves available in the first/second turn
			}
		}

		if (gameBrd.getBoard()[1][1] == 'n') {	//try middle
			return new Move(symbolAI, 1, 1);
		} else if (!cornerMoves.isEmpty()) {	//now try corners
			return cornerMoves.get((int) (Math.random()*(cornerMoves.size())));
		} else {	//now make random move
			return super.randomNextMove();	
		}
	}



	////////////////////////////
	///////Helper Methods///////
	////////////////////////////

	public void generateCornerMoves() {
		cornerMoves.clear();
		for (int i = 0; i < Board.dim; i+=2) {
			if (gameBrd.getBoard()[i][0] == 'n') {
				cornerMoves.add(new Move(symbolAI, i, 0));
			}
			if (gameBrd.getBoard()[i][2] == 'n') {
				cornerMoves.add(new Move(symbolAI, i, 2));
			}
		}
	}


}

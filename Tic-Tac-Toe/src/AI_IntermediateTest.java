import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class AI_IntermediateTest {

	@Test
	public void testAI_Intermediate() {
		Board game1 = new Board();
		AI_Intermediate ai1 = new AI_Intermediate("Joey", 'x', 'o', game1);
		assertTrue(ai1.getName().equals("Joey"));
		assertTrue(ai1.getSymbol() == 'x');
		assertTrue(ai1.symbolPlayer == 'o');
		assertTrue(ai1.getBrd().equals(game1));
		assertTrue (ai1.winningMoves.equals(new ArrayList<Move>()));
		assertTrue (ai1.blockingMoves.equals(new ArrayList<Move>()));
	}
	
	@Test
	public void testGenerateWinningMoves() {
		Board game1 = new Board();
		AI_Intermediate ai1 = new AI_Intermediate("Joey", 'x', 'o', game1);
		
		Move testMv = new Move('x', 0, 0);
		game1.update(testMv);
		testMv = new Move('x', 0, 1);
		game1.update(testMv);
		testMv = new Move('x', 1, 0);
		game1.update(testMv);
		testMv = new Move('x', 1, 1);
		game1.update(testMv);
		
		ai1.generateWinningMoves();
		assertTrue(ai1.winningMoves.contains(new Move('x', 0, 2)));
		assertTrue(ai1.winningMoves.contains(new Move('x', 2, 0)));
		assertTrue(ai1.winningMoves.contains(new Move('x', 2, 2)));
	}
	
	@Test
	public void testGenerateBlockingMoves() {
		Board game1 = new Board();
		AI_Intermediate ai1 = new AI_Intermediate("Joey", 'x', 'o', game1);
		
		Move testMv = new Move('o', 0, 0);
		game1.update(testMv);
		testMv = new Move('o', 0, 1);
		game1.update(testMv);
		testMv = new Move('o', 1, 0);
		game1.update(testMv);
		testMv = new Move('o', 1, 1);
		game1.update(testMv);
		
		ai1.generateBlockingMoves();
		assertTrue(ai1.blockingMoves.contains(new Move('x', 0, 2)));
		assertTrue(ai1.blockingMoves.contains(new Move('x', 2, 0)));
		assertTrue(ai1.blockingMoves.contains(new Move('x', 2, 2)));
	}
	
	@Test
	public void testNextMove() {
		Board game1 = new Board();
		AI_Intermediate ai1 = new AI_Intermediate("Joey", 'x', 'o', game1);
		
		Move testMv = new Move('x', 0, 0);
		game1.update(testMv);
		testMv = new Move('x', 1, 0);
		game1.update(testMv);
		testMv = new Move('o', 0, 1);
		game1.update(testMv);
		testMv = new Move('o', 1, 1);
		game1.update(testMv);
		
		//winning move: 'x', 2, 0
		//blocking move: 'x', 2, 1
		assertTrue(ai1.nextMove().equals(new Move('x', 2, 0)));	//should choose the winning move over blocking move
		
		game1.undoMove(new Move('x', 1, 0));
		assertTrue(ai1.nextMove().equals(new Move('x', 2, 1)));	//no winning move, next move should now be blocking move
		
		game1.undoMove(new Move('o', 1, 1));
		Move next = ai1.nextMove();
		assertTrue(ai1.winningMoves.isEmpty());
		assertTrue(ai1.blockingMoves.isEmpty());
		assertFalse(game1.getPossibleMoves().contains(new Move(next.getX(), next.getY())));
	}

}

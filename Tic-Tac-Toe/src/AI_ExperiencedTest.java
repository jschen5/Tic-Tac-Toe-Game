import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class AI_ExperiencedTest {

	@Test
	public void testAI_Experienced() {
		Board game1 = new Board();
		AI_Experienced ai1 = new AI_Experienced("Joey", 'x', 'o', game1);
		assertTrue(ai1.getName().equals("Joey"));
		assertTrue(ai1.getSymbol() == 'x');
		assertTrue(ai1.symbolPlayer == 'o');
		assertTrue(ai1.getBrd().equals(game1));
		assertTrue(ai1.winningMoves.equals(new ArrayList<Move>()));
		assertTrue(ai1.blockingMoves.equals(new ArrayList<Move>()));
		assertTrue(ai1.cornerMoves.equals(new ArrayList<Move>()));
	}
	
	@Test
	public void testGenerateCornerMoves() {
		Board game1 = new Board();
		AI_Experienced ai1 = new AI_Experienced("Joey", 'x', 'o', game1);
		
		ai1.generateCornerMoves();
		assertTrue(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 0, 0)));
		assertTrue(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 0, 2)));
		assertTrue(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 2, 0)));
		assertTrue(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 2, 2)));
		
		game1.update(new Move(ai1.getSymbol(), 0, 0));
		
		ai1.generateCornerMoves();
		assertFalse(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 0, 0)));
		assertTrue(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 0, 2)));
		assertTrue(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 2, 0)));
		assertTrue(ai1.cornerMoves.contains(new Move(ai1.getSymbol(), 2, 2)));
	}
	
	@Test
	public void testNextMove() {
		Board game1 = new Board();
		AI_Experienced ai1 = new AI_Experienced("Joey", 'x', 'o', game1);
		ArrayList<Move> corners = new ArrayList<Move>();
		
		corners.add(new Move(ai1.getSymbol(), 0, 0));
		corners.add(new Move(ai1.getSymbol(), 0, 2));
		corners.add(new Move(ai1.getSymbol(), 2, 0));
		corners.add(new Move(ai1.getSymbol(), 2, 2));
		
		Move testMv = ai1.nextMove();
		assertTrue(corners.contains(testMv) || testMv.equals(new Move(ai1.getSymbol(), 1, 1)));	//first move should either be a corner or middle
		
		game1.resetBoard();
		
		game1.update(new Move('o', 1, 1));
		testMv = ai1.nextMove();
		assertTrue(corners.contains(testMv));
		
		game1.resetBoard();
		
		game1.update(new Move('o', 0, 0));
		testMv = ai1.nextMove();
		assertTrue(testMv.equals(new Move(ai1.getSymbol(), 1, 1)));
		
		game1.resetBoard();
		
		game1.update(new Move('o', 0, 0));
		game1.update(new Move('o', 1, 0));
		game1.update(new Move('o', 1, 1));
		
		testMv = ai1.nextMove();
		assertTrue(testMv.equals(new Move(ai1.getSymbol(), 2, 0)) || testMv.equals(new Move(ai1.getSymbol(), 2, 2)));	//should make move that is blocking and corner
		game1.update(testMv);
		
		testMv = ai1.nextMove();
		assertTrue(testMv.equals(new Move(ai1.getSymbol(), 2, 0)) || testMv.equals(new Move(ai1.getSymbol(), 2, 2)));	//should make move that is blocking and corner
		game1.update(testMv);
		
		testMv = ai1.nextMove();
		assertTrue(testMv.equals(new Move(ai1.getSymbol(), 2, 1)));	//should make winning move
		
		game1.undoMove(new Move(ai1.getSymbol(), 2, 1));
		game1.update(new Move('o', 2, 1));
		
		testMv = ai1.nextMove();
		assertTrue(testMv.equals(new Move(ai1.getSymbol(), 1, 2)) || testMv.equals(new Move(ai1.getSymbol(), 0, 1)));	//should make blocking but not corner move

		game1.update(new Move(ai1.getSymbol(), 1, 2));
		testMv = ai1.nextMove();
		assertTrue(testMv.equals(new Move(ai1.getSymbol(), 0, 2)));	//should make winning move
		game1.update(testMv);
		
	}

}

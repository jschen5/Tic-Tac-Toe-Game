import static org.junit.Assert.*;

import org.junit.Test;


public class AI_BasicTest {

	@Test
	public void testAI_Basic() {
		Board game1 = new Board();
		AI_Basic ai1 = new AI_Basic("Joey", 'x', 'o', game1);
		assertTrue(ai1.getName().equals("Joey"));
		assertTrue(ai1.getSymbol() == 'x');
		assertTrue(ai1.symbolPlayer == 'o');
		assertTrue(ai1.getBrd().equals(game1));
	}

	@Test
	public void testNextMove() {
		Board game1 = new Board();
		AI_Basic ai1 = new AI_Basic("Joey", 'x', 'o', game1);
		Move nextMv = ai1.nextMove();
		assertTrue(game1.getPossibleMoves().contains(nextMv));	//at this point in time, possibleMoves contains the move with the AIsymbol
																//usually will be followed by update(...) call to remove nextMv from possibleMoves
		assertTrue(game1.getBoard()[nextMv.getX()][nextMv.getY()] == 'n');
	}

}

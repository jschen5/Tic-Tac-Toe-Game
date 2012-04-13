import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {

	@Test
	public void testBoard() {
		Board testBd = new Board();
		for (int i = 0; i < Board.dim; i++) {
			for (int k = 0; k < Board.dim; k++) {
				assertTrue(testBd.getBoard()[i][k] == 'n');	//also tests getBoard() method
			}
		}
	}

	@Test
	public void testUpdate() {
		Board testBd = new Board();
		
		Move testMv = new Move('x', 0, 0);
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[0][0] == 'x');
		
		testMv = new Move('x', 1, 1);
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[1][1] == 'x');
		
		testMv = new Move('x', 2, 2);
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[2][2] == 'x');
		
		testMv = new Move('o', 0, 0);
		assertTrue(testBd.update(testMv) == 0);
		assertTrue(testBd.getBoard()[0][0] == 'x');
		
		testMv = new Move('o', 1, 0);
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[1][0] == 'o');
	}

	@Test
	public void testVictConfig() {
		Board testBd = new Board();
		
		//diagonal top left to bottom right
		Move testMv = new Move('x', 0, 0);
		testBd.update(testMv);
		testMv = new Move('x', 1, 1);
		testBd.update(testMv);
		testMv = new Move('x', 2, 2);
		testBd.update(testMv);
		assertTrue(testBd.victConfig() == 'x');
		
		testBd.resetBoard();
		
		//diagonal bottom left to top right
		testMv = new Move('o', 2, 0);
		testBd.update(testMv);
		testMv = new Move('o', 1, 1);
		testBd.update(testMv);
		testMv = new Move('o', 0, 2);
		testBd.update(testMv);
		assertTrue(testBd.victConfig() == 'o');
		
		//test all other vertical and horizontal cases
		for (int i = 0; i < Board.dim; i++) {
			testBd.resetBoard();
			testMv = new Move('x', i, 0);
			testBd.update(testMv);
			testMv = new Move('x', i, 1);
			testBd.update(testMv);
			testMv = new Move('x', i, 2);
			testBd.update(testMv);
			assertTrue(testBd.victConfig() == 'x');
			
			testBd.resetBoard();
			testMv = new Move('o', 0, i);
			testBd.update(testMv);
			testMv = new Move('o', 1, i);
			testBd.update(testMv);
			testMv = new Move('o', 2, i);
			testBd.update(testMv);
			assertTrue(testBd.victConfig() == 'o');
		}
		
	}

	
}

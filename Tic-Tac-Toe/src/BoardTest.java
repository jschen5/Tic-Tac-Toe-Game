import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class BoardTest {

	//all tests assume that Move object is functional and error free
	
	@Test
	public void testBoard() {
		Board testBd = new Board();
		for (int i = 0; i < Board.dim; i++) {
			for (int k = 0; k < Board.dim; k++) {
				assertTrue(testBd.getBoard()[i][k] == 'n');	//also tests getBoard() method
			}
		}
		for (int i = 0; i < Board.dim; i++) {
			for (int j = 0; j < Board.dim; j++) {
				assertTrue(testBd.getPossibleMoves().contains(new Move(i, j)));
			}
		}
		assertTrue(testBd.getPastMoves().equals(new ArrayList<Move>()));
	}

	@Test
	public void testUpdate() {
		Board testBd = new Board();
		
		Move testMv = new Move(0, 0);
		assertTrue(testBd.getPossibleMoves().contains(testMv));
		testMv.setMark('x');
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[0][0] == 'x');
		assertTrue(testBd.getPastMoves().contains(testMv));
		testMv.setMark('n');
		assertFalse(testBd.getPossibleMoves().contains(testMv));
		
		testMv = new Move(1, 1);
		assertTrue(testBd.getPossibleMoves().contains(testMv));
		testMv.setMark('x');
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[1][1] == 'x');
		assertTrue(testBd.getPastMoves().contains(testMv));
		testMv.setMark('n');
		assertFalse(testBd.getPossibleMoves().contains(testMv));
		
		testMv = new Move(2, 2);
		assertTrue(testBd.getPossibleMoves().contains(testMv));
		testMv.setMark('x');
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[2][2] == 'x');
		assertTrue(testBd.getPastMoves().contains(testMv));
		testMv.setMark('n');
		assertFalse(testBd.getPossibleMoves().contains(testMv));
		
		testMv = new Move(0, 0);
		assertFalse(testBd.getPossibleMoves().contains(testMv));
		testMv.setMark('o');
		assertTrue(testBd.update(testMv) == 0);
		assertTrue(testBd.getBoard()[0][0] == 'x');
		assertFalse(testBd.getPastMoves().contains(testMv));
		testMv.setMark('n');
		assertFalse(testBd.getPossibleMoves().contains(testMv));
		
		testMv = new Move(1, 0);
		assertTrue(testBd.getPossibleMoves().contains(testMv));
		testMv.setMark('o');
		assertTrue(testBd.update(testMv) == 1);
		assertTrue(testBd.getBoard()[1][0] == 'o');
		assertTrue(testBd.getPastMoves().contains(testMv));
		testMv.setMark('n');
		assertFalse(testBd.getPossibleMoves().contains(testMv));
	}
	
	@Test
	public void testUndoMove() {
		Board testBd = new Board();
		Move testMv = new Move('x', 0, 1);
		testBd.update(testMv);
		
		assertTrue(testBd.getBoard()[testMv.getX()][testMv.getY()] == 'x');
		assertFalse(testBd.getPossibleMoves().contains(new Move(testMv.getX(), testMv.getY())));
		assertTrue(testBd.getPastMoves().contains(testMv));
		
		testBd.undoMove(testMv);
		assertTrue(testBd.getBoard()[testMv.getX()][testMv.getY()] == 'n');
		assertTrue(testBd.getPossibleMoves().contains(new Move(testMv.getX(), testMv.getY())));
		assertFalse(testBd.getPastMoves().contains(testMv));
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

	@Test
	public void testEqualsAndClone() {
		Board testBd = new Board();
		Board failBd = new Board();
		
		Move testMv = new Move('o', 0, 1);
		testBd.update(testMv);
		testMv = new Move('o', 1, 1);
		testBd.update(testMv);
		testMv = new Move('o', 2, 1);
		testBd.update(testMv);
		
		Board copyBd = testBd.clone();
		
		assertTrue(testBd.equals(copyBd));
		assertFalse(failBd.equals(copyBd));
	}
	
}

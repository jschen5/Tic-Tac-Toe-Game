import static org.junit.Assert.*;

import org.junit.Test;


public class MoveTest {

	@Test
	public void testMove() {
		
		//'O' = 79; 'X' = 88; 'o' = 111; 'x' = 120;
		
		Move testMv;
		
		//test valid/invalid symbols
		for (int i = 32; i < 128; i++) {
			try {
				testMv = new Move((char) i, 0, 0);
				char testMark = testMv.getMark();
				//no exception thrown
				assertTrue((testMark == 'o') || (testMark == 'x'));
			} catch (IllegalArgumentException e) {
				assertFalse((i == 111) || (i == 120));
			}
		}
		
		//test valid/invalid coordinates
		for (int i = 0; i < Board.dim + 2; i++) {
			//test positive bounds
			for (int k = 0; k < Board.dim + 2; k++) {
				try {
					testMv = new Move('x', i, k);
					//no exception thrown
					assertTrue((i < Board.dim) || (k < Board.dim));
				} catch (IllegalArgumentException e) {
					assertTrue((i >= Board.dim) || (k >= Board.dim));
				}
			}
		}
		
		//test negatives
		//nTest0
		try {
			testMv = new Move('x', -1, 0);
		} catch (IllegalArgumentException e) {
			assertTrue("nTest0 failed\n", true);
		}
		//nTest1
		try {
			testMv = new Move('x', 0, -1);
		} catch (IllegalArgumentException e) {
			assertTrue("nTest1 failed\n", true);
		}
		//nTest2
		try {
			testMv = new Move('x', -1, -1);
		} catch (IllegalArgumentException e) {
			assertTrue("nTest2 failed\n", true);
		}
		
	}
	
	
	public void testEquals() {
		Move mv1 = new Move('x', 0, 1);
		assertTrue(mv1.equals(new Move('x', 0, 1)));
		assertFalse(mv1.equals(new Move('o', 0, 1)));
		assertFalse(mv1.equals(new Move('x', 1, 1)));
		assertFalse(mv1.equals(new Move('x', 0, 0)));
	}
	
}

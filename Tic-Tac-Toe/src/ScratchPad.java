import static org.junit.Assert.assertTrue;


public class ScratchPad {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 120;
		System.out.println((char) b);
		
		String str = "x";
		System.out.println(str.matches("[nox]"));
		
		char a = str.toCharArray()[0];
		System.out.println(a);
		
		char[] str1 = {'x', 'o', 'n'};
		
		System.out.println(str1.toString());
		
		System.out.println();
		
		Board testBd = new Board();
		Move testMv = new Move('o', 1, 0);
		testBd.update(testMv);
		testMv = new Move('o', 1, 1);
		testBd.update(testMv);
		testMv = new Move('o', 1, 2);
		testBd.update(testMv);
		
		for (int i = 0; i < 3; i++) {
			System.out.println(testBd.getBoard()[i]);
		}
		
		System.out.println("");
		
		char[][] testBdT = Board.transpose(testBd.getBoard());
		for (int i = 0; i < 3; i++) {
			System.out.println(testBdT[i]);
		}
		
		
		
	}

}

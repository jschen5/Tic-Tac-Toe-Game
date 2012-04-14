
public class Move {
	
	//valid symbols: x, o, n (n = none)
	
	private char symbol;
	private int x;
	private int y;
	
	public Move(int horiz, int vert) {	//if this constructor is called, must be followed by call to setMark() when dequeuing
		x = horiz;
		y = vert;
		symbol = 'n';
	}
	
	public Move(Character mark, int horiz, int vert) {	// needs to be used in try/catch statement
		
		String stdMark = mark.toString();
		stdMark = stdMark.replaceAll(" ", "");		//remove whitespaces
		stdMark = stdMark.toLowerCase();			//make lowercase
		
		if (stdMark.equals("")) {	//check to see if stdMark is empty (mark was only whitespace)
			throw new IllegalArgumentException("Player symbol must only be either 'x' or 'o'.");
		}
		if (!stdMark.matches("[ox]")) {	//check to see if mark is either 'x' or 'o'
			throw new IllegalArgumentException("Player symbol must only be either 'x' or 'o'.");
		}
		if ((horiz >= Board.dim) || (vert >= Board.dim)) {
			throw new IllegalArgumentException("Destination xy-coordinates must be less than " + Board.dim);
		}
		if ((horiz < 0) || (vert < 0)) {
			throw new IllegalArgumentException("Destination xy-coordinates must be positive");
		}
		
		symbol = stdMark.toCharArray()[0];
		x = horiz;
		y = vert;
	}
	
	public Move clone() {
		Move cloneMv = new Move(x, y);
		cloneMv.setMark(symbol);
		return cloneMv;
	}
	
	public boolean xEquals(Move mv) {
		return (x == mv.getX());
	}
	
	public boolean yEquals(Move mv) {
		return (y == mv.getY());
	}
	
	public boolean markEquals(Move mv) {
		return (symbol == mv.getMark());
	}
	
	public boolean equals(Object obj) {
		Move mv = (Move) obj;
		return (xEquals(mv) && yEquals(mv) && markEquals(mv));
	}
	
	
	////////////////////////////
	///////Getter Methods///////
	////////////////////////////
	
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
	}
	
	
	public char getMark() {
		return symbol;
	}
	
	
	////////////////////////////
	///////Setter Methods///////
	////////////////////////////
	
	public void setMark(char c) {
		symbol = c;
	}
	
	
}	//end of move class

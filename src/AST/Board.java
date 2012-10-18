package AST;

public class Board {
	
	public static final int BOARDSIZE = 5;
	
	public int[][] boardValues; 

	public Board() {
		
		boardValues = new int[BOARDSIZE][BOARDSIZE];

		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				boardValues[i][j] = 100 / (i + j + 5);
			}
		}
	}
	
	public int getSize()
	{
		return BOARDSIZE;
	}
	
	public void print()
	{
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				System.out.print(boardValues[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
	}

}

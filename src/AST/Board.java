package AST;

import java.util.Random;

public class Board {
	
	public static final int BOARDSIZE = 5;
	
	private int[][] boardValues; 

	public Board() {
		
		//boardValues = new int[BOARDSIZE][BOARDSIZE];
		
//		int[][] temp = { {25,13,19,18,4},	
//				{25	,7	,21,	23,	28},	
//				{3	,22,	0,	1,	25},	
//				{1	,14,	24,	25,	16},	
//				{21	,29,	9	,8	,15}};
		
		int[][] Kalamazoo = { { 1, 1, 1, 1, 1 }, { 1, 2, 2, 2, 1 },
				{ 1, 2, 3, 2, 1 }, { 1, 2, 2, 2, 1 }, { 1, 1, 1, 1, 1 } };

		int[][] Peoria = { { 99, 1, 99, 1, 99 }, { 1, 99, 1, 99, 1 },
				{ 99, 1, 99, 1, 99 }, { 1, 99, 1, 99, 1 }, { 99, 1, 99, 1, 99 } };

		int[][] Piqua = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };

		int[][] Punxsutawney = { { 10, 5, 2, 1, 1 }, { 10, 5, 2, 1, 1 },
				{ 10, 5, 2, 1, 1 }, { 10, 5, 2, 1, 1 }, { 10, 5, 2, 1, 1 } };

		int[][] Wallawalla = { { 1, 10, 1, 1, 2 }, { 10, 1, 2, 5, 10 },
				{ 5, 1, 1, 3, 7 }, { 2, 1, 1, 1, 3 }, { 1, 2, 2, 1, 2 } };
		
		boardValues = Piqua;
		
//		generateRandomValues();

//		for (int i = 0; i < BOARDSIZE; i++) {
//			for (int j = 0; j < BOARDSIZE; j++) {
//				boardValues[i][j] = 100 / (i + j + 5);
//			}
//		}
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
	
	public int getValue(int row, int col){
		return boardValues[row][col];
	}
	
	public void generateRandomValues()
	{
		Random generator = new Random();
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				boardValues[i][j] = generator.nextInt(30);
			}
		}
	}
	
	public int getSum()
	{
		int sum=0;
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				sum+=boardValues[i][j];
			}
		}
		return sum;
	}
	

}

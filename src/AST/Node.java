package AST;

import java.util.HashSet;
import java.util.Set;

public abstract class Node {
	
	public static final int P1OP = 1;
	public static final int P2OP = 2;
	public static final int UNOP = 0;
	
	public static final Board board = new Board();
	
	
	public Set<int[][]> results; //all the children configuration
		
	public int[][] currentOccupancy;

	public Set<Node> children;
	
	public abstract boolean checkBlitz(int y, int x);
	
	public abstract void Blitz(int[][] op, int y, int x);
	
	public abstract int MinMax();
	
	public abstract void generateResults();
	
	public Node() {
		currentOccupancy = new int[board.getSize()][board.getSize()];
		
		for(int i=0; i<currentOccupancy.length;i++)
		{
			for (int j=0;j<currentOccupancy.length;j++)
			{
				currentOccupancy[i][j]=UNOP;
			}
		}
		
		children = new HashSet<Node>();
		results= new HashSet<int[][]>();
		
	}
	
	public Node(int[][] occupancy) {
		currentOccupancy = occupancy;
		children = new HashSet<Node>();
		results= new HashSet<int[][]>();
	}
	

	
	
	public boolean isTerminal()
	{
		for(int i=0; i<currentOccupancy.length;i++)
		{
			for (int j=0;j<currentOccupancy.length;j++)
			{
				if (currentOccupancy[i][j]==UNOP)
				{
					return false;
				}
			}
		} 
		
		return true;
	}
	
	
	
	
	
	public int getUtility() {
		int sum =0 ;
		for (int i=0; i<currentOccupancy.length;i++)
		{
			for (int j=0;j<currentOccupancy.length;j++)			
			{
				if ( currentOccupancy[i][j]==P1OP)
				{
					sum += board.boardValues[i][j];
				}
			}
		}
		return sum;
	}



}

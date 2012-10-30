package AST;

import java.util.HashSet;
import java.util.Set;

public abstract class Node {
	
	public static final int P1OP = 1;
	public static final int P2OP = 2;
	public static final int UNOP = 0;
	
	public static final int DEPTHLIMIT = 4;
	
	public static final Board board = new Board();
	
	public Node choice;
	
	private boolean onPath;
	
	public int currDepth;
		
	public int[][] currentOccupancy;

	public Set<Node> children;
	
	public abstract boolean checkBlitz(int[][] op, int y, int x);
	
	public abstract void Blitz(int[][] op, int y, int x);
	
	public abstract int MinMax();
	
	public abstract void generateResults(Set<int[][]> results);
	
	
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
		onPath = false;
		currDepth = 0;
		
		
	}
	
	public Node(int[][] occupancy, int newDepth) {
		currentOccupancy = occupancy;
		children = new HashSet<Node>();
		onPath = false;
		currDepth=newDepth;
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
					sum += board.getValue(i,j);
				}
			}
		}
		return sum;
	}

	public void markOnPath()
	{
		onPath = true;
	}
	
	public void printOccupancy()
	{
		for (int i=0; i<currentOccupancy.length;i++)
		{
			for (int j=0; j < currentOccupancy.length; j++)
			{
				System.out.format("%d ",currentOccupancy[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();
		
	}
	
	public boolean isReachingDepthLimit()
	{
		if (currDepth >= DEPTHLIMIT)
		{
			return true;
		}
		return false;
	}
	
	public int evaluationFunction()
	{
		int sum = 0;
		for (int i=0; i<board.getSize() ;i++){
			for (int j = 0; j <board.getSize(); j++) {
				if (currentOccupancy[i][j]==UNOP)
				{
					sum+=board.getValue(i, j);
				}
			}
		}
		
		return sum/2 + getUtility();
	}


}

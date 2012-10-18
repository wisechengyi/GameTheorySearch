package AST;

import java.util.HashSet;
import java.util.Set;

public class Node {
	
	public static final Board board = new Board();
	
	public int[][] currentOccupancy;

	public Set<Node> children;
	
	public Node() {
		currentOccupancy = new int[board.getSize()][board.getSize()];
		
		for(int i=0; i<currentOccupancy.length;i++)
		{
			for (int j=0;j<currentOccupancy.length;j++)
			{
				currentOccupancy[i][j]=0;
			}
		}
		
		children = new HashSet<Node>();
		
	}

}

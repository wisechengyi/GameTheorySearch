package AST;

import java.util.HashSet;
import java.util.Set;

public class maxNode extends Node {

	public maxNode() {
		super();
	}
	
	public maxNode(int[][] op) {
		super(op);
	}

	
	
	@Override
	public int MinMax()
	{
		
		
		//determine if this is a terminal state
		
		if (isTerminal())
		{
			//return utility value
			return getUtility();
		}
		
		
		//if not
		//for each action, pass it to min max next level
		
		int v = Integer.MIN_VALUE;
		generateResults();
		
		for (int[][] r : results)
		{
			Node theMinNode =  new minNode(r);
			v = Math.max(v,theMinNode.MinMax());
		}
		
		return v;
	}
				


	@Override
	public void generateResults() {
		
		for (int i = 0; i < currentOccupancy.length; i++) {
			for (int j = 0; j < currentOccupancy.length; j++) {
				if (currentOccupancy[i][j] == UNOP) {

					// create new occupancy array
					int[][] result = new int[board.getSize()][board.getSize()];
					for (int k = 0; k < currentOccupancy.length; k++) {
						for (int l = 0; l < currentOccupancy.length; l++) {
							result[k][l] = currentOccupancy[k][l];
						}
					}
					result[i][j] = MAXOP;

					// check if current i,j 's neighbor are max player
					// if so, its a blitz
					if (checkBlitz(i, j)) {
						Blitz(result, i, j);
						results.add(result);
					}
					// if not, its a drop
					else {
						results.add(result);
					}
				}
			}
		}
	}







	@Override
	public boolean checkBlitz(int y, int x) {
		// TODO Auto-generated method stub
		
		int left = x - 1;
		int up = y - 1;
		int right = x+1;
		int down = y+1;
		
		if (left>=0)
		{
			if (currentOccupancy[y][left]==MAXOP)
			{
				return true;
			}
		}
		
		if (right<currentOccupancy.length)
		{
			if (currentOccupancy[y][right]==MAXOP)
			{
				return true;
			}
		}
		
		if (up>=0)
		{
			if (currentOccupancy[up][x]==MAXOP)
			{
				return true;
			}
		}
		
		if (down<currentOccupancy.length)
		{
			if (currentOccupancy[down][x]==MAXOP)
			{
				return true;
			}
		}
		
		return false;
	}



	@Override
	public void Blitz(int[][] op, int y, int x) {
		int left = x - 1;
		int up = y - 1;
		int right = x+1;
		int down = y+1;
		
		if (left>=0)
		{
			if (currentOccupancy[y][left]==MINOP)
			{
				currentOccupancy[y][left]=MAXOP;
			}
		}
		
		if (right<currentOccupancy.length)
		{
			if (currentOccupancy[y][right]==MINOP)
			{
				currentOccupancy[y][right]=MAXOP;
			}
		}
		
		if (up>=0)
		{
			if (currentOccupancy[up][x]==MINOP)
			{
				currentOccupancy[up][x]=MAXOP;
			}
		}
		
		if (down<currentOccupancy.length)
		{
			if (currentOccupancy[down][x]==MINOP)
			{
				currentOccupancy[down][x]=MAXOP;
			}
		}
		
	}
}

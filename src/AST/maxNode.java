package AST;

import java.util.HashSet;
import java.util.Set;

// Player 1: Nickname Max
public class maxNode extends Node {

	public maxNode() {
		super();
	}
	
	public maxNode(int[][] op, int newDepth) {
		super(op, newDepth);
	}

	public maxNode(int[][] op) {
		super(op, 0);
	}
	
	@Override
	public int minMax()
	{
		
		
		//determine if this is a terminal state
		
		if (isTerminal())
		{
			//return utility value
			return getUtility();
		}
		
		if (isMinMaxReachingDepthLimit())
		{
			return evaluationFunction();
		}
		
		//if not 
		//for each action, pass it to min max next level
		
		int maxValue = Integer.MIN_VALUE;
		
		
		Set<int[][]> results= new HashSet<int[][]>();
		generateResults(results);
		
		
		
		for (int[][] r : results)
		{
			Node theMinNode =  new minNode(r,currDepth+1); //create a child node
			children.add(theMinNode);
			
			int minMaxValue = theMinNode.minMax();
			
			if (minMaxValue > maxValue) {
				maxValue = minMaxValue;
				choice = theMinNode;
			}
		
		}
		
		choice.markOnPath(); //set the max child to be onPath
	
		return maxValue;
	}
				


	@Override
	public void generateResults(Set<int[][]> results) {
		
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
					result[i][j] = P1OP;

					// check if current i,j 's neighbor are max player
					// if so, its a blitz
					if (checkBlitz(result, i, j)) {
						blitz(result, i, j);
					}
					results.add(result);
				}
			}
		}
	}







	@Override
	public boolean checkBlitz(int[][] op, int y, int x) {
		// TODO Auto-generated method stub
		
		int left = x - 1;
		int up = y - 1;
		int right = x+1;
		int down = y+1;
		
		if (left>=0)
		{
			if (op[y][left]==P1OP)
			{
				return true;
			}
		}
		
		if (right<op.length)
		{
			if (op[y][right]==P1OP)
			{
				return true;
			}
		}
		
		if (up>=0)
		{
			if (op[up][x]==P1OP)
			{
				return true;
			}
		}
		
		if (down<op.length)
		{
			if (op[down][x]==P1OP)
			{
				return true;
			}
		}
		
		return false;
	}



	@Override
	public void blitz(int[][] op, int y, int x) {
		int left = x - 1;
		int up = y - 1;
		int right = x+1;
		int down = y+1;
		
		if (left>=0)
		{
			if (op[y][left]==P2OP)
			{
				op[y][left]=P1OP;
			}
		}
		
		if (right<op.length)
		{
			if (op[y][right]==P2OP)
			{
				op[y][right]=P1OP;
			}
		}
		
		if (up>=0)
		{
			if (op[up][x]==P2OP)
			{
				op[up][x]=P1OP;
			}
		}
		
		if (down<op.length)
		{
			if (op[down][x]==P2OP)
			{
				op[down][x]=P1OP;
			}
		}
		
	}

	@Override
	public int alphaBeta(int alpha, int beta) {
		// TODO Auto-generated method stub
	//determine if this is a terminal state
		
		if (isTerminal())
		{
			//return utility value
			return getUtility();
		}
		
		if (isAlphaBetaReachingDepthLimit())
		{
			return evaluationFunction();
		}
		
		//if not 
		//for each action, pass it to min max next level
		
		int maxValue = Integer.MIN_VALUE;
		
		
		Set<int[][]> results= new HashSet<int[][]>();
		generateResults(results);
		
		
		
		for (int[][] r : results)
		{
			Node theMinNode =  new minNode(r,currDepth+1); //create a child node
			children.add(theMinNode);
			
			int minMaxValue = theMinNode.alphaBeta(alpha,beta);
			
			if (minMaxValue > maxValue) {
				maxValue = minMaxValue;
				choice = theMinNode;	
			}
			
			if (maxValue >= beta) {
				return maxValue;
			}
			
			alpha = Math.max(alpha, maxValue);
		
		}
		
//		choice.markOnPath(); //set the max child to be onPath
	
		return maxValue;
	}
}

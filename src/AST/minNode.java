package AST;

import java.util.HashSet;
import java.util.Set;


//Player 2: Nickname Min
public class minNode extends Node {

	public minNode() {
		super();
	}

	public minNode(int[][] op, int newDepth) {
		super(op, newDepth);
	}
	
	public minNode(int[][] op) {
		super(op, 0);
	}


	@Override
	public int minMax() {
		
		incrementNodeCount();

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
				
				int minValue = Integer.MAX_VALUE;
				
				Set<int[][]> results= new HashSet<int[][]>();
				generateResults(results);
				
				
				for (int[][] r : results)
				{
					Node theMaxNode =  new maxNode(r,currDepth+1);
					children.add(theMaxNode);
					
					int minMaxValue = theMaxNode.minMax();
					
					if (minMaxValue < minValue) {
						minValue = minMaxValue;
						choice = theMaxNode;
					}
				}
				
				choice.markOnPath();
				
				return minValue;
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
					
					result[i][j] = P2OP;

					// check if current i,j 's neighbor are min player
					// if so, its a blitz
					if (checkBlitz(result, i, j)) {
						blitz(result, i, j);
					}
					// if not, its a drop
						results.add(result);
				}
			}
		}
	}

	@Override
	public boolean checkBlitz(int[][] op, int y, int x) {
		
		int left = x - 1;
		int up = y - 1;
		int right = x+1;
		int down = y+1;
		
		if (left>=0)
		{
			if (op[y][left]==P2OP)
			{
				return true;
			}
		}
		
		if (right<op.length)
		{
			if (op[y][right]==P2OP)
			{
				return true;
			}
		}
		
		if (up>=0)
		{
			if (op[up][x]==P2OP)
			{
				return true;
			}
		}
		
		if (down<op.length)
		{
			if (op[down][x]==P2OP)
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
			if (op[y][left]==P1OP)
			{
				op[y][left]=P2OP;
			}
		}
		
		if (right<op.length)
		{
			if (op[y][right]==P1OP)
			{
				op[y][right]=P2OP;
			}
		}
		
		if (up>=0)
		{
			if (op[up][x]==P1OP)
			{
				op[up][x]=P2OP;
			}
		}
		
		if (down<op.length)
		{
			if (op[down][x]==P1OP)
			{
				op[down][x]=P2OP;
			}
		}
		
	}

	@Override
	public int alphaBeta(int alpha, int beta) {
		
		incrementNodeCount();
		
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
		
		int minValue = Integer.MAX_VALUE;
		
		Set<int[][]> results= new HashSet<int[][]>();
		generateResults(results);
		
		
		for (int[][] r : results)
		{
			Node theMaxNode =  new maxNode(r,currDepth+1);
			children.add(theMaxNode);
			
			int minMaxValue = theMaxNode.alphaBeta(alpha,beta);
			
			if (minMaxValue < minValue) {
				minValue = minMaxValue;
				choice = theMaxNode;
			}
			
			if (minValue <= alpha)
			{
				return minValue;
			}
			
			beta = Math.min(beta,minValue);
		}
		
//		choice.markOnPath();
		
		return minValue;
	}
}


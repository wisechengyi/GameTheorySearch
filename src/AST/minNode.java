package AST;

public class minNode extends Node {

	public minNode() {
		super();
	}

	public minNode(int[][] op) {
		super(op);
	}

	@Override
	public int MinMax() {

		//determine if this is a terminal state
		
				if (isTerminal())
				{
					//return utility value
					return getUtility();
				}
				
				
				//if not
				//for each action, pass it to min max next level
				
				int v = Integer.MAX_VALUE;
				generateResults();
				
				for (int[][] r : results)
				{
					Node theMaxNode =  new maxNode(r);
					v = Math.min(v,theMaxNode.MinMax());
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
					result[i][j] = MINOP;

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
		
		int left = x - 1;
		int up = y - 1;
		int right = x+1;
		int down = y+1;
		
		if (left>=0)
		{
			if (currentOccupancy[y][left]==MINOP)
			{
				return true;
			}
		}
		
		if (right<currentOccupancy.length)
		{
			if (currentOccupancy[y][right]==MINOP)
			{
				return true;
			}
		}
		
		if (up>=0)
		{
			if (currentOccupancy[up][x]==MINOP)
			{
				return true;
			}
		}
		
		if (down<currentOccupancy.length)
		{
			if (currentOccupancy[down][x]==MINOP)
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
			if (currentOccupancy[y][left]==MAXOP)
			{
				currentOccupancy[y][left]=MINOP;
			}
		}
		
		if (right<currentOccupancy.length)
		{
			if (currentOccupancy[y][right]==MAXOP)
			{
				currentOccupancy[y][right]=MINOP;
			}
		}
		
		if (up>=0)
		{
			if (currentOccupancy[up][x]==MAXOP)
			{
				currentOccupancy[up][x]=MINOP;
			}
		}
		
		if (down<currentOccupancy.length)
		{
			if (currentOccupancy[down][x]==MAXOP)
			{
				currentOccupancy[down][x]=MINOP;
			}
		}
		
	}
}


package test;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

import AST.*;

import org.junit.Test;

public class MinMaxTest {

	@Test
	public void test() {
		
		//start timing
		 Calendar lCDateTime = Calendar.getInstance();
	     long start = lCDateTime.getTimeInMillis();
	     
		int maxNodeTotal=0;
		int minNodeTotal=0;
		

		
		Node maxPlayer = new maxNode();
		maxPlayer.resetNodeCount();
		maxPlayer.minMax();
		int maxNodeNum = maxPlayer.getNodeCount();
		maxNodeTotal+=maxNodeNum;
		
		Node minPlayer = new minNode(maxPlayer.choice.currentOccupancy);
		minPlayer.resetNodeCount();
		minPlayer.minMax();
		int minNodeNum = minPlayer.getNodeCount();
		minNodeTotal+=minNodeNum;

		
		maxPlayer.getBoard().print();
		
		while ( true ) {
			
			System.out.format("Max: %d\n",maxNodeNum);
			maxPlayer.choice.printOccupancy();
			if (maxPlayer.choice.isTerminal())
			{
				break;
			}

			System.out.format("Min: %d\n",minNodeNum);
			minPlayer.choice.printOccupancy();
			if (minPlayer.choice.isTerminal())
			{
				break;
			}

			

			maxPlayer = new maxNode(minPlayer.choice.currentOccupancy);
			maxPlayer.resetNodeCount();
			maxPlayer.minMax();
			maxNodeNum = maxPlayer.getNodeCount();
			maxNodeTotal+=maxNodeNum;
			
			minPlayer = new minNode(maxPlayer.choice.currentOccupancy);
			minPlayer.resetNodeCount();
			minPlayer.minMax();
			minNodeNum = minPlayer.getNodeCount();
			minNodeTotal+=minNodeNum;
			
			
		}
		
		//end timing
		
		 Calendar CDateTime = Calendar.getInstance();
	     long end = CDateTime.getTimeInMillis();

		
	     int totalMoves = (maxPlayer.getBoard().getSize()*maxPlayer.getBoard().getSize());
		System.out.format("maxPlayer score: %d\n",maxPlayer.getUtility());
		
		System.out.format("minPlayer score: %d\n",maxPlayer.getBoard().getSum()-maxPlayer.getUtility());
		System.out.format("Total # of moves: %d\n",totalMoves);
		System.out.format("Total # of nodes by max: %d\n",maxNodeTotal);
		System.out.format("Total # of nodes by min: %d\n",minNodeTotal);
		
		System.out.format("The average number of nodes examined per move: %d\n",
				(minNodeTotal+maxNodeTotal)/totalMoves);
		
		System.out.format("Average amount of time per move: %d ms(including I/O)\n", 
				(end-start)/totalMoves);
		System.out.println("-----------------------------");


	     
	     
	}
	
//	@Test
//	public void test2() {
//		
//		System.out.println("test2");
//		int[][] x= { {0,0,0,0,0},	
//					{1,0,0,0,2},	
//					{0,0,0,0,0},	
//					{0,0,1,2,0},	
//					{0,0,0,0,0}};
//		
//		Node max = new maxNode(x);
//		
//		max.MinMax();
//		max.choice.printOccupancy();
//		
//		
//	
//	
//	}
}

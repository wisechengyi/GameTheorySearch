package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import AST.Node;
import AST.maxNode;
import AST.minNode;

public class AlphaBetaTest {

	@Test
	public void test() {
		
		//start timing
		 Calendar lCDateTime = Calendar.getInstance();
	     long start = lCDateTime.getTimeInMillis();
	     
		int maxNodeTotal=0;
		int minNodeTotal=0;
		

		
		Node maxPlayer = new maxNode();
		maxPlayer.resetNodeCount();
		maxPlayer.alphaBeta();
		int maxNodeNum = maxPlayer.getNodeCount();
		maxNodeTotal+=maxNodeNum;
		
		Node minPlayer = new minNode(maxPlayer.choice.currentOccupancy);
		minPlayer.resetNodeCount();
		minPlayer.alphaBeta();
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
			maxPlayer.alphaBeta();
			maxNodeNum = maxPlayer.getNodeCount();
			maxNodeTotal+=maxNodeNum;
			
			minPlayer = new minNode(maxPlayer.choice.currentOccupancy);
			minPlayer.resetNodeCount();
			minPlayer.alphaBeta();
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
	

}

package test;
import AST.*;

import org.junit.Test;

public class MinMaxTest {

	@Test
	public void test() {
		
		
		Node maxPlayer = new maxNode();
		maxPlayer.minMax();
		
		Node minPlayer = new minNode(maxPlayer.choice.currentOccupancy);
		minPlayer.minMax();
		
		maxPlayer.board.print();
		
		while ( true ) {
			if (!maxPlayer.choice.isTerminal())
			{
				System.out.println("Max:");
				maxPlayer.choice.printOccupancy();
			}
			else
			{
				System.out.println("Max:");
				maxPlayer.choice.printOccupancy();
				break;
			}
			
			if (!minPlayer.choice.isTerminal())
			{
				System.out.println("Min:");
				minPlayer.choice.printOccupancy();
			}
			else
			{
				System.out.println("Min:");
				minPlayer.choice.printOccupancy();
				break;
			}
			

			maxPlayer = new maxNode(minPlayer.choice.currentOccupancy);
			maxPlayer.minMax();
			
			minPlayer = new minNode(maxPlayer.choice.currentOccupancy);
			minPlayer.minMax();
			
			
		}
		
		System.out.format("maxPlayer util: %d\n",maxPlayer.getUtility());
		
		System.out.format("minPlayer util: %d\n",maxPlayer.board.getSum()-maxPlayer.getUtility());
	
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

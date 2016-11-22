/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       11/21/2016
 *  
 *  Programming assignment for Week 2
 *  Princeton Algorithms Part I
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int k = Integer.parseInt(args[0]);
		
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		
		while (StdIn.hasNextLine() && !StdIn.isEmpty()) 
			queue.enqueue(StdIn.readString());
		
		for (int i = 0; i < k; i++)
			StdOut.println(queue.dequeue());

	}

}

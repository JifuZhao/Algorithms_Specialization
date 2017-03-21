/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/21/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	
   public static void main(String[] args) {
	   int k = Integer.parseInt(args[0]);
	   RandomizedQueue<String> s = new RandomizedQueue<String>();
	   
	   while (!StdIn.isEmpty())
		   s.enqueue(StdIn.readString());
	   
	   for (int i = 0; i < k; i++)
		   StdOut.println(s.dequeue());
	   
   }
}

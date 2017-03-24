/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/23/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  8 Puzzle Problem
 *  Refer to some online resource
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class Solver {
	private boolean solvable;
	private Node originLast;
	
	public Solver(Board initial) {
		// find a solution to the initial board (using the A* algorithm)
		if (initial == null)
			throw new java.lang.NullPointerException();
		
		solvable = true;
		MinPQ<Node> queue = new MinPQ<Node>();
		
		queue.insert(new Node(initial, null, 0, false));
		queue.insert(new Node(initial.twin(), null, 0, true));
		
		while (!queue.isEmpty()) {
			Node processed = queue.delMin();
			
			if (!processed.isTwin)
				originLast = processed;
			
			if (processed.board.isGoal()) {
				if (processed.isTwin)
					solvable = false;
				break;
			}
			
			for (Board neighbor : processed.board.neighbors()) {
				if ((processed.pre == null) || (!processed.pre.board.equals(neighbor))) {
					queue.insert(new Node(neighbor, processed, processed.move + 1, processed.isTwin));
				}
			}
		}
		
	}
    
	private class Node implements Comparable<Node> {
		// private helper Node
		private Board board;
		private Node pre;
		private int move;
		private boolean isTwin;
		
		public Node(Board cur, Node pre, int move, boolean isTwin) {
			this.board = cur;
			this.pre = pre;
			this.move = move;
			this.isTwin = isTwin;
		}
		
		public int compareTo(Node that) {
			// compare manhattan distance
			int manhattan1 = this.board.manhattan() + this.move;
			int manhattan2 = that.board.manhattan() + that.move;
			
//			// compare manhattan distance
//			int manhattan1 = this.board.hamming() + this.move;
//			int manhattan2 = that.board.hamming() + that.move;
			
			if (manhattan1 == manhattan2)
				return 0;
			else if (manhattan1 > manhattan2)
				return 1;
			else
				return -1;
		}
	}
	
	public boolean isSolvable() {
		// is the initial board solvable?
		return solvable;
	}
    
	public int moves() {
		// min number of moves to solve initial board; -1 if unsolvable
		if (isSolvable())
			return originLast.move;
		else
			return -1;
	}
    
	public Iterable<Board> solution() {
		// sequence of boards in a shortest solution; null if unsolvable
		if (!isSolvable())
			return null;
		
		Stack<Board> solutions = new Stack<Board>();
		Node current = originLast;
		
		while (current.pre != null) {
			solutions.push(current.board);
			current = current.pre;
		}
		
		solutions.push(current.board);
		Stack<Board> ans = new Stack<Board>();
		
		while (!solutions.empty())
			ans.push(solutions.pop());
		
		return ans;
	}
    
    public static void main(String[] args) {
    	// solve a slider puzzle (given below)
    	// create initial board from file
        In in = new In("src/8puzzle/puzzle04.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
        	for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       11/06/2016
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *  
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int nRow;
	private int start = 0;
	private int end;
	private WeightedQuickUnionUF qf;
	private boolean[][] opened;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException("n is smaller than 1");
		}
		nRow = n;  // number of rows
		end = n * n + 1;  // the bottom virtual site
		qf = new WeightedQuickUnionUF(n * n + 2);
		opened = new boolean[n][n];
	}
	
	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		opened[row - 1][col - 1] = true;
		int index = getIndex(row, col);  // get the index for (row, col)
		if (row == 1) qf.union(start, index);  // union top and the first row
		if (row == nRow) qf.union(end, index);  // union end and the last row
		
		// check the left neighbor
		if (col > 1 && isOpen(row, col-1)) {
			qf.union(index, getIndex(row, col-1));
		}
		
		// check the right neighbor
		if (col < nRow && isOpen(row, col+1)) {
			qf.union(index, getIndex(row, col+1));
		}
				
		// check the upper neighbor
		if (row > 1 && isOpen(row-1, col)) {
			qf.union(index, getIndex(row-1, col));
		}
				
		// check the lower neighbor
		if (row < nRow && isOpen(row+1, col)) {
			qf.union(index, getIndex(row+1, col));
		}
	}
	
	// get the index of (i, j)
	private int getIndex(int i, int j) {
		return nRow * (i - 1) + j;
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		return opened[row-1][col-1];
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row >= 1 && row <= nRow && col >= 1 && col <= nRow) {
			return qf.connected(start, getIndex(row, col));
		} else {
			throw new java.lang.IndexOutOfBoundsException();
		}
	}
	
	// does the system percolate?
	public boolean percolates() {
		return qf.connected(start, end);
	}
	
	// test client
	public static void main(String[] args) {
		Percolation test = new Percolation(2);
		test.open(1, 1);
		StdOut.println(test.isOpen(1, 1));
		StdOut.println(test.isFull(1, 1));
		StdOut.println(test.isOpen(2, 1));
		StdOut.println(test.isFull(2, 1));
		test.open(2, 1);
		StdOut.println(test.isOpen(2, 1));
		StdOut.println(test.isFull(2, 1));
		StdOut.println(test.percolates());
	}

}

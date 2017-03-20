/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/20/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int nRow;  // n by n grid
	private WeightedQuickUnionUF UF;  // weighted quick union
	private int top = 0;  // virtual top site
	private int bottom;  // virtual bottom site
	private boolean[][] opened;  // for each site
	private int openSite = 0;  // number of opened sites
	
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException("n is smaller than 1");
		}
		nRow = n;
		bottom = n * n + 1;
		UF = new WeightedQuickUnionUF(n * n + 2);
		opened = new boolean[n][n];
	}
	
	private int getIndex(int row, int col) {
		// get index at given row and column
		return nRow * (row - 1) + col;
	}
	
	public void open(int row, int col) {
		// open site (row, col) if it is not open already
		if (row < 1 || row > nRow || col < 1 || col > nRow){
			throw new java.lang.IndexOutOfBoundsException();
		}
		int index = getIndex(row, col);
		
		if (!isOpen(row, col)) {
			opened[row - 1][col - 1] = true;
			openSite ++;
		}
		
		if (row == 1) UF.union(top, index);
		if (row == nRow) UF.union(index, bottom);
		
		if (col > 1 && isOpen(row, col - 1)) UF.union(index, getIndex(row, col - 1));
		if (col < nRow && isOpen(row, col + 1)) UF.union(index, getIndex(row, col + 1));
		if (row > 1 && isOpen(row - 1, col)) UF.union(index, getIndex(row - 1, col));
		if (row < nRow && isOpen(row + 1, col)) UF.union(index, getIndex(row + 1, col));
	}
	
	public boolean isOpen(int row, int col) {
		// is site (row, col) open?
		if (row < 1 || row > nRow || col < 1 || col > nRow){
			throw new java.lang.IndexOutOfBoundsException();
		}
		return opened[row - 1][col - 1];	
	}
	
	public boolean isFull(int row, int col) {
		// is site (row, col) full?
		if (row < 1 || row > nRow || col < 1 || col > nRow){
			throw new java.lang.IndexOutOfBoundsException();
		}
		return UF.connected(top, getIndex(row, col));
	}
	
    public int numberOfOpenSites() {
    	// return the number of open sites
       return openSite;
    }
	
	public boolean percolates() {
		// does the system percolate?
		return UF.connected(top, bottom);		
	}
	
	
	public static void main(String[] args) {
		// test client
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

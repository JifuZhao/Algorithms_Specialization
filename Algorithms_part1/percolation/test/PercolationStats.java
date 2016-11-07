/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       11/06/2016
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *  
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int T;  // expriment trials
	private Percolation percolation;
	private double[] ratio;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		
		T = trials;
		ratio = new double[T];
		for (int i = 0; i < trials; i++) {
			percolation = new Percolation(n);
			
			int count = 0;
			while (!percolation.percolates()) {
				int row = StdRandom.uniform(1, n+1);
				int col = StdRandom.uniform(1, n+1);
				if (!percolation.isOpen(row, col)) {
					percolation.open(row, col);
					count++;
				}
			}
				
			double fraction = (double) count / (n * n);
			ratio[i] = fraction;
		}	
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(ratio);
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(ratio);
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(T);
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(T);
	}
	
	// test client (described below)
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		PercolationStats test = new PercolationStats(n, trials);
		StdOut.println("mean                    = " + test.mean());
        StdOut.println("stddev                  = " + test.stddev());
        StdOut.println("95% confidence interval = " + test.confidenceLo() 
        				+ ", " + test.confidenceHi());
	}
}

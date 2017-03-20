/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/20/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] fraction;
	private int times;
	private int count;
	private int row;
	private int col;
	
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		times = trials;
		fraction = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			Percolation tmp = new Percolation(n);
			count = 0;
			while (!tmp.percolates()) {
				row = StdRandom.uniform(1, n + 1);
				col = StdRandom.uniform(1, n + 1);
				if (!tmp.isOpen(row, col)) {
					tmp.open(row, col);
					count++;
				}
			}
			fraction[i] = (double) count / (n * n);
		}
	}
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(fraction);
	}
	
	
	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(fraction);
	}
	
	
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return mean() - 1.96 * stddev() / Math.sqrt(times);
	}
	
	
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + 1.96 * stddev() / Math.sqrt(times);
	}
	
	
	public static void main(String[] args) {
		// test client (described below)
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		PercolationStats test = new PercolationStats(n, trials);
		StdOut.println("mean                    = " + test.mean());
        StdOut.println("stddev                  = " + test.stddev());
        StdOut.println("95% confidence interval = " + test.confidenceLo() 
        				+ ", " + test.confidenceHi());
	}
}
/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/22/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Collinear Problem
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.ArrayList; 

public class BruteCollinearPoints {
	private int number;  // number of line segments
	private ArrayList<LineSegment> seg;  // array of line segments
	
	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		if (points == null) throw new java.lang.NullPointerException("Null input");
		
		number = 0;
		int length = points.length;
		seg = new ArrayList<LineSegment>();
		
		if (length < 4)
			return;
		
		// check null points and repeated points
		for (int i = 0; i < length; i++){
			// null points
			if (points[i] == null)
				throw new java.lang.NullPointerException("Null point"); 
			// repeated points
			for (int j = i + 1; j < length; j++) {
				if (points[i].compareTo(points[j]) == 0) 
					throw new java.lang.IllegalArgumentException("Repeated Points");
			}
		}
		
		// sort points
		Arrays.sort(points);
		for (int p = 0; p < length; p++){
			for (int q = p + 1; q < length; q++) {
				double slopePQ = points[p].slopeTo(points[q]);  // sloper of p to q
				for (int r = q + 1; r < length; r++) {
					double slopePR = points[p].slopeTo(points[r]);  // sloper of p to r
					if (slopePQ == slopePR) {
						for (int s = r + 1; s < length; s++) {
							double slopePS = points[p].slopeTo(points[s]);  // sloper of p to s
							if (slopePQ == slopePS) {
								// p, q, r, s are collinear
								seg.add(new LineSegment(points[p], points[s]));
								number++;
							}
						}
					}
				}
			}
		}
		
	}
   
	public int numberOfSegments() {
		// the number of line segments
		return number;
	}
   
	public LineSegment[] segments() {
		// the line segments
		return seg.toArray(new LineSegment[number]); 
	}
	
	public static void main(String[] args) {
		// read the n points from a file
		In in = new In("src/collinear/input6.txt");
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
   
}
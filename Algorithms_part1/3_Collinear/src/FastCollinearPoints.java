/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/22/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.ArrayList; 

public class FastCollinearPoints {
	private int number;  // number of line segments
	private ArrayList<LineSegment> seg;  // array of line segments
	private Point[] copy;  // a copy of original points
	private Point origin;  // origin point
	private Point min;  // start index
	private Point max;  // end index
	
	public FastCollinearPoints(Point[] points) {
		// finds all line segments containing 4 or more points
		if (points == null) throw new java.lang.NullPointerException("Null input");
		
		number = 0;
		int length = points.length;
		seg = new ArrayList<LineSegment>();
		
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
		
		// sort to make sure there is no repeated points being considered
		Arrays.sort(points);
		int count;
		double curSlope;
		
		for (int i = 0; i < length; i++) {
			origin = points[i];
			copy = new Point[length];
			
			for (int j = 0; j < length; j++) 
				// copy orginal points into copy for sorting
				copy[j] = points[j];
			
			// sort according to slopeOrder()
			Arrays.sort(copy, origin.slopeOrder());
			
			if (origin.compareTo(copy[1]) > 0) {
				min = copy[1];
				max = origin;
			} else {
				min = origin;
				max = copy[1];
			}
			// set count and current slope
			count = 2;
			curSlope = min.slopeTo(max);
			
			for (int k = 2; k < length; k++) {
				if (origin.slopeTo(copy[k]) == curSlope) {
					count++;
					if (copy[k].compareTo(min) < 0)
						min = copy[k];
					if (copy[k].compareTo(max) > 0)
						max = copy[k];
				} else {
					if (count >= 4 && min.compareTo(origin) == 0) {
						// since original points has been sorted, if min != origin
						// it means that we have considered this case previously
						seg.add(new LineSegment(min, max)); 
						number++;
					}
					// reset for next iteration
					if (origin.compareTo(copy[k]) > 0) {
						min = copy[k];
						max = origin;
					} else {
						min = origin;
						max = copy[k];
					}
					count = 2;
					curSlope = min.slopeTo(max);
				}
			}
			
			// need to consider the last case separately
			if (count >= 4 && min.compareTo(origin) == 0) {
				// since original points has been sorted, if min != origin
				// it means that we have considered this case previously
				seg.add(new LineSegment(min, max)); 
//				seg[number] = new LineSegment(min, max);
				number++;
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
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
	
}
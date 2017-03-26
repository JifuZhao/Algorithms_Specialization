/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/26/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Kd Tree Problem
 *  Refer to online solutions
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Stack;

public class KdTree {
	private Node root;
	private int count;
	
	public KdTree() {
		// construct an empty set of points 
		root = null;
		count = 0;
	}
	
	private static class Node {
		private Node left;
		private Node right;
		private RectHV rect;
		private Point2D value;  // the point
		private boolean isH;
		
		public Node(Point2D p, Node parent, boolean isLeftUp) {
			value = p;
			left = null;
			right = null;
			if (parent == null) {
				isH = false;
				rect = new RectHV(0, 0, 1, 1);
			} else {
				isH = !parent.isH;
				if (isH) {
					if (isLeftUp) {
						rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), 
										  parent.value.x(), parent.rect.ymax());
					} else {
						rect = new RectHV(parent.value.x(), parent.rect.ymin(), 
										  parent.rect.xmax(), parent.rect.ymax());
					}
				} else {
					if (isLeftUp) {
						rect = new RectHV(parent.rect.xmin(), parent.value.y(), 
										 parent.rect.xmax(), parent.rect.ymax());
					} else {
						rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), 
								          parent.rect.xmax(), parent.value.y());
					}
				}
			}
		}
		
		public void draw(Node parent, boolean isLeftUp) {
			StdOut.println(isLeftUp ? "leftup" : "rightdown");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.01);
			value.draw();
			StdDraw.setPenRadius();
			
			if (parent == null) {
				StdDraw.setPenColor(StdDraw.RED);
				new Point2D(value.x(), 1).drawTo(new Point2D(value.x(), 0));
			} else {
				StdOut.println(value);
				StdOut.println(parent.rect);
				
				if (parent.isH) {
					StdDraw.setPenColor(StdDraw.RED);
					new Point2D(value.x(), rect.ymin()).drawTo(new Point2D(value.x(), rect.ymax()));
				} else {
					StdDraw.setPenColor(StdDraw.BLUE);
					new Point2D(rect.xmax(), value.y()).drawTo(new Point2D(rect.xmin(), value.y()));
				}
			}
			StdDraw.pause(100);
			
			if (left != null)
				left.draw(this, true);
			
			if (right != null) 
				right.draw(this, false);
		}
		
		public boolean isAtLeftSideOfNode(Point2D p) {
			if (isH) {
				return p.y() > value.y();
			} else {
				return p.x() < value.x();
			}
		}
	}
   
	public boolean isEmpty() {
		// is the set empty? 
		return count == 0;
	}
   
	public int size() {
		// number of points in the set 
		return count;
	}
	
	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (p == null)
			throw new java.lang.NullPointerException();
		
		if (root == null) {
			root = new Node(p, null, false);
			count++;
		} else {
			Node current = root;
			while (true) {
				if (current.value.compareTo(p) == 0) 
					return;
				if (current.isAtLeftSideOfNode(p)) {
					if (current.left == null) {
						current.left = new Node(p, current, true);
						count++;
						break;
					} else {
						current = current.left;
					}
				} else {
					if (current.right == null) {
						current.right = new Node(p, current, false);
						count++;
						break;
					} else {
						current = current.right;
					}
				}
			}
		}
	}
   
	public boolean contains(Point2D p) {
		// does the set contain point p? 
		if (p == null)
			throw new java.lang.NullPointerException();
		
		Node current = root;
		while (current != null) {
			if (current.value.compareTo(p) == 0) {
				return true;
			} else if (current.isAtLeftSideOfNode(p)) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}
   
	public void draw() {
		// draw all points to standard draw 
		if (root != null) 
			root.draw(null, false);
	}
	
	private void addToStack(Stack<Point2D> stack, Node current, RectHV rect) {
		if (!rect.intersects(current.rect)) 
			return;
		
		if (current.value.x() >= rect.xmin() && current.value.x() <= rect.xmax() &&
			current.value.y() >= rect.ymin() && current.value.y() <= rect.ymax()) {
			stack.push(current.value);
		}
		
		if (current.left != null)
			addToStack(stack, current.left, rect);
		
		if (current.right != null)
			addToStack(stack, current.right, rect);
	}
	
	private Point2D searchNode(Point2D toSearch, Node current, Point2D nearestPoint) {
		if (nearestPoint == null)
			nearestPoint = current.value;
		
		double distance = nearestPoint.distanceSquaredTo(toSearch);
		double newdistance = current.value.distanceSquaredTo(toSearch);
		
		if (distance >= current.rect.distanceSquaredTo(toSearch) || distance >= newdistance) {
			if (distance > newdistance)
				nearestPoint = current.value;
			
			if ((current.isH && toSearch.y() > current.value.y()) || 
				(!current.isH && toSearch.x() < current.value.x())) {
				
				if (current.left != null)
					nearestPoint = searchNode(toSearch, current.left, nearestPoint);
				
				if (current.right != null)
					nearestPoint = searchNode(toSearch, current.right, nearestPoint);
			} else {
				if (current.right != null)
					nearestPoint = searchNode(toSearch, current.right, nearestPoint);
				
				if (current.left != null)
					nearestPoint = searchNode(toSearch, current.left, nearestPoint);
			}
		}
		return nearestPoint;
	}
   
	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle 
		if (rect == null)
			throw new java.lang.NullPointerException();
		
		Stack<Point2D> stack = new Stack<Point2D>();
		if (root != null)
			addToStack(stack, root, rect);
		return stack;
	}
   
	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty 
		if (p == null)
			throw new java.lang.NullPointerException();
		
		Point2D point = null;
		if (root != null) 
			point = searchNode(p, root, point);
		
		return point;
	}

	public static void main(String[] args) {
		// unit testing of the methods (optional) 
	}
}
/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/21/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *----------------------------------------------------------------*/

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private int n;  // number of iterms
	private Node first;  // begin of Deque
	private Node last;  // end of Deque
	
	private class Node {
		Item item;
		Node pre;
		Node next;
	}
	
	public Deque() {
		// construct an empty deque
		n = 0;
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		// is the deque empty?
		return n == 0;
	}
	
	public int size() {
		// return the number of items on the deque
		return n;
	}
	
	public void addFirst(Item item) {
		// add the item to the front
		if (item == null) throw new NullPointerException("Null item");
		
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.pre = null;
		first.next = oldFirst;
		n++;
		if (n == 1) last = first;
	}
	
	public void addLast(Item item) {
		// add the item to the end
		if (item == null) throw new NullPointerException("Null item");
		
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.pre = oldLast;
		last.next = null;
		if (isEmpty()) first = last;
		else		   oldLast.next = last;
		n++;
	}
	
	public Item removeFirst() {
		// remove and return the item from the front
		if (isEmpty()) throw new java.util.NoSuchElementException();
		
		Item item = first.item;
		first = first.next;
		n--;
		if (n == 0) last = null;
		else		first.pre = null;
		return item;
	}
	
	public Item removeLast() {
		// remove and return the item from the end
		if (isEmpty()) throw new java.util.NoSuchElementException();
		
		Item item = last.item;
		last = last.pre;
		last.next = null;
		n--;
		if (n == 0) first = last;
		return item;
	}
	
	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	public static void main(String[] args) {
		// unit testing (optional)
		Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        System.out.println(deque.removeLast());
        deque.iterator();
        System.out.println(deque.iterator().hasNext());
	}
}

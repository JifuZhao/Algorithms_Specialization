/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/21/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  Percolation Problem
 *----------------------------------------------------------------*/

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int n;  // number of items
	private Item[] s;  // array of items
	
	public RandomizedQueue() {
		// construct an empty randomized queue
		s = (Item[]) new Object[1];
		n = 0;
	}
	
	public boolean isEmpty() {
		// is the queue empty?
		return n == 0;
	}
	
	public int size() {
		// return the number of items on the queue
		return n;
	}
	
	public void enqueue(Item item) {
		// add the item
		if (item == null) throw new NullPointerException("Null item");
		
		if (n == s.length) resize(2 * s.length);
		s[n] = item;
		n++;
	}
	
	private void resize(int capacity) {
		// resize the Queue
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) 
			copy[i] = s[i];
		s = copy;
	}
	
	public Item dequeue() {
		// remove and return a random item
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int idx = StdRandom.uniform(n);
		Item item = s[idx];
		if (idx != n - 1) s[idx] = s[n - 1];
		s[n - 1] = null;
		n--;
		if (n > 0 && n == s.length / 4) resize(s.length / 2);
		return item;
	}
	
	public Item sample() {
		// return (but do not remove) a random item
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int idx = StdRandom.uniform(n);
		return s[idx];
	}
	
	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private int i = 0;
		private int[] idx;
		
		public ListIterator() {
			idx = new int[n];
			for (int j = 0; j < n; j++) {
				idx[j] = j;
			}
			StdRandom.shuffle(idx);
		}
		
		public boolean hasNext() {
			return i < n;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			Item item = s[idx[i]];
			i++;
			return item;
		}
	}
	
	public static void main(String[] args) {
		// unit testing (optional)
		RandomizedQueue<Integer> a = new RandomizedQueue<Integer>();
		a.enqueue(1);
		a.enqueue(2);
		System.out.println(a.isEmpty());
		System.out.println(a.size());
		System.out.println(a.iterator().next());
		System.out.println(a.size());
		System.out.println(a.iterator().next());
		System.out.println(a.size());
	}
}

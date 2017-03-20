/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       11/21/2016
 *  
 *  Programming assignment for Week 2
 *  Princeton Algorithms Part I
 *----------------------------------------------------------------*/
import java.util.Iterator;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private ArrayList<Item> list;
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		list = new ArrayList<Item>();
	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	// return the number of items on the queue
	public int size() {
		return list.size();
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		list.add(item);
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		int tmp = StdRandom.uniform(size());
		return list.remove(tmp);
	}
	
	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		int tmp = StdRandom.uniform(size());
		return list.get(tmp);
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	
	private class ListIterator implements Iterator<Item> {
		public boolean hasNext() {
			return !isEmpty();
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			return dequeue();
		}
	}
	
	// unit testing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

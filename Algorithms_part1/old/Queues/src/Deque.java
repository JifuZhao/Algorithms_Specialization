/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       11/21/2016
 *  
 *  Programming assignment for Week 2
 *  Princeton Algorithms Part I
 *----------------------------------------------------------------*/

import java.util.Iterator;
import java.util.LinkedList;

public class Deque<Item> implements Iterable<Item> {
	
	private LinkedList<Item> list;
	
	// construct an empty deque
	public Deque() {	
		list = new LinkedList<Item>();
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	// return the number of items on the deque
	public int size() {
		return list.size();
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		list.add(0, item);
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		list.add(item);
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		return list.remove(0);
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		return list.remove(size()-1);
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new ListIterator();
	}	
	
	private class ListIterator implements Iterator<Item> {
		public boolean hasNext() {
			return !isEmpty();
		}
		
		public Item next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			return removeFirst();
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}
	
	// unit testing
	public static void main(String[] args) {	
		Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        System.out.println(deque.removeLast());
        deque.iterator();
        System.out.println(deque.iterator().hasNext());
        
    }

}

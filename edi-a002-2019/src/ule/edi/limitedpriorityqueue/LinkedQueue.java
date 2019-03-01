package ule.edi.limitedpriorityqueue;

public class LinkedQueue<T> implements QueueADT<T> {
	
	protected static class Node<D> {
		D element;
		Node<D> next;
		
		Node() {
		this.element = null;
		this.next = null;
		}
		Node(D element) {
		this.element = element;
		this.next = null;
		}
		
		}

	private int count;
	private Node<T> front, rear; 
	
	public LinkedQueue()
	 {
		// TODO Auto-generated method stub
	 } 
	
	@Override
	public void enqueue(T element) {
		// TODO Auto-generated method stub
	 

	}

	@Override
	public T dequeue() throws EmptyCollectionException
	   {
		// TODO Auto-generated method stub
		return null;

	   }

	@Override
	public T first()  throws EmptyCollectionException{
		// TODO Auto-generated method stub
		return null;
	
		
	}

	@Override
	public boolean isEmpty() {
		 // TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public T dequeueLast() throws EmptyCollectionException {
	  // TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		if (! this.isEmpty()) {
			StringBuffer rx = new StringBuffer();
			Node<T> actual=front;
			while (actual!=null) {
				rx.append(actual.element.toString());
				rx.append(", ");
				actual=actual.next;
			}
			rx.delete(rx.length() - 2, rx.length());
			return rx.toString();
		}
		return ""; 


};

}

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
		
		
		count = 0;
		front = null;
		rear= null;
		
	 } 
	
	@Override
	public void enqueue(T element) {
		
		Node<T> nodo = new Node<T>(element);
		if (isEmpty()) {
			front = rear= nodo;
		}else {
			
			rear.next= nodo;
			rear = nodo;
		}
		count ++;
		
	 

	}

	@Override
	public T dequeue() throws EmptyCollectionException
	   {
		
		if (isEmpty()) {
			throw new EmptyCollectionException("La cola esta vacia");
		}
		Node<T> nodo = front;
		front = front.next;
		count --;
		if (isEmpty()) {
			rear = null;
		}
		
		
		return nodo.element;

	   }

	@Override
	public T first()  throws EmptyCollectionException{
		if (isEmpty()) {
			throw new EmptyCollectionException("La cola esta vacia");
		}
		return front.element;
	
		
	}

	@Override
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		
		return count;
	}

	@Override
	public T dequeueLast() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("La cola esta vacia");
		}
		Node<T> nodo = front;
		
		if (nodo.next==null) {
			front = null;
			rear= null;
			count --;
			return nodo.element;
		}
		
		Node<T> auxiliar = null;
		while (nodo!= null) {
			auxiliar= nodo;
			nodo= nodo.next;
			
		}
		auxiliar.next=null;
		count--;
		
		return nodo.element;
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

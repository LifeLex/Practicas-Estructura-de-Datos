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
		//No hace falta inicializar nada 
		
	 } 
	
	@Override
	public void enqueue(T element) {
		Node<T> actual  = front;
		Node<T> nodo = new Node<T>(element);
		
		if(actual == rear) {
			front = nodo;
			nodo.next = rear;
			//Si no hago return se queda en bucle infinito en los test
			return;
		}
		while (actual != rear) {
			if(actual.next == rear) {
				nodo.next = rear;
				actual.next = nodo;
				//Si no hago return se queda en bucle infinito en los test
				return;
			}
			actual = actual.next;
		}
	}

	@Override
	public T dequeue() throws EmptyCollectionException {
		Node<T> nodo;
		
		if(front == null)
			throw new EmptyCollectionException("");
		else {
			nodo = front;
			front = front.next;
		}
		return nodo.element;
	}

	@Override
	public T first()  throws EmptyCollectionException{
		if(front == null) {
			throw new EmptyCollectionException("");
		}else {
			return front.element;
		}
			
	}

	@Override
	public boolean isEmpty() {
		if(front == null) {
			return true;
		}else {
			return false;
		}
			
	}

	@Override
	public int size() {
		count = 0;
		Node<T> actual  = front;
		while (actual != rear) {
			count++;
			actual = actual.next;
		}
		return count;
	}

	@Override
	public T dequeueLast() throws EmptyCollectionException {
		Node<T> nodo;
		Node<T> actual  = front;
		
		if(front == null)
			throw new EmptyCollectionException("");
		else {
			if(front.next == null) {
				nodo = actual;
				front = null;
				rear = null;
				return nodo.element;
			}
			while(actual != null) {
				if(actual.next.next == null) {
					nodo = actual.next;
					actual.next = null;
					return nodo.element;
				}
				actual = actual.next;
			}
		}
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

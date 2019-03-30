package ule.edi.limitedpriorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class LimitedPriorityQueueArrayImpl<T> implements LimitedPriorityQueue<T> {
	private int capacity;
	private int npriorities;
	private int count;

	private ArrayList<LinkedQueue<T>> colas;



	public LimitedPriorityQueueArrayImpl(int capacity, int npriorities) {

		
		this.capacity= capacity;
		this.npriorities= npriorities;

		for (int i = 0; i < npriorities; i++) {
			LinkedQueue<T> queue = new LinkedQueue<T>();
			colas.add(queue);

		}

		if (capacity<=0) {
			throw new IllegalArgumentException("La capacidad tiene que ser mayor que 0");
		}

	}




	@Override
	public int getCapacity() {
		return capacity;

	}

	@Override
	public int getSize() {
		for (int i = 0; i < colas.size(); i++) {
			count = count+colas.get(i).size();
		}
		return count;
	}

	@Override
	public boolean isFull() {
		if (capacity==getSize()) {
			return true;
		}
		return false;
	}

	@Override
	public T enqueue(int p, T element) {
		
		if (isFull()) {
			colas.get(p).enqueue(element);
			for (int i = npriorities; i >0; i--) {
				if (colas.get(i)!=null) {
					if (colas.get(i).size()==1) {
						try {
							return  colas.get(i).dequeue();
						} catch (EmptyCollectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						try {
							return colas.get(i).dequeueLast();
						} catch (EmptyCollectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}

		}

		return null;

	}


	@Override
	public T first() throws EmptyCollectionException {
		if (isEmpty()) {
			throw  new EmptyCollectionException("La cola esta vacia");
		}
		for (int i = 0; i < npriorities; i++) {
			if (!colas.get(i).isEmpty()) {
				return colas.get(i).first();
			}
		}
		
		return null;
	}



	@Override
	public T dequeue() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("La cola esta vacia");
		}
		T elemento = null;
		for (int i = 0; i <npriorities; i++) {
			if (!colas.get(i).isEmpty()) {
				elemento = colas.get(i).first();
				colas.get(i).dequeue();
				return elemento;
			}
		}
		return elemento;
	}

	@Override
	public boolean isEmpty() {
		if (getSize()==0) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String toString() {
		if (! this.isEmpty()) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			for (int n = 0; n < this.npriorities; ++n) {
				rx.append("( Priority:"+(n+1)+" (");
				rx.append(colas.get(n).toString());
				rx.append(")), ");
			}
			rx.delete(rx.length() - 2, rx.length());
			rx.append("]");
			return rx.toString();
		} else {
			return "[]";
		}
	}

};



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
		colas = new ArrayList<LinkedQueue<T>>();
		
		this.capacity= capacity;
		this.npriorities= npriorities;
		
		
		for (int i = 0; i < npriorities; i++) {
			LinkedQueue<T> queue = new LinkedQueue<T>();
			colas.add(queue);

		}
		/*
		if (capacity<=0) {
			throw new IllegalArgumentException("La capacidad tiene que ser mayor que 0");
		}
		*/
	}




	@Override
	public int getCapacity() {
		return capacity;

	}

	@Override
	public int getSize() {
		count=0;
		for (int i = 0; i < colas.size(); i++) {
			count = count+colas.get(i).size();
		}
		return count;
	}

	@Override
	public boolean isFull() {
		
		if (capacity == getSize() ) {
			
			return true;
		}
		return false;
	}

	@Override
	public T enqueue(int p, T element) {
		int prioridadMinima=0;
		int pr=1;
		T elemento =null;
		
		for(int i = 0; i < npriorities; i++) {
			if(!colas.get(i).isEmpty())
				prioridadMinima = i+1;
		}
		
		for(int i = 0; i < npriorities; i++) {
			if(pr == p) {
				if(capacity > this.getSize()) {
					colas.get(i).enqueue(element);
					return null;
				}else {
					for(int j = npriorities-1; j >= 0 ; j--) {
						if(!colas.get(j).isEmpty()) {
							try {
								if(prioridadMinima <= p) {
									return element;
								}else {
									elemento = colas.get(j).dequeueLast();
									enqueue(p, element);
									return elemento;
								}
							
							} catch (EmptyCollectionException e) { 
								e.printStackTrace();
							}
						}
					}
				}
			}
			pr++;
		}
		return element;
		
		

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



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

		//TODO  asignar los valores de los atributos
		// Crear el arrayList, y añadir una cola por cada una de las prioridades (1..npriorities)
		// Si capacidad <=0 disparar la excepción: IllegalArgumentException
		this.capacity= capacity;
		this.npriorities= npriorities;
		
		
		colas = new ArrayList<LinkedQueue<T>>();
		for (int i = 0; i < npriorities; i++) {
			
			colas.add(new LinkedQueue<T>());
			 	
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
		return count;
	}

	@Override
	public boolean isFull() {
		if (getSize()==capacity) {
			return true;
		}
		return false;
	}

	@Override
	public T enqueue(int p, T element) {
		if (colas.size()==capacity) {
			colas.remove(p);
			colas.add((LinkedQueue<T>) element);
			 return element ;
		}
		colas.add((LinkedQueue<T>) element);
		return null;

	}


	@Override
	public T first() throws EmptyCollectionException {
		if (colas.isEmpty()) {
			throw  new EmptyCollectionException("La cola esta vacia");
		}
		
		return colas.get(1).first();

	}



	@Override
	public T dequeue() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return count==0; 
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



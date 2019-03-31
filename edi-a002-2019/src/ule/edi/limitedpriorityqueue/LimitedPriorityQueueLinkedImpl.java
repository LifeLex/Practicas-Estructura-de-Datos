package ule.edi.limitedpriorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;


public class LimitedPriorityQueueLinkedImpl<T> implements LimitedPriorityQueue<T> {
	private int capacity;

	private QueueNode<T> first;
	private int count;


	private static class QueueNode<E> {

		public QueueNode(int priority, E content) {
			this.priority = priority;
			this.content = content;
			this.next = null;
		}

		public int priority;

		public E content;

		public QueueNode<E> next;
	};



	public LimitedPriorityQueueLinkedImpl(int capacity) {
		this.capacity= capacity;

	}




	@Override
	public int getCapacity() {

		return capacity;
	}

	@Override
	public int getSize() {
		QueueNode<T> actual = first;
		while (actual!= null) {
			count++;
			actual= actual.next;
			
		}
		return count ;
	}

	@Override
	public boolean isFull() {
		if (getSize()<getCapacity()) {
			return false;
		}
		return true;

	}

	@Override
	public T enqueue(int p, T element) {
		QueueNode<T> actual = first;
		QueueNode<T> nodo = null;//nodo auxiliar
		QueueNode<T> newNodo = new QueueNode<T>(p, element);
		
		
		//Recorro y obtengo la prioridad minima
		QueueNode<T> nodoParaPrioridadMinima = first;
		int prioridadMinima = 0;
		while (nodoParaPrioridadMinima!=null) {
			if (nodoParaPrioridadMinima.next==null) {
				prioridadMinima= nodoParaPrioridadMinima.priority;
			}
			nodoParaPrioridadMinima= nodoParaPrioridadMinima.next;
		}
		
		//Cola llena
		if (!isFull()) {
			
			
			if (isEmpty()) {
				first= newNodo;
				newNodo.next= null;
				return null;
			}
			
			
			if (p<actual.priority) {
				newNodo.next=first;
				first= newNodo;
				return null;
			}
			
			while (actual !=null) {
				if (p>= actual.priority && actual.next==null) {
					actual.next= newNodo;
					newNodo= null;
					return null;
				}
				
				if (p>= actual.priority && p<actual.next.priority) {
					newNodo.next= actual.next;
					actual.next= newNodo;
					return null;
				}
				actual= actual.next;
			}
		}
		
		//Lista llena y menos prioridad que el ultimo	
		if (prioridadMinima <= p) {
			return element;
		}
		
		//Lista llena y mas prioridad que el ultimo
		actual = first;
		while (actual!=null) {
			if (actual.next.next == null) {
				nodo= actual.next;
				actual.next =null;
			}
			actual = actual.next;
		}
		enqueue(p, element);
		return nodo.content;

	}

	@Override
	public T first() throws EmptyCollectionException {
		if (first==null) {
			throw new EmptyCollectionException("Vacio");
		}
		
		return first.content;
	}

	@Override
	public T dequeue() throws EmptyCollectionException {
		if (first==null) {
			throw new EmptyCollectionException("Vacio");
		}
		QueueNode<T> actual= first;
		QueueNode<T> nodo;
		nodo= actual;
		first= null;
		return nodo.content;
	}

	@Override
	public boolean isEmpty() {
		if ( first== null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		QueueNode<T> actual= first;
		boolean condicion = true;
		if (! this.isEmpty()) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			// TODO : MOSTRAR LOS ELEMENTOS DE LA COLA DE PRIORIDAD CON EL MISMO FORMATO QUE LA OTRA IMPLEMENTACIÓN
			while (actual!= null) {
				if (condicion=true) {
					rx.append("( Priority:"+(actual.priority)+" (");
					condicion= false;
				}
				rx.append(actual.content+", ");
				if (actual.next == null || actual.priority != actual.next.priority) {
					rx.delete(rx.length() - 2 , rx.length());
					condicion = true;
					rx.append(")), ");
				}
				actual= actual.next;
			}
			rx.delete(rx.length()-2 , rx.length());
			rx.append("]");
			return rx.toString();
		} else {
			return "[]";
		}
	}



}

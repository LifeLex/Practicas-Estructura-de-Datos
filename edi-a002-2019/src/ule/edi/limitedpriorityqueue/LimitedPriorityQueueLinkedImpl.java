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
		QueueNode<T> current = first;
		QueueNode<T> previous = null;
		QueueNode<T> newNode = new QueueNode<T>(p, element);
		newNode.priority = p;
		if (isFull()) {
			for (int i = 0; i < getSize(); i++) {
				if (newNode.priority>current.priority) {
					previous= newNode;
					current = previous.next;
				}else {
					current = current.next;
				}
			}
		}
		return null;
	}

	@Override
	public T first() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Vacio");
		}
		
		return first.content;
	}

	@Override
	public T dequeue() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Vacio");
		}
		T itemAux = first.content;
		first = first.next;
		count--;
		return itemAux;
	}

	@Override
	public boolean isEmpty() {
		if (count==0 && first== null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (! this.isEmpty()) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			// TODO : MOSTRAR LOS ELEMENTOS DE LA COLA DE PRIORIDAD CON EL MISMO FORMATO QUE LA OTRA IMPLEMENTACIÓN

			rx.append("]");
			return rx.toString();
		} else {
			return "[]";
		}
	}



}

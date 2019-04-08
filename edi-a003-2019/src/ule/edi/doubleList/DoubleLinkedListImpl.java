package ule.edi.doubleList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<T> implements DoubleLinkedList<T> {
	/**
	 * Nodo doblemente enlazado.
	 * 
	 * Como es estática, no tiene en ámbito el parámetro 'T' de la
	 * clase que la contiene. El parámetro 'D' será sustituído por
	 * un tipo particular cuando se use el nodo, por ejemplo:
	 * 
	 * 		DoubleNode<T> cab;
	 * 
	 * en la lista.
	 *
	 * @param <D>
	 */
	static class DoubleNode<D> {

		DoubleNode(D element) {
			this.content = element;
			this.previous = null;
			this.next = null;
		}

		//	dato a almacenar en el nodo
		D content;

		DoubleNode<D> previous;

		DoubleNode<D> next;
	}

	/**
	 * Apunta al nodo cabecera; siempre habrá un nodo vacío (sin elemento) que actua de cabecera
	 *  OJO!!! ESTE NODO CABECERA DEBERÁ CREARSE EN CADA CONSTRUCTOR DE LA LISTA
	 */
	private DoubleNode<T> cab;



	//////////////////////
	/////  CONSTRUCTORES
	//////////////////////


	/**
	 * Construye una lista vacía.
	 */
	public DoubleLinkedListImpl() {
		//TODO
		// Deberá crear el nodo cabecera vacío
		cab = new DoubleNode<T>(null);
		cab.next= cab;
		cab.previous= cab;

	}

	/**
	 * Construye una lista con los elementos dados.
	 * 
	 * Java creará un array 'elements' con los dados en la
	 * llamada al constructor; por ejemplo:
	 * 
	 * 	x = new DoubleLinkedList<String>("A", "B", "C");
	 * 
	 * ejecuta este método con un array [A, B, C] en 
	 * 'elements'.
	 * 
	 * @param elements
	 */
	public DoubleLinkedListImpl(T ... elements) {
		//TODO
		cab = new DoubleNode<T>(null);
		cab.next = cab;
		cab.previous=cab;
		ArrayList<T> listaElementosDados = new ArrayList<T>();
		for (int i = 0; i < elements.length; i++) {
			addLast(elements[i]);
		}


	}

	/**
	 * Construye una lista a partir de otra.
	 * 
	 * Las listas tienen nodos independientes, con los mismos
	 * contenidos.
	 */
	public DoubleLinkedListImpl(DoubleLinkedList<T> other) {
		//TODO
		cab = new DoubleNode<T>(null);
		cab.next = cab;
		cab.previous=cab;
		Iterator<T> iteradorAux = other.iterator();
		while (iteradorAux.hasNext()) {
			addLast(iteradorAux.next());

		}
	}



	//////////////////////
	/////  ITERADORES
	//////////////////////

	private class ForwardIterator implements Iterator<T> {

		private DoubleNode<T> at ;

		@Override
		public boolean hasNext() {

			// TODO Auto-generated method stub
			if (at.next==cab) {
				return false;
			}else {
				return true;
			}
		}

		@Override
		public T next() {

			// TODO Auto-generated method stub
			if (hasNext()){
				at = at.next;
			}else {
				throw new NoSuchElementException("The iteration has no more elements");
			}
			return at.content;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};


	private class reverseIterator implements Iterator<T> {

		//private DoubleNode<T> at ;
		private DoubleNode<T> at = cab;
		@Override
		public boolean hasNext() {
			//at= cab;
			if (at.previous == cab ) {
				return false;
			}else {
				return true;
			}

			// TODO Auto-generated method stub

		}

		@Override
		public T next() {

			// TODO Auto-generated method stub
			if (hasNext()) {
				at = at.previous;
			}else {
				throw new NoSuchElementException("The iteration has no more elements");
			}
			return at.content;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};

	private class OddAndEvenIterator implements Iterator<T> {
		boolean Odd = false;//impar
		boolean Even = true;//par
		boolean first= true;
		boolean insertFirst= false;
		private DoubleNode<T> at = cab;
		// Definir los atributos necesarios para implementar el iterador

		public OddAndEvenIterator(){

		}

		@Override
		public boolean hasNext() {
			if (Even) {
				if (at.next!=cab && at.next.next!=cab) { //Si la lista tiene mas de dos elementos
					return true;
				}else if ((at.next.next==cab && at.next.next.next!= cab)|| (at.next==cab && at.next.next != cab)) {
					Even= false;
					Odd=true;
				}
			}
			if (Odd) {
				if (at.next!=cab && at.next.next!=cab) {
					return true;
				}else if (first) {
					first= false;
					insertFirst= true;
					if ((at.next.next==cab && at.next.next.next!= cab)|| (at.next==cab && at.next.next != cab)) {
						return true;
					}
				}
			}
			return false;
			// TODO Auto-generated method stub

		}

		@Override
		public T next() {
			if (insertFirst) {
				insertFirst= false;
				at= cab;
				if (at.next!=cab) {
					at = at.next;
					return at.content;
				}
			}
			return null;
			// TODO Auto-generated method stub

		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};




	////// FIN DE ITERADORES ///////
	////////////////////////////////

	@Override
	public boolean isEmpty() {
		if (cab.next == cab || cab.previous== cab) {
			return true;
		}
		return false;
		// TODO Auto-generated method stub


	}

	@Override
	public int size() {
		int size=0;
		DoubleNode<T> aux =cab;
		while (aux.next!= cab) {
			aux= aux.next;
			size= size+1;
		}
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void addFirst(T element) {
		DoubleNode<T> nodoInsert = new DoubleNode<T>(element);
		if (isEmpty()) {//vacio
			cab.next= nodoInsert;
			cab.previous= nodoInsert;
			nodoInsert.next=cab;
			nodoInsert.previous= cab;
		} else if (cab.next.next == cab) {//2 elem
			cab.next= nodoInsert;
			cab.previous.previous= nodoInsert;
			nodoInsert.next= cab.previous;
			nodoInsert.previous= cab;

		}else if (cab.next.next!= cab) {//+ de 2 elem
			cab.next.previous= nodoInsert;
			nodoInsert.previous= cab;
			nodoInsert.next= cab.next;
			cab.next= nodoInsert;


			//nodoInsert.next= cab.next.next;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void addLast(T element) {
		DoubleNode<T> nodoInsert = new DoubleNode<T>(element);
		if (isEmpty()) {//vacio es igual que para añadir primero ya que no hay elementos
			cab.next= nodoInsert;
			cab.previous= nodoInsert;
			nodoInsert.previous= cab;
			nodoInsert.next=cab;
		}else if(cab.next.next==cab) {
			cab.previous= nodoInsert;
			nodoInsert.next= cab;
			nodoInsert.previous= cab.next;
			cab.next.next= nodoInsert;

		}else {
			cab.previous.next= nodoInsert;
			nodoInsert.previous= cab.previous;
			nodoInsert.next= cab;
			cab.previous= nodoInsert;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void addAtPos(T element, int p) {
		// TODO Auto-generated method stub
		DoubleNode<T> nodoInsert = new DoubleNode<T>(element);
		DoubleNode<T> aux = cab;
		//Lista Con menos elementos que p
		if (p>size()) {
			addLast(element);
		}else if(p==1){
			addFirst(element);
		}else {
			for (int i = 0; i <=p; i++) {
				if (i==p) {
					aux.previous.next= nodoInsert;
					nodoInsert.previous=  aux.previous;
					aux.previous= nodoInsert;
					nodoInsert.next= aux;
				}else {
					aux= aux.next;
				}
			}
		}
	}

	@Override
	public void addNTimes(T element, int n) {
		for (int i = 0; i < n; i++) {
			addLast(element);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public T getElem(int p) {
		DoubleNode<T> aux = cab;
		for (int i = 0; i <=size(); i++) {
			if (i==p) {
				return aux.content;
			}
			aux= aux.next;
		}
		throw new IndexOutOfBoundsException();

		// TODO Auto-generated method stub
	}

	@Override
	public void setElem(T elem, int p) {
		DoubleNode<T> aux = cab;
		if (p>size()) {
			throw new IndexOutOfBoundsException();
		}else{
			for (int i = 0; i <=p; i++) {
				if (i==p) {
					aux.content= elem;
				}
				aux = aux.next;
			}
		}

		// TODO Auto-generated method stub

	}

	@Override
	public int indexOf(T elem) {
		DoubleNode<T> aux = cab;
		int posicion=0;
		for (int i = 0; i <= size(); i++) {
			if (aux.content == elem) {
				posicion= i;
				return posicion;
			}
			aux = aux.next;
		}
		throw new NoSuchElementException();

		// TODO Auto-generated method stub

	}

	@Override
	public int indexOf(T elem, int p) {
		DoubleNode<T> aux = cab;
		int posicion=0;
		if (p>size()) {
			throw new IndexOutOfBoundsException();
		}else {
			for (int i = 0; i <= size(); i++) {
				if (i==p && aux.content == elem) {
					posicion= i;
					return posicion;
				}
				aux = aux.next;
			}
		}
		throw new NoSuchElementException();


		// TODO Auto-generated method stub

	}

	@Override
	public T removeFirst(T elem) throws EmptyCollectionException {
		DoubleNode<T> aux = cab;

		if (isEmpty()) {
			throw new EmptyCollectionException("");
		}
		for (int i = 0; i <= size(); i++) {
			if (aux.content==elem) {
				aux.previous.next = aux.next;
				aux.next.previous = aux.previous;
				return elem;
			}
			aux = aux.next;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeAll(T elem) throws EmptyCollectionException {
		DoubleNode<T> aux = cab;
		boolean estaBorrado=false;
		if (isEmpty()) {
			throw new EmptyCollectionException("");
		}
		for (int i = 0; i <= size(); i++) {
			if (aux.content==elem) {
				aux.previous.next = aux.next;
				aux.next.previous= aux.previous;
				estaBorrado=true;
				i--;
			}
			aux= aux.next;
		}
		if (estaBorrado==true) {
			return elem;
		}
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T removeLast() throws EmptyCollectionException{
		DoubleNode<T> aux = cab;
		T ultimo;
		if (isEmpty()) {
			throw new EmptyCollectionException("");
		}
		ultimo = cab.previous.content;
		cab.previous.previous.next=cab;
		cab.previous= cab.previous.previous;
		return ultimo;
		// TODO Auto-generated method stub
		
		
	}




	@Override
	public void reverse() {
		// TODO Auto-generated method stub

	}

	@Override
	public int isSubList(DoubleLinkedList<T> part) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void interlace(DoubleLinkedList<T> other) {
		// TODO Auto-generated method stub

	}	

	@Override
	public String toString() {

		if (cab != cab.next) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			DoubleNode<T> i = cab.next;
			while (i != cab) {
				rx.append(i.content);
				rx.append(", ");
				i = i.next;
			}
			rx.delete(rx.length() - 2, rx.length());
			rx.append("]");

			return rx.toString();
		} else {
			return "[]";
		}
	}



	///////////////////////////////////////////
	// métodos que devuelve iteradores
	///////////////////////////////////////
	@Override
	public Iterator<T> oddAndEvenIterator() {
		return null;
		// TODO Auto-generated method stub


	}

	@Override
	public Iterator<T> iterator() {
		return new ForwardIterator();

	}

	@Override
	public Iterator<T> reverseIterator() {
		// TODO Auto-generated method stub
		return null;
	}



}

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

		private DoubleNode<T> at = cab ;

		@Override
		public boolean hasNext() {

			if (at.next.equals(cab)) {
				return false;
			}else {
				return true;
			}
		}

		@Override
		public T next() {

			if (hasNext()){
				at = at.next;
			}else {
				throw new NoSuchElementException("The iteration has no more elements");
			}
			return at.content;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	};


	private class reverseIterator implements Iterator<T> {


		private DoubleNode<T> at = cab;
		@Override
		public boolean hasNext() {

			if (at.previous.equals(cab)) {
				return false;
			}
			return true;



		}

		@Override
		public T next() {

			if (hasNext()) {
				at = at.previous;
			}else {
				throw new NoSuchElementException("The iteration has no more elements");
			}
			return at.content;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	};

	private class OddAndEvenIterator implements Iterator<T> {
		private DoubleNode<T> at = cab;
		boolean first = true;
		boolean insertFirst = false;
		boolean even = true;
		boolean odd= false;
		// Definir los atributos necesarios para implementar el iterador

		public OddAndEvenIterator(){

		}

		@Override
		public boolean hasNext() {
			if(even== true && odd== false) {
				if(!at.next.equals(cab) && !at.next.next.equals(cab))
					return true;
				else if((at.next.next.equals(cab) && !at.next.next.next.equals(cab)) ||(at.next.equals(cab)) && !at.next.next.equals(cab)) 
					even = false;
					odd= true;
			}

			if(even== false && odd== true) {
				if(!at.next.equals(cab) && !at.next.next.equals(cab))
					return true;
				if(first) {
					insertFirst = true;
					first = false;
					if((at.next.equals(cab) && !at.next.next.equals(cab)) || (at.next.next.equals(cab) && !at.next.next.next.equals(cab)))
						return true;
				}
			}
			return false;
		}

		@Override
		public T next() {
			if(insertFirst) {
				insertFirst = false;
				at = cab;
				if(!at.next.equals(cab)) {
					at = at.next;
					return at.content;
				}
			}

			if(hasNext()) {
				at = at.next.next;
				return at.content;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	};




	////// FIN DE ITERADORES ///////
	////////////////////////////////

	@Override
	public boolean isEmpty() {
		if (cab.next.equals(cab) || cab.previous.equals(cab)) {
			return true;
		}
		return false;


	}

	@Override
	public int size() {
		int size=0;
		DoubleNode<T> aux =cab;
		while (aux.next!= cab) {
			aux= aux.next;
			size= size+1;
		}
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


		
		}

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
	

	}

	@Override
	public void addAtPos(T element, int p) {
		
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

	}

	@Override
	public T removeFirst(T elem) throws EmptyCollectionException {
		DoubleNode<T> aux = cab;
		T elemento=null;
		if (isEmpty()) {
			throw new EmptyCollectionException("");
		}
		for (int i = 0; i <= size(); i++) {
			if (aux.content==elem) {
				aux.previous.next = aux.next;
				aux.next.previous = aux.previous;
				elemento= elem;
				
			}
			aux = aux.next;
		}
		
		return elemento;
	

	}

	@Override
	public T removeAll(T elem) throws EmptyCollectionException {
		DoubleNode<T> aux = cab;
		boolean estaBorrado=false;
		T elemento= null;
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
			elemento= elem;
			
		}
		return elemento;
	
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
		


	}




	@Override
	public void reverse() {
		ArrayList<T> lista = new ArrayList<T>();
		DoubleNode<T> aux = cab;
		aux= aux.next;
		while(cab!=aux) {
			lista.add(aux.content);
			aux= aux.next;
		}
		cab.next= cab;
		cab.previous= cab;
		for (int i = 0; i < lista.size(); i++) {
			addFirst(lista.get(i));
		}
		

	}

	@Override
	public int isSubList(DoubleLinkedList<T> part) {
	
		DoubleNode<T> aux=cab.next;
		Iterator<T> iterador = part.oddAndEvenIterator();
		ArrayList<T> elementosLista = new ArrayList<T>();
		int inicio= 1;
		int p= 0;
		boolean introducir = false;

		if (!iterador.hasNext()) {
			return 1;
		}
		while (!introducir) {
			if (!cab.equals(aux)) {
				elementosLista.add(aux.content);
				aux= aux.next;
			}
			if (cab.equals(aux)) {
				introducir = true;
			}
		}

		while (introducir) {
			if (!iterador.hasNext()) {
				return inicio;
			}
			if (elementosLista.get(p) !=iterador.next()) {
				p=0;
				inicio++;
				if (elementosLista.size()==1) {
					introducir = false;
				}else {
					elementosLista.remove(p);
				}
				iterador = part.iterator();
			}else {
				p++;
			}
		}
		return -1;
	}

	@Override
	public void interlace(DoubleLinkedList<T> other) {
		
		DoubleNode<T> aux = cab.next;
		Iterator<T> iterador = other.iterator();
		ArrayList<T> lista = new ArrayList<T>();
		boolean insertar = false;

		while (!insertar) {
			if(cab!= aux) {
				lista.add(aux.content);
				aux= aux.next;
			}
			if (iterador.hasNext()) {
				lista.add(iterador.next());
			}
			if (cab== aux&& !iterador.hasNext()) {
				insertar = true;
			}

		}
		cab.next= cab;
		cab.previous= cab;

		for (int i = lista.size()-1; i>=0; i--) {
			addFirst(lista.get(i));
		}
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
		return new OddAndEvenIterator();
		


	}

	@Override
	public Iterator<T> iterator() {
		return new ForwardIterator();

	}

	@Override
	public Iterator<T> reverseIterator() {
		return new reverseIterator();
	}



}

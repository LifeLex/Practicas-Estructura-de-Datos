package ule.edi.SimpleList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T ... elements) {
		// IMPLEMENTAR DE FORMA RECURSIVA 
		header=null;
		Node<T>aux = new Node<T>(null);
		if (elements.length>0) {
			aux.content= elements[0];
			header= aux;
			constructorRec(1, aux, elements);
		}

	}


	private void constructorRec(int l, Node<T> aux, T[] elements) {
		if (l!= elements.length) {
			Node<T> nodo = new Node<T>(elements[l]);
			aux.next = nodo;
			constructorRec(++l, aux.next, elements);
		}

	}


	@Override
	public void addLast(T element) {
		Node<T> nodoAdd = new Node<T>(element);
		if (isEmpty()) {
			header = nodoAdd; //header.equals(nodoAdd);
		}else {
			addLastRec(header, nodoAdd);
		}


	}

	private void addLastRec(Node<T> aux, Node<T> nodoAdd) {
		if (aux.next==null) {
			aux.next=nodoAdd;
		}else{
			addLastRec(aux.next,  nodoAdd);
		}
	}
	
	
	private class ForwardIterator implements Iterator<T> {

		private Node<T> at = header ;

		@Override
		public boolean hasNext() {
			if(header == null)
				return false;
			else if(at.next == null)
				return false;
			else
				return true;
		}

		@Override
		public T next() {

			if(hasNext())
				at = at.next;
			else
				throw new NoSuchElementException();
			return at.content;

		}
	};
	@Override
	public Iterator<T> iterator() {
		return new ForwardIterator();
	}

	@Override
	public boolean isEmpty() {
		if (header==null) {
			return true;
		}else{
			return false;
		}

	}

	@Override
	public int size() {
		int size;
		
		if (isEmpty()) {
			size=0;
		}else{
			size = sizeRec(header);
		}
		return size;
	}
	private int sizeRec(Node<T>aux) {
		
		if (aux.next==null) {
			return 1;
		}else {	
			return 1 + sizeRec(aux.next);
		}

	}

	@Override
	public void addFirst(T element) {
		Node<T>aux = new Node<T>(element);
		if (header==null) {
			header= aux;
		}else {
			aux.next= header;
			header= aux;
		}

	}




	@Override
	public void addAtPos(T element, int p) {
		if (p<= 0) {
			throw new IllegalArgumentException();
		}
		if (p==1) {
			addFirst(element);
		}else if (p>size()) {
			addLast(element);
		}else {
			addAtPosRec(element, p, 1, header);
		}

	}
	private void addAtPosRec(T element, int p, int n, Node<T>aux) {
		Node<T> nodoAdd = new Node<T>(element);

		if (p-1 != n) {
			n=+1;
			addAtPosRec(element, p, n, aux.next);
		} else {
			nodoAdd.next= aux.next;
			aux.next= nodoAdd;
		}
	}

	@Override
	public void addNTimes(T element, int n) {
		if (n<0) {
			throw new IllegalArgumentException();
		}
		addNTimesRec(element, n);

	}

	private void addNTimesRec(T element, int n) {
		
		if (n>0) {
			addLast(element);
			n=n-1;
			addNTimesRec(element, n);
		}
	}

	@Override
	public int indexOf(T elem) {
		int index;
		index = indexOfRec(header, elem);
		return index;
	}

	private int indexOfRec(Node<T>aux, T elem) {
		if (aux==null) {
			throw new NoSuchElementException();
		}
		if (aux.content.equals(elem)) {
			return 1;
		}else {
			return 1+indexOfRec(aux.next, elem);
		}
	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		Node <T>contenido = new Node<T>(null);
		T borrar;
		if (isEmpty()) {
			throw new EmptyCollectionException("Vacio");
		}
		if (size()==1) {
			borrar= header.content;
			header=null;
			return borrar;
			//return contenido.content;
		}else {
			contenido.content = removeLastRec(header);
			borrar = contenido.content;
			return borrar;
		}
		
	}

	private T removeLastRec(Node<T> aux) {
		Node <T>contenido = new Node<T>(null);
		T borrar;
		if (aux.next.next!=null) {
			return removeLastRec(aux.next);
		}else {
			borrar= aux.next.content;
			//contenido.content= aux.next.content;
			aux.next= null;
			return borrar;
		}


	}

	@Override
	public T removeLast(T elem) throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("");
		}
		int pos;
		pos = removeLastRec2(elem, header, 1, 0);
		return removeLastPosRec(header, pos, 2);
	}


	private T removeLastPosRec(Node<T> aux, int pos, int i) {
		if(pos == 1) {
			T borrado = aux.content;
			header= header.next;
			return borrado;
		}
		if(pos == 2) {
			T borrado = aux.next.content;
			aux.next = aux.next.next;
			return borrado;
		}
		if(pos == i) {
			T borrado = aux.next.content;

			aux.next = aux.next.next;
			return borrado; 
		}else
			return removeLastPosRec(aux.next, pos, ++i);
	}



	private int removeLastRec2(T elem, Node<T> aux, int i, int j) {
		if(i >= size() && j == 0)
			throw new NoSuchElementException();
		if(aux == null)
			return j;
		if(aux.content.equals(elem)) {
			j  = i;
			return removeLastRec2(elem, aux.next, ++i, j);
		}

		else
			return removeLastRec2(elem, aux.next, ++i, j);

	}


	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {
		SingleLinkedListImpl<T> listaReverse = new SingleLinkedListImpl<T>();
		listaReverse = reverseRec(header, listaReverse);
		return listaReverse;
	}

	private SingleLinkedListImpl<T> reverseRec(Node<T> aux, SingleLinkedListImpl<T> listaReverse) {
		if(aux != null) {
			listaReverse.addFirst(aux.content);
			return reverseRec(aux.next, listaReverse);
		}
		return listaReverse;
	}


	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {
		if(part.isEmpty())
			return 1;
		else if(isEmpty())
			return -1;
		else { 
			Node<T> aux = part.header;
			return 1 + isSubListRec(part, header,aux);
		}
	}


	private int isSubListRec(AbstractSingleLinkedListImpl<T> part, Node<T> aux, Node<T> nodo) {
		if(aux.content.equals(nodo.content)) {

			if(nodo.next == null)
				return 0;
			if(aux.next == null)
				return -2;
			else 
				return isSubListRec(part, aux.next, nodo.next);
		}else {

			if(aux.next != null) {
				header = header.next;
				aux = header;
				nodo = part.header;
				return 1 + isSubListRec(part, aux, nodo);
			}else
				return -2;
		}
	}


}

package ule.edi.SimpleList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T ... elements) {
		// IMPLEMENTAR DE FORMA RECURSIVA 
		header=null;
		
		
    }
	private void constructorRec() {
		
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

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
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
		if (aux.next.equals(null)) {
			return 0;
		}else {
			return 1 + sizeRec(aux.next);
		}
		
	}

	@Override
	public void addFirst(T element) {
		Node<T>aux = new Node<T>(element);
		if (header.equals(null)) {
			header= aux;
		}else {
			aux.next= header;
			header= aux;
		}
		
	}
	

	

	@Override
	public void addAtPos(T element, int p) {
	
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
		if (aux.equals(null)) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeLast(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}

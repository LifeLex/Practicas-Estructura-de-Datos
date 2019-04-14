package ule.edi.doubleList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.model.Person;


public class DoubleLinkedListImplTests {

	private DoubleLinkedListImpl<String> lS;
	private DoubleLinkedListImpl<String> lSABC;
	private DoubleLinkedListImpl<String> lSABCDE;
	private DoubleLinkedListImpl<String> lSVacia;
	private DoubleLinkedListImpl<Person> listaPersona;


	@Before
	public void setup() {
		this.lS = new DoubleLinkedListImpl<String>();
		this.lSVacia = new DoubleLinkedListImpl<String>();
	    this.lSABC=new DoubleLinkedListImpl<String>("A", "B", "C");
	    this.lSABCDE=new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E");
	    this.listaPersona = new DoubleLinkedListImpl<Person>();
	    
	}
	
	
	//Mis Test
	@Test
	public void testIsEmpty() {
		Assert.assertEquals(true, lS.isEmpty());
		lS.addFirst("Hola");
		Assert.assertEquals(false, lS.isEmpty());
		
		
		
	}
	
	@Test
	public void testSize() {
		Assert.assertEquals(0, lS.size());
		lS.addFirst("Hola");
		Assert.assertEquals(1, lS.size());
		
	}
	
	@Test
	public void testAddFirst() {
		Assert.assertEquals("[]", lS.toString());
		lS.addFirst("Hola");
		Assert.assertEquals("[Hola]", lS.toString());
		lS.addFirst("Mundo");
		Assert.assertEquals("[Mundo, Hola]", lS.toString());
		lS.addFirst("TercerElemento");
		Assert.assertEquals("[TercerElemento, Mundo, Hola]", lS.toString());
		lS.addFirst("CuartoElemento");
		Assert.assertEquals("[CuartoElemento, TercerElemento, Mundo, Hola]", lS.toString());
	}
	
	@Test
	public void testAddLast() {
		Assert.assertEquals("[]", lS.toString());
		lS.addLast("Hola");
		Assert.assertEquals("[Hola]", lS.toString());
		lS.addLast("Mundo");
		Assert.assertEquals("[Hola, Mundo]", lS.toString());
		lS.addLast("TercerElemento");
		Assert.assertEquals("[Hola, Mundo, TercerElemento]", lS.toString());
		lS.addLast("CuartoElemento");
		Assert.assertEquals("[Hola, Mundo, TercerElemento, CuartoElemento]", lS.toString());
	}
	
	@Test
	public void testAddAtPos() {
		Assert.assertEquals("[]", lS.toString());
		lS.addAtPos("Hola", 6);//Añadir a pos mayor que n
		Assert.assertEquals("[Hola]", lS.toString());
		lS.addAtPos("Mundo", 1);//Añadir al principio
		Assert.assertEquals("[Mundo, Hola]", lS.toString());
		lS.addAtPos("TercerElemento", 2);//Añadir en medio
		Assert.assertEquals("[Mundo, TercerElemento, Hola]", lS.toString());
		
	}
	
	@Test
	public void testAddNTimes() {
		Assert.assertEquals("[]", lS.toString());
		lS.addNTimes("ED", 3);
		Assert.assertEquals("[ED, ED, ED]", lS.toString());
	}
	
	@Test
	public void testGetElemen() {
		lS.addFirst("Hola");
		Assert.assertEquals("Hola", lS.getElem(1));
		lS.addLast("Mundo");
		Assert.assertEquals("Mundo", lS.getElem(2));
	}
	@Test(expected= IndexOutOfBoundsException.class)
	public void testGetElemenException() {
		lS.getElem(20);
	}
	
	@Test
	public void testSetElemen() {
		lS.addFirst("Mundo");
		lS.addLast("Hola");
		Assert.assertEquals("[Mundo, Hola]", lS.toString());
		lS.setElem("Test", 1);
		lS.setElem("SetElem", 2);
		Assert.assertEquals("[Test, SetElem]", lS.toString());
	}
	@Test(expected= IndexOutOfBoundsException.class)
	public void testSetElemenException() throws IndexOutOfBoundsException{
		lS.setElem("TEST", 20);
	}
	
	
	@Test
	public void testIndexOf() {
		lS.addFirst("Mundo");
		lS.addFirst("Hola");
		Assert.assertEquals(1, lS.indexOf("Hola"));
		Assert.assertEquals(2, lS.indexOf("Mundo"));
	}
	@Test(expected = NoSuchElementException.class)
	public void testIndexOfException() throws NoSuchElementException {
		lS.indexOf("TESTFAIL");
	}
	
	@Test
	public void testIndexOfElemen() {
		lS.addFirst("Mundo");
		lS.addLast("TEST");
		lS.addFirst("Hola");
		Assert.assertEquals(1, lS.indexOf("Hola", 1));
		Assert.assertEquals(3,lS.indexOf("TEST", 3));
		Assert.assertEquals(2, lS.indexOf("Mundo", 2));
		
		
	}
	@Test(expected = NoSuchElementException.class)
	public void testIndexOfElemNoElemException() throws NoSuchElementException {
		lS.addFirst("Hola");
		lS.indexOf("TESTFAIL", 1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOfElemOutBoundsException() throws IndexOutOfBoundsException {
		lS.indexOf("TESTFAIL", 20);
	}
	
	@Test
	public void testRemoveFirst() throws EmptyCollectionException{
		lS.addFirst("Hola");
		Assert.assertEquals("Hola", lS.removeFirst("Hola"));
		lS.addLast("Mundo");
		Assert.assertEquals("Mundo", lS.removeFirst("Mundo"));
	}
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveFirstEmptyException() throws EmptyCollectionException {
		lS.removeFirst("Vacio");
	}
	
	@Test
	public void testRemoveLast() throws EmptyCollectionException{
		lS.addFirst("Hola");
		lS.addLast("Mundo");
		Assert.assertEquals("Mundo", lS.removeLast());
		Assert.assertEquals("Hola", lS.removeLast());
	}
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastEmptyException() throws EmptyCollectionException {
		lS.removeLast();
	}
	
	@Test
	public void testRemoveAll() throws EmptyCollectionException{
		lS.addFirst("Hola");
		lS.addFirst("Hola");
		lS.addFirst("Hola");
		lS.addLast("Mundo");
		lS.addLast("Mundo");
		lS.addLast("Mundo");
		lS.addLast("Mundo");
		lS.addFirst("Primero");
		
		Assert.assertEquals("Mundo", lS.removeAll("Mundo"));
		Assert.assertEquals("[Primero, Hola, Hola, Hola]", lS.toString());
		Assert.assertEquals("Hola", lS.removeAll("Hola"));
		Assert.assertEquals("[Primero]", lS.toString());
	}
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveAllEmptyException() throws EmptyCollectionException {
		lS.removeLast();
	}
	
	@Test
	public void testReverse() {
		lS.addFirst("Hola");
		lS.addFirst("Hola");
		lS.addFirst("Hola");
		lS.addLast("Mundo");
		lS.addLast("Mundo");
		lS.addLast("Mundo");
		lS.addLast("Mundo");
		lS.addFirst("Primero");
		lS.reverse();
		Assert.assertEquals("[Mundo, Mundo, Mundo, Mundo, Hola, Hola, Hola, Primero]", lS.toString());
		
	}
	
	@Test
	public void testSubList() {
		
		lS.addFirst("Tal");
		lS.addFirst("Que");
		lS.addFirst("Mundo");
		lS.addFirst("Hola");
		Assert.assertEquals(-1, lS.isSubList(lSABC));
		Assert.assertEquals(1, lS.isSubList(lSVacia));
		lSVacia.addFirst("Tal");
		lSVacia.addFirst("Que");
		lSVacia.addFirst("Mundo");
		Assert.assertEquals(2, lS.isSubList(lSVacia));
	}
	
	@Test
	public void testInterlace() {
		lSABC.interlace(lSABCDE);
		Assert.assertEquals("[A, A, B, B, C, C, D, E]", lSABC.toString());
	}
	
	
	@Test
	public void testReverseIterator() {
		Iterator<String> iteratorE = lS.reverseIterator();
		Assert.assertEquals(false, iteratorE.hasNext());
		
		lS.addFirst("Hola");
		lS.addFirst("Mundo");
		lS.addFirst("Test");
		Iterator<String> iteratorF = lS.reverseIterator();
		Assert.assertEquals(true, iteratorF.hasNext());
		Assert.assertEquals(true, iteratorF.hasNext());
		Assert.assertEquals(true, iteratorF.hasNext());
		Assert.assertEquals("Hola", iteratorF.next());
		Assert.assertEquals(true, iteratorF.hasNext());
		Assert.assertEquals("Mundo", iteratorF.next());
		Assert.assertEquals(true, iteratorF.hasNext());
		Assert.assertEquals("Test", iteratorF.next());
		Assert.assertEquals(false, iteratorF.hasNext());
		Assert.assertEquals(false, iteratorF.hasNext());
		Assert.assertEquals(false, iteratorF.hasNext());
		
	}
	@Test(expected = NoSuchElementException.class)
	public void testReverseItException() {
		Iterator<String> i = lS.reverseIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	
	@Test
	public void testEquals() {
		Person persona1 = new Person("Alejandro","71465351G",21);
		Person persona11 = new Person("Alejandro","71465351G",21);
		Person persona12 = new Person("Alejandro","71465352G",21);
		Person persona2 = new Person("Karolina","71465351G",22);
		Person persona3 = new Person("NoNamer", "1234567A", 99);
		listaPersona.addFirst(persona1);
		listaPersona.addLast(persona2);		
		Assert.assertEquals("[{ NIF: 71465351G  Name : Alejandro, Age:21}, { NIF: 71465351G  Name : Karolina, Age:22}]", listaPersona.toString());
		Assert.assertTrue(persona1.equals(persona2));
		Assert.assertFalse(persona1.equals(persona3));
		Assert.assertTrue(persona11.equals(persona1));
		Assert.assertFalse(persona11.equals(persona12));
	}
	//Test dados
	@Test
	public void testToStringVacio(){
		Assert.assertEquals(lS.toString(),"[]");		
	}
	
	@Test
	public void testToStringNoVacio(){
		Assert.assertEquals(lSABC.toString(),"[A, B, C]");		
	}
	
	@Test
	public void testConstructorConLista(){
		DoubleLinkedListImpl<String> nueva= new DoubleLinkedListImpl<String>(lSABCDE);
		Assert.assertEquals("[A, B, C, D, E]", nueva.toString());
	}
	
	@Test
	public void testForwardIt() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.iterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[A, B, C, D]", lS.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testForwardItException() {
		Iterator<String> i = lS.iterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	@Test
	public void testOddAndEvenIt() {
		lS = new DoubleLinkedListImpl<>("A", "B", "C", "D", "E");
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("E", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[A, B, C, D, E]", lS.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testOddAndEvenException() {
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testReverseIteratorException() {
		Iterator<String> i = lS.reverseIterator();
		i.remove();
	}
	@Test(expected = UnsupportedOperationException.class)
	public void testForewardIteratorException() {
		Iterator<String> i = lS.iterator();
		i.remove();
	}
	@Test(expected = UnsupportedOperationException.class)
	public void testOddIteratorExceptionException() {
		Iterator<String> i = lS.oddAndEvenIterator();
		i.remove();
	}
}

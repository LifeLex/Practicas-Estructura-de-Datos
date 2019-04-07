package ule.edi.doubleList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class DoubleLinkedListImplTests {

	private DoubleLinkedListImpl<String> lS;
	private DoubleLinkedListImpl<String> lSABC;
	private DoubleLinkedListImpl<String> lSABCDE;


	@Before
	public void setup() {
		this.lS = new DoubleLinkedListImpl<String>();
	    this.lSABC=new DoubleLinkedListImpl<String>("A", "B", "C");
	    this.lSABCDE=new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E");
	}
	
	
	//Mis Test
	@Test
	public void testIsEmpty() {
		Assert.assertEquals(true, lS.isEmpty());
		lS.addFirst("Hola");
		Assert.assertEquals(false, lS.isEmpty());
		//REMOVE
		
		
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
}

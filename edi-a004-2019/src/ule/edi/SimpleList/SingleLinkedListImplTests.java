package ule.edi.SimpleList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SingleLinkedListImplTests {

	

	private SingleLinkedListImpl<String> lS;
	private SingleLinkedListImpl<String> lSABC;
	

	@Before
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();
		
		
		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
	}
	
   @Test
   public void constructorElemens(){
	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("[A, B, C, D]", lS.toString());
   }
	
	//MIS TEST
	//ISEMPTY
	@Test
	   public void testIsEmpty(){
		   Assert.assertEquals(true, lS.isEmpty());
		   Assert.assertEquals(false, lSABC.isEmpty());
	   }
	//SIZE
	 @Test
	   public void testSize(){
		   Assert.assertEquals(0, lS.size());
		   Assert.assertEquals(3, lSABC.size());
	   }
	//ADDFIRST
	 @Test
	   public void testAddFirst(){
		   lS.addFirst("X");
		   Assert.assertEquals("[X]", lS.toString());
		   lS.addFirst("Y");
		   Assert.assertEquals("[Y, X]", lS.toString());
		   lS.addFirst("Z");
		   Assert.assertEquals("[Z, Y, X]", lS.toString());
	   }
	//ADDLAST
	 @Test
	   public void testAddLast(){
		   lS.addLast("X");
		   Assert.assertEquals("[X]", lS.toString());
		   lS.addLast("Y");
		   Assert.assertEquals("[X, Y]", lS.toString());
		   lS.addLast("Z");
		   Assert.assertEquals("[X, Y, Z]", lS.toString());
	   }
	//INDEXOF
	 @Test
	   public void testIndexOf(){
		   Assert.assertEquals(1, lSABC.indexOf("A"));
		   Assert.assertEquals(2, lSABC.indexOf("B"));
		   Assert.assertEquals(3, lSABC.indexOf("C"));
	   }
	 @Test(expected = NoSuchElementException.class)
	  	public void testIdexOfException() {
	  		lS.indexOf("L");
	  	}
	//REMOVELAST
	  @Test
	   public void testRemoveLast() throws EmptyCollectionException{
		   Assert.assertEquals("C", lSABC.removeLast());
		   Assert.assertEquals("[A, B]", lSABC.toString());
		   Assert.assertEquals("B", lSABC.removeLast());
		   Assert.assertEquals("[A]", lSABC.toString());
		   Assert.assertEquals("A", lSABC.removeLast());
		   Assert.assertEquals("[]", lSABC.toString());
		   
	   }
	   
	   @Test(expected = EmptyCollectionException.class)
	 	public void testRemoveLastException() throws EmptyCollectionException {
	 		lS.removeLast();
	 	}
	   
	   @Test
	   public void removeLastElemTest() throws EmptyCollectionException {
		   lSABC.addLast("B");
		   lSABC.addLast("X");
		   lSABC.addLast("A");
		   
		   lS.addFirst("C");
		   lS.addFirst("A");
		   lS.addFirst("B");
		   
		   Assert.assertEquals("A", lS.removeLast("A"));
		   Assert.assertEquals("A", lSABC.removeLast("A"));
		   Assert.assertEquals("A", lSABC.removeLast("A"));
	   }
	   
	   @Test(expected = EmptyCollectionException.class)
		public void testRemoveLastEmptyException() throws EmptyCollectionException {
			lS.removeLast("L");
		}
	   
	   @Test(expected = NoSuchElementException.class)
		public void testRemoveLastElemenException() throws EmptyCollectionException{
			lSABC.removeLast("L");
		}
	   
	  
	//REVERSE
	   @Test
	   public void testReverse() throws EmptyCollectionException {
		   Assert.assertEquals("[C, B, A]", lSABC.reverse().toString());
		   Assert.assertEquals("[]", lS.reverse().toString());
	   }
	//ITERADOR
	   @Test
	   public void testForwardIterator() {
		   Iterator<String> iteratorE = lS.iterator();
		   
		   Iterator<String> iteratorF = lSABC.iterator();
		   
		  Assert.assertEquals(false, iteratorE.hasNext());
		  
		  Assert.assertEquals(true, iteratorF.hasNext());
		  Assert.assertEquals("B", iteratorF.next());
		  Assert.assertEquals(true, iteratorF.hasNext());
		  Assert.assertEquals("C", iteratorF.next());
		  Assert.assertEquals(false, iteratorF.hasNext());
	   }
	   
	   @Test(expected = NoSuchElementException.class)
		public void testForewardIteratorException() {
			Iterator<String> i = lS.iterator();
			i.next();
		}
	  
	
	//


   @Test
   public void testAddAtPos(){
	   
	   lS.addAtPos("A", 1);
	   Assert.assertEquals("[A]", lS.toString());
	   lS.addAtPos("C", 1);
	   Assert.assertEquals("[C, A]", lS.toString());
	   lS.addAtPos("B", 2);
	   Assert.assertEquals("[C, B, A]", lS.toString());
	   lS.addAtPos("D", 5);
	   Assert.assertEquals("[C, B, A, D]", lS.toString());
   }
   
   @Test
   public void testAddNTimes(){
	   
	   lS.addNTimes("A", 3);
	   Assert.assertEquals("[A, A, A]", lS.toString());
	   lS.addNTimes("B", 2);
	   Assert.assertEquals("[A, A, A, B, B]", lS.toString());
	   
   }
   
   
// TEST DE SUBLIST
	@Test
	public void testSubListEnListaVacia() {
	
		Assert.assertEquals(-1, lS.isSubList(lSABC));		
	}

		@Test
		public void tesSubListConSubListaVacia() {
			Assert.assertEquals(1, lSABC.isSubList(lS));		
		}
		
		
		@Test
		public void subListVarios() {
			lS = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E");
			Assert.assertEquals(1, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C", "D", "E");
			Assert.assertEquals(3, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C");
			Assert.assertEquals(3, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("A", "B", "C");
			Assert.assertEquals(1, lS.isSubList(lSABC));	
		}
	 
   

}

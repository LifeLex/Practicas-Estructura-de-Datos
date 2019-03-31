package ule.edi.limitedpriorityqueue;

import org.junit.*;



public class LimitedPriorityQueueArrayTests {

	private LimitedPriorityQueueArrayImpl<String> pq3;
	private LimitedPriorityQueueArrayImpl<String> pq5;
	private LinkedQueue<String> pq4 = new LinkedQueue<>();
	
	
	public LimitedPriorityQueueArrayTests() {
		

	}
	
	@Before
	public void testBefore() throws Exception{
	    pq3 = new LimitedPriorityQueueArrayImpl<String>(3,2); // limitado a 3 elementos, las posibles prioridades son [1,2]
	    pq5 = new LimitedPriorityQueueArrayImpl<String>(5,3); // limitado a 5 elementos, las posibles prioridades son [1,2,3]

	}
	@Test
	public void testIsFull() throws Exception{
		Assert.assertEquals(false, pq3.isFull());
		Assert.assertEquals(false, pq5.isFull());
		Assert.assertEquals(null, pq3.enqueue(1, "1_1"));
		Assert.assertEquals(null, pq3.enqueue(2, "2_1"));
		Assert.assertEquals(null, pq3.enqueue(1, "3_1"));
		Assert.assertEquals(true, pq3.isFull());
		Assert.assertEquals("1_1", pq3.dequeue());
		Assert.assertEquals(false, pq3.isFull());
	}
	
	@Test
	public void testGetCapacity() throws Exception{
		Assert.assertEquals(3, pq3.getCapacity());
		Assert.assertEquals(5, pq5.getCapacity());
	}
	@Test
	public void testGetSize() throws Exception{
		Assert.assertEquals(pq3.getSize(), 0);
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq3.getSize(), 1);
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq3.getSize(), 2);	
	}
	
	@Test
	public void testIsFirst() throws Exception{
		Assert.assertEquals(null, pq3.enqueue(1, "1_1"));
		Assert.assertEquals("1_1", pq3.first());
		Assert.assertEquals(null, pq3.enqueue(2, "2_1"));
	    Assert.assertEquals("1_1", pq3.first());
	    Assert.assertEquals("1_1", pq3.dequeue());
	    Assert.assertEquals("2_1", pq3.first());
	}
	
	@Test
	public void testIsEmpty() throws Exception{
		Assert.assertEquals(true, pq3.isEmpty());
		Assert.assertEquals(null, pq3.enqueue(1, "1_1"));
		Assert.assertEquals(false, pq3.isEmpty());
		Assert.assertEquals("1_1", pq3.dequeue());
		Assert.assertEquals(true, pq3.isEmpty());
	}
	
	@Test
	public void testMisCosas() throws Exception {
		Assert.assertEquals(false, pq3.isFull());
		Assert.assertEquals(true, pq3.isEmpty());
		Assert.assertEquals(3, pq3.getCapacity());
		Assert.assertEquals(null, pq3.enqueue(1, "1_1"));
		Assert.assertEquals(null, pq3.enqueue(2, "2_1"));
		Assert.assertEquals(null, pq3.enqueue(1, "3_1"));
		Assert.assertEquals("2_1", pq3.enqueue(1, "5_1"));
		Assert.assertEquals("6_1", pq3.enqueue(2, "6_1"));
		
		Assert.assertEquals(true, pq3.isFull());
		Assert.assertEquals("1_1", pq3.first());
		Assert.assertEquals("1_1", pq3.dequeue());
		Assert.assertEquals(false, pq3.isFull());
		Assert.assertEquals(false, pq3.isEmpty());
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testDequeue() throws EmptyCollectionException {
		pq3.dequeue();
	}
	@Test(expected = EmptyCollectionException.class)
	public void testFirst() throws EmptyCollectionException {
		pq3.first();
	}
	
	@Test
	public void testEnVacia() throws Exception {
		
	    Assert.assertEquals(pq3.isEmpty(), true);
	    Assert.assertEquals(pq3.isFull(), false);
	    Assert.assertEquals(pq3.getSize(), 0);
	    Assert.assertEquals(pq3.toString(), "[]");
	}
	
	@Test
	public void testInsertarHastaLLenar() throws Exception{
	    Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq3.isEmpty(), false);
	    Assert.assertEquals(pq3.getSize(), 1);
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq3.isEmpty(), false);
	    Assert.assertEquals(pq3.getSize(), 2);	
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
	    Assert.assertEquals(pq3.isEmpty(), false);
	    Assert.assertEquals(pq3.getSize(), 3);	
	    Assert.assertEquals(pq3.isFull(), true);
	    Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	  
	}
	
	@Test
	public void testInsertarMenorPrioEnLLena() throws Exception{
		System.out.println(pq3.toString());
	    Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
	    System.out.println(pq3.toString());
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
	    System.out.println(pq3.toString());
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
	    System.out.println(pq3.toString());
	    Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_3"), "Prior2_3");    // El elemento insertado tiene menor prioridad que los que estaban, por tanto es el que sale
	    System.out.println(pq3.toString());
	    Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	  
	}
	
	@Test
	public void testInsertarMayorPrioEnLLena() throws Exception{
	    Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
	    Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	    Assert.assertEquals(pq3.enqueue(1, "Prior1_2"), "Prior2_2");
	    Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1, Prior1_2)), ( Priority:2 (Prior2_1))]");
	  
	}
	
	
	//Lista de prueba
	@Test
	public void testListaPrueba() throws Exception {
		Assert.assertEquals(true, pq4.isEmpty());
		Assert.assertEquals(0, pq4.size());
		Assert.assertEquals("", pq4.toString());
		
		pq4.enqueue("1");
		pq4.enqueue("2");
		pq4.enqueue("3");
		pq4.enqueue("111");
		
		Assert.assertEquals(4, pq4.size());
		Assert.assertEquals("1, 2, 3, 111", pq4.toString());
		Assert.assertEquals("1", pq4.first());
		Assert.assertEquals("1", pq4.dequeue());
		Assert.assertEquals("111", pq4.dequeueLast());
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testDequeueLinkedQueue() throws EmptyCollectionException {
		pq4.dequeue();
	}
	@Test(expected = EmptyCollectionException.class)
	public void testDequeueLastLinkedQueue() throws EmptyCollectionException {
		pq4.dequeueLast();
	}
	@Test(expected = EmptyCollectionException.class)
	public void testFirstEmptyLinkedQueue() throws EmptyCollectionException {
		pq4.first();
		
	}
}
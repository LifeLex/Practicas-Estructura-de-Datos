package ule.edi.limitedpriorityqueue;

import org.junit.*;




public class LimitedPriorityQueueArrayTests {

	
	private LimitedPriorityQueueArrayImpl<String> pq10;
	private LimitedPriorityQueueArrayImpl<String> pq5;
	private LinkedQueue<String> pq1 = new LinkedQueue<>();
	
	public LimitedPriorityQueueArrayTests() {
		

	}
	
	@Before
	public void testBefore() throws Exception{
	    pq10 = new LimitedPriorityQueueArrayImpl<String>(3,2); // limitado a 3 elementos, las posibles prioridades son [1,2]
	    pq5 = new LimitedPriorityQueueArrayImpl<String>(5,3); // limitado a 5 elementos, las posibles prioridades son [1,2,3]

	}
	
	@Test
	public void testEnVacia() throws Exception {
		
	    Assert.assertEquals(pq10.isEmpty(), true);
	    Assert.assertEquals(pq10.isFull(), false);
	    Assert.assertEquals(pq10.getSize(), 0);
	    Assert.assertEquals(pq10.toString(), "[]");
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testDequeueLinkedQueue() throws EmptyCollectionException {
		pq1.dequeue();
	}
	@Test(expected = EmptyCollectionException.class)
	public void testDequeueLastLinkedQueue() throws EmptyCollectionException {
		pq1.dequeueLast();
	}
	@Test(expected = EmptyCollectionException.class)
	public void testFirstEmptyLinkedQueue() throws EmptyCollectionException {
		pq1.first();
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEnqueueException() throws IllegalArgumentException {
		pq10.enqueue(-1,"1");
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEnqueueException1() throws IllegalArgumentException {
		pq10.enqueue(100,"1");
	}
	@Test(expected = NullPointerException.class)
	public void testEnqueueException2() throws NullPointerException {
		pq5.enqueue(1,null);
		
	}
	/*
	@Test
	public void testInsertarHastaLLenar() throws Exception{
	    Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq5.isEmpty(), false);
	    Assert.assertEquals(pq5.getSize(), 1);
	    Assert.assertEquals(pq5.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq5.isEmpty(), false);
	    Assert.assertEquals(pq5.getSize(), 2);	
	    Assert.assertEquals(pq5.enqueue(2, "Prior2_2"), null);
	    Assert.assertEquals(pq5.isEmpty(), false);
	    Assert.assertEquals(pq5.getSize(), 3);	
	    Assert.assertEquals(pq5.isFull(), true);
	    Assert.assertEquals(pq5.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	  
	}
	
	@Test
	public void testInsertarMenorPrioEnLLena() throws Exception{
	    Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq5.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq5.enqueue(2, "Prior2_2"), null);
	    Assert.assertEquals(pq5.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	    Assert.assertEquals(pq5.enqueue(2, "Prior2_3"), "Prior2_3");    // El elemento insertado tiene menor prioridad que los que estaban, por tanto es el que sale
	    Assert.assertEquals(pq5.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	  
	}
	
	@Test
	public void testInsertarMayorPrioEnLLena() throws Exception{
	    Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq5.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq5.enqueue(2, "Prior2_2"), null);
	    Assert.assertEquals(pq5.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	    Assert.assertEquals(pq5.enqueue(1, "Prior1_2"), "Prior2_2");
	    Assert.assertEquals(pq5.toString(), "[( Priority:1 (Prior1_1, Prior1_2)), ( Priority:2 (Prior2_1))]");
	  
	}
	*/
	@Test
	public void testInsertarMenorPrioEnLLena2() throws Exception{
		System.out.println(pq10.toString());
	    Assert.assertEquals(pq10.enqueue(1, "Prior1_1"), null);
	    System.out.println(pq10.toString());
	    Assert.assertEquals(pq10.enqueue(2, "Prior2_1"), null);
	    System.out.println(pq10.toString());
	    Assert.assertEquals(pq10.enqueue(2, "Prior2_2"), null);
	    System.out.println(pq10.toString());
	    Assert.assertEquals(pq10.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	    Assert.assertEquals(pq10.enqueue(2, "Prior2_3"), "Prior2_3");    // El elemento insertado tiene menor prioridad que los que estaban, por tanto es el que sale
	    System.out.println(pq10.toString());
	    Assert.assertEquals(pq10.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	  
	}
	
	@Test
	public void testInsertarMayorPrioEnLLena3() throws Exception{
	    Assert.assertEquals(pq10.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq10.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq10.enqueue(2, "Prior2_2"), null);
	    Assert.assertEquals(pq10.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	    Assert.assertEquals(pq10.enqueue(1, "Prior1_2"), "Prior2_2");
	    Assert.assertEquals(pq10.toString(), "[( Priority:1 (Prior1_1, Prior1_2)), ( Priority:2 (Prior2_1))]");
	  
	}
	
	@Test
	public void testInsertarHastaLLenar2() throws Exception{
	    Assert.assertEquals(pq10.enqueue(1, "Prior1_1"), null);
	    Assert.assertEquals(pq10.isEmpty(), false);
	    Assert.assertEquals(pq10.getSize(), 1);
	    Assert.assertEquals(pq10.enqueue(2, "Prior2_1"), null);
	    Assert.assertEquals(pq10.isEmpty(), false);
	    Assert.assertEquals(pq10.getSize(), 2);	
	    Assert.assertEquals(pq10.enqueue(2, "Prior2_2"), null);
	    Assert.assertEquals(pq10.isEmpty(), false);
	    Assert.assertEquals(pq10.getSize(), 3);	
	    Assert.assertEquals(pq10.isFull(), true);
	    Assert.assertEquals(pq10.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
	  
	}
}

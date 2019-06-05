package ule.edi.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;




public class BinarySearchTreeADTTests {

    /*
	* ∅
    */
	private BinarySearchTreeADTImpl<Integer> TE = null;
	
	/*
	* 1
	* |  ∅
	* |  2
	* |  |  ∅
	* |  |  3
	* |  |  |  ∅
	* |  |  |  4
	* |  |  |  |  ∅
	* |  |  |  |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> T1234 = null;
	
	/*
	* 4
	* |  3
	* |  |  2
	* |  |  |  1
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> T4321 = null;

	/*
	* 50
	* |  20
	* |  |  10
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
	* |  80
	* |  |  70
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  90
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> TC3 = null;

	/*
	* 10
	* |  5
	* |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
	*/
	private BinarySearchTreeADTImpl<Integer> TEx = null;

	/*
	 * 10
	 * |  5
	 * |  |  ∅
	 * |  |  7
	 * |  |  |  6
	 * |  |  |  |  ∅
	 * |  |  |  |  ∅
	 * |  |  |  ∅
	 * |  15
	 * |  |  ∅
	 * |  |  ∅
	 * 
	 */
	private BinarySearchTreeADTImpl<Integer> TV1 = null;

	@Before
	public void setupBSTs() {
		
		TE = new BinarySearchTreeADTImpl<Integer>();
		
		T1234 = new BinarySearchTreeADTImpl<Integer>();
		T1234.insert(1,2,3,4);
		Assert.assertEquals(T1234.toString(), "{1, ∅, {2, ∅, {3, ∅, {4, ∅, ∅}}}}");
		
		T4321 = new BinarySearchTreeADTImpl<Integer>();
		T4321.insert(4, 3, 2, 1);
		Assert.assertEquals(T4321.toString(), "{4, {3, {2, {1, ∅, ∅}, ∅}, ∅}, ∅}");
		
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 80, 10, 30, 70, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {80, {70, ∅, ∅}, {90, ∅, ∅}}}");
		
		TEx = new BinarySearchTreeADTImpl<Integer>();
		TEx.insert(10, 20, 30, 5);
		Assert.assertEquals(TEx.toString(), "{10, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}");
		
		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, 15);		
		Assert.assertEquals(TV1.toString(), "{10, {5, ∅, {7, {6, ∅, ∅}, ∅}}, {15, ∅, ∅}}");
		
		
	}


		@Test
		public void testTagDescendTC4() {
			List<String> lista= new LinkedList<String>();
			//TEx.parentChildPairsTagDescend(lista);
			TC3.parentChildPairsTagDescend(lista);
			Assert.assertEquals(lista.toString(), "[(80, 90), (80, 70), (50, 80), (50, 20), (20, 30), (20, 10)]");
			TC3.filterTags("descend");
			Assert.assertEquals("{50 [(descend, 4)], {20 [(descend, 6)], {10 [(descend, 7)], ∅, ∅}, {30 [(descend, 5)], ∅, ∅}}, {80 [(descend, 2)], {70 [(descend, 3)], ∅, ∅}, {90 [(descend, 1)], ∅, ∅}}}", TC3.toString());
			
		}
	
		@Test
		public void getSubtreeWithPathTest() {

			TE.insert(17);
			TE.insert(22);
			TE.insert(19);
			TE.insert(4);
			TE.insert(10);
			TE.insert(18);
			TE.insert(30);
			TE.insert(1);
			Assert.assertEquals("{18, ∅, ∅}", TE.getSubtreeWithPath("100").toString());
		}
	

		@Test(expected = NoSuchElementException.class)
		public void getSubtreeWithPath2Test() {

			TC3.getSubtreeWithPath("111111");
		}

		@Test(expected = IllegalArgumentException.class)
		public void getSubtreeWithPath3Test() {

			TC3.getSubtreeWithPath("84848");
		}

		@Test
		public void insertTest() {

			TE.insert(17);
			TE.insert(22);
			TE.insert(19);
			TE.insert(4);
			TE.insert(10);
			TE.insert(18);
			TE.insert(30);
			TE.insert(1);
			// System.out.println(TE.toString());
			Assert.assertEquals("{17, {4, {1, ∅, ∅}, {10, ∅, ∅}}, {22, {19, {18, ∅, ∅}, ∅}, {30, ∅, ∅}}}",
					TE.toString());
		}

		@Test(expected = IllegalArgumentException.class)
		public void insertExcepcionTest() {

			TE.insert(17);
			TE.insert(22);
			TE.insert(19);
			TE.insert(4);
			TE.insert(10);
			TE.insert(18);
			TE.insert(30);
			TE.insert(1);
			Integer a = null;
			TE.insert(a);
		}

		@Test(expected = IllegalArgumentException.class)
		public void insertExcepcionDTest() {

			TE.insert(17, 22, 19, 4, 10, 18, 30, 1);
			Integer a = null;
			TE.insert(a);
		}

		@Test(expected = IllegalArgumentException.class)
		public void insertExcepcionETest() {

			TE.insert(17, null, 22, 19, 4, 10, 18, 30, 1);
		}

		@Test
		public void insertExceptionFTest() {

			ArrayList<Integer> lista = new ArrayList<Integer>();
			lista.add(10);
			TE.insert(lista);
		}

		@Test(expected = IllegalArgumentException.class)
		public void insertExceptionGTest() {

			ArrayList<Integer> lista = new ArrayList<Integer>();
			lista.add(null);
			TE.insert(lista);
		}
		
		@Test
		public void tagWidthTest() {
			TV1.tagWidth();
			Assert.assertEquals("{10 [(width, 1)], {5 [(width, 2)], ∅, {7 [(width, 4)], {6 [(width, 5)], ∅, ∅}, ∅}}, {15 [(width, 3)], ∅, ∅}}" , TV1.toString());
		}
		@Test(expected = NoSuchElementException.class)
		public void tagWidthTest2() {
			TE.tagWidth();;
		}
		
	}



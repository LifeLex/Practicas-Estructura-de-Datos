package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}

	public EventArrayImplTests() {
		
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Before
	public void testBefore() throws Exception{
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10, 100);

	}
	
	@Test
	public void testSomething() throws Exception {
		
	    Assert.assertEquals(e.getNumberOfAvailableSeats(),110);
	    Assert.assertEquals(e.getNumberOfSilverSeats(), 100);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
	}
	
	@Test
	public void testSellSeat1Adult() throws Exception{
		
			
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		e.sellSeat(1, new Person("10203040A","Alice", 34),Type.GOLD);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
	  
	}
	
	@Test
	public void testgetAvailableSilverSeatsListBasic() throws Exception{
		 Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.SILVER),true);
		Assert.assertEquals(ep.getAvailableSilverSeatsList().toString(), "[2]");					
	}
	
	@Test
	public void testgetNumberOfSeats() throws Exception{
		Assert.assertEquals(10, e.getNumberOfGoldSeats());
		Assert.assertEquals(100, e.getNumberOfSilverSeats());
		Assert.assertEquals(110, e.getNumberOfSeats());
	}
	
	@Test
	public void testsellSeat() throws Exception{
		//Podria declarar un objeto person para cada assert dentro de este test
		Person persona1= new Person("12345678A", "Alex", 22);
		Person persona2= new Person("12345678A", "Alex", 22);
		Assert.assertEquals(true, e.sellSeat(1, persona1, Configuration.Type.GOLD));
		//Assert.assertEquals(false, e.sellSeat(1, persona2, Configuration.Type.GOLD));

//		Assert.assertEquals(true, e.sellSeat(1, persona, Configuration.Type.SILVER));
//		//probar una posicion inexistente
//		Assert.assertEquals(false, e.sellSeat(0, persona, Configuration.Type.GOLD));
//		Assert.assertEquals(false, e.sellSeat(0, persona, Configuration.Type.SILVER));
//		Assert.assertEquals(false, e.sellSeat(11, persona, Configuration.Type.GOLD));
//		Assert.assertEquals(false, e.sellSeat(101, persona, Configuration.Type.SILVER));
//		
		
		
	}
}

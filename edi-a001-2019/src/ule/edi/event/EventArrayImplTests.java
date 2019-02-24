package ule.edi.event;

import static org.junit.Assert.assertEquals;

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
	public void testSellSeat() throws Exception{
		Person persona1= new Person("12345678A", "Alex", 22);
		Person persona2= new Person("12345678B", "pedro", 23);
		Assert.assertEquals(true, e.sellSeat(1, persona1, Configuration.Type.GOLD));
		Assert.assertEquals(false, e.sellSeat(1, persona2, Configuration.Type.GOLD));

		Assert.assertEquals(true, e.sellSeat(1, persona1, Type.SILVER));
//		//probar una posicion inexistente
		Assert.assertEquals(false, e.sellSeat(0, persona1, Configuration.Type.GOLD));
		Assert.assertEquals(false, e.sellSeat(0, persona1, Configuration.Type.SILVER));
		Assert.assertEquals(false, e.sellSeat(11, persona1, Configuration.Type.GOLD));
		Assert.assertEquals(false, e.sellSeat(101, persona1, Configuration.Type.SILVER));
//		
		
		
	}
	
	@Test
	public void testConstructor1() throws Exception{
		e = new EventArrayImpl("nombre", parseLocalDate("24/02/2018 17:00:00"), 4, 6);
		Assert.assertEquals("nombre", e.getName());
		Assert.assertEquals(parseLocalDate("24/02/2018 17:00:00"), e.getDate());
		Assert.assertEquals(4, e.getNumberOfGoldSeats());
		Assert.assertEquals(6, e.getNumberOfSilverSeats());
		
		Assert.assertNotEquals("aaaaa", e.getName());
		Assert.assertNotEquals(parseLocalDate("25/03/2019 16:00:00"), e.getDate());
		Assert.assertNotEquals(5, e.getNumberOfGoldSeats());
		Assert.assertNotEquals(7, e.getNumberOfSilverSeats());
		
	}
	
	@Test
	public void testConstructor2() throws Exception{
		e = new EventArrayImpl("nombre", parseLocalDate("24/02/2018 17:00:00"), 4, 6, 5.5678, 3.4567);
		Assert.assertEquals("nombre", e.getName());
		Assert.assertEquals(parseLocalDate("24/02/2018 17:00:00"), e.getDate());
		Assert.assertEquals(4, e.getNumberOfGoldSeats());
		Assert.assertEquals(6, e.getNumberOfSilverSeats());
		Assert.assertEquals(Double.valueOf(5.5678), e.getPriceGold());
		Assert.assertEquals(Double.valueOf(3.4567), e.getPriceSilver());
			
		Assert.assertNotEquals("aaaaa", e.getName());
		Assert.assertNotEquals(parseLocalDate("25/03/2019 16:00:00"), e.getDate());
		Assert.assertNotEquals(5, e.getNumberOfGoldSeats());
		Assert.assertNotEquals(7, e.getNumberOfSilverSeats());
		Assert.assertNotEquals(Double.valueOf(7.5678), e.getPriceGold());
		Assert.assertNotEquals(Double.valueOf(5.4567), e.getPriceSilver());
	}
	
	
}

package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.Configuration.Type;
import ule.edi.model.*;

public class EventArrayImpl implements Event {
	
	
	private String name;
	private Date date;
	
	private Double priceGold;    // precio de entradas tipo GOLD
	private Double priceSilver;  // precio de entradas tipo SILVER
	
	private int nGold;    // Nº de butacas de tipo GOLD
	private int nSilver;  // Nº de butacas de tipo SILVER
	
	private Seat[] gold;
	private Seat[] silver;
	
	
	
   public Double getPriceGold() {
		return priceGold;
	}


	public void setPriceGold(Double priceGold) {
		this.priceGold = priceGold;
	}


	public Double getPriceSilver() {
		return priceSilver;
	}


	public void setPriceSilver(Double priceSilver) {
		this.priceSilver = priceSilver;
	}


public EventArrayImpl(String name, Date date, int nGold, int nSilver){
	   //TODO 
	   // utiliza los precios por defecto: DEFAULT_PRICE_GOLD y DEFAULT_PRICE_SILVER definidos en Configuration.java
	   this.name= name;
	   this.date= date;
	   this.nGold= nGold;
	   this.nSilver= nSilver;
	   
	   // Debe crear los arrays de butacas gold y silver
	   gold= new Seat[nGold];
	   silver= new Seat[nSilver];
   }
   
   
   public EventArrayImpl(String name, Date date, int nGold, int nSilver, Double priceGold, Double priceSilver){
	   //TODO 
	   // Debe crear los arrays de butacas gold y silver
	   this.name= name;
	   this.date= date;
	   this.priceGold= priceGold;
	   this.priceSilver= priceSilver;
	   this.nGold= nGold;
	   this.nSilver= nSilver;
	   gold= new Seat[nGold];
	   silver= new Seat[nSilver];
	   
	   
	   
   }
   

	@Override
	public String getName() {
				return name;
	}

	@Override
	public Date getDate() {
		return date;
	}

	
	@Override
	public int getNumberOfAttendingChildren() {
		int nChildren= 0;
		
		for (int i = 0; i < nGold; i++) {
			if (gold[i]!=null && gold[i].getHolder().getAge()<Configuration.CHILDREN_EXMAX_AGE) {
				nChildren++;
			}
		}
		for (int i = 0; i < nSilver; i++) {
			if (silver[i]!=null && silver[i].getHolder().getAge()<Configuration.CHILDREN_EXMAX_AGE) {
				nChildren++;
			}
		}
		return nChildren;
	}

	@Override
	public int getNumberOfAttendingAdults() {
		int nAdults=0;
		for (int i = 0; i < nGold; i++) {
			if (gold[i]!=null && gold[i].getHolder().getAge()>Configuration.CHILDREN_EXMAX_AGE && gold[i].getHolder().getAge()<Configuration.ELDERLY_PERSON_INMIN_AGE) {
				nAdults++;
			}
		}
		for (int i = 0; i < nSilver; i++) {
			if (silver[i]!=null && silver[i].getHolder().getAge()>Configuration.CHILDREN_EXMAX_AGE && silver[i].getHolder().getAge()<Configuration.ELDERLY_PERSON_INMIN_AGE) {
				nAdults++;
			}
		}
		return nAdults;
	}

	@Override
	public int getNumberOfAttendingElderlyPeople() {
		int nElder= 0;
		
		for (int i = 0; i < nGold; i++) {
			if (gold[i]!=null && gold[i].getHolder().getAge()>Configuration.ELDERLY_PERSON_INMIN_AGE) {
				nElder++;
			}
		}
		for (int i = 0; i < nSilver; i++) {
			if (silver[i]!=null && silver[i].getHolder().getAge()>Configuration.ELDERLY_PERSON_INMIN_AGE) {
				nElder++;
			}
		}
		return nElder;
		
	}

	@Override
	public int getNumberOfSoldSeats() {
		int numeroG=0;
		int numeroS=0;
		int numeroTotal=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]!= null) {
				numeroG++;
			}
		}
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]!= null) {
				numeroS++;
			}
		}
		numeroTotal= numeroG+numeroS;
			return numeroTotal;
	}

	@Override
	public int getNumberOfSoldGoldSeats() {
		int numeroG=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]!= null) {
				numeroG++;
			}
		}
		return numeroG;
	}

	@Override
	public int getNumberOfSoldSilverSeats() {
		int numeroS=0;
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]!=null) {
				numeroS++;
			}
		}
		return numeroS;
	}

	@Override
	public int getNumberOfSeats() {
		return nGold+nSilver;
	}

	@Override
	public int getNumberOfGoldSeats() {
		return nGold;
	}

	@Override
	public int getNumberOfSilverSeats() {
		return nSilver;
	}


	@Override
	public int getNumberOfAvailableSeats() {
		int disponiblesG=0;
		int disponiblesS=0;
		int disponibles=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]==null) {
				disponiblesG++;
			}
		}
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]==null) {
				disponiblesS++;
			}
		}
		disponibles=disponiblesG+disponiblesS;
		return disponibles;
	}


	@Override
	public Seat getSeat(int pos, Type type) {
		if (type==Type.GOLD) {
			
			return gold[pos-1];
			
		}
		if (type==Type.SILVER) {
			return silver[pos-1];
		}else {
		
		return null;
		}
	}


	@Override
	public Person refundSeat(int pos, Type type) {
		Person owner;
		if (type==Type.GOLD) {
			if (gold[pos-1]!=null) {
				owner=gold[pos-1].getHolder();
				gold[pos-1]=null;
				return owner;
			}
		}
		if (type==Type.SILVER) {
			owner=silver[pos-1].getHolder();
			silver[pos-1]=null;
			return owner;
		}
		return null;
	}


	@Override
	public boolean sellSeat(int pos, Person p, Type type) {
			if(pos-1>=0) {
			if (type == Configuration.Type.GOLD ) {
//				System.out.println(pos);
//				System.out.println(nGold);
				if(pos-1<nGold) {
//					System.out.println(pos);
					if (gold[pos-1]==null) {
						
							gold[pos-1]= new Seat(null, pos, Configuration.Type.GOLD, p);
						return true;
					}
				}
			}
			
			if(pos-1<nSilver) {
				
				if (type== Configuration.Type.SILVER) {
					System.out.println(pos);
						if (silver[pos-1]==null) {
								silver[pos-1]= new Seat(null, pos, Configuration.Type.SILVER, p);
							return true;
						
							}
						
					}
				}
			}
		
		
		return false;
	}


	@Override
	public List<Integer> getAvailableGoldSeatsList() {
		List<Integer> disponibles = new ArrayList<Integer>();
		
			for (int i = 0; i < gold.length; i++) {
				if (gold[i]==null) {
					disponibles.add(i+1);
				}
				
			}
		
		
		
		return disponibles;
	}


	@Override
	public List<Integer> getAvailableSilverSeatsList() {
		List<Integer> disponibles = new ArrayList<Integer>();
		
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]==null) {
				disponibles.add(i+1);
			}
			
		}
	
	
	
	return disponibles;
	}


	@Override
	public Double getPrice(Seat seat) {
		if (seat.getType()==Type.GOLD) {
			return getPriceGold();
		}else if(seat.getType()==Type.SILVER){
			return getPriceSilver();
		}
		return null;
	}


	@Override
	public Double getCollectionEvent() {
		double precio=0;
		for (int i = 0; i < gold.length; i++) {
			if (gold[i]!=null) {
				precio=precio+Configuration.DEFAULT_PRICE_GOLD;
			}
		}
		for (int i = 0; i < silver.length; i++) {
			if (silver[i]!=null) {
				precio=precio+Configuration.DEFAULT_PRICE_SILVER;
			}
		}
		return precio;
	}


	@Override
	public int getPosPersonGold(Person p) {
		for (int i = 0; i < gold.length; i++) {
			if (p.equals(gold[i].getHolder())) {
				
				return i+1;
			}
		}
		return 0;
	}


	@Override
	public int getPosPersonSilver(Person p) {
		for (int i = 0; i < silver.length; i++) {
			if (p.equals(silver[i].getHolder())) {
				return i+1;
			}
		}
		return 0;
	}


	@Override
	public boolean isGold(Person p) {
		for (int i = 0; i < gold.length; i++) {
			if (p.equals(gold[i].getHolder())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean isSilver(Person p) {
		for (int i = 0; i < silver.length; i++) {
			if (p.equals(silver[i].getHolder())) {
				return true;
			}
		}
		return false;
	}

	

	
}

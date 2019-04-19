package src.houses;

import java.util.*;
import src.bookings.Booking;
import src.users.*;
import src.Manageable;

public class House implements Manageable {

	private boolean[] facilities;
	private Booking[] calendar = new Booking[Booking.CAL_SIZE];
	private double pricePerNightPerPerson;
	private double rentalFee;
	private String location;
	private Owner owner;
	private String name;

	public House(Owner owner, String name, double pricePerNightPerPerson, double rentalFee, String location){
		if(owner == null) return; //throw exception
		this.owner = owner;
		this.pricePerNightPerPerson = pricePerNightPerPerson;
		this.rentalFee = rentalFee;
		this.location = location;
		this.name = name;
    this.facilities = Facility.chooseFacilities();
	}

	public boolean[] getFacilities() { return facilities; }
	public Booking[] getCalendar() { return calendar; }
	public double getPricePerNightPerPerson() { return pricePerNightPerPerson; }
	public double getRentalFee() { return rentalFee; }
	public String getLocation() { return location; }
	public Owner getOwner() { return owner; }
	public String getName() { return name; }

	public void setPricePerNightPerPerson(double pricePerNightPerPerson) {
		this.pricePerNightPerPerson = pricePerNightPerPerson;
	}
	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public void setName(String name){
		this.name = name;
	}
	public void addBooking(int in, int out, Client client, int numPeople){
		Booking b = new Booking(in, out, this, client, numPeople);
		for (int i=in; i<out; i++){
			calendar[i]=b;
		}
	}
  public void removeBooking(Booking booking){
    for(int i = 0; i < calendar.length; i++){
      if(calendar[i] == booking) calendar[i] = null;
    }
  }

	public void display(){
		System.out.println("\nHouse information\n");
		System.out.println("\tProperty's name: " + name);
		System.out.println("\tOwner: " + owner.getName());
		System.out.println("\tOwner's email: " + owner.getPublicEmail());
		System.out.println("\tPrice per night per person: " + pricePerNightPerPerson);
		System.out.println("\tRental fee: "+ rentalFee);
		System.out.println("\tLocation: " + location);
    System.out.println("\tFacilities:");
    Facility.display(facilities);
	}

	public boolean checkAvailability(int in, int out){
		for(int i=in; i<out; i++)
			if(calendar[i]!=null)
				return false;
			return true;
	}

	public static House [] search(String location, int in, int out, boolean[] facilities){
		House[] results = new House[100];
		User[] users = User.getUsers();
		int nresults=0;

		for(int i=0; i<users.length && users[i] != null; i++){
			if(users[i] instanceof Client) continue;
			House[] houses = ((Owner) users[i]).getHouses();
			for (int j=0; j<houses.length && houses[j] != null; j++)
				if (facilitiesMatch(facilities, houses[j].facilities) && houses[j].location.equals(location) && houses[j].checkAvailability(in, out))
					results[nresults++]=houses[j];
		}
		if (nresults==0) return null;
		else return results;
	}

	private static boolean facilitiesMatch(boolean [] search, boolean [] house) {

		boolean [] aux =  new boolean[Facility.SIZE];
		for(int i=0; i<Facility.SIZE; i++)
			aux[i] = (search[i] && house[i]);
		for(int i=0; i<Facility.SIZE; i++)
			if (aux[i]!=search[i])
				return false;
		return true;
	}

	public double getPrice(int numPeople, int duration){
		return rentalFee + numPeople * duration * pricePerNightPerPerson;
	}

	public void selectHouse(Client client, int in, int out, int numPeople) {
		display();

		Scanner kb = new Scanner(System.in);

		System.out.println("\nDo you want to book this house?\n");
		System.out.println("\ty - Yes");
		System.out.println("\tn - No");

		while(true){
			try {
				switch(kb.nextLine().charAt(0)){
					case 'y': addBooking(in, out, client, numPeople);
					case 'n': return;
					default: System.out.println("Invalid input.");
				}
			} catch(StringIndexOutOfBoundsException e) {
				continue;
			}
		}
	}

  public void delete(){
    for(int i = 0; i < calendar.length; i++){
      if(calendar[i] != null) calendar[i].delete();
    }
    owner.removeHouse(this);
  }
}

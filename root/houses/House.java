package root.houses;

import java.util.*;
import root.bookings.Booking;
import root.users.*;

public class House {

	private boolean[] facilities = new boolean[Facility.SIZE];
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

	public void display(){
		System.out.println("\nHouse information");
		System.out.println("Property's name: " + name);
		System.out.println("Owner: " + owner.getName());
		System.out.println("Price per night per person: " + pricePerNightPerPerson);
		System.out.println("Rental fee: "+ rentalFee);
		System.out.println("Location: " + location);
	}

	public boolean checkAvailability(int in, int out){
		for(int i=in; i<out; i++)
			if(calendar[i]!=null)
				return false;
			return true;
	}

	public static House [] search(String location, int in, int out){
		House[] results = new House[100];
		User[] users = User.getUsers();
		int nresults=0;

		for(int i=0; i<users.length && users[i] != null; i++){
			if(users[i] instanceof Client) continue;
			House[] houses = ((Owner) users[i]).getHouses();
			for (int j=0; j<houses.length && houses[j] != null; j++)
				if (houses[j].location.equals(location) && houses[j].checkAvailability(in, out))
					results[nresults++]=houses[j];
		}
		if (nresults==0) return null;
		else return results;
	}


	public Double getPrice(int numPersons, int duration){
		return rentalFee + numPersons * duration * pricePerNightPerPerson;
	}

	public void addBooking(int in, int out, Client client, int numPeople){
		Booking b = new Booking(in, out, this, client, numPeople);
		for (int i=in; i<out; i++){
			calendar[i]=b;
		}
	}

	/*
	public void link(Owner owner) {
		if (owner != null) {
			owner.getOwner().add(this);
		}

		unlinkOwner();
		setOwner(owner);
	}
/*
	public void link(Booking booking) {
		if (booking != null) {
			booking.unlink();
			booking.setHouse(this);
			getCalendar().add(booking);
		}
	}

	public void unlinkOwner() {
		if (get() != null) {
			get().get().remove(this);
			set(null);
		}
	}

	public void unlinkBooking(Booking booking) {
		if (booking != null) {
			booking.set(null);
			get().remove(booking);
		}
	}

	public void unlink(Booking booking, Iterator<Booking> it) {
		if (booking != null) {
			booking.set(null);
			it.remove();
		}
	} */

}

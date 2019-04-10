package root.bookings;

import java.util.*;
import java.time.*;
import java.text.*;
import root.houses.House;
import root.users.Client;
import root.users.Owner;

public class Booking {

	public static final int CAL_SIZE = 15;

	private LocalDate checkIn;
	private LocalDate checkOut;
	private House house;
	private Client client;
	private int numberOfPeople;
	private double price;

	public Booking(int in, int out, House house, Client client, int people){
	//	this.setCheckIn(int2LocalDate(in));
	//	this.setCheckOut(int2LocalDate(out));
		this.setHouse(house);
		this.setClient(client);
		this.setNumberOfPeople(people);
		this.setPrice(house.getPrice(people,out-in));
		client.setFutureBooking(this);
	}

	private LocalDate int2LocalDate(int i) {
		//do
		return new LocalDate();
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public House getHouse() {
		return house;
	}

	public Client getClient() {
		return client;
	}

	public double getPrice() {
		return price;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setNumberOfPeople(int numberOfPeople){
		this.numberOfPeople = numberOfPeople;
	}

	public void display(){
	//	LocalDateFormat dateFormat = new SimpleLocalDateFormat("dd/MM/yyyy");
		System.out.println("\nBooking information:");
	//	System.out.println("Check-in:" + dateFormat.format(checkIn));
	//	System.out.println("Check-out:" + dateFormat.format(checkOut));
		house.display();
		System.out.println("Total price: ");
	}

/*
	public void link(Client client) {
		if (client != null) {
			client.unlink();
			client.set(this);
		}

		unlinkClient();
		set(client);
	}

	public void link(House house) {
		if (house != null) {
			house.get().add(this);
		}

		unlinkHouse();
		set(house);
	}

	public void unlinkClient() {
		if (get() != null) {
			get().set(null);
			set(null);
		}
	}

	public void unlinkHouse() {
		if (get() != null) {
			get().get().remove(this);
			set(null);
		}
	}*/
}

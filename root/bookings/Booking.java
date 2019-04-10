package root.bookings;

import java.util.*;
import java.time.*;
import java.text.*;
import root.houses.House;
import root.users.Client;
import root.users.Owner;
import java.time.format.DateTimeFormatter;

public class Booking {

	public static final int CAL_SIZE = 15;

	private LocalDate checkIn;
	private LocalDate checkOut;
	private House house;
	private Client client;
	private int numberOfPeople;
	private double price;

	public Booking(int in, int out, House house, Client client, int people){
		this.setCheckIn(in);
		this.setCheckOut(out);
		this.setHouse(house);
		this.setClient(client);
		this.setNumberOfPeople(people);
		this.setPrice(house.getPrice(people,out-in));
		client.setFutureBooking(this);
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

	public void setCheckIn(int checkIn) {
		this.checkIn = LocalDate.now().plusDays(checkIn);
	}

	public void setCheckOut(int checkOut) {
		this.checkOut = LocalDate.now().plusDays(checkOut);
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
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("\nBooking information:");
		System.out.println("Check-in: " + dateFormat.format(checkIn));
		System.out.println("Check-out: " + dateFormat.format(checkOut));
		System.out.println("Total price: " + price);
		house.display();
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

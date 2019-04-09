package root.bookings;

import java.util.*;
import java.time.*;
import root.houses.House;
import root.users.Client;

public class Booking {

	public static final int CAL_SIZE = 15;

	private Date checkIn;
	private Date checkOut;
	private House house;
	private Client client;
	private double price;

	private Date getCheckIn() {
		return checkIn;
	}

	private Date getCheckOut() {
		return checkOut;
	}

	private House getHouse() {
		return house;
	}

	private Client getClient() {
		return client;
	}

	private double getPrice() {
		return price;
	}


	private void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	private void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	private void setHouse(House house) {
		this.house = house;
	}

	private void setClient(Client client) {
		this.client = client;
	}

	private void setPrice(double price) {
		this.price = price;
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

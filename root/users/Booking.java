package root.users;

import java.util.*;
import java.time.*;
import root.houses.House;

public class Booking {
	
	private Date checkIn;
	private Date checkOut;
	private House house;
	private Client client;
	private double price;
	private Client ;
	private House ;

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

	public Client get() {
		return ;
	}

	public House get() {
		return ;
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

	public void set(Client ) {
		this. = ;
	}

	public void set(House ) {
		this. = ;
	}

	public void link(Client _) {
		if (_ != null) {
			_.unlink();
			_.set(this);
		}

		unlink();
		set(_);
	}

	public void link(House _) {
		if (_ != null) {
			_.get().add(this);
		}

		unlink();
		set(_);
	}

	public void unlink() {
		if (get() != null) {
			get().set(null);
			set(null);
		}
	}

	public void unlink() {
		if (get() != null) {
			get().get().remove(this);
			set(null);
		}
	}

// ----------- << class.extras@AAAAAAFp+w6SZBqZmpM= >>
// ----------- >>
}

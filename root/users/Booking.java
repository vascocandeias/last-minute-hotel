
package root.users;

import java.util.*;
import java.time.*;


import root.houses.House;
// ----------- << imports@AAAAAAFp+w6SZBqZmpM= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAFp+w6SZBqZmpM= >>
// ----------- >>
public class Booking {
	// ----------- << attribute.annotations@AAAAAAFp+9trFV/ck9g= >>
	// ----------- >>
	private Date checkIn;

	// ----------- << attribute.annotations@AAAAAAFp+9ujrGBHTqg= >>
	// ----------- >>
	private Date checkOut;

	// ----------- << attribute.annotations@AAAAAAFp+/80LXNEGks= >>
	// ----------- >>
	private House house;

	// ----------- << attribute.annotations@AAAAAAFp+/+uinOKfyg= >>
	// ----------- >>
	private Client client;

	// ----------- << attribute.annotations@AAAAAAFp/ABMK3P1Haw= >>
	// ----------- >>
	private double price;

	// ----------- << attribute.annotations@AAAAAAFp+w7a+BrF2LY= >>
	// ----------- >>
	private Client ;

	// ----------- << attribute.annotations@AAAAAAFp+w/nHx3OKd4= >>
	// ----------- >>
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
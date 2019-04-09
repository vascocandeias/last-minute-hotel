
package root.houses;

import java.util.*;
import java.time.*;


import root.users.Booking;
import root.users.Owner;
// ----------- << imports@AAAAAAFp+w9sVhxfzwQ= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAFp+w9sVhxfzwQ= >>
// ----------- >>
public class House {
	// ----------- << attribute.annotations@AAAAAAFp+9BuGUdK7t8= >>
	// ----------- >>
	private Set<boolean> facilities = new HashSet<>();

	// ----------- << attribute.annotations@AAAAAAFp+9EZpEj966I= >>
	// ----------- >>
	private Set<Booking> calendar = new HashSet<>();

	// ----------- << attribute.annotations@AAAAAAFp+9Eo6UlLC6s= >>
	// ----------- >>
	private double pricePerNightPerPerson;

	// ----------- << attribute.annotations@AAAAAAFp+9nDMlXLLjI= >>
	// ----------- >>
	private double rentalFee;

	// ----------- << attribute.annotations@AAAAAAFp+9n0jVYRbmk= >>
	// ----------- >>
	private String location;

	// ----------- << attribute.annotations@AAAAAAFp+w/QRx17U8A= >>
	// ----------- >>
	private Owner ;

	// ----------- << attribute.annotations@AAAAAAFp+w/nHx3N6xU= >>
	// ----------- >>
	private Set<Booking>  = new HashSet<>();

	private Set<boolean> getFacilities() {
		return facilities;
	}

	private Set<Booking> getCalendar() {
		return calendar;
	}

	private double getPricePerNightPerPerson() {
		return pricePerNightPerPerson;
	}

	private double getRentalFee() {
		return rentalFee;
	}

	private String getLocation() {
		return location;
	}

	public Owner get() {
		return ;
	}

	public Set<Booking> get() {
		return ;
	}

	private void setPricePerNightPerPerson(double pricePerNightPerPerson) {
		this.pricePerNightPerPerson = pricePerNightPerPerson;
	}

	private void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}

	private void setLocation(String location) {
		this.location = location;
	}

	public void set(Owner ) {
		this. = ;
	}

	public void link(Owner _) {
		if (_ != null) {
			_.get().add(this);
		}

		unlink();
		set(_);
	}

	public void link(Booking _) {
		if (_ != null) {
			_.unlink();
			_.set(this);
			get().add(_);
		}
	}

	public void unlink() {
		if (get() != null) {
			get().get().remove(this);
			set(null);
		}
	}

	public void unlink(Booking _) {
		if (_ != null) {
			_.set(null);
			get().remove(_);
		}
	}

	public void unlink(Booking _, Iterator<Booking> it) {
		if (_ != null) {
			_.set(null);
			it.remove();
		}
	}

// ----------- << class.extras@AAAAAAFp+w9sVhxfzwQ= >>
// ----------- >>
}
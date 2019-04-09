package root.houses;

import java.util.*;
import root.users.Booking;
import root.users.Owner;
import root.bookings.Booking;

public class House {

	private boolean[] facilities = new boolean[Facility.allOf().size()];
	private Booking[] calendar = new Booking[Booking.CAL_SIZE];
	private double pricePerNightPerPerson;
	private double rentalFee;
	private String location;
	private Owner owner;

	private boolean[] getFacilities() { return facilities; }
	private Set<Booking> getCalendar() { return calendar; }
	private double getPricePerNightPerPerson() { return pricePerNightPerPerson; }
	private double getRentalFee() { return rentalFee; }
	private String getLocation() { return location; }
	public Owner getOwner() { return owner; }

	private void setPricePerNightPerPerson(double pricePerNightPerPerson) {
		this.pricePerNightPerPerson = pricePerNightPerPerson;
	}

	private void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}

	private void setLocation(String location) {
		this.location = location;
	}

	public void set(Owner owner) {
		this.owner = owner;
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

}

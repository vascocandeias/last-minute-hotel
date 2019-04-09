package root.users;

import java.util.*;

public class Client extends User {

	private Booking futureBooking;
	private Booking ;

	private Booking getFutureBooking() {
		return futureBooking;
	}

	public Booking get() {
		return ;
	}

	private void setFutureBooking(Booking futureBooking) {
		this.futureBooking = futureBooking;
	}

	public void set(Booking ) {
		this. = ;
	}

	public void link(Booking _) {
		if (_ != null) {
			_.unlink();
			_.set(this);
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
}

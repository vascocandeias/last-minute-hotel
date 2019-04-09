package root.users;

import java.util.*;
import root.bookings.*;

public class Client extends User {

	private Booking futureBooking;

	public Client(String username, String password, String phone, String address,
				String nationality, String email){
		super(username, password, phone, address, nationality, email);
		futureBooking=null;
	}

	public Booking getFutureBooking() {
		return futureBooking;
	}

	public void setFutureBooking(Booking futureBooking) {
		this.futureBooking = futureBooking;
	}

	public void display() {
		System.out.println("Client profile");
		super.display();
	}

	/*public void link(Booking futureBooking) {
		if (futureBooking != null) {
			futureBooking.unlinkFutureBooking();
			futureBooking.setFutureBooking(this);
		}
		unlinkFutureBooking();
		setFutureBooking(futureBooking);
	}

	public void unlinkFutureBooking() {
		if (getFutureBooking() != null) {
			getFutureBooking().setFutureBooking(null);
			setFutureBooking(null);
		}
	}*/
}

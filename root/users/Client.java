package root.users;

import java.util.*;

public class Client extends User {

	private Booking futureBooking;

	public Client(String username, String password, int phone, String address,
				String nationality, String emailUser, String emailDomain){
		super(username, password, phone, address, nationality, emailUser, emailDomain);
		futureBooking=null;

	private Booking getFutureBooking() {
		return futureBooking;
	}

	private void setFutureBooking(Booking futureBooking) {
		this.futureBooking = futureBooking;
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

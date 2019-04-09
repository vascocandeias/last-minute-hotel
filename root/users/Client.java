
package root.users;

import java.util.*;
import java.time.*;



// ----------- << imports@AAAAAAFp+wbVZhlClJ8= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAFp+wbVZhlClJ8= >>
// ----------- >>
public class Client extends User {
	// ----------- << attribute.annotations@AAAAAAFp+8sKvDzxKL0= >>
	// ----------- >>
	private Booking futureBooking;

	// ----------- << attribute.annotations@AAAAAAFp+w7a+RrGBDo= >>
	// ----------- >>
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

// ----------- << class.extras@AAAAAAFp+wbVZhlClJ8= >>
// ----------- >>
}
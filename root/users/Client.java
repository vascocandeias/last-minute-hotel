package root.users;

import java.util.*;
import root.bookings.*;
import root.houses.*;

public class Client extends User {

	private Booking futureBooking;

	public Client() throws Exception {
		futureBooking = null;
	}

	public Booking getFutureBooking() {
		return futureBooking;
	}

	public void setFutureBooking(Booking futureBooking) {
		this.futureBooking = futureBooking;
	}

	@Override
	public void display() {
		System.out.println("Client profile");
		super.display();
	}

	@Override
	public void menu(){

    Scanner kb = new Scanner(System.in);
    display();

    if(futureBooking != null){
      System.out.println("This service is limited to one booking! Check yours:");
      futureBooking.display();
      //TODO: cancel booking
      return;
    }

    while(true){

      System.out.println("Do you want to search for a house?");
      System.out.println("y - Yes");
      System.out.println("n - No");

      search:
      while(true){
        try {
          switch(kb.nextLine().charAt(0)){
            case 'y': break search;
            case 'n': return;
            default: System.out.println("Invalid input.");
          }
        } catch(StringIndexOutOfBoundsException e) {
          continue;
        }
      }
			search();
		}
	}

	public void search(){

		Scanner kb = new Scanner(System.in);
		boolean booked = false;
		String input;
		Integer in, out;

		Booking.displayCalendar();
    System.out.println("\nWhat are you looking for?");
    System.out.print("Location: ");
    String location = kb.nextLine();
		try {
			System.out.print("Check-in: ");
			in = Integer.parseInt(kb.nextLine());
			System.out.print("Check-out: ");
			out = Integer.parseInt(kb.nextLine());
			if(out <= in || in < 0 || out >= Booking.CAL_SIZE) throw new Exception();
		} catch(Exception e) {
			System.out.println("Invalid dates");
			return;
		}

    System.out.print("Number of people: ");
		Integer numPeople;
		try{
			numPeople = Integer.parseInt(kb.nextLine());
			if(numPeople < 1) throw new Exception();
		} catch(Exception e) {
			System.out.println("Invalid number of people");
			return;
		}

    boolean[] requested = Facility.chooseFacilities();
    House[] houses = House.search(location, in, out, requested);

    if(houses == null){
      System.out.println("Sorry! Your search did not get any results");
      return;
    }

    for(int j = 0; j < houses.length && houses[j] != null; j++){
      System.out.println("\t" + j + " : " + houses[j].getName() + ", " + houses[j].getLocation() + ": RM" + houses[j].getPrice(numPeople, out-in));
    }

    while(!booked){
	    System.out.print("Select one (or press b to go back): ");
			input = kb.nextLine();
	    try {
	      int k = Integer.parseInt(input);
	      booked = houses[k].selectHouse(this, in, out, numPeople);
	    } catch(NumberFormatException e){
				if(input.equals("b")) return;
				System.out.println("Invalid input.");
				continue;
			} catch(Exception e) {
	      System.out.println("Invalid input.");
	      continue;
	    }
		}
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

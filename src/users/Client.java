package src.users;

import java.util.*;
import src.bookings.*;
import src.houses.*;

public class Client extends User {

	private Booking futureBooking;

	public Client() throws Exception {
		futureBooking = null;
	}

	public Client(String username, String password, String phone, String address, String nationality, String email, String name) throws Exception {
		super(username, password, phone, address, nationality, email, name);
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
		System.out.println("\nClient profile\n");
		super.display();
	}

	@Override
	public void menu(){

    Scanner kb = new Scanner(System.in);
    display();

    if(futureBooking != null){
      System.out.println("\nThis service is limited to one booking! Check yours:\n");
      futureBooking.display();
      return;
    }

    while(futureBooking == null){

      System.out.println("\nDo you want to search for a house?\n");
      System.out.println("\ty - Yes");
      System.out.println("\tn - No");

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
		futureBooking.display();
	}

	public void search(){

		Scanner kb = new Scanner(System.in);
		boolean booked = false;
		String input;
		Integer in, out;

		Booking.displayCalendar();
    System.out.println("\nWhat are you looking for?\n");
    System.out.print("\tLocation: ");
    String location = kb.nextLine();
		try {
			System.out.print("\tCheck-in: ");
			in = Integer.parseInt(kb.nextLine());
			System.out.print("\tCheck-out: ");
			out = Integer.parseInt(kb.nextLine());
			if(out <= in || in < 0 || out >= Booking.CAL_SIZE) throw new Exception();
		} catch(Exception e) {
			System.out.println("Invalid dates");
			return;
		}

    System.out.print("\tNumber of people: ");
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

    while(futureBooking == null){
	    System.out.print("\tSelect one (or press b to go back): ");
			input = kb.nextLine();
	    try {
	      int k = Integer.parseInt(input);
	      houses[k].selectHouse(this, in, out, numPeople);
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

	public void delete(){
		if(futureBooking != null) futureBooking.delete();
	}
}

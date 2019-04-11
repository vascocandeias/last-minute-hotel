package root.bookings;

import java.util.*;
import java.time.*;
import java.text.*;
import root.houses.House;
import root.users.Client;
import root.users.Owner;
import java.time.format.DateTimeFormatter;

public class Booking {

	public static final int CAL_SIZE = 14;

	private LocalDate checkIn;
	private LocalDate checkOut;
	private House house;
	private Client client;
	private int numberOfPeople;
	private double price;

	public Booking(int in, int out, House house, Client client, int people){
		this.setCheckIn(in);
		this.setCheckOut(out);
		this.setHouse(house);
		this.setClient(client);
		this.setNumberOfPeople(people);
		this.setPrice(house.getPrice(people,out-in));
		client.setFutureBooking(this);
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public House getHouse() {
		return house;
	}

	public Client getClient() {
		return client;
	}

	public double getPrice() {
		return price;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setCheckIn(int checkIn) {
		this.checkIn = LocalDate.now().plusDays(checkIn);
	}

	public void setCheckOut(int checkOut) {
		this.checkOut = LocalDate.now().plusDays(checkOut);
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setNumberOfPeople(int numberOfPeople){
		this.numberOfPeople = numberOfPeople;
	}

	public void delete(){
		house.removeBooking(this);
		client.setFutureBooking(null);
	}

	public void display(){
		Scanner kb = new Scanner(System.in);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("\nBooking information:");
		System.out.println("Check-in: " + dateFormat.format(checkIn));
		System.out.println("Check-out: " + dateFormat.format(checkOut));
		System.out.println("Total price: " + price);
		house.display();

		while(true){

			System.out.println("Choose an action:");
			System.out.println("c - Cancel booking");
			System.out.println("q - Quit");

			try {
				switch(kb.nextLine().charAt(0)){
					case 'c': delete();
					case 'q': return;
					default: System.out.println("Invalid input.");
				}
			} catch(StringIndexOutOfBoundsException e) {
				continue;
			}
		}
	}


	public static void displayCalendar(){
		DateTimeFormatter output = DateTimeFormatter.ofPattern("dd/MM");
    LocalDate date = LocalDate.now();
    DayOfWeek[] daysOfWeek = DayOfWeek.values();
    int i;

		System.out.print("    ");
    for(DayOfWeek day : daysOfWeek)
      System.out.printf("|     %s     ", day.toString().charAt(0));
    System.out.println("|");
    System.out.println("    -------------------------------------------------------------------------------------");
		System.out.print("    ");

    for(i = 0; daysOfWeek[i] != date.getDayOfWeek(); i++){
      System.out.printf("|           ");
    };

    for(int j = 0; j < CAL_SIZE ; j++){
      System.out.printf("| %2d: %s ", j, date.plusDays(j).format(output));
      if(i == 6){
        System.out.println("|");
        System.out.println("    -------------------------------------------------------------------------------------");
				System.out.print("    ");
        i = 0;
      } else i++;
    }

    for(; i < 7 && i > 0; i++){
      System.out.printf("|           ");
      if(i == 6){
        System.out.println("|");
        System.out.println("    -------------------------------------------------------------------------------------");
        i = 0;
        break;
      }
    }
	}

/*
	public void link(Client client) {
		if (client != null) {
			client.unlink();
			client.set(this);
		}

		unlinkClient();
		set(client);
	}

	public void link(House house) {
		if (house != null) {
			house.get().add(this);
		}

		unlinkHouse();
		set(house);
	}

	public void unlinkClient() {
		if (get() != null) {
			get().set(null);
			set(null);
		}
	}

	public void unlinkHouse() {
		if (get() != null) {
			get().get().remove(this);
			set(null);
		}
	}*/
}

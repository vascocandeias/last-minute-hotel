package src.bookings;

import java.util.*;
import java.time.*;
import java.io.*;
import src.houses.House;
import src.users.*;
import src.Manageable;
import java.time.format.DateTimeFormatter;

public class Booking implements Manageable {

	public static final int CAL_SIZE = 14;

	private LocalDate checkIn, checkOut;
	private House house;
	private Client client;
	private int numberOfPeople;
	private double price;

	public Booking(int in, int out, House house, Client client, int people){
		setCheckIn(in);
		setCheckOut(out);
		setHouse(house);
		setClient(client);
		setNumberOfPeople(people);
		setPrice(house.getPrice(people,out-in));
		client.setFutureBooking(this);
	}

	public double getPrice() {
		return price;
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
		if(house != null) house.removeBooking(this);
		if(client != null) client.setFutureBooking(null);
	}

	public void display(){
		Scanner kb = new Scanner(System.in);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("\nBooking information:\n");
		System.out.println("\tCheck-in: " + dateFormat.format(checkIn));
		System.out.println("\tCheck-out: " + dateFormat.format(checkOut));
		System.out.println("\tTotal price: " + price);
		house.display();

		while(true){

			System.out.println("\nChoose an action:\n");
			System.out.println("\tc - Cancel booking");
			System.out.println("\tq - Quit");

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

		System.out.print("\n ");
    for(DayOfWeek day : daysOfWeek)
      System.out.printf("|     %s    ", day.toString().charAt(0));
    System.out.println("|");
    System.out.println(" ------------------------------------------------------------------------------");
		System.out.print(" ");

    for(i = 0; daysOfWeek[i] != date.getDayOfWeek(); i++){
      System.out.printf("|          ");
    };

    for(int j = 0; j < CAL_SIZE ; j++){
      System.out.printf("| %2d:%s ", j, date.plusDays(j).format(output));
      if(i == 6){
        System.out.println("|");
        System.out.println(" ------------------------------------------------------------------------------");
				System.out.print(" ");
        i = 0;
      } else i++;
    }

    for(; i < 7 && i > 0; i++){
      System.out.printf("|          ");
      if(i == 6){
        System.out.println("|");
        System.out.println(" ------------------------------------------------------------------------------");
        i = 0;
        break;
      }
    }
	}

	public void write(BufferedWriter file) throws Exception {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");

		file.write(client.getUsername()+"\n");
		file.write(checkIn.format(dateFormat)+"\n");
		file.write(checkOut.format(dateFormat)+"\n");
		file.write(numberOfPeople+"\n");
	}
}

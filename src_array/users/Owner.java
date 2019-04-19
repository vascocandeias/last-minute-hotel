package src.users;

import java.util.*;
import java.io.*;
import src.houses.House;
import src.bookings.Booking;
import java.time.format.DateTimeFormatter;

public class Owner extends User {

	private House [] houses;
	private String bio;
	private int numberOfHouses;
	private String publicEmail;

	public Owner() throws Exception{
		houses = new House[ARRAY_SIZE];
		numberOfHouses=0;

		Scanner kb = new Scanner(System.in);

		System.out.print("\tBio: ");
		setBio(kb.nextLine());
		System.out.print("\tPublic email username: ");
		String publicEmailUser = kb.nextLine();
		System.out.print("\tPublic email domain: ");
		String publicEmailDomain = kb.nextLine();
		String publicEmail = publicEmailUser + "@" + publicEmailDomain;
		setPublicEmail(publicEmail);
	}

	public Owner(String username, String password, String phone, String address, String nationality, String email, String name, String bio, String publicEmail) throws Exception{
		super(username, password, phone, address, nationality, email, name);
		houses = new House[ARRAY_SIZE];
		numberOfHouses=0;
		setPublicEmail(publicEmail);
		setBio(bio);
	}

	public House[] getHouses() {
		return houses;
	}
	public String getPublicEmail() {
		return publicEmail;
	}

	public void addHouse(){

		Scanner kb = new Scanner(System.in);

		System.out.println("\nFill out the details\n");
		System.out.print("\tName: ");
		String name = kb.nextLine();
		System.out.print("\tPrice per night per person: ");
		Double pricePerNightPerPerson;
		try{
			pricePerNightPerPerson = Double.parseDouble(kb.nextLine());
		} catch(Exception e){
			System.out.println("\tPrice must be a nonnegative number");
			return;
		}
		System.out.print("\tRental Fee: ");
		Double rentalFee;
		try{
			rentalFee = Double.parseDouble(kb.nextLine());
		} catch(Exception e){
			System.out.println("\tRental fee must be a nonnegative number");
			return;
		}
		System.out.print("\tLocation: ");
		String location = kb.nextLine();

		House house = new House(this, name, pricePerNightPerPerson, rentalFee, location);
		if (numberOfHouses == houses.length) {
			House [] aux = new House[(houses.length+1)*2];
			for(int i=0 ; i<houses.length; i++)
				aux[i]=houses[i];
			houses=aux;
		}
		houses[numberOfHouses++] = house;
	}
	public House addHouse(String name, double pricePerNightPerPerson, double rentalFee, String location, String fac){
		House house = new House(this, name, pricePerNightPerPerson, rentalFee, location, fac);
		if (numberOfHouses == houses.length) {
			House [] aux = new House[houses.length*2];
			for(int i=0 ; i<houses.length; i++)
				aux[i]=houses[i];
			houses=aux;
		}
		houses[numberOfHouses++] = house;
		return house;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}
	public void removeHouse(House house){
		int i;
    for(i = 0; i < houses.length && house != null; i++){
      if(houses[i] == house) {
				houses[i] = null;
				break;
			}
    }

		for(; i < houses.length - 1; i++){
			houses[i] = houses[i+1];
		}
		houses[i] = null;
		numberOfHouses--;
  }

	public void display(){
		System.out.println("Owner Profile");
		super.display();
		System.out.println("\tBiography: " + bio);
		System.out.println("\tNumber of Houses: " + numberOfHouses);
		System.out.println("\tPublic Email: " + publicEmail);
	}
	public void menu(){

		Scanner kb = new Scanner(System.in);

		display();

		while(true){

			System.out.println("\nChoose an action:\n");
			System.out.println("\tm - Manage houses");
			System.out.println("\tv - View bookings");
			System.out.println("\ta - Add house");
			System.out.println("\tq - Quit");

			try {
				switch(kb.nextLine().charAt(0)){
					case 'm':
						manageHouses();
						break;
					case 'v':
						manageBookings();
						break;
					case 'a':
						addHouse();
						break;
					case 'q':
						return;
					default:
						System.out.println("Invalid input.");
						break;
				}
			} catch(Exception e) {
				continue;
			}
		}
	}

	public void manageBookings(){

    Scanner kb = new Scanner(System.in);
    Booking[] bookings = displayBookings();

    System.out.print("Choose one to get more details: ");
    int i = Integer.parseInt(kb.nextLine());
    bookings[i].display();
  }
  public void manageHouses(){

    Scanner kb = new Scanner(System.in);

    displayHouses();

		int i;

		while(true){
			System.out.print("Choose one to get more details: ");
			try {
				i = Integer.parseInt(kb.nextLine());
		    houses[i].display();
				break;
			} catch(Exception e) {
				continue;
			}
		}

		select:
		while(true){
			System.out.println("\nDo you want to delete this house?\n");
			System.out.println("\ty - Yes");
			System.out.println("\tn - No");
			try {
				switch(kb.nextLine().charAt(0)){
					case 'y': houses[i].delete();
					case 'n': break select;
					default:
						System.out.println("Invalid input.");
						break;
				}
			} catch(StringIndexOutOfBoundsException e) {
				continue;
			}
		}
  }
	public void displayHouses(){
		System.out.println("\nYour houses:\n");
		for(int i=0; i<numberOfHouses; i++)
			System.out.println("\t" + i + " : " + houses[i].getName() + ", " + houses[i].getLocation());
	}
	public Booking[] displayBookings(){
		int i = 0;
		Booking previous = null;
		Booking[] bookings = new Booking[100];
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("\nYour bookings:\n");

		for(House house : houses){
			if(house == null) return bookings;
			for(Booking booking : house.getCalendar()){
				if(booking == null || booking == previous) continue;
				previous = booking;
				if(i == bookings.length){
					Booking[] aux = new Booking[(bookings.length+1)*2];
					for(int j = 0; j < bookings.length; j++){
						aux[j] = bookings[j];
					}
					bookings = aux;
				}
				bookings[i] = booking;
				System.out.println("\t" + i++ + " : " + house.getName() + ", " + house.getLocation() + ": RM" + booking.getPrice());
			}
		}
		return bookings;
	}

	public void delete(){
		for(int i = 0;  i<houses.length && houses[i] != null; i++){
			houses[i].delete();
		}
	}

	public void write(BufferedWriter file, BufferedWriter houseFile) throws Exception {
		file.write("O"+"\n");
		super.write(file, null);
		file.write(bio+"\n");
		file.write(publicEmail+"\n");

		for (int j=0; j<numberOfHouses; j++){
			houseFile.write(getUsername()+"\n");
			houses[j].write(houseFile);
		}
	}
}

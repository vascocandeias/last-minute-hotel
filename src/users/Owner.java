package src.users;

import java.util.*;
import java.io.*;
import src.houses.House;
import src.bookings.Booking;
import java.time.format.DateTimeFormatter;

public class Owner extends User {

	private ArrayList<House> houses;
	private String bio;
	private int numberOfHouses;
	private String publicEmail;

	public Owner() throws Exception{
		houses = new ArrayList<House>();
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
		houses = new ArrayList<House>();
		numberOfHouses=0;
		setPublicEmail(publicEmail);
		setBio(bio);
	}

	public ArrayList<House> getHouses() {
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
		houses.add(house);
		numberOfHouses++;
	}

	public House addHouse(String name, double pricePerNightPerPerson, double rentalFee, String location, String fac){
		House house = new House(this, name, pricePerNightPerPerson, rentalFee, location, fac);
		houses.add(house);
		numberOfHouses++;
		return house;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}
	public void removeHouse(House house){
		houses.remove(house);
		numberOfHouses--;
  }

	public void display(){
		System.out.println("\nOwner Profile");
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
		String input;
    ArrayList<Booking> bookings = displayBookings();

		while(true){
			System.out.print("\nChoose one to get more details (or press b to go back): ");
			input = kb.nextLine();
			try {
				int i = Integer.parseInt(input);
		    bookings.get(i).display();
				break;
			} catch(NumberFormatException e){
				if(input.equals("b")) return;
				System.out.println("Invalid input.");
				continue;
			} catch(Exception e) {
				continue;
			}
		}
  }

  public void manageHouses(){

    Scanner kb = new Scanner(System.in);
		String input;

    displayHouses();

		House house = null;

		while(true){
			System.out.print("\nChoose one to get more details (or press b to go back): ");
			input = kb.nextLine();
			try {
				int i = Integer.parseInt(input);
		    house = houses.get(i);
				house.display();
				break;
			} catch(NumberFormatException e){
				if(input.equals("b")) return;
				System.out.println("Invalid input.");
				continue;
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
					case 'y': house.delete();
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
		int i = 0;
		for(House house : houses)
			System.out.println("\t" + i++ + " : " + house.getName() + ", " + house.getLocation());
	}
	public ArrayList<Booking> displayBookings(){

		Booking previous = null;
		int i = 0;
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("\nYour bookings:\n");

		for(House house : houses){
			for(Booking booking : house.getCalendar()){
				if(booking == null || booking == previous) continue;
				previous = booking;
				bookings.add(booking);
				System.out.println("\t" + i++ + " : " + house.getName() + ", " + house.getLocation() + ": RM" + booking.getPrice());
			}
		}
		return bookings;
	}

	public void delete(){
		while(!houses.isEmpty()){
			houses.get(0).delete();
		}
	}

	public void write(BufferedWriter file, BufferedWriter houseFile) throws Exception {
		file.write("O"+"\n");
		super.write(file, null);
		file.write(bio+"\n");
		file.write(publicEmail+"\n");

		for (House house: houses){
			houseFile.write(getUsername()+"\n");
			house.write(houseFile);
		}
	}
}

package src.houses;

import java.util.*;
import src.bookings.Booking;
import src.users.*;
import src.Manageable;
import java.io.*;

public class House implements Manageable {

	private boolean[] facilities;
	private ArrayList<Booking> calendar = new ArrayList<Booking>();
	private double pricePerNightPerPerson;
	private double rentalFee;
	private String location;
	private Owner owner;
	private String name;

	public House(Owner owner, String name, double pricePerNightPerPerson, double rentalFee, String location){
		setOwner(owner);
		setPricePerNightPerPerson(pricePerNightPerPerson);
		setRentalFee(rentalFee);
		setLocation(location);
		setName(name);
    setFacilities(Facility.chooseFacilities());
		for(int i = 0; i < Booking.CAL_SIZE; i++){
			calendar.add(null);
		}
	}

	public House(Owner owner, String name, double pricePerNightPerPerson, double rentalFee, String location, String facilities){
		setOwner(owner);
		setPricePerNightPerPerson(pricePerNightPerPerson);
		setRentalFee(rentalFee);
		setLocation(location);
		setName(name);
		setFacilities(Facility.readFacilities(facilities));
		for(int i = 0; i < Booking.CAL_SIZE; i++){
			calendar.add(null);
		}
	}

	public boolean[] getFacilities() { return facilities; }
	public ArrayList<Booking> getCalendar() { return calendar; }
	public double getPricePerNightPerPerson() { return pricePerNightPerPerson; }
	public double getRentalFee() { return rentalFee; }
	public String getLocation() { return location; }
	public Owner getOwner() { return owner; }
	public String getName() { return name; }
	public double getPrice(int numPeople, int duration){
		return rentalFee + numPeople * duration * pricePerNightPerPerson;
	}
	public ArrayList<Booking> getBookings(){
		ArrayList<Booking> bookings = new ArrayList<Booking>();

		for(Booking booking : this.getCalendar())
			if(booking != null && !bookings.contains(booking))
				bookings.add(booking);

		return bookings;
	}

	public void setPricePerNightPerPerson(double pricePerNightPerPerson) {
		this.pricePerNightPerPerson = pricePerNightPerPerson;
	}
	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setFacilities(boolean[] facilities){
		this.facilities = facilities;
	}

	public void addBooking(int in, int out, Client client, int numPeople){
		Booking booking = new Booking(in, out, this, client, numPeople);
		if(in < 0) in = 0;
		for (int i = in; i < out; i++){
			calendar.set(i, booking);
		}
	}
  public void removeBooking(Booking booking){
    for(int i = 0; i < calendar.size(); i++){
      if(calendar.get(i) == booking) calendar.set(i,null);
    }
  }

	public void display(){
		System.out.println("\nHouse information\n");
		System.out.println("\tProperty's name: " + name);
		System.out.println("\tOwner: " + owner.getName());
		System.out.println("\tOwner's email: " + owner.getPublicEmail());
		System.out.println("\tPrice per night per person: " + pricePerNightPerPerson);
		System.out.println("\tRental fee: "+ rentalFee);
		System.out.println("\tLocation: " + location);
    System.out.println("\tFacilities:");
    Facility.display(facilities);
	}

	public boolean checkAvailability(int in, int out){
		for(int i=in; i<out; i++)
			if(calendar.get(i)!=null)
				return false;
			return true;
	}

	public static ArrayList<House> search(String location, int in, int out, boolean[] facilities){
		ArrayList<House> results = new ArrayList<House>();
		ArrayList<User> users = User.getUsers();

		for(User user : users){
			if(user instanceof Client) continue;
			ArrayList<House> houses = ((Owner) user).getHouses();
			for(House house : houses){
				if (facilitiesMatch(facilities, house.facilities) && house.location.equals(location) && house.checkAvailability(in, out)){
					results.add(house);
				}
			}
		}
		return results;
	}

	private static boolean facilitiesMatch(boolean[] search, boolean[] house) {

		boolean [] aux =  new boolean[Facility.SIZE];
		for(int i=0; i<Facility.SIZE; i++)
			aux[i] = (search[i] && house[i]);
		for(int i=0; i<Facility.SIZE; i++)
			if (aux[i]!=search[i])
				return false;
		return true;
	}

	public void selectHouse(Client client, int in, int out, int numPeople) {
		display();

		Scanner kb = new Scanner(System.in);

		System.out.println("\nDo you want to book this house?\n");
		System.out.println("\ty - Yes");
		System.out.println("\tn - No");

		while(true){
			try {
				switch(kb.nextLine().charAt(0)){
					case 'y': addBooking(in, out, client, numPeople);
					case 'n': return;
					default: System.out.println("Invalid input.");
				}
			} catch(StringIndexOutOfBoundsException e) {
				continue;
			}
		}
	}

  public void delete(){
    for(int i = 0; i < calendar.size(); i++){
      if(calendar.get(i) != null) calendar.get(i).delete();
    }
    owner.removeHouse(this);
  }

	public void write(BufferedWriter file) throws Exception {
		int k = 0;
		Booking previous = null;
		file.write(name+"\n");
		file.write(pricePerNightPerPerson+"\n");
		file.write(rentalFee+"\n");
		file.write(location+"\n");
		file.write(Facility.writeFacilities(facilities)+"\n");

		for (Booking booking : calendar){
			if(booking == null || booking == previous) continue;
			previous = booking;
			if(k++ == 0) file.write("b...b...b\n");
			booking.write(file);
		}
		if(k != 0) file.write("b...b...b\n");
	}
}

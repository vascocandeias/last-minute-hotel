package root.users;

import java.util.*;
import java.time.*;
import root.houses.House;

public class Owner extends User {
	private static final int NUM_HOUSES  = 10;

	private House [] houses;
	private String bio;
	private int numberOfHouses;
	private String publicEmail;

	public Owner(String username, String password, int phone, String address,
				String nationality, String email, String bio, String publicEmail){
		super(username, password, phone, address, nationality, email);
		houses = new House[NUM_HOUSES];
		numberOfHouses=0;
		this.publicEmail=publicEmail;
	}

	public House[] getHouses() {
		return houses;
	}
	public String getBio() {
		return bio;
	}
	public int getNumberOfHouses() {
		return numberOfHouses;
	}
	public String getPublicEmail() {
		return publicEmail;
	}

	public void addHouse(House house){
		if (numberOfHouses == houses.length) {
			House [] aux = new House[houses.length*2];
			for(int i=0 ; i<houses.length; i++)
				aux[i]=houses[i];
			this.houses=aux;
		}
		houses[numberOfHouses] = house;
		numberOfHouses++;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}

	//remove house from list
	public void removeHouse(String name) {
		boolean flag=false;

		numberOfHouses--;
		for(int i=0; i<houses.length; i++) {
			if (houses[i].getName().equals(name))
				flag=true;
			if(flag){ //if the house was found then next houses are shifted 1 back
				houses[i]=houses[i+1];
				if (houses[i+1]==null)
					break;
			}
		}
	}

	public void display(){
		System.out.println("Owner Profile");
		super.display();
		System.out.println("\tBiography = " + bio);
		System.out.println("\tNumber of Houses = " + numberOfHouses);
		System.out.println("\tPublic Email = " + publicEmail);
	}

	//TODO: link
	/*public void link(House house) {
		if (house != null) {
			house.unlinkHouse();
			house.set(this);
			getHouse().addHouse(_);
		}
	}

	public void unlinkHouse(House house) {
		if (house != null) {
			house.set(null);
			getHouse().removeHouse(house);
		}
	}

	public void unlinkHouse(House house, Iterator<House> it) {
		if (house != null) {
			house.setHouse(null);
			it.removeHouse();
		}
	}*/
}

package root.users;

import java.util.*;
import java.time.*;
import root.houses.House;

public class Owner extends User {

	private House [] houses;
	private String bio;
	private int numberOfHouses;
	private String publicEmailUser;
	private String publicEmailDomain;

	public Owner(String username, String password, int phone, String address,
				String nationality, String emailUser, String emailDomain,
				String bio, String publicEmailUser, String publicEmailDomain){
		super(username, password, phone, address, nationality, emailUser, emailDomain);
		houses = new House[10];
		numberOfHouses=0;
		this.publicEmailUser=publicEmailUser;
		this.publicEmailDomain=publicEmailDomain;
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
	public String getPublicEmailUser() {
		return publicEmailUser;
	}
	public String getPublicEmailDomain() {
		return publicEmailDomain;
	}

	public void addHouse(House house){
		if (numberOfHouses == houses.length) {
			House [] aux = new House[houses.length+10];
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
	public void setPublicEmailUser(String publicEmailUser) {
		this.publicEmailUser = publicEmailUser;
	}
	public void setPublicEmailDomain(String publicEmailDomain) {
		this.publicEmailDomain = publicEmailDomain;
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

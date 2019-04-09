package root.users;

import java.util.*;
import java.time.*;
import root.houses.House;

public class Owner extends User {

	private Set<House> houses = new HashSet<>();
	private String bio;
	private String publicEmail;
	private Set<House> = new HashSet<>();

	private Set<House> getHouses() {
		return houses;
	}

	private String getBio() {
		return bio;
	}

	private String getPublicEmail() {
		return publicEmail;
	}

	public Set<House> get() {
		return ;
	}

	private void setBio(String bio) {
		this.bio = bio;
	}

	private void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}

	public void link(House _) {
		if (_ != null) {
			_.unlink();
			_.set(this);
			get().add(_);
		}
	}

	public void unlink(House _) {
		if (_ != null) {
			_.set(null);
			get().remove(_);
		}
	}

	public void unlink(House _, Iterator<House> it) {
		if (_ != null) {
			_.set(null);
			it.remove();
		}
	}
}

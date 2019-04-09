
package root.users;

import java.util.*;
import java.time.*;


import root.houses.House;
// ----------- << imports@AAAAAAFp+wcn2Bm9fvE= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAFp+wcn2Bm9fvE= >>
// ----------- >>
public class Owner extends User {
	// ----------- << attribute.annotations@AAAAAAFp+8/MG0bWBR8= >>
	// ----------- >>
	private Set<House> houses = new HashSet<>();

	// ----------- << attribute.annotations@AAAAAAFp+9FHD0m0ayQ= >>
	// ----------- >>
	private String bio;

	// ----------- << attribute.annotations@AAAAAAFp+9GExEofQAI= >>
	// ----------- >>
	private String publicEmail;

	// ----------- << attribute.annotations@AAAAAAFp+w/QRx18tz8= >>
	// ----------- >>
	private Set<House>  = new HashSet<>();

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

// ----------- << class.extras@AAAAAAFp+wcn2Bm9fvE= >>
// ----------- >>
}
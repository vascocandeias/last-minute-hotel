package root.users;

import java.util.*;
import java.time.*;

public abstract class User {

	private String username;
	private String password;
	private int phone;
	private String address;
	private String nationality;
	private String email;

	private String getUsername() {
		return username;
	}

	private String getPassword() {
		return password;
	}

	private int getPhone() {
		return phone;
	}

	private String getAddress() {
		return address;
	}

	private String getNationality() {
		return nationality;
	}

	private String getEmail() {
		return email;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private void setPhone(int phone) {
		this.phone = phone;
	}

	private void setAddress(String address) {
		this.address = address;
	}

	private void setNationality(String nationality) {
		this.nationality = nationality;
	}

	private void setEmail(String email) {
		this.email = email;
	}
}

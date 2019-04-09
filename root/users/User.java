package root.users;

import java.util.*;
import java.time.*;

public abstract class User {

	private String username;
	private String password;
	private int phone;
	private String address;
	private String nationality;
	private String emailUser;
	private String emailDomain;

	public User(String username, String password, int phone, String address,
				String nationality, String emailUser, String emailDomain) {

		setUsername(username);
		setPassword(password);
		setPhone(phone);
		setAddress(address);
		setNationality(nationality);
		setEmailUser(emailUser);
		setEmailDomain(emailDomain);
	}

	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public int getPhone() {return phone;}
	public String getAddress() {return address;}
	public String getNationality() {return nationality;}
	public String getEmailUser() {return email;}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	//TODO: check if values are correct to be inserted (try/catch/exception probably)
}

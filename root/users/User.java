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
	private String email;

	public User(String username, String password, int phone, String address,
				String nationality, String emailUser, String emailDomain){
		this.username=username; //verificar que é unico
		this.password=password; //verificar pass
		this.phone=phone;
		this.address=address;
		this.nationality=nationality;
		this.emailUser=emailUser;
		this.emailDomain=emailDomain;
	}

	public String getUsername() {
		return username;
	}
	//private ?
	public String getPassword() {
		return password;
	}
	public int getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getNationality() {
		return nationality;
	}
	public String getEmail() {
		return email;
	}

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
	public void setEmail(String email) {
		this.email = email;
	}

	static public User logIn(String username, String password, User...users){
		for (int i=0; i<users.length; i++){
			if (users[i].getUsername.equals(username))
				if(users[i].getPassword.equals(password))
					return user[i];
				else return null;
		}
		return null;
	}

}

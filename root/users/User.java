package root.users;

import java.util.*;
import java.time.*;

public abstract class User {

	private static final int NUM_USERS = 10;

	//TODO meter um nome no gajo lol

	private String username;
	private String password;
	private int phone;
	private String address;
	private String nationality;
	private String email;
	private String name;
	private static User [] users = new User[NUM_USERS];
	private static int numberOfUsers;

	public User(String username, String password, String phone, String address,
				String nationality, String email, String name){
		this.username=username;
		this.password=password;
		this.phone=Integer.parseInt(phone);
		this.address=address;
		this.nationality=nationality;
		this.email=email;
		this.name=name;
		if (numberOfUsers == users.length) {
			User [] aux = new User[users.length*2];
			for(int i=0 ; i<users.length; i++)
				aux[i]=users[i];
			users=aux;
		}
		users[numberOfUsers] = this;
		numberOfUsers++;
	}

	public String getUsername() {
		return username;
	}
	//private ?
	public String getPassword() {
		return password;
	}
	//private ?
	public static User [] getUsers() {
		return users;
	}
	//private ?
	public static int getNumberOfUsers() {
		return numberOfUsers;
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
	public String getName(){
		return name; 
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
	public void setName(String name) {
		this.name=name;
	}

	static public User logIn(String username, String password){
		for (int i=0; i<users.length && users[i] != null; i++){
			if (users[i].getUsername().equals(username)){
				if(users[i].getPassword().equals(password))
					return users[i];
				else return null;
			}
		}
		return null;
	}

	public void display() {
		System.out.println("\tusername = " + username);
		System.out.println("\tPhone = " + phone);
		System.out.println("\tAddress = " + address);
		System.out.println("\tNationality = " + nationality);
		System.out.println("\tEmail = " + email);
		System.out.println("\tName =  " + name);
	}
}

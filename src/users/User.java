package src.users;

import java.util.*;
import java.io.*;
import java.time.*;
import src.Manageable;

public abstract class User implements Manageable {

	protected static final int ARRAY_SIZE = 10;

	private String username;
	private String password;
	private int phone;
	private String address;
	private String nationality;
	private String email;
	private String name;
	private static User [] users = new User[ARRAY_SIZE];
	private static int numberOfUsers;

	public User() throws Exception {

		Scanner kb = new Scanner(System.in);

		System.out.println("\nFill out the details\n");
    System.out.print("\tName: ");
		this.setName(kb.nextLine());
    System.out.print("\tUsername: ");
		this.setUsername(kb.nextLine());
    System.out.print("\tPassword: ");
		this.setPassword(kb.nextLine());
    System.out.print("\tPhone: ");
		try{
			this.setPhone(Integer.parseInt(kb.nextLine()));
		} catch(Exception e){
			System.out.println("\tPhone number must be a nonnegative integer");
			throw new Exception();
		}
    System.out.print("\tAddress: ");
		this.setAddress(kb.nextLine());
    System.out.print("\tNationality: ");
		this.setNationality(kb.nextLine());
    System.out.print("\tEmail username: ");
    String emailUser = kb.nextLine();
    System.out.print("\tEmail domain: ");
    String emailDomain = kb.nextLine();
    String email = emailUser + "@" + emailDomain;
		this.setEmail(email);

		if (numberOfUsers == users.length) {
			User [] aux = new User[users.length*2];
			for(int i=0 ; i<users.length; i++)
				aux[i]=users[i];
			users=aux;
		}
		users[numberOfUsers++] = this;
	}

	public User(String username, String password, String phone, String address, String nationality, String email, String name) throws Exception{

		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
		this.setPhone(Integer.parseInt(phone));
		this.setAddress(address);
		this.setNationality(nationality);
		this.setEmail(email);

		if (numberOfUsers == users.length) {
			User [] aux = new User[users.length*2];
			for(int i=0 ; i<users.length; i++)
				aux[i]=users[i];
			users=aux;
		}
		users[numberOfUsers++] = this;
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

	public void setUsername(String username) throws Exception {
		if(username.equals("b...b...b")){
			System.out.println("b...b...b is not a valid username");
			throw new Exception();
		}
		for(User user : users){
			if(user == null) break;
			if(user.username.equals(username)){
				System.out.println("This username already exists");
				throw new Exception();
			}
		}
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(int phone) throws Exception {
		if(phone < 0) throw new Exception();
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
		this.name = name;
	}

	public static User register() throws Exception {

    Scanner kb = new Scanner(System.in);

    System.out.println("\nWho are you?\n");
    System.out.println("\tc - A client");
    System.out.println("\to - An owner");
    System.out.println("\tq - None, let me go!");

    while(true){
      try {
        switch(kb.nextLine().charAt(0)){
          case 'c':
            return new Client();
          case 'o':
            return new Owner();
          case 'q':
            return null;
          default:
            System.out.println("Invalid input.");
            break;
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      }
    }
  }
	public static void logIn(){

		Scanner kb = new Scanner(System.in);
		User user = null;

		System.out.print("Username: ");
		String username = kb.nextLine();
		System.out.print("Password: ");
		String password = kb.nextLine();

		for (int i=0; i<users.length && users[i] != null; i++){
			if (users[i].getUsername().equals(username)){
				if(users[i].getPassword().equals(password)){
					users[i].menu();
					return;
				}
				else return;
			}
		}
	}

	public abstract void menu();

	public static void deleteAll(){
		for(int i = 0; i<users.length && users[i] != null; i++){
			users[i].delete();
			users[i] = null;
		}
	}

	public void display() {
		System.out.println("\tName:  " + name);
		System.out.println("\tPhone: " + phone);
		System.out.println("\tAddress: " + address);
		System.out.println("\tNationality: " + nationality);
		System.out.println("\tEmail: " + email);
	}

	public static User [] getUsersDatabase() throws Exception{
		String filename = "Users.txt";
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		int i = 0;
		User [] u = new User[ARRAY_SIZE];
		while ((line = reader.readLine()) != null){
			String type = line;
			String username = reader.readLine();
			String password = reader.readLine();
			String phone = reader.readLine();
			String address = reader.readLine();
			String nationality = reader.readLine();
			String email = reader.readLine();
			String name = reader.readLine();
			if (type.equals("O")){
				String bio = reader.readLine();
				String publicEmail = reader.readLine();
				u[i++] = new Owner(username, password, phone, address, nationality, email, name, bio, publicEmail);
			} else {
				u[i++] = new Client(username, password, phone, address, nationality, email, name);
			}
		}
		reader.close();
		numberOfUsers = i;
		return users; // return users?
	}
	public static void displayUsers(User [] u){
		for (int i=0; i<u.length && u[i]!=null; i++){
			if (u[i] instanceof Client)
				System.out.println("C");
			else System.out.println("O");
			System.out.println(u[i].getUsername());
			System.out.println(u[i].getPassword());
			System.out.println(u[i].getPhone());
			System.out.println(u[i].getAddress());
			System.out.println(u[i].getNationality());
			System.out.println(u[i].getEmail());
			System.out.println(u[i].getName());
			if (u[i] instanceof Owner){
				System.out.println(((Owner) u[i]).getBio());
				System.out.println(((Owner) u[i]).getPublicEmail());
			}
		}
	}
}

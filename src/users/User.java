package src.users;

import java.util.*;
import java.io.*;
import java.time.*;
import src.Manageable;
import src.houses.House;
import src.bookings.Booking;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public abstract class User implements Manageable {

	protected static final int ARRAY_SIZE = 10;

	private String username;
	private String password;
	private int phone;
	private String address;
	private String nationality;
	private String email;
	private String name;
	private static ArrayList<User> users = new ArrayList<User>();

	public User() throws Exception {

		Scanner kb = new Scanner(System.in);

		System.out.println("\nFill out the details\n");
    System.out.print("\tName: ");
		setName(kb.nextLine());
    System.out.print("\tUsername: ");
		setUsername(kb.nextLine());
    System.out.print("\tPassword: ");
		setPassword(kb.nextLine());
    System.out.print("\tPhone: ");
		try{
			setPhone(Integer.parseInt(kb.nextLine()));
		} catch(Exception e){
			System.out.println("\tPhone number must be a nonnegative integer");
			throw new Exception();
		}
    System.out.print("\tAddress: ");
		setAddress(kb.nextLine());
    System.out.print("\tNationality: ");
		setNationality(kb.nextLine());
    System.out.print("\tEmail username: ");
    String emailUser = kb.nextLine();
    System.out.print("\tEmail domain: ");
    String emailDomain = kb.nextLine();
    String email = emailUser + "@" + emailDomain;
		setEmail(email);

		users.add(this);
	}

	public User(String username, String password, String phone, String address, String nationality, String email, String name) throws Exception{

		setName(name);
		setUsername(username);
		setPassword(password);
		setPhone(Integer.parseInt(phone));
		setAddress(address);
		setNationality(nationality);
		setEmail(email);

		users.add(this);
	}

	public String getUsername() {
		return username;
	}
	public static ArrayList<User> getUsers() {
		return users;
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

		System.out.print("\nUsername: ");
		String username = kb.nextLine();
		System.out.print("Password: ");
		String password = kb.nextLine();

		for (User user : users){
			if (user.username.equals(username)){
				if(user.password.equals(password)){
					user.menu();
					return;
				}
				else return;
			}
		}
	}

	public abstract void menu();
	public static void deleteAll(){
		while(!users.isEmpty()){
			User user = users.get(0);
			user.delete();
			users.remove(user);
		}
	}

	public void display() {
		System.out.println("\tName:  " + name);
		System.out.println("\tPhone: " + phone);
		System.out.println("\tAddress: " + address);
		System.out.println("\tNationality: " + nationality);
		System.out.println("\tEmail: " + email);
	}

	public static void getHouseDatabase() throws Exception {
		BufferedReader housefile = new BufferedReader(new FileReader("Houses.txt"));
		String hline;
		String clientusername="";
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
		LocalDate date;

		hline = housefile.readLine();
		while(hline!=null){
			Owner o = (Owner) find(Owner.class, hline);
			String name = housefile.readLine();
			double pricePerNightPerPerson = Double.parseDouble(housefile.readLine());
			double rentalFee = Double.parseDouble(housefile.readLine());
			String location = housefile.readLine();
			String facilities = housefile.readLine();
			House house = o.addHouse(name, pricePerNightPerPerson, rentalFee, location, facilities);
			hline = housefile.readLine();
			if (hline!=null && hline.equals("b...b...b")){
				while(true){
					clientusername = housefile.readLine();
					if(clientusername.equals("b...b...b")){
						hline=housefile.readLine();
						break;
					}
					LocalDate checkIn = LocalDate.parse(housefile.readLine(), dateFormat);
					LocalDate checkOut = LocalDate.parse(housefile.readLine(), dateFormat);
					int in = (int) ChronoUnit.DAYS.between(LocalDate.now(), checkIn);
					int out = (int) ChronoUnit.DAYS.between(LocalDate.now(), checkOut);
					if(out <= in || out > Booking.CAL_SIZE) throw new Exception();
					int np = Integer.parseInt(housefile.readLine());
					Client c = (Client) find(Client.class, clientusername);
					house.addBooking(in,out,c,np);
				}
			}
		}
		housefile.close();
	}

	public static void getUserDatabase() throws Exception{
		BufferedReader userfile = new BufferedReader(new FileReader("Users.txt"));

		String uline;
		while ((uline = userfile.readLine()) != null){
			String type = uline;
			String username = userfile.readLine();
			String password = userfile.readLine();
			String phone = userfile.readLine();
			String address = userfile.readLine();
			String nationality = userfile.readLine();
			String email = userfile.readLine();
			String name = userfile.readLine();
			if (type.equals("O")){
				String bio = userfile.readLine();
				String publicEmail = userfile.readLine();
				new Owner(username, password, phone, address, nationality, email, name, bio, publicEmail);
			} else {
				new Client(username, password, phone, address, nationality, email, name);
			}
		}
		userfile.close();
	}

	public static <T extends User> User find(Class<T> c, String u) throws Exception {
		for(User user : users){
			if(user.username.equals(u) && user.getClass().equals(c)) return user;
		}
    throw new Exception();
  }

	public static void setDatabase() throws Exception{

		BufferedWriter userFile = new BufferedWriter(new FileWriter("Users.txt"));
		BufferedWriter houseFile = new BufferedWriter(new FileWriter("Houses.txt"));

		for (User user : users){
			user.write(userFile, houseFile);
		}
		userFile.close();
		houseFile.close();
	}

	public void write(BufferedWriter file, BufferedWriter houseFile) throws Exception {
		file.write(username+"\n");
		file.write(password+"\n");
		file.write(phone+"\n");
		file.write(address+"\n");
		file.write(nationality+"\n");
		file.write(email+"\n");
		file.write(name+"\n");
	}
}

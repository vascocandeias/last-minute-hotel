import java.util.*;
import root.bookings.*;
import root.houses.*;
import root.users.*;

public class LastMinuteHotel {

  private static final int NUM_USERS = 10;

  public static void menu(Client client){

    if(client == null){
      System.out.println("Invalid login");
      return;
    }

    Scanner kb = new Scanner(System.in);
    Booking booking = client.getFutureBooking();
    client.display();

    if(booking != null){
      System.out.println("This service is limited to one booking! Check yours:");
      booking.display();
      //TODO: cancel booking
      return;
    }

    while(true){

      System.out.println("Do you want to search for a house?");
      System.out.println("y - Yes");
      System.out.println("n - No");

      search:
      while(true){
        try {
          switch(kb.nextLine().charAt(0)){
            case 'y': break search;
            case 'n': return;
            default: System.out.println("Invalid input.");
          }
        } catch(StringIndexOutOfBoundsException e) {
          continue;
        }
      }

      System.out.println("What are you looking for?");

      System.out.print("Location: ");
      String location = kb.nextLine();
      System.out.print("Check-in: ");
      Integer in = Integer.parseInt(kb.nextLine());
      System.out.print("Check-out: ");
      Integer out = Integer.parseInt(kb.nextLine());
      System.out.print("Number of people: ");
      Integer numPeople = Integer.parseInt(kb.nextLine());

      boolean[] requested = Facility.chooseFacilities();

      House[] houses = House.search(location, in, out, requested);

      if(houses == null){
        System.out.println("Sorry! Your search did not get any results");
        continue;
      }

      for(int j = 0; j < houses.length && houses[j] != null; j++){
        System.out.println("\t" + j + " : " + houses[j].getName() + ", " + houses[j].getLocation() + ": RM" + houses[j].getPrice(numPeople, out-in));
      }
      //TODO: adicionar loop
      System.out.print("Select one (or press b to go back): ");
      try {
        int k = Integer.parseInt(kb.nextLine());
        selectHouse(client, houses[k], in, out, numPeople);
      } catch(Exception e) {
        System.out.println("Invalid input.");
        continue;
      }
    }
  }

  public static void menu(Owner owner){

    Scanner kb = new Scanner(System.in);

    if(owner == null){
      System.out.println("Invalid login");
      return;
    }

    owner.display();

    while(true){

      System.out.println("Choose an action:");
      System.out.println("m - Manage houses");
      System.out.println("v - View bookings");
      System.out.println("a - Add house");
      System.out.println("q - quit");

      try {
        switch(kb.nextLine().charAt(0)){
          case 'm':
            manageHouses(owner);
            break;
          case 'v':
            manageBookings(owner);
            break;
          case 'a':
            addHouse(owner);
            break;
          case 'q':
            return;
          default:
            System.out.println("Invalid input.");
            break;
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      }
    }
  }

  public static void manageBookings(Owner owner){

    Scanner kb = new Scanner(System.in);
    Booking[] bookings = owner.displayBookings();

    System.out.print("Choose one to get more details: ");
    int i = Integer.parseInt(kb.nextLine());
    bookings[i].display();
  }

  public static void selectHouse(Client client, House house, int in, int out, int numPeople){

    house.display();

    Scanner kb = new Scanner(System.in);

    System.out.println("Do you want to book this house?");
    System.out.println("y - Yes");
    System.out.println("n - No");

    while(true){
      try {
        switch(kb.nextLine().charAt(0)){
          case 'y':
            house.addBooking(in, out, client, numPeople);
            return;
          case 'n': return;
          default: System.out.println("Invalid input.");
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      }
    }
  }

  public static void manageHouses(Owner owner){

    Scanner kb = new Scanner(System.in);
    owner.displayHouses();

    System.out.print("Choose one to get more details: ");
    int i = Integer.parseInt(kb.nextLine());
    owner.getHouse(i).display();

  }

  public static void addHouse(Owner owner){

    Scanner kb = new Scanner(System.in);

    System.out.println("Fill out the details");
    System.out.print("Name: ");
    String name = kb.nextLine();
    System.out.print("Price per night per person: ");
    Double pricePerNightPerPerson = Double.parseDouble(kb.nextLine());
    System.out.print("Rental Feee: ");
    Double rentalFee = Double.parseDouble(kb.nextLine());
    System.out.print("Location: ");
    String location = kb.nextLine();

    owner.addHouse(name, pricePerNightPerPerson, rentalFee, location);
  }

  public static void login(){

    Scanner kb = new Scanner(System.in);

    System.out.print("Username: ");
    String username = kb.nextLine();
    System.out.print("Password: ");
    String password = kb.nextLine();
    User user = User.logIn(username, password);
    if(user instanceof Client)
      menu((Client) user);
    else
      menu((Owner) user);
  }

  public static void register(){

    boolean isClient = true;

    Scanner kb = new Scanner(System.in);

    System.out.println("Who are you?");
    System.out.println("c - A client");
    System.out.println("o - An owner");
    System.out.println("q - None, let me go!");

    options:
    while(true){
      try {
        switch(kb.nextLine().charAt(0)){
          case 'c':
            isClient = true;
            break options;
          case 'o':
            isClient = false;
            break options;
          case 'q':
            return;
          default:
            System.out.println("Invalid input.");
            break;
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      }
    }

    System.out.println("Fill out the details");
    System.out.print("Name: ");
    String name = kb.nextLine();
    System.out.print("Username: ");
    String username = kb.nextLine();
    System.out.print("Password: ");
    String password = kb.nextLine();
    System.out.print("Phone: ");
    String phone = kb.nextLine();
    System.out.print("Address: ");
    String address = kb.nextLine();
    System.out.print("Nationality: ");
    String nationality = kb.nextLine();
    System.out.print("Email username: ");
    String emailUser = kb.nextLine();
    System.out.print("Email domain: ");
    String emailDomain = kb.nextLine();
    String email = emailUser + "@" + emailDomain;

    if(isClient)
      new Client(username, password, phone, address, nationality, email, name);
    else{
      System.out.print("Bio: ");
      String bio = kb.nextLine();
      System.out.print("Public email username: ");
      String publicEmailUser = kb.nextLine();
      System.out.print("Public email domain: ");
      String publicEmailDomain = kb.nextLine();
      String publicEmail = publicEmailUser + "@" + publicEmailDomain;
      new Owner(username, password, phone, address, nationality, email, name, bio, publicEmail);
    }
  }

  public static void main(String[] args) {

    Scanner kb = new Scanner(System.in);

    System.out.println("Welcome to Last Minute Hotel Booking!");

    options:
    while(true){

      System.out.println("Choose an action:");
      System.out.println("l - login");
      System.out.println("r - register");
      System.out.println("q - quit");

      try {
        switch(kb.nextLine().charAt(0)){
          case 'l':
            login();
            break;
          case 'r':
            register();
            break;
          case 'q':
            break options;
          default:
            System.out.println("Invalid input.");
            break;
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      } /*catch(Exception e){
        break;
      }*/
    }
  }
}

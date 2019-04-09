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

    Booking booking = client.getFutureBooking();
    client.display();

    if(booking != null){
      System.out.println("This service is limited to one booking! Check yours:");
      booking.display();
    } else {
      System.out.println("What are you looking for?");
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
            System.out.print("Manage them all!");
            break;
          case 'v':
            System.out.println("Show me the money!");
            break;
          case 'a':
            System.out.println("Get more money!");
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

  public static void login(){

    Scanner kb = new Scanner(System.in);
    String username;
    String password;

    System.out.print("Username: ");
    username = kb.nextLine();
    System.out.print("Password: ");
    password = kb.nextLine();
    User user = User.logIn(username, password);
    if(user instanceof Client)
      menu((Client) user);
    else
      menu((Owner) user);
  }

  public static void register(){

    boolean isClient = true;

    Scanner kb = new Scanner(System.in);
    String username;
    String password;
    String phone;
    String address;
    String nationality;
    String emailUser;
    String emailDomain;
    String email;
    String bio;
    String publicEmailUser;
    String publicEmailDomain;
    String publicEmail;

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
    System.out.print("Username: ");
    username = kb.nextLine();
    System.out.print("Password: ");
    password = kb.nextLine();
    System.out.print("Phone: ");
    phone = kb.nextLine();
    System.out.print("Address: ");
    address = kb.nextLine();
    System.out.print("Nationality: ");
    nationality = kb.nextLine();
    System.out.print("Email username: ");
    emailUser = kb.nextLine();
    System.out.print("Email domain: ");
    emailDomain = kb.nextLine();
    email = emailUser + "@" + emailDomain;

    if(isClient)
      new Client(username, password, phone, address, nationality, email);
    else{
      System.out.print("Bio: ");
      bio = kb.nextLine();
      System.out.print("Public email username: ");
      publicEmailUser = kb.nextLine();
      System.out.print("Public email domain: ");
      publicEmailDomain = kb.nextLine();
      publicEmail = publicEmailUser + "@" + publicEmailDomain;
      new Owner(username, password, phone, address, nationality, email, bio, publicEmail);
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

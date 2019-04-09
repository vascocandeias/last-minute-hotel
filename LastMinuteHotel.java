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

    Booking booking;
    //client.display();

    booking = client.getFutureBooking();
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
      System.out.println("a - View bookings");
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
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      }
    }
  }

  public static void main(String[] args) {

    Scanner kb = new Scanner(System.in);
    String username;
    String password;
    User user;

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
            System.out.print("Username: ");
            username = kb.nextLine();
            System.out.print("Password: ");
            password = kb.nextLine();
            Client client = (Client) User.logIn(username, password);
            menu(client);
            break;
          case 'r':
            user = new Client("vcc", "1", 1, "a", "a", "a");
            break;
          case 'q':
            break options;
          default:
            System.out.println("Invalid input.");
            break;
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      }
    }
  }
}

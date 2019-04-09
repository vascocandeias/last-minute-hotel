import java.util.*;
import root.bookings.*;
import root.houses.*;
import root.users.*;

public class LastMinuteHotel {

  private static final int NUM_USERS = 10;

  public static void main(String[] args) {

    Scanner kb = new Scanner(System.in);

    System.out.println("Welcome to Last Minute Hotel Booking!");
    System.out.println("Choose an action:");
    System.out.println("l - login");
    System.out.println("r - register");
    System.out.println("q - quit");

    menu:
    while(true){
      try {
        switch(kb.nextLine().charAt(0)){
          case 'l':
            System.out.println("login!");
            break;
          case 'r':
            System.out.println("register!");
            break;
          case 'q':
            break menu;
        }
      } catch(StringIndexOutOfBoundsException e) {
        continue;
      }
    }
  }
}

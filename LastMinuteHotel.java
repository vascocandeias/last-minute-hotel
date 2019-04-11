import java.util.*;
import root.bookings.*;
import root.houses.*;
import root.users.*;
import java.io.*;

public class LastMinuteHotel {

  public static void main(String[] args) {

    try{
      User [] users = User.getUsersDatabase();
      User.displayUsers(users);
    } catch(FileNotFoundException e){
    } catch(Exception e){
      System.out.println("There was an error with the database. Starting fresh.");
    }


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
            User.logIn();
            break;
          case 'r':
            try {
              User.register().menu();
            } catch(Exception e) {}
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

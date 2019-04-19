import java.util.*;
import java.io.*;

import src.bookings.*;
import src.houses.*;
import src.users.*;

public class LastMinuteHotel {
  public static void main(String[] args) {

    System.out.println("\nWelcome to Last Minute Hotel Booking!");

    try{
      User [] users = User.getUsersDatabase();
      User.displayUsers(users);
    } catch(FileNotFoundException e){ // don't do anything if the file does not exist
    } catch(Exception e){
      User.deleteAll();
      System.out.println("\nThere was an error with the database. Starting fresh.");
    }


    Scanner kb = new Scanner(System.in);
    options:
    while(true){

      System.out.println("\nChoose an action:\n");
      System.out.println("\tl - Login");
      System.out.println("\tr - Register");
      System.out.println("\tq - Quit");

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

  //TODO: escrever na base de dados
}

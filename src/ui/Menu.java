package ui;

import exceptions.InvalidAccount;
import gui.MenuOptions;
import model.Account;
import model.Movie;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu {
    private static ArrayList<Account> allAccounts = new ArrayList<>();


    // MODIFIES: this
    // EFFECTS: checks to see if the given userName matches an account that already exists in the system
    //            - if it does then it calls chooseOption passing that account as a parameter
    //            - if it doesn't, it creates a new account and passes it as a parameter to chooseOption
    public static void checkAccounts(String name) {

        if (checkIfExist(name) != null) {
            new MenuOptions(checkIfExist(name));
        }

        else {
            Account a = new Account(name);
            allAccounts.add(a);
            new MenuOptions(a);
        }

    }

    public static Account checkIfExist(String name) {
        for (Account a: allAccounts) {
            if (name.equals(a.getUserName())) {
                return a;
            }

        }

        return null;

    }


    public static Account addAccount(String name) {
        Account myAccount = new Account(name);
        allAccounts.add(myAccount);
        return  myAccount;

    }

    // EFFECTS: takes the titles of each movie in list and forms one string by separating the titles with ", "
    public static String stringMoviesTogether(ArrayList<Movie> list) {
        String movieList = "";
        for (Movie m: list) {
            movieList = movieList+m.getTitle()+", ";
        }
        return movieList;

    }


    // MODIFIES: this
    // EFFECTS: splits string into two at ": "
    //          returns a new list with the two strings
    public static ArrayList<String> splitOnColon(String line){
        String[] splits = line.split(": ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // MODIFIES: this
    // EFFECTS: splits string into multiple at every ", "
    //          returns a new list containing all these strings
    public static ArrayList<String> splitOnComma(String line){
        String[] splits = line.split(", ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    // EFFECTS: returns the account with same username as name
    public static Account findFriend(String name) throws InvalidAccount {
        for (Account a: allAccounts) {
            if (name.equals(a.getUserName())) {
                return a;
            }
        }
        throw new InvalidAccount();
    }


}

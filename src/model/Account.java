package model;

import exceptions.InvalidMovie;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

public class Account extends Observable implements Accounts {
    private String userName;
    private ArrayList<Movie> myMovies;
    private ArrayList<Movie> myFavourites;
    private Inbox myInbox;

    // MODIFIES: this
    // EFFECTS: creates new Account object with fields 'userName', 'myMovies', 'myFavourites, and 'myInbox'
    public Account(String userName) {
        this.userName = userName;
        this.myMovies = new ArrayList<>();
        this.myFavourites = new ArrayList<>();
        this.myInbox = new Inbox();
    }


    // EFFECTS: returns userName of Account object
    public String getUserName(){
        return userName;
    }

    // EFFECTS: returns myMovies of Account object
    public ArrayList<Movie> getMyMovies() {
        return myMovies;
    }

    // EFFECTS: returns myFavourites of Account object
    public ArrayList<Movie> getMyFavourites() {
        return myFavourites;
    }

    // EFFECTS: returns myInbox of Account object
    public Inbox getMyInbox() {
        return myInbox;
    }

    public Movie getFavouriteMovie(String name) {
        return getMovie(name, myFavourites);
    }

    public Movie getRegularMovie(String name) {
        return getMovie(name, myMovies);
    }

    public Movie getMovie(String name, ArrayList<Movie> movies) {
        for (Movie m: movies) {
            if (m.getTitle().equals(name)) {
                return m;
            }
        }
        return null;
    }


    public void addMovie(Movie m, ArrayList<Movie> movies) {
        if (!movies.contains(m)) {
            movies.add(m);
            m.addAccount(this, movies);
        }

    }

    public boolean removeMovie(String title) throws InvalidMovie {
        for (Movie m: myMovies) {
            if (m.getTitle().equals(title)) {
                myMovies.remove(m);
                return true;
            }
        }

        for (Movie m: myFavourites) {
            if (m.getTitle().toLowerCase().equals(title)) {
                myFavourites.remove(m);
                return true;
            }
        }

        throw new InvalidMovie();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(userName, account.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName);
    }

}

package model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public abstract class Movie implements Observer, MovieList {
    protected String title;
    protected ArrayList<Account> accounts;

    // MODIFIES: this
    // EFFECTS: creates new Movie object with field "title"
    public Movie(String title) {
        this.title = title;
        accounts = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: sets the title of Movie object to "title"
    public abstract void setTitle(String title);


    // EFFECTS: returns title of Movie object
    public abstract String getTitle();


    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account a, ArrayList<Movie> movies) {
        if (!accounts.contains(a)) {
            accounts.add(a);
            a.addMovie(this, movies);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        String name = (String) arg;
        System.out.println(name+ " was successfully added to your list!");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title);
    }
}






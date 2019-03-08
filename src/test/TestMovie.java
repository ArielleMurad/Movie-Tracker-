package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMovie {
    Movie rm;
    Movie fm;
    private ArrayList<Movie> myMoviesTest;
    private ArrayList<Movie> myFavouritesTest;
    private Account acc;

    @BeforeEach
    public void setup() {
        rm = new RegularMovie("regular");
        fm = new FavouriteMovie("favourite");
        myMoviesTest = new ArrayList<>();
        myFavouritesTest = new ArrayList<>();
        acc = new Account("test");
    }

    @Test
    public void testSetAndGetRegularTitle() {
        MovieList myMovie = new RegularMovie("");
        myMovie.setTitle("Mission Impossible");
        assertEquals("Mission Impossible", myMovie.getTitle());
    }

    @Test
    public void testSetAndGetFavouriteTitle() {
        MovieList myFavourite = new FavouriteMovie("");
        myFavourite.setTitle("Inception");
        assertEquals("INCEPTION", myFavourite.getTitle());
    }

    @Test
    public void testAddThenGetAccountsRegular() {
        rm.addAccount(acc, myMoviesTest);
        assertTrue(rm.getAccounts().contains(acc));
        assertTrue(myMoviesTest.contains(rm));

    }

    @Test
    public void testAddThenGetAccountsFavourite() {
        fm.addAccount(acc, myFavouritesTest);
        assertTrue(fm.getAccounts().contains(acc));
        assertTrue(myFavouritesTest.contains(fm));

    }


}

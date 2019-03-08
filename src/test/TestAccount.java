package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAccount {
    private Account accountTest;

    @BeforeEach
    public void setup() {
        accountTest = new Account("test");
    }

    @Test
    public void testGetUserName() {
        assertEquals("test", accountTest.getUserName());
    }

    @Test
    public void testGetMyMovies() {
        assertTrue(accountTest.getMyMovies().isEmpty());

        RegularMovie movie = new RegularMovie("Mean Girls");
        accountTest.addMovie(movie, accountTest.getMyMovies());

        ArrayList<Movie> moviesTest = new ArrayList<>();
        moviesTest.add(movie);

        assertEquals(moviesTest, accountTest.getMyMovies());

    }

    @Test
    public void testGetMyFavourites() {
        assertTrue(accountTest.getMyFavourites().isEmpty());

        FavouriteMovie movie = new FavouriteMovie("Oceans 8");
        accountTest.addMovie(movie, accountTest.getMyFavourites());

        ArrayList<Movie> favouritesTest = new ArrayList<>();
        favouritesTest.add(movie);

        assertEquals(favouritesTest, accountTest.getMyFavourites());

    }

    @Test
    public void testGetMyInbox() {
        Movie movie = new RegularMovie("Mean Girls");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        Inbox inbox = accountTest.getMyInbox();

        assertTrue(inbox.getInbox().isEmpty());

        inbox.getInbox().put(accountTest, movies);
        assertEquals(1, accountTest.getMyInbox().getInbox().get(accountTest).size());

    }

    @Test
    public void testAddAndGetFavouriteMovie() {
        Movie m = new FavouriteMovie("mean girls");
        accountTest.addMovie(m, accountTest.getMyFavourites());
        assertEquals("MEAN GIRLS", accountTest.getFavouriteMovie("MEAN GIRLS").getTitle());
        assertTrue(accountTest.getMyFavourites().contains(m));

    }

    @Test
    public void testAddAndGetRegularMovie() {
        Movie m = new RegularMovie("hunger games");
        accountTest.addMovie(m, accountTest.getMyMovies());
        assertEquals("hunger games", accountTest.getRegularMovie("hunger games").getTitle());
        assertTrue(accountTest.getMyMovies().contains(m));


    }



}

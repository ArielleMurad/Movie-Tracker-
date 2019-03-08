package test;

import exceptions.InvalidAccount;
import model.Account;
import model.Movie;
import model.RegularMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static ui.Menu.*;

public class TestMenu {
    private Account accountTest;
    private ArrayList<Account> testAccounts;
    ArrayList<Movie> myMoviesTest;
    ArrayList<Movie> myFavouritesTest;


    @BeforeEach
    public void runBefore() {
        accountTest = new Account("test");
        addAccount("test");
        myMoviesTest = accountTest.getMyMovies();
        myFavouritesTest = accountTest.getMyFavourites();
    }

    @Test
    public void testAddAccount() {
        Account acc = new Account("acc");
        assertEquals(acc, addAccount("acc"));

    }


    @Test
    public void testStringMoviesTogether() {
        Movie m1 = new RegularMovie("m1");
        Movie m2 = new RegularMovie("m2");
        Movie m3 = new RegularMovie("m3");

        accountTest.addMovie(m1, myMoviesTest);
        accountTest.addMovie(m2, myMoviesTest);
        accountTest.addMovie(m3, myMoviesTest);

        assertEquals("m1, m2, m3, ", stringMoviesTogether(myMoviesTest));
    }


    @Test
    public void testSplitOnColon() {
        String list = "test: m1, m2, m3, ";
        ArrayList<String> newList = new ArrayList<>() ;
        newList.add("test");
        newList.add("m1, m2, m3, ");
        assertEquals(newList, splitOnColon(list));
    }

    @Test
    public void testSplitOnComma() {
        String list = "m1, m2, m3, ";
        ArrayList<String> newList = new ArrayList<>() ;
        newList.add("m1");
        newList.add("m2");
        newList.add("m3");
        assertEquals(newList, splitOnComma(list));
    }


    @Test
    public void testFindFriendNoException() {
        Account friend = new Account("acc1");
        addAccount("acc1");
        try {
            assertEquals(friend, findFriend("acc1"));
        } catch (InvalidAccount ia) {
            fail("Unexpected InvalidAccount exception was thrown");
        }

    }

    @Test
    public void testFindFriendWithException() {
        try {
            assertEquals(null, findFriend("acc2"));
            fail("Expected InvalidAccount exception was not thrown");

        } catch (InvalidAccount ia) {

        }

    }



}

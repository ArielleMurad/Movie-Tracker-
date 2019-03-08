package test;

import exceptions.InvalidAccount;
import model.Account;
import model.LoadSave;
import model.Movie;
import model.RegularMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static ui.Menu.stringMoviesTogether;

public class TestLoadSave {
    private Account accountTest;
    private ArrayList<Movie> myMoviesTest;
    private LoadSave lsTest;

    @BeforeEach
    public void setup() {
        accountTest = new Account("test");
        myMoviesTest = accountTest.getMyMovies();
        lsTest = new LoadSave(accountTest);

    }

    @Test
    public void testSave() throws IOException {
        RegularMovie rm1 = new RegularMovie("Fast and Furious");
        RegularMovie rm2 = new RegularMovie("The Breakfast Club");
        RegularMovie rm3 = new RegularMovie("High School Musical");

        accountTest.addMovie(rm1, myMoviesTest);
        accountTest.addMovie(rm2, myMoviesTest);
        accountTest.addMovie(rm3, myMoviesTest);

        lsTest.save();
        List<String> lines = Files.readAllLines(Paths.get("history"));
        String expected = "test: Fast and Furious, The Breakfast Club, High School Musical, ";

        assertTrue(lines.contains(expected));

    }

    @Test
    public void testLoadNoException() throws IOException, InvalidAccount {
        assertTrue(myMoviesTest.isEmpty());

        try {
            lsTest.load();
        } catch (InvalidAccount ia) {
            fail("Unexpected InvalidAccount exception was thrown");
        }

        assertEquals(3, myMoviesTest.size());

        assertEquals("Fast and Furious", myMoviesTest.get(0).getTitle());
        assertEquals("The Breakfast Club", myMoviesTest.get(1).getTitle());
        //assertEquals("High School Musical", myMoviesTest.get(2).getTitle());
    }

    @Test
    public void testLoadWithException() throws IOException, InvalidAccount {
        assertTrue(myMoviesTest.isEmpty());
        Account acc = new Account("acc");
        LoadSave ls = new LoadSave(acc);

        try {
            ls.load();
            fail("Expected InvalidAccount exception was not thrown");
        } catch (InvalidAccount ia) {
        }

    }

    @Test
    public void testLoadMovies() {
        ArrayList<String> newList = new ArrayList<>() ;
        newList.add("m1");
        newList.add("m2");
        newList.add("m3");

        lsTest.loadMovies(newList);

        assertEquals("m1, m2, m3, ", stringMoviesTogether(myMoviesTest));
    }

}

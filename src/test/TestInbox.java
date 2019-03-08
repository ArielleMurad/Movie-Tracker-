package test;

import model.Account;
import model.Movie;
import model.RegularMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInbox {
    private Account current;
    private Map<Account, ArrayList<Movie>> map;

    @BeforeEach
    public void setup() {
        current = new Account("current");
        map = new HashMap<>();
        map.put(current, new ArrayList<>());

    }

    @Test
    public void testGetInbox() {
        current.getMyInbox().getInbox().put(current, new ArrayList<>());
        assertEquals(map, current.getMyInbox().getInbox());

    }


    @Test
    public void testSendRecommendationToNewAccount() {
        Account friend = new Account("friend");
        Movie movie = new RegularMovie("Mean Girls");

        friend.getMyInbox().sendRecommendation(current, movie);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);

        assertTrue(friend.getMyInbox().getInbox().containsKey(current));
        assertEquals(movies, friend.getMyInbox().getInbox().get(current));


    }

    @Test
    public void testSendRecommendationToExistingAccount() {
        Account friend = new Account("friend");
        Movie m1 = new RegularMovie("Mean Girls");
        Movie m2 = new RegularMovie("Peppermint");

        friend.getMyInbox().sendRecommendation(current, m1);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(m1);

        assertTrue(friend.getMyInbox().getInbox().containsKey(current));
        assertEquals(movies, friend.getMyInbox().getInbox().get(current));
        assertEquals(1, friend.getMyInbox().getInbox().get(current).size());

        friend.getMyInbox().sendRecommendation(current, m2);
        movies.add(m2);
        assertEquals(movies, friend.getMyInbox().getInbox().get(current));
        assertEquals(2, friend.getMyInbox().getInbox().get(current).size());



    }
}

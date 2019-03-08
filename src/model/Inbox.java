package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inbox {
    private Map<Account, ArrayList<Movie>> inbox;

    public Inbox() {
        this.inbox = new HashMap<>();
    }

    public Map<Account, ArrayList<Movie>> getInbox() {
        return inbox;
    }


    // MODIFIES: this
    // EFFECTS: adds inbox to the list of Inbox in friend's account
    public void sendRecommendation(Account current, Movie movie){
        if (inbox.containsKey(current)) {
            inbox.get(current).add(movie);
        }
        else {
            ArrayList<Movie> movies = new ArrayList<>();
            movies.add(movie);
            inbox.put(current, movies);
        }

    }

    public void resetInbox() {
        inbox = new HashMap<>();
    }



}

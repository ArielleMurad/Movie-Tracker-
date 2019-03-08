package model;

import exceptions.InvalidAccount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static ui.Menu.*;

public class LoadSave {
    private Account account;

    public LoadSave(Account account) {
        this.account = account;
    }


    // REQUIRES: this user must have saved movies previously
    // MODIFIES: this
    // EFFECTS: opens file 'history' and finds line with same username as current account
    //          calls loadMovies on that line
    public boolean load() throws IOException, InvalidAccount {
        List<String> lines = Files.readAllLines(Paths.get("history"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnColon(line);
            if (account.getUserName().equals(partsOfLine.get(0))) {
                List<String> differentMovies = splitOnComma(partsOfLine.get(1));
                loadMovies(differentMovies);
                return true;
            }
        }
        throw new InvalidAccount();

    }

        // MODIFIES: this
        // EFFECTS: adds a new movie with title which is a string in movies to myMovies
        //          does this for every string in movies
        public void loadMovies(List<String> movies) {
            for (String title: movies) {
                Movie m = new RegularMovie(title);
                account.getMyMovies().add(m);
            }
        }



    // MODIFIES: file
    // EFFECTS: saves current account's list of movies to the 'history' file
    //          format is 'userName: movieList'
    public void save() throws IOException {
        List<String> lines = new ArrayList<>();
        String list = account.getUserName()+": " +
                stringMoviesTogether(account.getMyFavourites()) + stringMoviesTogether(account.getMyMovies());
        lines.add(list);
        Files.write(Paths.get("history"), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}

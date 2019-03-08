package model;

public class FavouriteMovie extends Movie {
    public FavouriteMovie(String title) {
        super(title.toUpperCase());
    }

    // EFFECTS: returns title of this movie object
    @Override
    public String getTitle() {
        return title;
    }

    // MODIFIES: this
    // EFFECTS: changes title to 'title' in UpperCase
    @Override
    public void setTitle(String title) {
        this.title = title.toUpperCase();
    }



}



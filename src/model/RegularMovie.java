package model;

public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title);
    }

    // EFFECTS: returns title
    @Override
    public String getTitle() {
        return title;
    }

    // MODIFIES: this
    // EFFECTS: changes title to 'title'
    @Override
    public void setTitle(String title) {
        this.title = title;
    }




}

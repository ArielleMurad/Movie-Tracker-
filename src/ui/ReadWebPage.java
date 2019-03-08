package ui;

import gui.MenuOptions;
import model.Account;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadWebPage extends JFrame implements ActionListener {
    private Account account;
    private JTextField field;
    final static boolean shouldFill = true;
    final static boolean RIGHT_TO_LEFT = false;
    private Color color;

    public ReadWebPage(Account account, int index, String name) {
        super("Movie Tracker");
        this.account = account;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new GridBagLayout());
        color = this.getBackground();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        if (index == 1) {
            searchMovie();
        }

        else if (index == 2) {
            try {
                readWebPage(name);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public void readWebPage(String name) throws IOException, JSONException {

        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        BufferedReader br = null;

        try {
            String movieName = "t="+name;
            String omdbmoviequery = "http://www.omdbapi.com/?apikey=b0cd74d0&";
            String theURL = omdbmoviequery + movieName;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String json = sb.toString();

            JSONObject obj = new JSONObject(json);
            String movieTitle = obj.getString("Title");
            String released = obj.getString("Released");
            String runtime = obj.getString("Runtime");
            String genre = obj.getString("Genre");
            String director = obj.getString("Director");
            String actors = obj.getString("Actors");
            String plot = obj.getString("Plot");
            String rating = obj.getString("imdbRating");

            JLabel title = new JLabel(movieTitle);
            title.setFont(new Font("Times New Roman", Font.BOLD, 20));
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 1;
            add(title, c);

            JLabel release = new JLabel("Date of Release: "+released);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 2;
            add(release, c);

            JLabel time = new JLabel("Runtime: "+runtime);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 3;
            add(time, c);

            JLabel type = new JLabel("Genre: "+genre);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 4;
            add(type, c);

            JLabel dir = new JLabel("Director: "+director);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 5;
            add(dir, c);

            JLabel act = new JLabel("Actors: "+actors);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 6;
            add(act, c);

            JTextArea plotline = new JTextArea("Plot Summary: "+plot);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 7;

            plotline.setLineWrap(true);
            plotline.setSize(400, 40);
            plotline.setEditable(false);
            plotline.setBackground(color);
            JScrollPane scrollPane = new JScrollPane(plotline);
            add(scrollPane, c);

            JLabel rate = new JLabel("IMDB Rating: "+rating);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 8;
            add(rate, c);

            JButton menu = new JButton("Go back to Menu");
            menu.setActionCommand("Menu");
            menu.addActionListener(this);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 9;
            add(menu, c);


        } finally {

            if (br != null) {
                br.close();
            }
        }
    }


    public void searchMovie() {
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }
        JLabel request = new JLabel("Please enter the title of the movie that you would like to know more about");
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(request, c);

        field = new JTextField(10);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(field, c);


        JButton enter = new JButton("Enter");
        enter.setActionCommand("Display Info");
        enter.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        add(enter, c);


    }

    public String convertTitle(String title) {
    for (int i=0; i<title.length(); i++) {
        if (title.substring(i,i+1).equals(" ")) {
            StringBuilder myTitle = new StringBuilder(title);
            String plus = "+";
            myTitle.setCharAt(i, plus.charAt(0));
            title = myTitle.toString();
        }
    }
    return title;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Display Info")) {
            String name = convertTitle(field.getText());
            dispose();
            new ReadWebPage(account, 2, name);

        }


        if (e.getActionCommand().equals("Menu")) {
            dispose();
            new MenuOptions(account);
        }

    }
}




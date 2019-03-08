package gui.tabs;

import gui.MenuOptions;
import model.Account;
import model.Movie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMovies extends JFrame implements ActionListener {
    private Account account;
    private Color color;
    final static boolean shouldFill = true;
    public ShowMovies(Account account) {
        super("Movie Tracker");
        this.account = account;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new GridBagLayout());
        validate();

        color = this.getBackground();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        showMovies();

    }

    public void showMovies() {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (account.getMyMovies().isEmpty() && account.getMyFavourites().isEmpty()) {
            JLabel noMovies = new JLabel("You have no movies in your list");
            noMovies.setAlignmentX(CENTER_ALIGNMENT);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;

            askToAddMovie();
            add(noMovies, c);


        }
        else {
            JLabel name = new JLabel(account.getUserName()+"'s Movies:");
            name.setFont(new Font("Apple Chancery", Font.BOLD, 20));
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            add(name, c);

            DefaultListModel listModel = new DefaultListModel();
            for (Movie m : account.getMyFavourites()) {
                listModel.addElement(m.getTitle());
            }
            for (Movie m : account.getMyMovies()) {
                listModel.addElement(m.getTitle());
            }
            JList list = new JList(listModel);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(10,0,0,0);
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;

            list.setBackground(color);
            askToAddMovie();
            add(list, c);



        }
    }

    public void askToAddMovie() {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        JLabel question = new JLabel("Would you like to add another movie?");
        question.setFont(new Font("Times New Roman", Font.BOLD, 14));
        question.setAlignmentX(CENTER_ALIGNMENT);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        add(question, c);

        validate();
        JButton add = new JButton("Yes");
        add.setActionCommand("Add Movie");
        add.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        add(add, c);

        JButton notAdd = new JButton("No");
        notAdd.setActionCommand("Do Not Add Movie");
        notAdd.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        add(notAdd, c);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add Movie")) {
            new addMovie(account);

        }
        if (e.getActionCommand().equals("Do Not Add Movie")) {
            new MenuOptions(account);
        }

        dispose();

    }
}

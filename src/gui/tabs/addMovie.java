package gui.tabs;

import gui.MenuOptions;
import model.Account;
import model.FavouriteMovie;
import model.RegularMovie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addMovie extends JFrame implements ActionListener {
    private Account account;
    private JTextField field;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public addMovie(Account account) {
        super("Movie Tracker");
        this.account = account;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new GridBagLayout());


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);


        add();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add favourite")) {
            FavouriteMovie favourite = new FavouriteMovie(field.getText());
            account.addMovie(favourite, account.getMyFavourites());
            account.addObserver(favourite);
        }

        if (e.getActionCommand().equals("Add regular")) {
            RegularMovie regular = new RegularMovie(field.getText());
            account.addMovie(regular, account.getMyMovies());
            account.addObserver(regular);
        }
        dispose();
        new MenuOptions(account);
    }


    public void add() {

        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel label1 = new JLabel("Which movie would you like to add?");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(label1, c);

        field = new JTextField(10);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        add(field, c);

        JLabel label2 = new JLabel("Is this a favourite?");
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        add(label2, c);

        JButton favourite = new JButton("Yes");
        favourite.setActionCommand("Add favourite");
        favourite.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;      //make this component tall
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        add(favourite, c);

        JButton regular = new JButton("No");
        regular.setActionCommand("Add regular");
        regular.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        add(regular, c);
    }




}

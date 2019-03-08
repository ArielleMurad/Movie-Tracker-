package gui.tabs;

import exceptions.InvalidMovie;
import gui.MenuOptions;
import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveMovie extends JFrame implements ActionListener {
    private Account account;
    private JTextField field;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public RemoveMovie(Account account, int index) {
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

        if (index == 1) {
            removeMovie();
        }
        else if (index == 2) {
            invalidMovie();
        }


    }
    public void removeMovie() {

        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel question = new JLabel("Which movie would you like to cross off your list?");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(question, c);

        field = new JTextField(10);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        add(field, c);

        JButton favourite = new JButton("Remove");
        favourite.setActionCommand("Remove movie");
        favourite.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 0;      //make this component tall
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        add(favourite, c);


    }

    public void invalidMovie(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel invalid = new JLabel("This movie is not on your list!");
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(invalid, c);

        JButton retry = new JButton("Cross Off a Different Movie");
        retry.setActionCommand("Retry");
        retry.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 0;      //make this component tall
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(retry, c);

        JButton menu = new JButton("Go Back to Menu");
        menu.setActionCommand("Menu");
        menu.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 0;      //make this component tall
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        add(menu, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if (e.getActionCommand().equals("Remove movie")) {
            try {
                account.removeMovie(field.getText());
                new MenuOptions(account);
            } catch (InvalidMovie invalidMovie) {
                new RemoveMovie(account, 2);
            }

        }

        if (e.getActionCommand().equals("Retry")) {
            new RemoveMovie(account, 1);
        }

        else if (e.getActionCommand().equals("Menu")) {
            new MenuOptions(account);
        }


    }
}

package gui.tabs;

import exceptions.InvalidAccount;
import gui.MenuOptions;
import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadSave extends JFrame implements ActionListener {
    private Account account;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public LoadSave(Account account, int index) {
        super("Movie Tracker");
        this.account = account;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        if (index == 1 ) {
            loadSave();
        }
        if (index == 2) {
            invalidAccount();
        }

    }

    public void loadSave() {

        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel question = new JLabel("What would you like to do?");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(question, c);


        JButton save = new JButton("Save My Movies to History");
        save.setActionCommand("Save");
        save.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;      //make this component tall
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        add(save, c);

        JButton load = new JButton("Load My Movies from History");
        load.setActionCommand("Load");
        load.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        add(load, c);
;
    }

    public void invalidAccount() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel noAccount = new JLabel("You don't have any movies saved");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(noAccount, c);


        JButton save = new JButton("Save My Movies to History");
        save.setActionCommand("Save");
        save.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;      //make this component tall
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(save, c);

        JButton menu = new JButton("Go Back to Menu");
        menu.setActionCommand("Menu");
        menu.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
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
        model.LoadSave ls = new model.LoadSave(account);

        if (e.getActionCommand().equals("Save")) {
            try {
                ls.save();

            } catch (IOException exp) {
                exp.printStackTrace();
            }
            new MenuOptions(account);

        }

        if (e.getActionCommand().equals("Load")) {
            try {
                ls.load();
                new MenuOptions(account);
            } catch (IOException exp) {
                exp.printStackTrace();
            } catch (InvalidAccount exp) {
                new LoadSave(account, 2);
            }


        }

        else if (e.getActionCommand().equals("Menu")) {
            new MenuOptions(account);
        }




    }
}

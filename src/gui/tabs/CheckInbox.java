package gui.tabs;

import exceptions.InvalidAccount;
import gui.MenuOptions;
import model.Account;
import model.Inbox;
import model.Movie;
import model.RegularMovie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Menu.findFriend;

public class CheckInbox extends JFrame implements ActionListener {
    private Account account;
    private JTextField friendName;
    private JTextField movieName;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public CheckInbox(Account account, int index) {
        super("Movie Tracker");
        this.account = account;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new GridBagLayout());


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);


        if (index == 1) {
            sendRecommendation();
        }

        if (index == 2) {
            recommendMore();
        }

        if (index == 3) {
            checkInbox();

        }

        if (index == 4) {
            showInbox();
        }

        else if (index == 5) {
            differentAccount();
        }

    }

    public void sendRecommendation() {

        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel question1 = new JLabel("Who would you like to recommend a movie to?");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(question1, c);

        friendName = new JTextField(10);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        add(friendName, c);

        JLabel question2 = new JLabel("Which movie would you like to recommend?");
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        add(question2, c);

        movieName = new JTextField(10);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        add(movieName, c);

        JButton send = new JButton("Send Recommendation");
        send.setActionCommand("Send");
        send.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 0;      //make this component tall
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        add(send, c);


    }

    public void recommendMore() {
        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel confirmation = new JLabel("Your recommendation was successfully sent!");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(confirmation, c);


        JLabel question = new JLabel("Would you like to recommend another movie?");
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        add(question, c);

        JButton more = new JButton("Yes");
        more.setActionCommand("Recommend more");
        more.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(more, c);

        JButton noMore = new JButton("No");
        noMore.setActionCommand("Go to menu");
        noMore.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        add(noMore, c);

    }

    public void checkInbox() {

        if (account.getMyInbox().getInbox().isEmpty()) {
            emptyInbox();

        }
        else {

            if (RIGHT_TO_LEFT) {
                setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            }

            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            if (shouldFill) {
                //natural height, maximum width
                c.fill = GridBagConstraints.CENTER;
            }

            JLabel num = new JLabel("You have " + numOfMessages(account.getMyInbox()) + " new messages");
            if (shouldWeightX) {
                c.weightx = 0.5;
            }
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(20,0,0,0);
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 0;
            add(num, c);

            JLabel question = new JLabel("Would you like to view them?");
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(20,0,0,0);
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;
            add(question, c);

            JButton show = new JButton("Yes");
            show.setActionCommand("Show Inbox");
            show.addActionListener(this);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(20,0,0,0);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 2;
            add(show, c);

            JButton noShow = new JButton("No");
            noShow.setActionCommand("Go to menu");
            noShow.addActionListener(this);
            c.fill = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.insets = new Insets(20,0,0,0);
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 2;
            add(noShow, c);
        }
    }

    public void emptyInbox() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }
        JLabel empty = new JLabel("You have no new messages");
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(empty, c);

        JButton menu = new JButton("Go Back to Menu");
        menu.setActionCommand("Go to menu");
        menu.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        add(menu,c);



    }

    public void showInbox() {
        int row = 0;
        Inbox inbox = account.getMyInbox();

        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        for (Account acc: inbox.getInbox().keySet()) {
            for (Movie m : inbox.getInbox().get(acc)) {

                JLabel recommendation =
                        new JLabel(acc.getUserName() + " recommends " + m.getTitle());
                c.fill = GridBagConstraints.CENTER;
                c.weightx = 0.5;
                c.insets = new Insets(20,0,0,0);
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = row;
                add(recommendation, c);

                JButton addFromInbox = new JButton("Accept");
                addFromInbox.setActionCommand("Accept");
                c.fill = GridBagConstraints.CENTER;
                c.weightx = 0.5;
                c.insets = new Insets(20,0,0,0);
                c.gridwidth = 1;
                c.gridx = 1;
                c.gridy = row;
                add(addFromInbox, c);
                addFromInbox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("Accept")) {
                            RegularMovie regular = new RegularMovie(m.getTitle());
                            account.addMovie(regular, account.getMyMovies());
                            account.addObserver(regular);

                        }
                    }
                });

                JButton noAddFromInbox = new JButton("Reject");
                noAddFromInbox.setActionCommand("Reject");
                c.fill = GridBagConstraints.CENTER;
                c.weightx = 0.5;
                c.insets = new Insets(20,0,0,0);
                c.gridwidth = 1;
                c.gridx = 2;
                c.gridy = row;
                add(noAddFromInbox, c);
                noAddFromInbox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("Reject")) {

                        }
                    }
                });

                row++;
            }

        }

        JButton done = new JButton("Done");
        done.setActionCommand("Go to menu");
        done.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = row;
        add(done,c);
        account.getMyInbox().resetInbox();

    }

    public int numOfMessages(Inbox inbox) {
        int num = 0;
        for (Account acc: inbox.getInbox().keySet()) {
            num += inbox.getInbox().get(acc).size();
        }
        return num;

    }

    public void differentAccount() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.CENTER;
        }

        JLabel num = new JLabel("This user does not have an account in our system");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        add(num, c);


        JButton show = new JButton("Recommend a Movie to a Different User");
        show.setActionCommand("Recommend more");
        show.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(show, c);

        JButton noShow = new JButton("Go Back to Menu");
        noShow.setActionCommand("Go to menu");
        noShow.addActionListener(this);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        add(noShow, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Send")) {
            Account friend = new Account(friendName.getText());
            try {
                friend = findFriend(friend.getUserName());
                Movie movie = new RegularMovie(movieName.getText());
                friend.getMyInbox().sendRecommendation(account, movie);

                dispose();
                new CheckInbox(account, 2);
            } catch (InvalidAccount invalidAccount) {
                dispose();
                new CheckInbox(account, 5);
            }



        }

        if (e.getActionCommand().equals("Recommend more")) {
            dispose();
            new CheckInbox(account, 1);
        }

        if (e.getActionCommand().equals("Go to menu")) {
            dispose();
            new MenuOptions(account);
        }

        if (e.getActionCommand().equals("Show Inbox")) {
            dispose();
            new CheckInbox(account, 4);

        }


    }
}

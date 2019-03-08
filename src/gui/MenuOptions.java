package gui;

import gui.tabs.*;
import model.Account;
import ui.ReadWebPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuOptions extends JFrame implements ActionListener {
    private Account account;

    public MenuOptions(Account account) {
        super("Movie Tracker");
        this.account = account;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new GridLayout(0, 1));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        chooseOption();

    }

    public Account getAccount() {
        return account;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();

        if (e.getActionCommand().equals("1")) {
            new addMovie(account);
        }
        if (e.getActionCommand().equals("2")) {
            new RemoveMovie(account, 1);

        }
        if (e.getActionCommand().equals("3")) {
            new ShowMovies(account);
        }

        if (e.getActionCommand().equals("4")) {
            new LoadSave(account, 1);
        }

        if (e.getActionCommand().equals("5")) {
            new CheckInbox(account, 1);
        }

        if (e.getActionCommand().equals("6")) {
            new CheckInbox(account, 3);
        }

        if (e.getActionCommand().equals("7")) {
            new ReadWebPage(account, 1, "");
        }

        if (e.getActionCommand().equals("8")) {
            new LogIn();
        }


    }

    public void chooseOption() {

        JButton btn1 = new JButton("Add Movie");
        btn1.setActionCommand("1");
        btn1.addActionListener(this); //sets "this" class as an action listener for btn.
       // btn1.add(getIcon("http://www.iconarchive.com/download/i80209/custom-icon-design/flatastic-1/add-1.ico"));

        JButton btn2 = new JButton("Cross Movie Off");
        btn2.setActionCommand("2");
        btn2.addActionListener(this);

        JButton btn3 = new JButton("Show My Movies");
        btn3.setActionCommand("3");
        btn3.addActionListener(this);

        JButton btn4 = new JButton("Save/Load Movies");
        btn4.setActionCommand("4");
        btn4.addActionListener(this);

        JButton btn5 = new JButton("Recommend a Movie");
        btn5.setActionCommand("5");
        btn5.addActionListener(this);

        JButton btn6 = new JButton("Check Inbox");
        btn6.setActionCommand("6");
        btn6.addActionListener(this);

        JButton btn7 = new JButton("Get Movie Information");
        btn7.setActionCommand("7");
        btn7.addActionListener(this);

        JButton btn8 = new JButton("Log Out");
        btn8.setActionCommand("8");
        btn8.addActionListener(this);

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
    }

    public JLabel getIcon(String site) {
        String path = site;
        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedImage image = null;

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image newImage = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(newImage));
        return imageLabel;

    }
}



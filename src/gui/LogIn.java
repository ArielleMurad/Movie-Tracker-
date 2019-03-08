package gui;

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

public class LogIn extends JFrame implements ActionListener
{
    private JLabel label;
    private JTextField field;

    public LogIn() {
        super("Movie Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 350));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(128,128,128));
        JButton btn = new JButton("LogIn");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); //sets "this" class as an action listener for btn.

        String path = "http://chesterplayhouse.ca/wp-content/uploads/2018/02/movie_reel.jpg";
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
        Image newImage = image.getScaledInstance(350, 225, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(newImage));
      

        JLabel title = new JLabel("Welcome to Movie Tracker!");
        title.setFont(new Font("Apple Chancery", Font.BOLD, 30));
        add(title);

        label = new JLabel("Username:");
        field = new JTextField(5);

        add(label);
        add(field);
        add(btn);
        add(imageLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }



    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("myButton"))
        {
            dispose();
            ui.Menu.checkAccounts(field.getText());

        }
    }

}

/*
~~~   General  Information   ~~~

Name            : Wan Nashrul Haqeem Bin Wan Kamal
Student ID      : 1191102618
Faculty         : Faculty of Computer Science and Informatics
Course          : Bahelors Degree of Computer Science (Hons.)
Specialisation  : Cybersecurity

~~~  Assignment Information  ~~~

Design Pattern  : Builder
Restaurant Name : O'Leary's Shake Shack
*/

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;
import java.awt.Dimension;

public class StartProg extends JFrame implements ActionListener {
    JFrame menuFrame;
    JButton startMenuButton;
    JButton exitButton;

    ArrayList<MenuItem> cartMenuItems = new ArrayList<MenuItem>();
    ArrayList<BundleItem> cartBundleItems = new ArrayList<BundleItem>();
    
    public StartProg() {
        menuFrame = new JFrame();
        menuFrame.setTitle("O'Leary's Shake Shack Menu");

        Container container = menuFrame.getContentPane();
        BoxLayout boxLayout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(boxLayout);

        JLabel logoLabel        = ConstructGui.createLabel("", "Assets/OLearyRestaurantLogo.jpg");
        startMenuButton = ConstructGui.createButton("Order Now!", 200, 50);
        exitButton = ConstructGui.createButton("Exit", 200, 50);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startMenuButton.addActionListener(this);
        exitButton.addActionListener(this);

        container.add(logoLabel);
        container.add(startMenuButton);
        container.add(Box.createRigidArea(new Dimension(0,20)));
        container.add(exitButton);
        container.setBackground(Color.WHITE);
        
        menuFrame.setVisible(true);
        menuFrame.setResizable(false);
        menuFrame.setPreferredSize(new Dimension(800,800));
        menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuFrame.pack();
    }
    
    public static void main(String[] args) {
        new StartProg();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startMenuButton) {
            menuFrame.setVisible(false);
            new CategoryMenu(cartMenuItems, cartBundleItems);
        }
        else if(e.getSource() == exitButton) {
            System.exit(0);
        }
        
    }
}

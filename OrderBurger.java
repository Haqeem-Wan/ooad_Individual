import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;
import java.awt.Dimension;

public class OrderBurger extends JFrame implements ActionListener {
    JFrame burgerFrame;
    ArrayList<MenuItem> menuItemList;
    JButton addToCartButton;
    JButton backButton;
    JComboBox<String> burgerList;
    JComboBox<String> quantityList;

    ArrayList<MenuItem> cartMenuItemsTemp = new ArrayList<MenuItem>();
    ArrayList<BundleItem> cartBundleItemsTemp = new ArrayList<BundleItem>();

    GridBagConstraints gbc = new GridBagConstraints();

    private int rowAmount(ArrayList<MenuItem> menuItemList) {
        int rows = 0;
        int categoryCount = 0;

        for(int i = 0; i < menuItemList.size(); i++) {
            if(menuItemList.get(i).getFoodType().equals("Burger")) {
                categoryCount++;
            }
        }

        rows = categoryCount / 3;

        if(rows % 3 == 0) {
            return rows;    
        }
        else {
            return rows + 1;
        }
    }

    OrderBurger(ArrayList<MenuItem> cartMenuItems, ArrayList<BundleItem> cartBundleItems) {
        burgerFrame = new JFrame();
        burgerFrame.setTitle("O'Leary's Shake Shack Menu");
        burgerFrame.setBackground(Color.WHITE);
        burgerFrame.setLayout(new GridLayout(2,0));
        
        menuItemList = BuildMenu.buildMenuItemList();

        cartMenuItemsTemp.clear();
        cartBundleItemsTemp.clear();
        cartMenuItems.addAll(cartMenuItemsTemp);
        cartBundleItems.addAll(cartBundleItemsTemp);

        JPanel burgerListPanel = new JPanel();
        burgerListPanel.setBackground(Color.WHITE);
        burgerListPanel.setLayout(new GridLayout(rowAmount(menuItemList),3, 0,0));

        ArrayList<String> burgerNameArrayList = new ArrayList<String>();

        for(int i = 0; i < menuItemList.size(); i++) {
            if(menuItemList.get(i).getFoodType().equals("Burger")) {
                JPanel newPanel = ConstructGui.createPanel(menuItemList.get(i).getName(), menuItemList.get(i).getPrice(), 100, 200);
                burgerListPanel.add(newPanel);

                burgerNameArrayList.add(menuItemList.get(i).getName());
            }
        }

        JPanel burgerOrderPanel = new JPanel();
        burgerOrderPanel.setBackground(Color.WHITE);
        burgerOrderPanel.setLayout(new GridBagLayout());

        String[] burgerNameArray = burgerNameArrayList.toArray(new String[burgerNameArrayList.size()]);
        burgerList = new JComboBox<String>(burgerNameArray);

        String[] quantityArray = {"1","2","3","4","5","6","7","8","9","10","11","12","13", "14","15","16","17","18","19", "20"};
        quantityList = new JComboBox<String>(quantityArray);

        addToCartButton = ConstructGui.createButton("Add To Cart", 100, 50);
        backButton = ConstructGui.createButton("Back To Menu",100,50);

        addToCartButton.addActionListener(this);
        backButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        burgerOrderPanel.add(burgerList, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        burgerOrderPanel.add(quantityList, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        burgerOrderPanel.add(addToCartButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        burgerOrderPanel.add(backButton, gbc);

        burgerFrame.add(burgerListPanel, BorderLayout.CENTER);
        burgerFrame.add(burgerOrderPanel, BorderLayout.SOUTH);
        burgerFrame.setVisible(true);
        burgerFrame.setResizable(false);
        burgerFrame.setPreferredSize(new Dimension(800,800));
        burgerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        burgerFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addToCartButton) {
            String chosenItem = burgerList.getSelectedItem().toString();
            int chosenQuantity = Integer.parseInt(quantityList.getSelectedItem().toString());

            for(int i = 0; i < menuItemList.size(); i++) {
                if(chosenItem.equals(menuItemList.get(i).getName())) {
                    int currentQuantity = menuItemList.get(i).getQuantity();

                    if(cartMenuItemsTemp.contains(menuItemList.get(i))) {
                        for(int j = 0; j < cartMenuItemsTemp.size(); j++) {
                            if(cartMenuItemsTemp.get(j).equals(menuItemList.get(i))) {
                                cartMenuItemsTemp.get(j).setQuantity(currentQuantity + chosenQuantity);
                            }
                        }
                    }
                    else {
                        menuItemList.get(i).setQuantity(currentQuantity + chosenQuantity);
                        cartMenuItemsTemp.add(menuItemList.get(i));
                    }
                    menuItemList.get(i).setQuantity(currentQuantity + chosenQuantity);
                    cartMenuItemsTemp.add(menuItemList.get(i));
                    break;
                }
            }
        }
        else if(e.getSource() == backButton) {
            burgerFrame.dispose();
            new CategoryMenu(cartMenuItemsTemp, cartBundleItemsTemp);
        }
    }
}
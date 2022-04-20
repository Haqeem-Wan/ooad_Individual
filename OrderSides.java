import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OrderSides extends JFrame implements OrderItem, ActionListener {
    JFrame sidesFrame;
    ArrayList<MenuItem> menuItemList;
    JButton addToCartButton;
    JButton backButton;
    JComboBox<String> sidesList;
    JComboBox<String> quantityList;

    ArrayList<MenuItem> cartMenuItemsTemp = new ArrayList<MenuItem>();
    ArrayList<BundleItem> cartBundleItemsTemp = new ArrayList<BundleItem>();

    GridBagConstraints gbc = new GridBagConstraints();

    public int rowAmount(ArrayList<MenuItem> menuItemList) {
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

    OrderSides(ArrayList<MenuItem> cartMenuItems, ArrayList<BundleItem> cartBundleItems) {
        sidesFrame = new JFrame();
        sidesFrame.setTitle("O'Leary's sides Shack Menu");
        sidesFrame.setBackground(Color.WHITE);
        sidesFrame.setLayout(new GridLayout(2,0));
        
        menuItemList = BuildMenu.buildMenuItemList();

        cartMenuItemsTemp.clear();
        cartBundleItemsTemp.clear();
        cartMenuItemsTemp.addAll(cartMenuItems);
        cartBundleItemsTemp.addAll(cartBundleItems);

        JPanel sidesListPanel = new JPanel();
        sidesListPanel.setBackground(Color.WHITE);
        sidesListPanel.setLayout(new GridLayout(rowAmount(menuItemList),3, 0,0));

        ArrayList<String> sidesNameArrayList = new ArrayList<String>();

        for(int i = 0; i < menuItemList.size(); i++) {
            if(menuItemList.get(i).getFoodType().equals("Sides")) {
                JPanel newPanel = ConstructGui.createPanel(menuItemList.get(i).getName(), menuItemList.get(i).getPrice(), 100, 200);
                sidesListPanel.add(newPanel);

                sidesNameArrayList.add(menuItemList.get(i).getName());
            }
        }

        JPanel sidesOrderPanel = new JPanel();
        sidesOrderPanel.setBackground(Color.WHITE);
        sidesOrderPanel.setLayout(new GridBagLayout());

        String[] sidesNameArray = sidesNameArrayList.toArray(new String[sidesNameArrayList.size()]);
        sidesList = new JComboBox<String>(sidesNameArray);

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
        sidesOrderPanel.add(sidesList, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        sidesOrderPanel.add(quantityList, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        sidesOrderPanel.add(addToCartButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        sidesOrderPanel.add(backButton, gbc);

        sidesFrame.add(sidesListPanel, BorderLayout.CENTER);
        sidesFrame.add(sidesOrderPanel, BorderLayout.SOUTH);
        sidesFrame.setVisible(true);
        sidesFrame.setResizable(false);
        sidesFrame.setPreferredSize(new Dimension(800,800));
        sidesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sidesFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addToCartButton) {
            String chosenItem = sidesList.getSelectedItem().toString();
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
                    break;
                }
            }
        }
        else if(e.getSource() == backButton) {
            sidesFrame.dispose();
            new CategoryMenu(cartMenuItemsTemp, cartBundleItemsTemp);
        }
    }
}

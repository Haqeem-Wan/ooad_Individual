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

public class OrderBundles extends JFrame implements OrderItem, ActionListener {
    JFrame bundlesFrame;
    ArrayList<MenuItem> menuItemList;
    ArrayList<BundleItem> bundleItemList;
    JButton addToCartButton;
    JButton backButton;
    JComboBox<String> bundlesList;
    JComboBox<String> quantityList;

    ArrayList<MenuItem> cartMenuItemsTemp = new ArrayList<MenuItem>();
    ArrayList<BundleItem> cartBundleItemsTemp = new ArrayList<BundleItem>();

    GridBagConstraints gbc = new GridBagConstraints();

    public int rowAmount(ArrayList<MenuItem> menuItemList) {
        int rows = 0;
        int categoryCount = 0;

        for(int i = 0; i < menuItemList.size(); i++) {
            if(menuItemList.get(i).getFoodType().equals("Bottles")) {
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

    OrderBundles(ArrayList<MenuItem> cartMenuItems, ArrayList<BundleItem> cartBundleItems) {
        bundlesFrame = new JFrame();
        bundlesFrame.setTitle("O'Leary's bundles Shack Menu");
        bundlesFrame.setBackground(Color.WHITE);
        bundlesFrame.setLayout(new GridLayout(2,0));
        
        menuItemList = BuildMenu.buildMenuItemList();
        bundleItemList = BuildMenu.buildBundleItemList(menuItemList);

        cartMenuItemsTemp.clear();
        cartBundleItemsTemp.clear();
        cartMenuItemsTemp.addAll(cartMenuItems);
        cartBundleItemsTemp.addAll(cartBundleItems);

        JPanel bundlesListPanel = new JPanel();
        bundlesListPanel.setBackground(Color.WHITE);
        bundlesListPanel.setLayout(new GridLayout(rowAmount(menuItemList),3, 0,0));

        ArrayList<String> bundlesNameArrayList = new ArrayList<String>();

        for(int i = 0; i < menuItemList.size(); i++) {
            if(menuItemList.get(i).getFoodType().equals("Bottled")) {
                JPanel newPanel = ConstructGui.createPanel(menuItemList.get(i).getName(), menuItemList.get(i).getPrice(), 100, 200);
                bundlesListPanel.add(newPanel);

                bundlesNameArrayList.add(menuItemList.get(i).getName());
            }
        }

        for(int i = 0; i < bundleItemList.size(); i++) {
            JPanel newPanel = ConstructGui.createPanel(bundleItemList.get(i).getName(), bundleItemList.get(i).getPrice(), 100, 200);
            bundlesListPanel.add(newPanel);

            bundlesNameArrayList.add(bundleItemList.get(i).getName());
        }

        JPanel bundlesOrderPanel = new JPanel();
        bundlesOrderPanel.setBackground(Color.WHITE);
        bundlesOrderPanel.setLayout(new GridBagLayout());

        String[] bundlesNameArray = bundlesNameArrayList.toArray(new String[bundlesNameArrayList.size()]);
        bundlesList = new JComboBox<String>(bundlesNameArray);

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
        bundlesOrderPanel.add(bundlesList, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        bundlesOrderPanel.add(quantityList, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bundlesOrderPanel.add(addToCartButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bundlesOrderPanel.add(backButton, gbc);

        bundlesFrame.add(bundlesListPanel, BorderLayout.CENTER);
        bundlesFrame.add(bundlesOrderPanel, BorderLayout.SOUTH);
        bundlesFrame.setVisible(true);
        bundlesFrame.setResizable(false);
        bundlesFrame.setPreferredSize(new Dimension(800,800));
        bundlesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bundlesFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addToCartButton) {
            String chosenItem = bundlesList.getSelectedItem().toString();
            int chosenQuantity = Integer.parseInt(quantityList.getSelectedItem().toString());

            boolean bundleToggle = false;

            for(int i = 0; i < bundleItemList.size(); i++) {
                if(chosenItem.equals(bundleItemList.get(i).getName())) {
                    bundleToggle = true;
                    break;
                }
                else {
                    bundleToggle = false;
                }
            }

            if(!bundleToggle) {

                for(int i = 0; i < menuItemList.size(); i++) {
                    int currentQuantity = menuItemList.get(i).getQuantity();
                    if(chosenItem.equals(menuItemList.get(i).getName()) && cartMenuItemsTemp.contains(menuItemList.get(i))) {

                        for(int j = 0; j < cartMenuItemsTemp.size(); j++) {
                            if(cartMenuItemsTemp.get(j).equals(menuItemList.get(i))) {
                                cartMenuItemsTemp.get(j).setQuantity(currentQuantity + chosenQuantity);
                            }
                        }
                    }

                    else if(chosenItem.equals(menuItemList.get(i).getName())) {
                        menuItemList.get(i).setQuantity(currentQuantity + chosenQuantity);
                        cartMenuItemsTemp.add(menuItemList.get(i));
                    }
                    menuItemList.get(i).setQuantity(currentQuantity + chosenQuantity);
                }
            }

            else if(bundleToggle) {
                for(int i = 0; i < bundleItemList.size(); i++) {
                    int currentQuantity = bundleItemList.get(i).getQuantity();
                    if(chosenItem.equals(bundleItemList.get(i).getName()) && cartBundleItemsTemp.contains(bundleItemList.get(i))) {

                        for(int j = 0; j < cartBundleItemsTemp.size(); j++) {
                            if(cartBundleItemsTemp.get(j).equals(bundleItemList.get(i))) {
                                cartBundleItemsTemp.get(j).setQuantity(currentQuantity + chosenQuantity);
                            }
                        }
                    }

                    else if(chosenItem.equals(bundleItemList.get(i).getName())) {
                        bundleItemList.get(i).setQuantity(currentQuantity + chosenQuantity);
                        cartBundleItemsTemp.add(bundleItemList.get(i));
                    }
                    bundleItemList.get(i).setQuantity(currentQuantity + chosenQuantity);
                }
            }
        }
        else if(e.getSource() == backButton) {
            bundlesFrame.dispose();
            new CategoryMenu(cartMenuItemsTemp, cartBundleItemsTemp);
        }
    }
}

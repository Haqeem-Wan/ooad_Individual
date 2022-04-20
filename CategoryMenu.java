import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CategoryMenu extends JFrame implements ActionListener {
    JFrame categoryFrame;
    GridBagConstraints gbc = new GridBagConstraints();

    JButton burgerButton, shakeButton, sidesButton, bundlesButton, cartButton, cancelButton;

    ArrayList<MenuItem> cartMenuItemsTemp = new ArrayList<MenuItem>();
    ArrayList<BundleItem> cartBundleItemsTemp = new ArrayList<BundleItem>();

    CategoryMenu(ArrayList<MenuItem> cartMenuItems, ArrayList<BundleItem> cartBundleItems) {
        categoryFrame = new JFrame();
        categoryFrame.setTitle("O'Leary's Shake Shack Menu");
        categoryFrame.setLayout(new GridBagLayout());

        cartMenuItemsTemp.clear();
        cartBundleItemsTemp.clear();
        cartMenuItems.addAll(cartMenuItemsTemp);
        cartBundleItems.addAll(cartBundleItemsTemp);

        burgerButton = ConstructGui.createButton("Burgers", 400, 280);
        shakeButton = ConstructGui.createButton("Shakes", 400, 280);
        sidesButton = ConstructGui.createButton("Sides", 400, 280);
        bundlesButton = ConstructGui.createButton("Bundles and More", 400, 280);
        cartButton = ConstructGui.createButton("Cart", 400, 100);
        cancelButton = ConstructGui.createButton("Cancel Order", 400, 100);
        
        burgerButton.addActionListener(this);
        shakeButton.addActionListener(this);
        sidesButton.addActionListener(this);
        bundlesButton.addActionListener(this);
        cartButton.addActionListener(this);
        cancelButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        categoryFrame.add(burgerButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        categoryFrame.add(shakeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        categoryFrame.add(sidesButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.gridheight = 5;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        categoryFrame.add(bundlesButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridheight = 1;
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        categoryFrame.add(cartButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridheight = 1;
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        categoryFrame.add(cancelButton, gbc);

        categoryFrame.setVisible(true);
        categoryFrame.setResizable(false);
        categoryFrame.setPreferredSize(new Dimension(800,800));
        categoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        categoryFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == burgerButton) {
            categoryFrame.dispose();
            new OrderBurger(cartMenuItemsTemp, cartBundleItemsTemp);
        }

        else if(e.getSource() == shakeButton) {
            categoryFrame.dispose();
            new OrderShake(cartMenuItemsTemp, cartBundleItemsTemp);
        }

        else if(e.getSource() == sidesButton) {
            categoryFrame.dispose();
            new OrderSides(cartMenuItemsTemp, cartBundleItemsTemp);
        }

        else if(e.getSource() == bundlesButton) {
            categoryFrame.dispose();
            new OrderBundles(cartMenuItemsTemp, cartBundleItemsTemp);
        }

        else if(e.getSource() == cartButton) {
            categoryFrame.dispose();
        }

        else if(e.getSource() == cancelButton) {
            categoryFrame.dispose();
            new StartProg();
        }
        
    }
}

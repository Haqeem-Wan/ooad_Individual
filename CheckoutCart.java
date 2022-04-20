import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CheckoutCart extends JFrame implements ActionListener{
    JFrame checkoutFrame;
    double totalPrice;

    JPanel discountPanel;
    JPanel receiptPanel;
    JPanel pricePanel;

    JTextField discountField;
    JButton submitDiscountButton;
    JButton orderMoreButton;
    JButton completeOrderButton;

    ArrayList<DiscountVoucher> discountVouchers = BuildMenu.buildDiscountVoucherList();
    ArrayList<MenuItem> cartMenuItemsTemp = new ArrayList<MenuItem>();
    ArrayList<BundleItem> cartBundleItemsTemp = new ArrayList<BundleItem>();

    JLabel priceBeforeDiscountLabel     = ConstructGui.createLabel("");
    JLabel discountCutLabel             = ConstructGui.createLabel("Discount Percent     : 0.00%");
    JLabel discountAmountLabel          = ConstructGui.createLabel("Discount Amount      : RM0.00");
    JLabel priceAfterDiscountLabel      = ConstructGui.createLabel("Price After Discount : RM0.00");

    GridBagConstraints gbc = new GridBagConstraints();

    CheckoutCart(ArrayList<MenuItem> cartMenuItems, ArrayList<BundleItem> cartBundleItems) {
        checkoutFrame = new JFrame();
        checkoutFrame.setTitle("Checkout");
        checkoutFrame.setLayout(new GridBagLayout());

        cartMenuItemsTemp.clear();
        cartBundleItemsTemp.clear();
        cartMenuItemsTemp.addAll(cartMenuItems);
        cartBundleItemsTemp.addAll(cartBundleItems);

        discountPanel = new JPanel();
        receiptPanel = new JPanel();
        pricePanel = new JPanel();

        discountField = new JTextField("Insert Discount Code");
        discountField.setFont(new Font("Roboto", Font.BOLD, 20));
        submitDiscountButton = ConstructGui.createButton("Submit",200,50);
        orderMoreButton = ConstructGui.createButton("Order More", 200, 50);
        completeOrderButton = ConstructGui.createButton("Complete Order", 200, 50);

        submitDiscountButton.addActionListener(this);
        orderMoreButton.addActionListener(this);
        completeOrderButton.addActionListener(this);

        discountPanel.add(discountField);
        discountPanel.add(submitDiscountButton);

        String col[] = {"Name", "Price (x1)", "Quantity", "Total Price"};

        DefaultTableModel model = new DefaultTableModel(col,0);
        JTable table = new JTable(model);

        totalPrice = 0;

        for(int i = 0; i < cartMenuItems.size(); i++) {
            double finalPrice = cartMenuItems.get(i).getPrice() * cartMenuItems.get(i).getQuantity();

            String name = cartMenuItems.get(i).getName();
            String priceForOne = String.format("%.2f", cartMenuItems.get(i).getPriceAfterDiscount());
            String quantity = Integer.toString(cartMenuItems.get(i).getQuantity());
            String priceForAll = String.format("%.2f", finalPrice);
            totalPrice += finalPrice;

            String[] data = {name, priceForOne, quantity, priceForAll};
            model.addRow(data);
        }

        for(int i = 0; i < cartBundleItems.size(); i++) {
            double finalPrice = cartBundleItems.get(i).getPrice() * cartBundleItems.get(i).getQuantity();

            String name = cartBundleItems.get(i).getName();
            String priceForOne = String.format("%.2f", cartBundleItems.get(i).getPrice());
            String quantity = Integer.toString(cartBundleItems.get(i).getQuantity());
            String priceForAll = String.format("%.2f", finalPrice);
            totalPrice += finalPrice;

            String[] data = {name, priceForOne, quantity, priceForAll};
            model.addRow(data);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        priceBeforeDiscountLabel.setText("Price Before Discount : RM" + String.format("%.2f", totalPrice));

        pricePanel.setLayout(new GridLayout(4,0));

        pricePanel.add(priceBeforeDiscountLabel);
        pricePanel.add(discountCutLabel);
        pricePanel.add(discountAmountLabel);
        pricePanel.add(priceAfterDiscountLabel);
        pricePanel.add(orderMoreButton);
        pricePanel.add(completeOrderButton);

        receiptPanel.add(scrollPane);

        gbc.gridx = 0;
        gbc.gridy = 0;
        checkoutFrame.add(discountPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        checkoutFrame.add(receiptPanel, gbc);

        gbc. gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        checkoutFrame.add(pricePanel, gbc);

        checkoutFrame.setVisible(true);
        checkoutFrame.setResizable(false);
        checkoutFrame.setPreferredSize(new Dimension(800,800));
        checkoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkoutFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitDiscountButton) {
            String userInput = discountField.getText();
            double discountCut = 0;
            double discountAmount = 0;
            double priceAfterDiscount = 0;

            for(int i = 0; i < discountVouchers.size(); i++) {
                if(userInput.equals(discountVouchers.get(i).getVoucherCode())) {
                    discountCut = discountVouchers.get(i).getDiscount();
                    discountAmount = totalPrice * discountCut / 100;
                    priceAfterDiscount = totalPrice - discountAmount;
                    break;
                };
            }
            discountCutLabel.setText        ("Discount Percent     : " + String.format("%.2f", discountCut) + "%");
            discountAmountLabel.setText     ("Discount Amount      : RM" + String.format("%.2f", discountAmount));
            priceAfterDiscountLabel.setText ("Price After Discount : RM" + String.format("%.2f", priceAfterDiscount));
        }

        else if(e.getSource() == orderMoreButton) {
            checkoutFrame.dispose();
            new CategoryMenu(cartMenuItemsTemp, cartBundleItemsTemp);
        }

        else if(e.getSource() == completeOrderButton) {
            System.exit(0);
        }
    }
}
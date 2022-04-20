import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class ConstructGui {
    public static JButton createButton(String text, int xSize, int ySize) {
        JButton button = new JButton(text);
        button.setMinimumSize(new Dimension(xSize, ySize));
        button.setMaximumSize(new Dimension(xSize, ySize));
        button.setPreferredSize(new Dimension(xSize, ySize));
        button.setFont(new Font("Roboto", Font.BOLD, 20));
        button.setFocusPainted(false);
        
        return button;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setText(text);
        label.setFont(new Font("Roboto", Font.BOLD, 20));

        return label;
    }

    public static JLabel createLabel(String text, String imageFilePath) {
        JLabel label = new JLabel();
        label.setText(text);

        ImageIcon imageIcon = new ImageIcon(imageFilePath);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(600,600,java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        label.setIcon(imageIcon);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setFont(new Font("Roboto", Font.BOLD, 20));
        label.setIconTextGap(10);

        return label;
    }

    public static JPanel createPanel(String nameText, double priceText, int xSize, int ySize) {
        JPanel panel = new JPanel();

        JLabel priceLabel = createLabel("Price : RM" + String.format("%.2f", priceText));

        panel.setSize(new Dimension(xSize, ySize));
        panel.setLayout(new GridLayout(2,1,0, -10));
        panel.setBackground(Color.WHITE);
        
        TitledBorder title;
        title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), nameText);
        title.setTitleJustification(TitledBorder.CENTER);
        
        panel.setBorder(title);
        panel.add(priceLabel);

        return panel;
    }
}

package gui;

import java.util.Collections;

import javax.swing.*;

public class ShopGUI {
    public ShopGUI() {
        JFrame frame = new JFrame("Monku Games");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));
        frame.setVisible(true);
    }
}

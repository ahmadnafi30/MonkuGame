package gui;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Collections;

public class HomeBase {
    public HomeBase(){
        JFrame frame = new JFrame("Monku Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/IMG-20231127-WA0029 (1).png");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBackground(new Color(135, 206, 235));

        int buttonWidth = 150;
        int buttonHeight = 40;
        int spacing = 20;

        int totalButtonWidth = buttonWidth * 2 + spacing;
        int xStart = (frame.getWidth() - totalButtonWidth) / 2;
        int yPosition = frame.getHeight() - buttonHeight - 200;

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(xStart, yPosition, buttonWidth, buttonHeight);
        newGameButton.setFont(new Font("Arial", Font.BOLD, 18));
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setForeground(Color.BLACK);
        panel.add(newGameButton);

        JButton loadGameButton = new JButton("LOAD GAME");
        loadGameButton.setBounds(xStart + buttonWidth + spacing, yPosition, buttonWidth, buttonHeight);
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 18));
        loadGameButton.setBackground(Color.WHITE);
        loadGameButton.setForeground(Color.BLACK);
        panel.add(loadGameButton);

        JButton aboutUsButton = new JButton("ABOUT US");
        int xAboutUs = frame.getWidth() - 150;
        int yAboutUs = 20;
        aboutUsButton.setBounds(xAboutUs, yAboutUs, 100, 25);
        aboutUsButton.setFont(new Font("Arial", Font.BOLD, 10));
        aboutUsButton.setBackground(Color.WHITE);
        aboutUsButton.setForeground(Color.BLACK);
        panel.add(aboutUsButton);

        addAboutUsActionListener(aboutUsButton, frame);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void addAboutUsActionListener(JButton aboutUsButton, JFrame frame) {
        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aboutMessage = """
                        Silahkan memilih 
                        """;
                JOptionPane.showMessageDialog(frame, aboutMessage, "About Us", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }



}

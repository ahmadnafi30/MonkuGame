package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MonkuGamesGUI {

    public MonkuGamesGUI() {
        JFrame frame = new JFrame("Monku Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1450, 850);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(135, 206, 235));

        String imagePath = "asset/Blue Green Yellow Video.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        if (originalIcon.getIconWidth() == -1) {
            System.out.println("Gambar tidak ditemukan: " + imagePath);
        } else {
            Image originalImage = originalIcon.getImage();
            int width = 1450;
            int height = 830;
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel background = new JLabel(resizedIcon);
            background.setBounds(0, 0, width, height);
            panel.add(background);

            int buttonWidth = 200;
            int buttonHeight = 50;
            int spacing = 20;

            int totalButtonWidth = buttonWidth * 2 + spacing;
            int xStart = (frame.getWidth() - totalButtonWidth) / 2;
            int yPosition = height - buttonHeight - 200;

            JButton newGameButton = new JButton("NEW GAMES");
            newGameButton.setBounds(xStart, yPosition, buttonWidth, buttonHeight);
            newGameButton.setFont(new Font("Arial", Font.BOLD, 18));
            newGameButton.setBackground(Color.WHITE);
            newGameButton.setForeground(Color.BLACK);
            background.add(newGameButton);

            JButton loadGameButton = new JButton("LOAD GAMES");
            loadGameButton.setBounds(xStart + buttonWidth + spacing, yPosition, buttonWidth, buttonHeight);
            loadGameButton.setFont(new Font("Arial", Font.BOLD, 18));
            loadGameButton.setBackground(Color.WHITE);
            loadGameButton.setForeground(Color.BLACK);
            background.add(loadGameButton);

            JButton aboutus = new JButton("ABOUT US");
            int xAboutUs = frame.getWidth() - 150;
            int yAboutUs = 20;
            aboutus.setBounds(xAboutUs, yAboutUs, 100, 25);
            aboutus.setFont(new Font("Arial", Font.BOLD, 10));
            aboutus.setBackground(Color.WHITE);
            aboutus.setForeground(Color.BLACK);
            background.add(aboutus);

            addAboutUsActionListener(aboutus, frame);
            addButtonHoverEffects(newGameButton);
            addButtonHoverEffects(loadGameButton);
            addButtonHoverEffects(aboutus);

            // Add panel to frame
            frame.setContentPane(panel);
            frame.setVisible(true);
        }
    }

    private void addAboutUsActionListener(JButton aboutus, JFrame frame) {
        aboutus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aboutMessage = """
                        Nama Games : Monku (Monster Kusayang)
                        Nama Pembuat : Ahmad Nafi Mubarok dan Muhammad Raka Fadhillah
                        Prodi : Teknik Informatika Universitas Brawijaya
                        
                        Tujuan dibuat game ini adalah merupakan tugas akhir dari pembelajaran OOP 
                        bersama guru tersayang Bapak Budi bismiilah dapet A
                        """;
                JOptionPane.showMessageDialog(frame, aboutMessage, "About Us", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void addButtonHoverEffects(JButton button) {
        button.addMouseListener(new MouseListener() {
            private Timer hoverTimer;

            @Override
            public void mouseEntered(MouseEvent e) {
                hoverTimer = new Timer(10, event -> {
                    int r = button.getForeground().getRed();
                    int g = button.getForeground().getGreen();
                    int b = button.getForeground().getBlue();

                    if (r > 0 && g > 0 && b > 0) {
                        r -= 10;
                        g -= 10;
                        b -= 10;
                        button.setForeground(new Color(r, g, b));
                    } else {
                        button.setForeground(Color.WHITE);
                        button.setBackground(Color.BLACK);
                        hoverTimer.stop();
                    }
                });
                hoverTimer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoverTimer = new Timer(10, event -> {
                    int r = button.getForeground().getRed();
                    int g = button.getForeground().getGreen();
                    int b = button.getForeground().getBlue();

                    if (r < 255 && g < 255 && b < 255) {
                        r += 10;
                        g += 10;
                        b += 10;
                        button.setForeground(new Color(r, g, b));
                    } else {
                        button.setForeground(Color.BLACK);
                        button.setBackground(Color.WHITE);
                        hoverTimer.stop();
                    }
                });
                hoverTimer.start();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseClicked(MouseEvent e) {}
        });
    }
}

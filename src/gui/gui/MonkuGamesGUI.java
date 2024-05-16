package gui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

public class MonkuGamesGUI {

    public MonkuGamesGUI() {
        JFrame frame = new JFrame("Monku Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/Blue Green Yellow Video.png");
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
        addButtonHoverEffects(newGameButton);
        addButtonHoverEffects(loadGameButton);
        addButtonHoverEffects(aboutUsButton);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void addAboutUsActionListener(JButton aboutUsButton, JFrame frame) {
        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aboutMessage = """
                        Nama Games : Monku (Monster Kusayang)
                        Nama Pembuat : Ahmad Nafi Mubarok dan Muhammad Raka Fadhillah
                        Prodi : Teknik Informatika Universitas Brawijaya
                        
                        Tujuan dibuat game ini adalah merupakan tugas akhir dari pembelajaran OOP 
                        bersama guru tersayang Bapak Budi bismillah dapat A
                        """;
                JOptionPane.showMessageDialog(frame, aboutMessage, "About Us", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void addButtonHoverEffects(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            private Timer hoverTimer;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (hoverTimer != null && hoverTimer.isRunning()) {
                    hoverTimer.stop();
                }
                hoverTimer = new Timer(10, new ActionListener() {
                    int r = button.getForeground().getRed();
                    int g = button.getForeground().getGreen();
                    int b = button.getForeground().getBlue();

                    @Override
                    public void actionPerformed(ActionEvent event) {
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
                    }
                });
                hoverTimer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (hoverTimer != null && hoverTimer.isRunning()) {
                    hoverTimer.stop();
                }
                hoverTimer = new Timer(10, new ActionListener() {
                    int r = button.getForeground().getRed();
                    int g = button.getForeground().getGreen();
                    int b = button.getForeground().getBlue();

                    @Override
                    public void actionPerformed(ActionEvent event) {
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
                    }
                });
                hoverTimer.start();
            }
        });
    }

}

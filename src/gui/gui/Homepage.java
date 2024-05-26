package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import app.Monku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Homepage {
    private String[] imageChar = {
            "asset/Char/acetrainer-gen3jp.png",
            "asset/Char/acetrainer-gen4.png",
            "asset/Char/acetrainer-gen7.png",
            "asset/Char/acetrainerf-gen4.png",
            "asset/Char/acetrainerf-gen7.png",
            "asset/Char/akari.png"
    };

    private String characterSelected = imageChar[0];

    private JPanel panel;

    public Homepage() throws IOException {
        JFrame frame = new JFrame("Monku Games");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));
    
        panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/Monku Games (3).gif");
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
    
        BufferedImage buttonIcon = ImageIO.read(new File("asset/text-box.png"));
        Image scaledButtonImage = buttonIcon.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        BufferedImage buttonPressedIcon = ImageIO.read(new File("asset/toppng.com-text-box-pixel-art-cupcake-601x211.png"));
        Image scaledButtonPressedImage = buttonPressedIcon.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
    
        JButton newGameButton = createStyledButton("NEW GAME", scaledButtonImage, xStart, yPosition - 30, buttonWidth, buttonHeight);
        panel.add(newGameButton);
    
        JButton loadGameButton = createStyledButton("LOAD GAME", scaledButtonImage, xStart + buttonWidth + spacing, yPosition - 30, buttonWidth, buttonHeight);
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
        addPlayGameActionListener(newGameButton, scaledButtonPressedImage, frame);
        addPlayGameActionListenerByLoadGames(loadGameButton, scaledButtonPressedImage, frame);
        addButtonHoverEffects(newGameButton);
        addButtonHoverEffects(loadGameButton);
        addButtonHoverEffects(aboutUsButton);
        addButtonPressedEffect(newGameButton);
        addButtonPressedEffect(loadGameButton);
        addButtonPressedEffect(aboutUsButton);
    
        frame.setContentPane(panel);
        frame.setVisible(true);
        Template.playMusic("asset/Music/Introduction.wav");
        
    }
    

    private JButton createButton(BufferedImage charImage, String imagePath) {
        JButton button = new JButton(new ImageIcon(charImage));
        button.setBounds(0, 0, charImage.getWidth(), charImage.getHeight());
        button.setBackground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setContentAreaFilled(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterSelected = imagePath;
                Monku.player.setImage(characterSelected);
                button.setBackground(Color.GRAY); // Change background color to gray when selected
            }
        });
        return button;
    }

    private JButton createStyledButton(String text, Image icon, int x, int y, int width, int height) {
        JButton button = new JButton(text, new ImageIcon(icon));
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Public Pixel", Font.BOLD, 13));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setForeground(Color.BLACK);
        button.setContentAreaFilled(false);
        return button;
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

    private void addPlayGameActionListener(JButton newGameButton, Image buttonPressed, JFrame frame) {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel charPanel = new JPanel();
                charPanel.setLayout(new GridLayout(0, 1, 10, 10)); 

                for (String s : imageChar) {
                    try {
                        BufferedImage charImage = ImageIO.read(new File(s));
                        JButton button = createButton(charImage, s);
                        addCharButtonHoverEffect(button);
                        charPanel.add(button);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                JScrollPane scrollPane = new JScrollPane(charPanel);
                scrollPane.setBounds(385, 230, 200, 200);

                JButton okButton = new JButton("OK");
                okButton.setBounds((frame.getWidth() - 100) / 2 - 20, 450, 100, 40);
                addButtonPressedEffect(okButton);

                panel.removeAll();
                panel.add(scrollPane);
                panel.add(okButton);
                panel.revalidate();
                panel.repaint();

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Monku.player.getImage() == null) {
                            JOptionPane.showMessageDialog(frame, "Please select a character", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            Template.stopMusic();
                            frame.dispose();
                            new Awalan(1);
                        }
                    }
                });

                handleButtonPress(newGameButton, buttonPressed, frame, () -> {
                    // Any additional actions when new game button is pressed
                });
            }
        });
    }

    private void addPlayGameActionListenerByLoadGames(JButton loadGameButton, Image buttonPressed, JFrame frame) {
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Template.stopMusic();
                Monku.loadGame();
                handleButtonPress(loadGameButton, buttonPressed, frame, () -> new HomeBaseGUI());
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
                            r -= 5;
                            g -= 5;
                            b -= 5;
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
                            r += 5;
                            g += 5;
                            b += 5;
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

    private void addButtonPressedEffect(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color originalBackground = button.getBackground();
                Color originalForeground = button.getForeground();
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        button.setBackground(originalBackground);
                        button.setForeground(originalForeground);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
    }

    private void addCharButtonHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 0, 0, 0)); // Set background to transparent
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (characterSelected != null && characterSelected.equals(button.getActionCommand())) {
                    button.setBackground(Color.GRAY); // Set background to gray if selected
                } else {
                    button.setBackground(Color.WHITE); // Set background to white otherwise
                }
            }
        });
    }

    private void handleButtonPress(JButton button, Image pressedIcon, JFrame frame, Runnable action) {
        Icon originalIcon = button.getIcon();
        button.setIcon(new ImageIcon(pressedIcon));
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                button.setIcon(originalIcon);
                action.run();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Homepage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}


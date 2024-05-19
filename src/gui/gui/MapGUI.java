package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class MapGUI {

    private BufferedImage homeIcon;
    private BufferedImage homeIconPressed;
    private BufferedImage shopIcon;
    private BufferedImage shopIconPressed;
    private BufferedImage dungeonIcon;
    private BufferedImage dungeonIconPressed;


    public MapGUI() throws IOException {
        JFrame frame = new JFrame("Monku Games");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));

        // Ensure the icons are loaded correctly
        try {
            homeIcon = ImageIO.read(new File("asset/home.png"));
            homeIconPressed = ImageIO.read(new File("asset/home_pressed.png"));
        } catch (IOException e) {
            System.err.println("Error loading icons: " + e.getMessage());
            return; // Exit the constructor if the icons can't be loaded
        }

        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/map.jpg");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, -160, 0, getWidth() + 300, getHeight(), this);
            }
        };

        Image scaledHomeIcon = homeIcon.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Image scaledHomeIconPressed = homeIconPressed.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        JButton homeButton = new JButton(new ImageIcon(scaledHomeIcon));

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(homeButton, scaledHomeIconPressed, frame, () -> new HomeBaseGUI(0));
            }
        });

        try {
            shopIcon = ImageIO.read(new File("asset/shop1.png"));
            shopIconPressed = ImageIO.read(new File("asset/shop1-pressed.png"));
        } catch (IOException e) {
            System.err.println("Error loading icons: " + e.getMessage());
            return; // Exit the constructor if the icons can't be loaded
        }

        Image scaledShopIcon = shopIcon.getScaledInstance(70, 63, Image.SCALE_SMOOTH);
        Image scaledShopIconPressed = shopIconPressed.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        JButton shopButton = new JButton(new ImageIcon(scaledShopIcon));
  
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(shopButton, scaledShopIconPressed, frame, () -> new ShopGUI());
            }
        });
        homeButton.setBounds(279, 502, 30, 30);
        homeButton.setBorder(BorderFactory.createEmptyBorder());
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusable(false);
        shopButton.setBounds(289, 269, 70, 63);
        shopButton.setBorder(BorderFactory.createEmptyBorder());
        shopButton.setContentAreaFilled(false);
        shopButton.setFocusable(false);
        addHoverEffect(shopButton, shopIcon, 2);
        addHoverEffect(homeButton, homeIcon, 2);
        panelBG.setBounds(0, 0, 1000, 750);
        panelBG.add(homeButton);
        panelBG.add(shopButton);
        frame.setContentPane(panelBG);
        frame.setVisible(true);
    }

    private void handleButtonPress(JButton button, Image pressedIcon, JFrame frame, Runnable action) {
        Icon originalIcon = button.getIcon();
        button.setIcon(new ImageIcon(pressedIcon)); // Change to pressed icon

        Timer timer = new Timer(100, new ActionListener() { // Delay to show the pressed effect
            @Override
            public void actionPerformed(ActionEvent evt) {
                button.setIcon(originalIcon); // Revert to the original icon
                frame.dispose();
                action.run();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void addHoverEffect(JButton button, BufferedImage originalIcon, double scaleFactor) {
    // Store original icon and dimensions
    int originalWidth = button.getWidth();
    int originalHeight = button.getHeight();
    int originalPosX = button.getX();
    int originalPosY = button.getY();

    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            int width = (int) (originalWidth * scaleFactor);
            int height = (int) (originalHeight * scaleFactor);

            button.setSize(width, height);
            button.setLocation(originalPosX - (width - originalWidth) / 2, originalPosY - (height - originalHeight) / 2);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setSize(originalWidth, originalHeight); // Revert to original dimensions
            button.setLocation(originalPosX, originalPosY);

        }
    });
}

    
}

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

import Entity.Locations.Dungeon;
import app.Monku;

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
            shopIcon = ImageIO.read(new File("asset/shop1.png"));
            shopIconPressed = ImageIO.read(new File("asset/shop1-pressed.png"));
            dungeonIcon = ImageIO.read(new File("asset/Pixel_Dungeon_logo.png"));
            dungeonIconPressed = ImageIO.read(new File("asset/Pixel_Dungeon_logo_pressed.png"));
        } catch (IOException e) {
            System.err.println("Error loading icons: " + e.getMessage());
            return; // Exit the constructor if the icons can't be loaded
        }

        homeIcon = resize(homeIcon, 90, 90);
        homeIconPressed = resize(homeIconPressed, 90, 90);

        shopIcon = resize(shopIcon, 70+40, 63+40);
        shopIconPressed = resize(shopIconPressed, 70+40, 63+40);

        dungeonIcon = resize(dungeonIcon, 68, 68);
        dungeonIconPressed = resize(dungeonIconPressed, 90, 90);

        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/map.jpg");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, -160, 0, getWidth() + 300, getHeight(), this);
            }
        };

        Image scaledHomeIcon = homeIcon.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        Image scaledHomeIconPressed = homeIconPressed.getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        JButton homeButton = new JButton(new ImageIcon(scaledHomeIcon));

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(homeButton, scaledHomeIconPressed, frame, () -> new HomeBaseGUI(2));
            }
        });

        Image scaledShopIcon = shopIcon.getScaledInstance(70+40, 63+40, Image.SCALE_SMOOTH);
        Image scaledShopIconPressed = shopIconPressed.getScaledInstance(70+40, 63+40, Image.SCALE_SMOOTH);

        JButton shopButton = new JButton(new ImageIcon(scaledShopIcon));
  
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(shopButton, scaledShopIconPressed, frame, () -> new ShopGUI());
            }
        });

        Image scaledDungeonIcon = dungeonIcon.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        Image scaledDungeonIconPressed = dungeonIconPressed.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        JButton dungeonButton = new JButton(new ImageIcon(scaledDungeonIcon));
        dungeonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(dungeonButton, scaledDungeonIconPressed, frame, () -> new DungeonGUI((Dungeon)Monku.loc, Monku.player));
            }
            }
        );
        
        homeButton.setBounds(279, 502, 90, 90);
        homeButton.setBorder(BorderFactory.createEmptyBorder());
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusable(false);
        shopButton.setBounds(289, 269, 70+40, 63+40);
        shopButton.setBorder(BorderFactory.createEmptyBorder());
        shopButton.setContentAreaFilled(false);
        shopButton.setFocusable(false);
        dungeonButton.setFocusable(false);
        dungeonButton.setBounds(422, 101, 68, 68);
        dungeonButton.setBorder(BorderFactory.createEmptyBorder());
        dungeonButton.setContentAreaFilled(false);
        dungeonButton.setFocusable(false);
        addHoverEffect(shopButton, shopIcon);
        addHoverEffect(homeButton, homeIcon);
        addHoverEffect(dungeonButton, dungeonIcon);
        panelBG.setBounds(0, 0, 1000, 750);
        panelBG.add(homeButton);
        panelBG.add(shopButton);
        panelBG.add(dungeonButton);
        frame.setContentPane(panelBG);
        frame.setVisible(true);
    }

    public BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
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

    private void addHoverEffect(JButton button, BufferedImage originalIcon) {
        // Add transparency effect
        float originalAlpha = 1.0f; // Original opacity
        float hoverAlpha = 0.7f; // Opacity on hover
    
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Apply hover effect (change opacity)
                BufferedImage hoverImage = applyOpacity(originalIcon, hoverAlpha);
                button.setIcon(new ImageIcon(hoverImage));
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                // Revert to original opacity
                button.setIcon(new ImageIcon(originalIcon));
            }
        });
    }
    
    // Method to apply opacity to an image
    private BufferedImage applyOpacity(BufferedImage image, float opacity) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MapGUI();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}

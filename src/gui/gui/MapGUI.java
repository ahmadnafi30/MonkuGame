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
    private BufferedImage dungeonCrypt;
    private BufferedImage dungeonCryptPressed;
    private BufferedImage dungeonEternalIcon;
    private BufferedImage dungeonEternalPressed;
    private BufferedImage dungeonInfernocon;
    private BufferedImage dungeonInfernoPressed;
    private BufferedImage dungeonObsidian;
    private BufferedImage dungeonObsidianPressed;
    private BufferedImage dungeonDoom;
    private BufferedImage dungeonDoomPressed;


    public MapGUI() throws IOException {
        Template.stopMusic();
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
            dungeonCrypt = ImageIO.read(new File("asset/Untitled design/3.png"));
            dungeonCryptPressed = ImageIO.read(new File("asset/Untitled design/4.png"));
            dungeonEternalIcon = ImageIO.read(new File("asset/Untitled design/5.png"));
            dungeonEternalPressed = ImageIO.read(new File("asset/Untitled design/6.png"));
            dungeonDoom = ImageIO.read(new File("asset/Untitled design/1.png"));
            dungeonDoomPressed = ImageIO.read(new File("asset/Untitled design/2.png"));
            dungeonInfernocon = ImageIO.read(new File("asset/Untitled design/7.png"));
            dungeonInfernoPressed = ImageIO.read(new File("asset/Untitled design/8.png"));
            dungeonObsidian = ImageIO.read(new File("asset/Untitled design/9.png"));
            dungeonObsidianPressed = ImageIO.read(new File("asset/Untitled design/10.png"));
        } catch (IOException e) {
            System.err.println("Error loading icons: " + e.getMessage());
            return; // Exit the constructor if the icons can't be loaded
        }
        
        homeIcon = resize(homeIcon, 90, 90);
        homeIconPressed = resize(homeIconPressed, 90, 90);

        shopIcon = resize(shopIcon, 70+40, 63+40);
        shopIconPressed = resize(shopIconPressed, 70+40, 63+40);

        dungeonCrypt = resize(dungeonCrypt, 100, 100);
        dungeonCryptPressed = resize(dungeonCryptPressed, 150, 150);

        dungeonEternalIcon = resize(dungeonEternalIcon, 100, 100);
        dungeonEternalPressed = resize(dungeonEternalPressed, 150, 150);

        dungeonInfernocon = resize(dungeonInfernocon, 150, 150);
        dungeonInfernoPressed = resize(dungeonInfernoPressed, 100, 100);

        dungeonObsidian = resize(dungeonObsidian, 100, 100);
        dungeonObsidianPressed = resize(dungeonObsidianPressed, 150, 150);

        dungeonDoom = resize(dungeonDoom, 100, 100);
        dungeonDoomPressed = resize(dungeonDoomPressed, 150, 150);

        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/map.jpg");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, -160, 0, getWidth() + 300, getHeight(), this);
            }
        };
        
        Template.addCoinLabel(panelBG);
        Image scaledHomeIcon = homeIcon.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        Image scaledHomeIconPressed = homeIconPressed.getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        JButton homeButton = new JButton(new ImageIcon(scaledHomeIcon));

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(homeButton, scaledHomeIconPressed, frame, () -> new HomeBaseGUI());
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

        Image scaleDungeonCrypt = dungeonCrypt.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        Image scaledDungeonCryptPressed = dungeonCryptPressed.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        JButton dungeonCryptButton = new JButton(new ImageIcon(scaleDungeonCrypt));
        dungeonCryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(dungeonCryptButton, scaledDungeonCryptPressed, frame, () -> new DungeonGUI((Dungeon)Monku.cryptDungeon, Monku.player));
            }
            }
        );

        dungeonCryptButton.setFocusable(false);
        dungeonCryptButton.setBounds(422, 101, 68, 68);
        dungeonCryptButton.setBorder(BorderFactory.createEmptyBorder());
        dungeonCryptButton.setContentAreaFilled(false);
        dungeonCryptButton.setFocusable(false);
        addHoverEffect(dungeonCryptButton, dungeonCrypt);
        panelBG.add(dungeonCryptButton);

        Image scaleDungeonEternal = dungeonEternalIcon.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        Image scaledDungeonEternalPressed = dungeonEternalPressed.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        JButton dungeonEternalButton = new JButton(new ImageIcon(scaleDungeonEternal));
        dungeonEternalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(dungeonEternalButton, scaledDungeonEternalPressed, frame, () -> new DungeonGUI((Dungeon)Monku.eternalDungeon, Monku.player));
            }
            }
        );

        dungeonEternalButton.setFocusable(false);
        dungeonEternalButton.setBounds(840, 131, 68, 68);
        dungeonEternalButton.setBorder(BorderFactory.createEmptyBorder());
        dungeonEternalButton.setContentAreaFilled(false);
        dungeonEternalButton.setFocusable(false);
        addHoverEffect(dungeonEternalButton, dungeonEternalIcon);
        panelBG.add(dungeonEternalButton);

        Image scaledDungeonInferno = dungeonInfernocon.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        Image scaledDungeonInfernoPressed = dungeonInfernoPressed.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        JButton dungeonInfernoButton = new JButton(new ImageIcon(scaledDungeonInferno));
        dungeonInfernoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(dungeonInfernoButton, scaledDungeonInfernoPressed, frame, () -> new DungeonGUI((Dungeon)Monku.infernoDungeon, Monku.player));
            }
            }
        );

        dungeonInfernoButton.setFocusable(false);
        dungeonInfernoButton.setBounds(190, 90, 68, 68);
        dungeonInfernoButton.setBorder(BorderFactory.createEmptyBorder());
        dungeonInfernoButton.setContentAreaFilled(false);
        dungeonInfernoButton.setFocusable(false);
        addHoverEffect(dungeonInfernoButton, dungeonInfernocon);
        panelBG.add(dungeonInfernoButton);

        Image scaledObsidian = dungeonObsidian.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        Image scaledObsidianPressed = dungeonObsidianPressed.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        JButton dungeonObsidianButton = new JButton(new ImageIcon(scaledObsidian));
        dungeonObsidianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(dungeonObsidianButton, scaledObsidianPressed, frame, () -> new DungeonGUI((Dungeon)Monku.obsidianDungeon, Monku.player));
            }
            }
        );

        dungeonObsidianButton.setFocusable(false);
        dungeonObsidianButton.setBounds(615, 480, 68, 68);
        dungeonObsidianButton.setBorder(BorderFactory.createEmptyBorder());
        dungeonObsidianButton.setContentAreaFilled(false);
        dungeonObsidianButton.setFocusable(false);
        addHoverEffect(dungeonObsidianButton, dungeonObsidian);
        panelBG.add(dungeonObsidianButton);

        Image scaleDoom = dungeonDoom.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        Image scaledDoomPressed = dungeonDoomPressed.getScaledInstance(68, 68, Image.SCALE_SMOOTH);
        JButton dungeonDoomButton = new JButton(new ImageIcon(scaleDoom));
        dungeonDoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonPress(dungeonDoomButton, scaledDoomPressed, frame, () -> new DungeonGUI((Dungeon)Monku.doomDungeon, Monku.player));
            }
            }
        );

        dungeonDoomButton.setFocusable(false);
        dungeonDoomButton.setBounds(720, 230, 68, 68);
        dungeonDoomButton.setBorder(BorderFactory.createEmptyBorder());
        dungeonDoomButton.setContentAreaFilled(false);
        dungeonDoomButton.setFocusable(false);
        addHoverEffect(dungeonDoomButton, dungeonDoom);
        panelBG.add(dungeonDoomButton);

        homeButton.setBounds(279, 502, 90, 90);
        homeButton.setBorder(BorderFactory.createEmptyBorder());
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusable(false);
        shopButton.setBounds(289, 269, 70+40, 63+40);
        shopButton.setBorder(BorderFactory.createEmptyBorder());
        shopButton.setContentAreaFilled(false);
        shopButton.setFocusable(false);
        addHoverEffect(shopButton, shopIcon);
        addHoverEffect(homeButton, homeIcon);
        panelBG.add(homeButton);
        panelBG.add(shopButton);
        frame.setContentPane(panelBG);
        frame.setVisible(true);
        Template.playMusic("asset/Music/TV Station.wav");
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

        Timer timer = new Timer(100, new ActionListener() { 
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

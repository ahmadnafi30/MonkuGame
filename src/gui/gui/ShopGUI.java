package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;

import Entity.NPC.ItemSeller;
import Entity.NPC.*;
import app.Monku;

public class ShopGUI extends JFrame implements ActionListener {
    private CardLayout dialogText;
    private JPanel dialogTextPanel;
    private JPanel dialogBox;
    private JButton invisibleButton;
    private JLabel coin;

    public ShopGUI() {
        Template.stopMusic();
        Monku.player.setLocation(Monku.shop);
        JFrame frame = new JFrame("Monku Games");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));

        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/ShopBG.gif");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        frame.setContentPane(panelBG);
        Template.showNameLoc(Monku.player.getLocationPlayer(), panelBG, 50, 650, 1000, 130, 10);

        dialogText = new CardLayout();
        dialogTextPanel = new JPanel(dialogText);

        dialogBox = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/dialog box with pokeball.png");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        dialogBox.setBounds(3, 10, 980, 250);
        dialogBox.setOpaque(false);  // Ensure the dialog box panel itself is transparent
        dialogTextPanel.setBounds(70, 60, dialogBox.getWidth() - 160, 265);
        dialogTextPanel.setOpaque(false);
        createDialogCard("<html><p style=\"margin-left: 99px\">Halo " + Monku.player.getName() + "!</p>Apa yang ingin kamu lakukan?</html>");
        createDialogCard("Pleasure doing business with you!");
        
        // Create an invisible button to capture clicks and switch dialogs
        invisibleButton = new JButton();
        invisibleButton.setBounds(dialogBox.getBounds());
        invisibleButton.setOpaque(false);
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setBorderPainted(false);
        invisibleButton.addActionListener(e -> {
            int cardCount = getCardPosition();
            if (cardCount == 0) {
                String result = getInputKegiatan(frame);
                if (result.equalsIgnoreCase("beli")) {
                    potionButtons(panelBG, frame, "beli");
                } else if (result.equalsIgnoreCase("jual")) {
                    potionButtons(panelBG, frame, "jual");
                }
            }
            if (isLastCard()) {
                Monku.player.printDetailPlayer();
                // Transition to new scene
                newScene(frame);
            } else {
                dialogText.next(dialogTextPanel);
            }
        });
        panelBG.add(invisibleButton);
        panelBG.add(dialogTextPanel);
        panelBG.add(dialogBox);
        panelBG.setComponentZOrder(invisibleButton, 0);
        frame.setVisible(true);
        // Request focus for the frame to ensure it receives key events
        frame.requestFocusInWindow();
        Template.playMusic("asset/Music/Jubilife City (Day).wav");
    }

    private void potionButtons(JPanel panelBG, JFrame frame, String buyOrSell) {
        JButton healButton = addButtons(panelBG, null, "asset/potions/HealPotion.png", 200, 200, 200, 200, 110, 20, "Heal Potion");
        JButton defButton = addButtons(panelBG, null, "asset/potions/DefensePotion.png", 200, 200, 200, 200, 150 + 110 + 10, 20, "Defense Potion");
        JButton buffButton = addButtons(panelBG, null, "asset/potions/BuffPotion.png", 200, 200, 200, 200, (150 * 2) + 110 + 10, 20, "Buff Potion");
        JButton poisonButton = addButtons(panelBG, null, "asset/potions/PoisonPotion.png", 200, 200, 200, 200, (150 * 3) + 110 + 10, 20, "Poison Potion");
        JButton teleButton = addButtons(panelBG, null, "asset/potions/Teleportation.png", 200, 200, 200, 200, (150 * 4) + 110 + 10, 20, "Health Potion");
        
        dialogBox.setVisible(false);
        dialogTextPanel.setVisible(false);
        invisibleButton.setVisible(false); // Hide the invisible button when showing potion buttons
        coin = Template.addCoinLabel(panelBG);
        panelBG.add(healButton);
        panelBG.add(defButton);
        panelBG.add(buffButton);
        panelBG.add(poisonButton);
        panelBG.add(teleButton);
        
        healButton.addActionListener(e -> {
            options(panelBG, frame, "Jamu Kencur", buyOrSell);
        });
        defButton.addActionListener(e -> {
            options(panelBG, frame, "Susu Kambing", buyOrSell);
        });
        buffButton.addActionListener(e -> {
            options(panelBG, frame, "Jamu Kuat", buyOrSell);
        });
        poisonButton.addActionListener(e -> {
            options(panelBG, frame, "Ludah Buzzer", buyOrSell);
        });
        teleButton.addActionListener(e -> {
            options(panelBG, frame, "BECAK", buyOrSell);
        });
        
        JButton mapButton = Template.mapButton(panelBG, frame);
        mapButton.addActionListener(e -> {
            dialogBox.setVisible(true);
            dialogTextPanel.setVisible(true);
            mapButton.setVisible(false);
            createDialogCard("<html><br>Senang berbisnis denganmu!</br></html>");
            dialogText.next(dialogTextPanel);
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new MapGUI();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally{
                        frame.dispose();
                    }
                } 
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    private void options(JPanel panelBG, JFrame frame, String potionName, String buyOrSell) {
        String[] rarityOptions = { "COMMON", "RARE", "EPIC" };
        int rarityChoice = JOptionPane.showOptionDialog(
            frame,
            "Pilih Rarity", 
            "Pilih Rarity", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            rarityOptions, 
            rarityOptions[0]
        );
        if(rarityChoice == -1){
            return;
        }
        String input = JOptionPane.showInputDialog(
            frame,
            "Masukkan jumlah potion yang ingin kamu "+ buyOrSell,
            "Input jumlah", JOptionPane.QUESTION_MESSAGE
        );
        if(input == null) return;

    // Get the selected rarity as a String
        String selectedRarity = (rarityChoice >= 0) ? rarityOptions[rarityChoice] : null;
        //if(Monku.shopKeeper.getItem(potionName, selectedRarity)){}
        try {
            // Attempt to parse input as an integer
            int quantity = Integer.parseInt(input);
            if (quantity > 0) {
                if(buyOrSell.equals("beli")){
                    int confirmation = JOptionPane.showConfirmDialog(null, "Harga Total: " + Monku.shopKeeper.getItem(potionName, selectedRarity).getPrice() * quantity, "Pembelian Potion", JOptionPane.OK_CANCEL_OPTION);
                    if(confirmation != JOptionPane.OK_OPTION) {
                        return;
                    }
                    String msg = Monku.player.buyItem(Monku.shopKeeper.getItem(potionName, selectedRarity), quantity, Monku.shopKeeper);
                    System.out.println(msg);
                    if(msg.equalsIgnoreCase("Tidak ada koin yang cukup untuk membeli item ini.")){
                        JOptionPane.showMessageDialog(panelBG, msg, "Tidak cukup", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    System.out.println(Monku.player.getCoin());
                    Monku.player.getInventory().forEach((k, v) -> {
                        System.out.println(k.getName() + " = " + v);
                    });
                } else if(buyOrSell.equals("jual")){
                    int confirmation = JOptionPane.showConfirmDialog(null, "Harga Total: " + Monku.shopKeeper.getItem(potionName, selectedRarity).getPrice() * quantity, "Penjualan Potion", JOptionPane.OK_CANCEL_OPTION);
                    if(confirmation != JOptionPane.OK_OPTION) {
                        return;
                    }
                    Monku.player.sellItem(Monku.shopKeeper.getItem(potionName, selectedRarity), quantity, Monku.shopKeeper);
                    System.out.println(Monku.player.getCoin());
                    Monku.player.getInventory().forEach((k, v) -> {
                        System.out.println(k.getName() + " = " + v);
                    });
                }
            }
            panelBG.remove(coin);
            panelBG.repaint();
            coin = Template.addCoinLabel(panelBG);
            panelBG.add(coin);
            // Process the quantity here
        } catch (NumberFormatException ex) {
            // Input is not a valid integer
            JOptionPane.showMessageDialog(frame, "Mohon masukkan angka yang valid.", "Input Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton addButtons(JPanel panelBG, BufferedImage img, String path, int buttonWidth, int buttonHeight, int imageWidth, int imageHeight, int x, int y, String name) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        BufferedImage iconImage;
        iconImage = resize(img, imageWidth, imageHeight);
        Image scaledButtonImage = iconImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);

        JButton button = new JButton(new ImageIcon(scaledButtonImage));
        button.setBounds(x, y, buttonWidth, buttonHeight);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        return button;
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public String getInputKegiatan(JFrame frame) {
        String[] options = {"Beli", "Jual"};
        int choice = JOptionPane.showOptionDialog(
            frame,
            "Apakah kamu mau beli atau jual item?",
            "Pilih opsi",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        if (choice == JOptionPane.CLOSED_OPTION) {
            return null; // No selection made
        }
        return options[choice];
    }

    private boolean isLastCard() {
        Component[] components = dialogTextPanel.getComponents();
        for (Component comp : components) {
            if (comp.isVisible()) {
                return dialogTextPanel.getComponent(components.length - 1) == comp;
            }
        }
        return false;
    }

    private int getCardPosition() {
        Component[] components = dialogTextPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i].isVisible()) {
                return i;
            }
        }
        return -1;
    }

    private void newScene(JFrame frame) {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
            try {
                new MapGUI();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void createDialogCard(String text) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Purisa Bold", Font.BOLD, 30));
        label.setForeground(Color.BLACK); // Set text color
        label.setVisible(true);
        panel.add(label);
        dialogTextPanel.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Action performed!", "Peringatan", JOptionPane.INFORMATION_MESSAGE);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShopGUI::new);
    }
}

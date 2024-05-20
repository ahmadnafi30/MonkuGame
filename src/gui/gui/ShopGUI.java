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
    public ShopGUI() {
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
        createDialogCard("Ini adalah dialog 3");
        
        // Create an invisible button to capture clicks and switch dialogs
        JButton invisibleButton = new JButton();
        invisibleButton.setBounds(dialogBox.getBounds());
        invisibleButton.setOpaque(false);
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setBorderPainted(false);
        invisibleButton.addActionListener(e -> {
            int cardCount = getCardPosition();
            if (cardCount == 0) {
                String result = getInputKegiatan(frame);
                if (result.equalsIgnoreCase("beli")) {
                    potionButtons(panelBG);
                    Monku.player.buyItem(null, cardCount, e);
                    Monku.shopKeeper.setCoin(cardCount);
                } else if (result.equalsIgnoreCase("jual")) {
                    Monku.player.sellItem(null, cardCount, e);
                    Monku.shopKeeper.setCoin(-cardCount);
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
    }

    // private void setInvisibleDialogText() {
        
    // }
    private void potionButtons(JPanel panelBG){
        JButton healButton = addButtons(panelBG, null, "asset/healthPotion.png", 150, 150, 150, 150, 110, 20, "Heal Potion");
        JButton defButton = addButtons(panelBG, null, "asset/healthPotion.png", 150, 150, 150, 150, 150+110+10, 20, "Heal Potion");
        JButton buffPotion = addButtons(panelBG, null, "asset/healthPotion.png", 150, 150, 150, 150, (150*2)+110+10, 20, "Heal Potion");
        JButton poisonButton = addButtons(panelBG, null, "asset/healthPotion.png", 150, 150, 150, 150, (150*3)+110+10, 20, "Heal Potion");
        JButton teleButton = addButtons(panelBG, null, "asset/healthPotion.png", 150, 150, 150, 150, (150*4)+110+10, 20, "Heal Potion"); 
        dialogBox.setVisible(false);
        dialogTextPanel.setVisible(false);
        panelBG.add(healButton);
        panelBG.add(defButton);
        panelBG.add(buffPotion);
        panelBG.add(poisonButton);
        panelBG.add(teleButton);
    }

    private void removeDialogBox() {
        
    }
    private JButton addButtons(JPanel panelBG, BufferedImage img, String path, int buttonWidth, int buttonHeight, int imageWidth, int imageHeight, int x, int y, String name) {
        try{
            img = ImageIO.read(new File(path));
        } catch(IOException e) {
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

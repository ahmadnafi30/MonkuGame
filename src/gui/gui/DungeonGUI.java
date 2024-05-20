package gui;

import Entity.Locations.Dungeon;
import Entity.Monster.AirType;
import Entity.Monster.Monster;
import Entity.Player.Player;
import app.Monku;
import Entity.Item.BuffPotion;
import Entity.Item.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class DungeonGUI extends JFrame {
    private Dungeon dungeon;
    private Player player;
    private BufferedImage details;
    private CardLayout dialogText;
    private JPanel dialogTextPanel;
    private JPanel dialogBox;
    private JPanel guard;
    private JPanel panelBG;

    private int loadornew = 1; // Just for demo purpose

    public DungeonGUI(Dungeon dungeon, Player player) {
        this.dungeon = dungeon;
        this.player = player;

        JFrame frame = new JFrame("Monku Games");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);


        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon(dungeon.getImages());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        try {
            details = ImageIO.read(new File("asset/about.png"));
            details = resize(details, 30, 30);
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }

        frame.setContentPane(panelBG);
        Image scaledDetailImage = details.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton detailDungeon = new JButton(new ImageIcon(scaledDetailImage));
        detailDungeon.setBounds(10, 10, 50, 50);
        detailDungeon.setBorderPainted(false);
        detailDungeon.setContentAreaFilled(false);
        detailDungeon.setFocusPainted(false);

        detailDungeon.addActionListener(e -> showDungeonDetails(dungeon));

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

        int dialogBoxWidth = 700;
        int dialogBoxHeight = 265;
        int dialogBoxX = (frame.getWidth() - dialogBoxWidth) / 2;
        int dialogBoxY = (frame.getHeight() - dialogBoxHeight) / 2;

        dialogBox.setOpaque(false);
        dialogBox.setBounds(dialogBoxX, dialogBoxY, dialogBoxWidth, dialogBoxHeight);
        dialogTextPanel.setBounds(dialogBoxX + 120, dialogBoxY + 20, dialogBoxWidth - 160, 200);
        dialogTextPanel.setOpaque(false);
        panelBG.add(dialogTextPanel);
        panelBG.add(dialogBox);

        int guardX = dialogBoxX + 50;
        int guardY = dialogBoxY + 50;

        guard = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon(dungeon.getGuardImage());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        guard.setBounds(guardX, guardY, 149, 148);
        panelBG.add(guard);

        createDialogCard("<html><p style=\"margin-left: 20px\">" + dungeon.getGuardName() + ": Hello " + player.getName() + "!<br>Welcome to " + dungeon.getLocationName() + "!</html>");
        createDialogCard("<html><p style=\"margin-left: 20px\">" + dungeon.getGuardName() + ": Hati-hati ya di sini,<br>banyak monster yang tiba-tiba bisa saja menyerangmu</p></html>");
        createDialogCard("<html><p style=\"margin-left: 20px\">" + dungeon.getGuardName() + ": Hati-hati ya di sini,<br>tapi jika memang kamu ingin menangkap monster,<br> maka carilah monster yang kamu mau <br>dan kalahkan mereka</p></html>");

        JButton invisibleButton = new JButton();
        invisibleButton.setBounds(dialogBox.getBounds());
        invisibleButton.setOpaque(false);
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setBorderPainted(false);
        invisibleButton.addActionListener(e -> {
    
            if (isLastCard()) {
                newScene();
                Monku.player.printDetailPlayer();
            } else {
                dialogText.next(dialogTextPanel);
            }
        });

        panelBG.add(invisibleButton);

        panelBG.add(detailDungeon);
        panelBG.revalidate();
        panelBG.repaint();
        frame.setVisible(true);
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private void showDungeonDetails(Dungeon dungeon) {
        dialogText.first(dialogTextPanel);
        dialogBox.setOpaque(false);
        dialogBox.setVisible(true);
        dialogText.show(dialogTextPanel, "Dungeon Details Dialog 1");
    }

    private void createDialogCard(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Purisa Bold", Font.BOLD, 15));
        label.setForeground(Color.BLACK);
        panel.add(label, BorderLayout.CENTER);
        dialogTextPanel.add(panel);
    }

    private boolean isLastCard() {
        return getCardPosition() == dialogTextPanel.getComponentCount() - 1;
    }

    private int getCardPosition() {
        for (int i = 0; i < dialogTextPanel.getComponentCount(); i++) {
            Component component = dialogTextPanel.getComponent(i);
            if (component.isVisible()) {
                return i;
            }
        }
        return -1;
    }

    private void newScene() {
        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            private int opacity = 100;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity > 0) {
                    opacity -= 2;
                    setOpacity(dialogBox, opacity / 100.0f);
                    setOpacity(guard, opacity / 100.0f);
                } else {
                    ((Timer) e.getSource()).stop();
                    
                    dialogBox.setVisible(false);
                    panelBG.remove(dialogBox);
                    panelBG.remove(guard);
        
                    JButton battleButton = new JButton("Battle");
                    battleButton.setBounds(200, 400, 100, 50);
                    battleButton.setVisible(true); // Ensure button is visible
                    battleButton.setComponentZOrder(battleButton, 9);
                    panelBG.add(battleButton);
                    
        
                    JButton exitButton = new JButton("Exit");
                    exitButton.setBounds(400, 400, 100, 50);
                    exitButton.setVisible(true); // Ensure button is visible
                    panelBG.add(exitButton);
        
                    panelBG.revalidate();
                    panelBG.repaint();
                }
            }
        });
        timer.start();
    }
    
    
    
    private void setOpacity(JComponent component, float opacity) {
        component.setOpaque(true);
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        Graphics2D g2 = (Graphics2D) component.getGraphics();
        g2.setComposite(ac);
    }
    

public static void main(String[] args) {
    Monster monster = new AirType("kehed", 4, 4);
    Item item = new BuffPotion("Jamu Kuat", "COMMON");
    Monster[] monsters = {monster};
    Item[] rewards = {item};
    Dungeon dungeon = new Dungeon("Mystic Cave", monsters, rewards, 1, "asset/den4zwg-45a7fe9e-d38a-417c-815c-3e56972adf62.jpg", "asset/wizard1.gif", "Sapi");
    Player player = new Player("Hero", dungeon, "w");

    SwingUtilities.invokeLater(() -> new DungeonGUI(dungeon, player));
}
}
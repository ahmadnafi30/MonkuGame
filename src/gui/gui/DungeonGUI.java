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
    private BufferedImage buttonBackgroundImage;
    private CardLayout dialogText;
    private JPanel dialogTextPanel;
    private JPanel dialogBox;
    private JPanel guard;
    private JPanel panelBG;
    private JPanel buttonBackgroundPanel;
    private JButton battle;
    private JButton exit;

    private JLabel playerLabel;
    private JLabel chatLabel;
    private JLabel chatTextLabel;

    public DungeonGUI(Dungeon dungeon, Player player) {
        this.dungeon = dungeon;
        this.player = player;

        setTitle("Monku Games");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        setIconImages(Collections.singletonList(icon.getImage()));

        panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon(dungeon.getImages());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        loadDetailImage();
        try {
            buttonBackgroundImage = ImageIO.read(new File("asset/text-box.png"));
        } catch (IOException e) {
            System.out.println("Error loading button background image: " + e.getMessage());
        }

        setContentPane(panelBG);
        JButton detailDungeon = createDetailDungeonButton();

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

        configureDialogBox();

        guard = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon(dungeon.getGuardImage());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        configureGuard();

        createDialogCards();

        panelBG.add(detailDungeon);
        panelBG.revalidate();
        panelBG.repaint();
        setVisible(true);

        panelBG.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (isLastCard()) {
                    newScene();
                    Monku.player.printDetailPlayer();
                } else {
                    dialogText.next(dialogTextPanel);
                }
            }
        });
    }

    private void loadDetailImage() {
        try {
            details = ImageIO.read(new File("asset/about.png"));
            details = resize(details, 30, 30);
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    private JButton createDetailDungeonButton() {
        Image scaledDetailImage = details.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton detailDungeon = new JButton(new ImageIcon(scaledDetailImage));
        detailDungeon.setBounds(10, 10, 50, 50);
        detailDungeon.setBorderPainted(false);
        detailDungeon.setContentAreaFilled(false);
        detailDungeon.setFocusPainted(false);

        detailDungeon.addActionListener(e -> showDungeonDetails(dungeon));
        return detailDungeon;
    }

    private void configureDialogBox() {
        int dialogBoxWidth = 700;
        int dialogBoxHeight = 265;
        int dialogBoxX = (getWidth() - dialogBoxWidth) / 2;
        int dialogBoxY = (getHeight() - dialogBoxHeight) / 2;

        dialogBox.setOpaque(false);
        dialogBox.setBounds(dialogBoxX, dialogBoxY, dialogBoxWidth, dialogBoxHeight);
        dialogTextPanel.setBounds(dialogBoxX + 120, dialogBoxY + 20, dialogBoxWidth - 160, 200);
        dialogTextPanel.setOpaque(false);
        panelBG.add(dialogTextPanel);
        panelBG.add(dialogBox);
    }

    private void configureGuard() {
        int guardX = (getWidth() - 700) / 2 + 50;
        int guardY = (getHeight() - 265) / 2 + 50;

        guard.setBounds(guardX, guardY, 149, 148);
        panelBG.add(guard);
    }

    private void createDialogCards() {
        createDialogCard("<html><p style=\"margin-left: 20px\">" + dungeon.getGuardName() + ": Hello " + player.getName() + "!<br>Welcome to " + dungeon.getLocationName() + "!</html>");
        createDialogCard("<html><p style=\"margin-left: 20px\">" + dungeon.getGuardName() + ": Hati-hati ya di sini,<br>banyak monster yang tiba-tiba bisa saja menyerangmu</p></html>");
        createDialogCard("<html><p style=\"margin-left: 20px\">" + dungeon.getGuardName() + ": Hati-hati ya di sini,<br>tapi jika memang kamu ingin menangkap monster,<br> maka carilah monster yang kamu mau <br>dan kalahkan mereka</p></html>");
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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
                    panelBG.remove(dialogBox);
                    panelBG.remove(guard);
                    panelBG.remove(dialogTextPanel);

                    addButtons();

                    panelBG.revalidate();
                    panelBG.repaint();
                }
            }
        });
        timer.start();
    }

    private void setOpacity(JComponent component, float opacity) {
        component.setOpaque(true);
        Graphics g = component.getGraphics();
        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(null, 0, 0, null);
        }
    }

    private void addButtons() {
        int buttonWidth = 200;
        int buttonHeight = 200;
    
        buttonBackgroundPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(buttonBackgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
    
        buttonBackgroundPanel.setBounds((getWidth() - buttonWidth) / 2 + 200, (getHeight() - buttonHeight) / 2 - 100, buttonWidth, buttonHeight);
        panelBG.add(buttonBackgroundPanel);
    
        BufferedImage iconInteract = null;
        try {
            iconInteract = ImageIO.read(new File("asset/b4c70567-4596-46d2-a276-4820ae3adaf5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        Image scaledButtonImage = iconInteract.getScaledInstance(150, 50, Image.SCALE_SMOOTH);
    
        battle = new JButton("BATTLE", new ImageIcon(scaledButtonImage));
        battle.addActionListener(e -> {
            System.out.println("Battle button pressed");
            createBattleScene();
        });
        battle.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2, 40, 150, 50);
        battle.setFont(new Font("Public Pixel", Font.BOLD, 15));
        battle.setHorizontalTextPosition(SwingConstants.CENTER);
        battle.setVerticalTextPosition(SwingConstants.CENTER);
        battle.setBorder(BorderFactory.createEmptyBorder());
        battle.setForeground(Color.BLACK);
        battle.setContentAreaFilled(false);
    
        exit = new JButton("EXIT", new ImageIcon(scaledButtonImage));
        exit.addActionListener(e -> {
            System.out.println("Exit button pressed");
        });
        exit.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2, 110, 150, 50);
        exit.setFont(new Font("Public Pixel", Font.BOLD, 15));
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setVerticalTextPosition(SwingConstants.CENTER);
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setForeground(Color.BLACK);
        exit.setContentAreaFilled(false);
    
        buttonBackgroundPanel.add(battle);
        buttonBackgroundPanel.add(exit);
    
        BufferedImage playerImage = null;
        try {
            playerImage = ImageIO.read(new File(player.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        if (playerImage != null) {
            BufferedImage resizedPlayerImage = resize(playerImage, 200, 200);
            playerLabel = new JLabel(new ImageIcon(resizedPlayerImage));
            playerLabel.setBounds(50, 400, resizedPlayerImage.getWidth(), resizedPlayerImage.getHeight());
            panelBG.add(playerLabel);
        }
    
        BufferedImage chatBox = null;
        try {
            chatBox = ImageIO.read(new File("asset/Untitled design (7).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (chatBox != null) {
            BufferedImage resizedChatBoxImage = resize(chatBox, 300, 200);
            chatLabel = new JLabel(new ImageIcon(resizedChatBoxImage));
            chatLabel.setBounds(160, 300, resizedChatBoxImage.getWidth(), resizedChatBoxImage.getHeight());
            
            chatTextLabel = new JLabel("Mau ngapain ya?");
            chatTextLabel.setFont(new Font("Public Pixel", Font.BOLD, 15));
            chatTextLabel.setForeground(Color.BLACK);
            chatTextLabel.setBounds(200, 340, 250, 50);
            panelBG.add(chatTextLabel);
            panelBG.add(chatLabel);
        }
    
        panelBG.revalidate();
        panelBG.repaint();
    }

    private void createBattleScene() {
        panelBG.removeAll();
        panelBG.repaint();
        panelBG.revalidate();
        panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage battleBackground = ImageIO.read(new File("asset/Untitled design (8).png"));
                    g.drawImage(battleBackground, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    
        BufferedImage bcAttackImage;
        BufferedImage speAttackImage;
        BufferedImage eleAttackImage;
        BufferedImage fleeImage;
        BufferedImage boxHpImage;
    
        try {
            bcAttackImage = ImageIO.read(new File("asset/SPECIAL ATTACK/2.png"));
            speAttackImage = ImageIO.read(new File("asset/SPECIAL ATTACK/1.png"));
            eleAttackImage = ImageIO.read(new File("asset/SPECIAL ATTACK/3.png"));
            fleeImage = ImageIO.read(new File("asset/SPECIAL ATTACK/4.png"));
            boxHpImage = ImageIO.read(new File("asset/HPBAR.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    
        JButton bcAttackButton = Template.addButtons(panelBG, bcAttackImage, "asset/SPECIAL ATTACK/2.png", 250, 92, 92, 250, 500, 532);
        JButton speAttackButton = Template.addButtons(panelBG, speAttackImage, "asset/SPECIAL ATTACK/1.png", 250, 92, 92, 250, 740, 532);
        JButton eleAttackButton = Template.addButtons(panelBG, eleAttackImage, "asset/SPECIAL ATTACK/3.png", 250, 92, 92, 250, 500, 623);
        JButton fleeButton = Template.addButtons(panelBG, fleeImage, "asset/SPECIAL ATTACK/4.png", 250, 92, 92, 250, 740, 623);
    
        JPanel hpBoxPanelPlayer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(boxHpImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel hpBoxPanelMonster = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(boxHpImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
    

        int hpBoxWidth = 510;  
        int hpBoxHeight = 184;  
        int hpBoxX = 0;       
        int hpBoxY = 532;  
        hpBoxPanelMonster.setBounds(20,20,470,150);
        hpBoxPanelPlayer.setBounds(hpBoxX, hpBoxY, hpBoxWidth, hpBoxHeight);
        hpBoxPanelPlayer.setOpaque(false); 
        hpBoxPanelMonster.setOpaque(false); 
    
        panelBG.add(bcAttackButton);
        panelBG.add(speAttackButton);
        panelBG.add(eleAttackButton);
        panelBG.add(fleeButton);
        panelBG.add(hpBoxPanelPlayer);
        panelBG.add(hpBoxPanelMonster);
    
        setContentPane(panelBG);
        panelBG.revalidate();
        panelBG.repaint();
    }
    
    private void showDungeonDetails(Dungeon dungeon) {
        if (buttonBackgroundPanel != null) {
            panelBG.remove(buttonBackgroundPanel);
        }
        if (playerLabel != null) {
            panelBG.remove(playerLabel);
        }
        if (chatLabel != null) {
            panelBG.remove(chatLabel);
        }
        if (chatTextLabel != null) {
            panelBG.remove(chatTextLabel);
        }
    
        panelBG.remove(dialogBox);
        panelBG.remove(guard);
        panelBG.remove(dialogTextPanel);
    
        configureDialogBox();
        configureGuard();
        createDialogCards();
    
        panelBG.revalidate();
        panelBG.repaint();
    }

    public static void main(String[] args) {
        Monster monster = new AirType("kehed", 4, 4);
        Item item = new BuffPotion("Jamu Kuat", "COMMON");
        Monster[] monsters = {monster};
        Item[] rewards = {item};
        Dungeon dungeon = new Dungeon("Mystic Cave", monsters, rewards, 1, "asset/den4zwg-45a7fe9e-d38a-417c-815c-3e56972adf62.jpg", "asset/wizard1.gif", "Sapi");
        Player player = new Player("Hero", dungeon, "asset/wizard.gif");

        SwingUtilities.invokeLater(() -> new DungeonGUI(dungeon, player));
    }
}

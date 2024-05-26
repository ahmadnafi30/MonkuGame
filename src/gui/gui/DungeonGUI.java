package gui;

import Entity.Locations.Dungeon;
import Entity.Locations.HomeBase;
import Entity.Monster.AirType;
import Entity.Monster.ElementalAttack;
import Entity.Monster.Monster;
import Entity.NPC.ItemSeller;
import Entity.Player.Player;
import app.Monku;
import Entity.Item.BuffPotion;
import Entity.Item.DefensivePotion;
import Entity.Item.HealthPotion;
import Entity.Item.Item;
import Entity.Item.PoisonPotion;
import Entity.Item.Teleportation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

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
    private JButton selectedPokemonButton = null;
    private JLabel playerLabel;
    private JLabel chatLabel;
    private JLabel chatTextLabel;
    private int indeksMonku = -1;
    private int indeksItem = -1;
    private JButton selectedItemButton = null;
    private JPanel listPanel;
    private JPanel monsterDungeonPanel;
    private JPanel monsterPlayerPanel;
    private JPanel monsterHpPanel;
    private JPanel playerHpPanel;
    private Monster monsterBattle;
    private Monster monsterPlayer;
    private JButton goToHomeBase;
    // private JPanel monsterHpPanel;
    String[] skills = {"basic", "special", "elemental"};
    private Timer timerEffect;
    private Item curItem;
    private int turn = 0;
    JScrollPane scrollPane;
    JScrollBar verticalScrollBar;
    private JButton tangkap;
    private JButton goToDungeon;
    private JPanel monsterDead; 


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
        // System.out.println(dungeon.getima);
        panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon(dungeon.getImages());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Template.showNameLoc(dungeon, panelBG, 30, 700, 900, 340, 10);
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
        System.out.println(dungeon.getGuardImage());
        
        JButton mapButton = Template.mapButton(panelBG, this);
        mapButton.addActionListener(e -> {
            try {
                new MapGUI();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            dispose();
        });
        panelBG.add(mapButton);

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
        int guardX = (getWidth() - 700) / 2 + 30;
        int guardY = (getHeight() - 265) / 2 +50;
        guard.setOpaque(false);
        guard.setBounds(guardX, guardY, 149, 148);
        //panelBG.setComponentZOrder(guard, getComponents().length-1);
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
        buttonBackgroundPanel.setOpaque(false);
    
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
    

        BufferedImage boxHpImage;
        BufferedImage boxHpImage2;
        BufferedImage GoButton;
        try {
            boxHpImage = ImageIO.read(new File("asset/HPBAR/5.png"));
            boxHpImage2 = ImageIO.read(new File("asset/HPBAR/HPBAR (6).png"));
            GoButton = ImageIO.read(new File("asset/HPBAR/HPBAR (3).png"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }    

    JButton goButton = Template.addButtons(panelBG, GoButton, "asset/HPBAR/HPBAR (3).png", 500, 184, 500, 500, 500, 532);

    JPanel hpBoxPanelPlayer = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(boxHpImage, 0, 0, getWidth(), getHeight(), this);
        }
    };

    // JPanel hpBoxPanelMonster = new JPanel() {
    //     @Override
    //     protected void paintComponent(Graphics g) {
    //         super.paintComponent(g);
    //         g.drawImage(boxHpImage2, 0, 0, getWidth(), getHeight(), this);
    //     }
    // };

    int hpBoxWidth = 510;  
    int hpBoxHeight = 184;  
    int hpBoxX = 0;       
    int hpBoxY = 532;  
    // hpBoxPanelMonster.setBounds(20, 20, 470, 220);
    hpBoxPanelPlayer.setBounds(hpBoxX, hpBoxY, hpBoxWidth, hpBoxHeight);
    hpBoxPanelPlayer.setOpaque(false); 
    // hpBoxPanelMonster.setOpaque(false); 


    panelBG.add(goButton);

    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    listPanel.setOpaque(false);

    ArrayList<Monster> pokemonNames = player.getMonsters();
    ArrayList<JButton> pokemonButtons = new ArrayList<>();
    for (int i = 0; i < pokemonNames.size(); i++) {
        try {
            //kalo player.getImage bukannya ngedapetin path gambar palyernya??? 
            BufferedImage pokemonImage = ImageIO.read(new File(player.getImage(i)));
            JButton button = createPokemonButton(player.getImage(i), player.printMonster(i), i);
            listPanel.add(button);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    setMonsterDungeon();

    monsterDungeonPanel = new JPanel(null) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon originalIcon = new ImageIcon(monsterBattle.getImage());
            Image originalImage = originalIcon.getImage();
            g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
        }
    };

    monsterDungeonPanel.setBounds(575, 75, 300, 300);
    monsterDungeonPanel.setOpaque(false);

    monsterHpPanel = createHpPanel(monsterBattle.getName(), monsterBattle.getHealthPoint(), monsterBattle.getCurrentMaxHealthPoint(), 0, monsterBattle);
    monsterHpPanel.getComponent(1).setForeground(Color.WHITE);
    monsterHpPanel.setBounds(515, 120, 400, 40);
    panelBG.add(monsterHpPanel);
    panelBG.add(monsterDungeonPanel);
    // panelBG.add(hpBoxPanelMonster);
    panelBG.add(hpBoxPanelPlayer);

    JScrollPane scrollPane = new JScrollPane(listPanel);
    scrollPane.setBounds(10, 10, hpBoxPanelPlayer.getWidth() - 20, hpBoxPanelPlayer.getHeight() - 20); 
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false); 
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
    verticalScrollBar.setUI(new BasicScrollBarUI() {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = Color.GRAY; 
            this.trackColor = new Color(0, 0, 0, 0); 
        }
    });

    scrollPane.getVerticalScrollBar().setUnitIncrement(16);  
    scrollPane.getVerticalScrollBar().setBlockIncrement(50); 

    hpBoxPanelPlayer.setLayout(null);  
    hpBoxPanelPlayer.add(scrollPane);


    goButton.addActionListener(e -> {
        System.out.println("basic attack button pressed");
        if (selectedPokemonButton != null) {
            goButton.setVisible(false);
            panelBG.remove(scrollPane);
            panelBG.remove(verticalScrollBar);
            panelBG.remove(hpBoxPanelPlayer);
            try {
                addBattleButtons();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a Pokémon first.", "Reminder", JOptionPane.WARNING_MESSAGE);
        }
        
    });

    setContentPane(panelBG);
    panelBG.revalidate();
    panelBG.repaint();
}

private void setMonsterDungeon(){
    monsterBattle = dungeon.getRandomMonster();
    // switch(monsterBattle.getELementTypeStr()){
    //     case "Fire":
            
    //         break;
    //     case "Water":
    //         monsterDungeonPanel.setBackground(Color.BLUE);
    //         break;
    //     case "Earth":
    //         monsterDungeonPanel.setBackground(Color.GREEN);
    //         break;
    //     default:
    //         break;
    //     }
}

public void popUp() {
    if (isDead(monsterBattle)) {
        player.addCoin(15*dungeon.getLevel());
        int buttonWidth = 600;
        int buttonHeight = 300;
        monsterBattle.setHealthPoint(monsterBattle.getCurrentMaxHealthPoint());
        buttonBackgroundPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(buttonBackgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
    
        buttonBackgroundPanel.setBounds((getWidth() - buttonWidth) / 2, (getHeight() - buttonHeight) / 2 - 100, buttonWidth, buttonHeight);
        buttonBackgroundPanel.setOpaque(false);
        panelBG.add(buttonBackgroundPanel);
    
        BufferedImage iconInteract = null;
        try {
            iconInteract = ImageIO.read(new File("asset/b4c70567-4596-46d2-a276-4820ae3adaf5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        Image scaledButtonImage = iconInteract.getScaledInstance(150, 50, Image.SCALE_SMOOTH);

        JLabel defeatLabel = new JLabel("Monster " + monsterBattle.getName() + " mati! Kamu menang!");
        defeatLabel.setBounds(buttonBackgroundPanel.getX() -60, buttonBackgroundPanel.getY() + 20 , buttonBackgroundPanel.getWidth() - 20, 30);
        defeatLabel.setFont(new Font("Purisa Bold", Font.BOLD, 15));
        
        buttonBackgroundPanel.add(defeatLabel, 0);
    
        tangkap = new JButton("Catch", new ImageIcon(scaledButtonImage));
        tangkap.addActionListener(e -> {
            System.out.println("Battle button pressed");
            buttonBackgroundPanel.remove(tangkap);
            buttonBackgroundPanel.remove(defeatLabel);
            validate();
            repaint();
            buttonBackgroundPanel.revalidate();
            buttonBackgroundPanel.repaint();
            JLabel catchJLabel = new JLabel("Monster " + monsterBattle.getName() + " wes ditangkep, sekien wis ning kantong yoh");
            catchJLabel.setBounds(buttonBackgroundPanel.getX() - 100, buttonBackgroundPanel.getY() + 20 , buttonBackgroundPanel.getWidth() - 20, 30);
            catchJLabel.setFont(new Font("Purisa Bold", Font.BOLD, 15));
            goToDungeon.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2, 200- 20, 150, 50);
            buttonBackgroundPanel.add(catchJLabel, 0);
            goToDungeon.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2, 200- 20, 150, 50);
            buttonBackgroundPanel.add(goToDungeon);
            player.catchMonster(monsterBattle);
            
            // buttonBackgroundPanel.remove(defeatLabel);
        });

        tangkap.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2 - 100, 200 - 20, 150, 50);
        tangkap.setFont(new Font("Public Pixel", Font.BOLD, 15));
        tangkap.setOpaque(false);
        tangkap.setHorizontalTextPosition(SwingConstants.CENTER);
        tangkap.setVerticalTextPosition(SwingConstants.CENTER);
        tangkap.setBorder(BorderFactory.createEmptyBorder());
        tangkap.setForeground(Color.BLACK);
        tangkap.setContentAreaFilled(false);
    
        goToDungeon = new JButton("Dungeon", new ImageIcon(scaledButtonImage));
        goToDungeon.addActionListener(e -> {
            System.out.println("Exit button pressed");
            panelBG.removeAll();
            dispose();
            new DungeonGUI(dungeon, player);

        });
        goToDungeon.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2 + 90, 200- 20, 150, 50);
        goToDungeon.setFont(new Font("Public Pixel", Font.BOLD, 15));
        goToDungeon.setHorizontalTextPosition(SwingConstants.CENTER);
        goToDungeon.setVerticalTextPosition(SwingConstants.CENTER);
        goToDungeon.setBorder(BorderFactory.createEmptyBorder());
        goToDungeon.setForeground(Color.BLACK);
        goToDungeon.setContentAreaFilled(false);
    
        buttonBackgroundPanel.add(tangkap);
        buttonBackgroundPanel.add(goToDungeon);
    
        monsterDead = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon(monsterBattle.getImage());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        monsterDead.setBounds(buttonBackgroundPanel.getX() + 40, buttonBackgroundPanel.getY() - 100, 150, 150);
        monsterDead.setOpaque(false);
        buttonBackgroundPanel.add(monsterDead);
        
        panelBG.setLayout(null); 
        panelBG.setComponentZOrder(buttonBackgroundPanel, 0); 
        panelBG.repaint();
    } else if (isDead(monsterPlayer)) {
        int buttonWidth = 600;
        int buttonHeight = 300;
        monsterBattle.setHealthPoint(monsterBattle.getCurrentMaxHealthPoint());
        buttonBackgroundPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(buttonBackgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        buttonBackgroundPanel.setOpaque(false);
        buttonBackgroundPanel.setBounds((getWidth() - buttonWidth) / 2, (getHeight() - buttonHeight) / 2 - 100, buttonWidth, buttonHeight);
        panelBG.add(buttonBackgroundPanel);
    
        BufferedImage iconInteract = null;
        try {
            iconInteract = ImageIO.read(new File("asset/b4c70567-4596-46d2-a276-4820ae3adaf5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        Image scaledButtonImage = iconInteract.getScaledInstance(150, 50, Image.SCALE_SMOOTH);

        JLabel defeatLabel = new JLabel("Monster " + monsterPlayer.getName() + " mati! Kamu KALAH");
        defeatLabel.setBounds(buttonBackgroundPanel.getX() -60, buttonBackgroundPanel.getY() + 20 , buttonBackgroundPanel.getWidth() - 20, 30);
        defeatLabel.setFont(new Font("Purisa Bold", Font.BOLD, 15));
        
        buttonBackgroundPanel.add(defeatLabel, 0);
    
        goToHomeBase = new JButton("HomeBase", new ImageIcon(scaledButtonImage));
        goToHomeBase.addActionListener(e -> {
            System.out.println("Battle button pressed");
            dispose();
            new HomeBaseGUI();
        });

        goToHomeBase.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2 - 100, 200 - 20, 150, 50);
        goToHomeBase.setFont(new Font("Public Pixel", Font.BOLD, 15));
        goToHomeBase.setHorizontalTextPosition(SwingConstants.CENTER);
        goToHomeBase.setVerticalTextPosition(SwingConstants.CENTER);
        goToHomeBase.setBorder(BorderFactory.createEmptyBorder());
        goToHomeBase.setForeground(Color.BLACK);
        goToHomeBase.setContentAreaFilled(false);
    
        goToDungeon = new JButton("Dungeon", new ImageIcon(scaledButtonImage));
        goToDungeon.addActionListener(e -> {
            System.out.println("Exit button pressed");
            panelBG.removeAll();
            dispose();
            new DungeonGUI(dungeon, player);

        });
        goToDungeon.setBounds((buttonBackgroundPanel.getWidth() - 150) / 2 + 90, 200- 20, 150, 50);
        goToDungeon.setFont(new Font("Public Pixel", Font.BOLD, 15));
        goToDungeon.setHorizontalTextPosition(SwingConstants.CENTER);
        goToDungeon.setVerticalTextPosition(SwingConstants.CENTER);
        goToDungeon.setBorder(BorderFactory.createEmptyBorder());
        goToDungeon.setForeground(Color.BLACK);
        goToDungeon.setContentAreaFilled(false);
    
        buttonBackgroundPanel.add(goToHomeBase);
        buttonBackgroundPanel.add(goToDungeon);
    
        monsterDead = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon(monsterPlayer.getImage());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        monsterDead.setBounds(buttonBackgroundPanel.getX() + 40, buttonBackgroundPanel.getY() - 100, 150, 150);
        monsterDead.setOpaque(false);
        buttonBackgroundPanel.add(monsterDead);
        
        panelBG.setLayout(null); 
        panelBG.setComponentZOrder(buttonBackgroundPanel, 0); 
        panelBG.repaint();
    }
}

public void checkItem(){
    if(curItem != null && !curItem.itemHasRanOut(turn)){
        useItem(curItem, turn++, player);
    }
    if(curItem != null &&curItem.itemHasRanOut(turn)) {
        Template.teksMenghilang(curItem.getName()+" habis", panelBG, 30, 650, 1000, 130, 10);
        turn = 0;
        curItem = null;
    }
}

private void addBattleButtons() throws IOException {
    monsterPlayer = player.deployMonster(indeksMonku);
    BufferedImage bcAttackImage;
    BufferedImage speAttackImage;
    BufferedImage eleAttackImage;
    BufferedImage usePotionImage;
    BufferedImage boxHpImage;
    BufferedImage boxHpImage2;
    
    try {
        bcAttackImage = ImageIO.read(new File("asset/HPBAR/2.png"));
        speAttackImage = ImageIO.read(new File("asset/HPBAR/1.png"));
        eleAttackImage = ImageIO.read(new File("asset/HPBAR/3.png"));
        usePotionImage = ImageIO.read(new File("asset/HPBAR/HPBAR (5).png"));
        boxHpImage = ImageIO.read(new File("asset/HPBAR/5.png"));
        boxHpImage2 = ImageIO.read(new File("asset/HPBAR/HPBAR (6).png"));
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

    JButton bcAttackButton = Template.addButtons(panelBG, bcAttackImage, "asset/HPBAR/2.png", 250, 92, 92, 250, 500, 532);
    JButton speAttackButton = Template.addButtons(panelBG, speAttackImage, "asset/HPBAR/1.png", 250, 92, 92, 250, 740, 532);
    JButton eleAttackButton = Template.addButtons(panelBG, eleAttackImage, "asset/HPBAR/3.png", 250, 92, 92, 250, 500, 623);
    JButton usePotionButton = Template.addButtons(panelBG, usePotionImage, "asset/HPBAR/HPBAR (5).png", 250, 92, 92, 250, 740, 623);

    JPanel hpBoxPanelPlayer = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(boxHpImage2, 0, 0, getWidth(), getHeight(), this);
        }
    };

    int hpBoxWidth = 510;
    int hpBoxHeight = 285;
    int hpBoxX = -5;
    int hpBoxY = 484;

    hpBoxPanelPlayer.setBounds(hpBoxX, hpBoxY, hpBoxWidth, hpBoxHeight);
    hpBoxPanelPlayer.setOpaque(false);

    monsterPlayerPanel = new JPanel(null) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon originalIcon = new ImageIcon(player.getImgBack(indeksMonku));
            Image originalImage = originalIcon.getImage();
            g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    monsterPlayerPanel.setBounds(75, 330, 300, 300);
    monsterPlayerPanel.setOpaque(false);

    JPanel playerHpPanel = createHpPanel(monsterPlayer.getName(), monsterPlayer.getHealthPoint(), monsterPlayer.getCurrentMaxHealthPoint(), 1, monsterPlayer);
    playerHpPanel.setBounds(50, 600, 400, 40);

    panelBG.add(playerHpPanel);
    panelBG.add(hpBoxPanelPlayer);
    panelBG.add(bcAttackButton);
    panelBG.add(speAttackButton);
    panelBG.add(eleAttackButton);
    panelBG.add(usePotionButton);
    panelBG.add(monsterPlayerPanel);

    bcAttackButton.addActionListener(e -> {
        checkItem();
        monsterBattle.getAttacked("basic", monsterPlayer, null);
        updateHpPanel(monsterHpPanel, monsterBattle.getHealthPoint(), monsterBattle.getCurrentMaxHealthPoint(),0,1);
        System.out.println("Updating enemy HP panel");
        System.out.println("Enemy health point: " + monsterBattle.getHealthPoint() + "/" + monsterBattle.getCurrentMaxHealthPoint());
        System.out.println("Monku health point: " + monsterPlayer.getHealthPoint() + "/" + monsterPlayer.getCurrentMaxHealthPoint());
        popUp();
        System.out.println("Monster musuh mati "+isDead(monsterBattle));
        if(isDead(monsterBattle)) return;
        // try {
        //     Thread.sleep(7000); // Menunda selama 7 detik
        // } catch (InterruptedException e3) {
        //     // Tangani pengecualian jika diperlukan
        //     e3.printStackTrace();
        // }
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Basic attack button pressed");
                monsterPlayer.getAttacked(skills[new Random().nextInt(2)], monsterPlayer, null);
                System.out.println("Updating player HP panel");
                updateHpPanel(playerHpPanel, monsterPlayer.getHealthPoint(), monsterPlayer.getCurrentMaxHealthPoint(),1,1);
                popUp();
                System.out.println("2Enemy health point: " + monsterBattle.getHealthPoint() + "/" + monsterBattle.getCurrentMaxHealthPoint());
                System.out.println("Monku health point: " + monsterPlayer.getHealthPoint() + "/" + monsterPlayer.getCurrentMaxHealthPoint());
            }
        } 
        );    
        timer.start();
        timer.setRepeats(false);
        if(isDead(monsterPlayer)) return;
            
    // bcAttackButton.setEnabled(false);
        
    });

    speAttackButton.addActionListener(e -> {
        checkItem();

        System.out.println("Special attack button pressed");
        monsterBattle.getAttacked("special", monsterPlayer, null);
        updateHpPanel(monsterHpPanel, monsterBattle.getHealthPoint(), monsterBattle.getCurrentMaxHealthPoint(),1,2);
        System.out.println("Enemy health point: " + monsterBattle.getHealthPoint() + "/" + monsterBattle.getCurrentMaxHealthPoint());
        System.out.println("Monku health point: " + monsterPlayer.getHealthPoint() + "/" + monsterPlayer.getCurrentMaxHealthPoint());

        popUp();
        if(isDead(monsterBattle))return;
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monsterPlayer.getAttacked(skills[new Random().nextInt(2)], monsterPlayer, null);
                updateHpPanel(playerHpPanel, monsterPlayer.getHealthPoint(), monsterPlayer.getCurrentMaxHealthPoint(),0,2);
                popUp();
                if(isDead(monsterPlayer))return;
                System.out.println("2Enemy health point: " + monsterBattle.getHealthPoint() + "/" + monsterBattle.getCurrentMaxHealthPoint());
                System.out.println("Monku health point: " + monsterPlayer.getHealthPoint() + "/" + monsterPlayer.getCurrentMaxHealthPoint());
            }
        });
        timer.start();
        timer.setRepeats(false);
    });


    eleAttackButton.addActionListener(e -> {
        checkItem();
    
        JPanel charPanel = new JPanel();
        charPanel.setLayout(new GridLayout(0, 1, 10, 10));
        int index = 0;
        JScrollPane scrollPane = new JScrollPane(charPanel);
        scrollPane.setBounds(385, 230, 200, 200);
    
        // panelBG.removeAll();
        panelBG.add(scrollPane);
        for (ElementalAttack s : monsterPlayer.getElementalAttacks()) {
            JButton button = createElementalButton(s, index++, charPanel);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int option = JOptionPane.showConfirmDialog(
                        null, 
                        s.detailAttack(), 
                        s.getNama(), 
                        JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        
                        System.out.println("Elemental attack button pressed");
                        monsterBattle.getAttacked("elemental", monsterPlayer, s);
                        updateHpPanel(monsterHpPanel, monsterBattle.getHealthPoint(), monsterBattle.getCurrentMaxHealthPoint(), 1, 3);
                        System.out.println("Enemy health point: " + monsterBattle.getHealthPoint() + "/" + monsterBattle.getCurrentMaxHealthPoint());
                        panelBG.remove(scrollPane);
                        panelBG.revalidate();
                        panelBG.repaint();
                        popUp();

                        if (isDead(monsterBattle)) return;
                        
                        Timer timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                monsterPlayer.getAttacked(skills[new Random().nextInt(2)], monsterPlayer, null);
                                updateHpPanel(playerHpPanel, monsterPlayer.getHealthPoint(), monsterPlayer.getCurrentMaxHealthPoint(), 0, 3);
                                System.out.println("Monku health point: " + monsterPlayer.getHealthPoint() + "/" + monsterPlayer.getCurrentMaxHealthPoint());
                                popUp();
                                if (isDead(monsterPlayer)) return;
                    }});
                        timer.start();
                        timer.setRepeats(false);
                    } else if (option == JOptionPane.CANCEL_OPTION) {
                        panelBG.remove(scrollPane);
                        panelBG.revalidate();
                        panelBG.repaint();
                    }           
                }
            });
            charPanel.add(button);
        }
        
    });
    

    usePotionButton.addActionListener(e -> {
        checkItem();
        System.out.println("Use potion button pressed");
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);
    
        JButton backButton = new JButton("Cancel");
        backButton.setVisible(true);
        listPanel.add(backButton);
        listPanel.setVisible(true);
        panelBG.add(listPanel);
        panelBG.revalidate();
        panelBG.repaint();
    
        backButton.addActionListener(e1 -> {
            // listPanel.setVisible(false);
            panelBG.remove(listPanel);
            panelBG.remove(verticalScrollBar);
            panelBG.remove(scrollPane);
            // panelBG.remove(listPanel);
            panelBG.revalidate();
            panelBG.repaint();
        });
    
        ArrayList<Item> inventoryItems = new ArrayList<>(player.getInventory().keySet());
        player.getInventory().forEach((k, v) -> {
            System.out.println(k.getName() + " = " + v);
        });
        System.out.println("Inventory items: " + inventoryItems);
    
        for (int i = 0; i < inventoryItems.size(); i++) {
            String detailItem = "";
            Item item = inventoryItems.get(i);
            if (item instanceof BuffPotion) {
                detailItem = ((BuffPotion) item).printDetailItemm();
            } else if (item instanceof DefensivePotion) {
                detailItem = ((DefensivePotion) item).printDetailItemm();
            } else if (item instanceof HealthPotion) {
                detailItem = ((HealthPotion) item).printDetailItemm();
            } else if (item instanceof PoisonPotion) {
                detailItem = ((PoisonPotion) item).printDetailItemm();
            } else if (item instanceof Teleportation) {
                detailItem = ((Teleportation) item).printDetailItemm();
            }
            JButton button = createItemButton(item, detailItem, i, listPanel);
            listPanel.add(button);
        }
    
        JButton pakaiButton = new JButton("Pakai");
        pakaiButton.setVisible(true);
        listPanel.add(pakaiButton);
    
        pakaiButton.addActionListener(e1 -> {
            if (indeksItem != -1) {
                Item selectedItem = inventoryItems.get(indeksItem);
                useItem(selectedItem, turn, player);
                updateHpPanel(monsterHpPanel, monsterBattle.getHealthPoint(), monsterBattle.getCurrentMaxHealthPoint(), 0, 4);
                updateHpPanel(playerHpPanel, monsterPlayer.getHealthPoint(), monsterPlayer.getCurrentMaxHealthPoint(), 0, 4);
                System.out.println("Using item: " + selectedItem.getName());
                listPanel.setVisible(false);
                panelBG.remove(listPanel);
                panelBG.remove(verticalScrollBar);
                panelBG.remove(scrollPane);
                // panelBG.remove();
                panelBG.revalidate();
                panelBG.repaint();
            } else {
                JOptionPane.showMessageDialog(panelBG, "Pilih item terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        scrollPane = new JScrollPane(listPanel);
        scrollPane.setBounds(10, 10, hpBoxPanelPlayer.getWidth() - 20, hpBoxPanelPlayer.getHeight() - 20);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
                this.trackColor = new Color(0, 0, 0, 0);
            }
        });
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBlockIncrement(50);
    
        // hpBoxPanelPlayer.setLayout(null);
        // hpBoxPanelPlayer.add(scrollPane);
    
        panelBG.add(scrollPane);
        panelBG.add(verticalScrollBar);
        panelBG.revalidate();
        panelBG.repaint();
    });    
    panelBG.revalidate();
    panelBG.repaint();
}

private JButton createElementalButton(ElementalAttack s, int i, JPanel panel) {
    JButton elementalButton = new JButton();
    elementalButton.setLayout(new BorderLayout());

    String details = s.getNama() + " x"+ s.getQuantity(); 
    JLabel detailsLabel = new JLabel("<html>" + details.replace("\n", "<br>") + "</html>");

    elementalButton.add(detailsLabel, BorderLayout.CENTER);
    elementalButton.setOpaque(false);
    elementalButton.setBorder(BorderFactory.createEmptyBorder());
    elementalButton.setContentAreaFilled(false);
    elementalButton.setFocusable(false);

    elementalButton.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (selectedItemButton != elementalButton) {
                elementalButton.setBackground(Color.LIGHT_GRAY);
                elementalButton.setOpaque(true);
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (selectedItemButton != elementalButton) {
                elementalButton.setBackground(null);
                elementalButton.setOpaque(false);
            }
        }
    });

    elementalButton.addActionListener(e -> {
        System.out.println("Elemental button clicked: " + details);
        indeksItem = i;
        if (selectedItemButton != null) {
            selectedItemButton.setBackground(null);
            selectedItemButton.setOpaque(false);
        }
        selectedItemButton = elementalButton;
        elementalButton.setBackground(Color.WHITE);
        elementalButton.setOpaque(true);
    });

    return elementalButton;
}


private JButton createItemButton(Item item, String details, int i, JPanel panel) {
    JButton itemButton = new JButton();

    itemButton.setLayout(new BorderLayout());

    JLabel imageLabel = new JLabel(new ImageIcon(item.getImgPath())); // Ganti dengan path gambar item
    JLabel detailsLabel = new JLabel("<html>" + details.replace("\n", "<br>") + "</html>");

    itemButton.add(imageLabel, BorderLayout.WEST);
    itemButton.add(detailsLabel, BorderLayout.CENTER);
    itemButton.setOpaque(false);
    itemButton.setBorder(BorderFactory.createEmptyBorder());
    itemButton.setContentAreaFilled(false);
    itemButton.setFocusable(false);

    itemButton.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (selectedItemButton != itemButton) {
                itemButton.setBackground(Color.LIGHT_GRAY);
                itemButton.setOpaque(true);
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (selectedItemButton != itemButton) {
                itemButton.setBackground(null);
                itemButton.setOpaque(false);
            }
        }
    });

    itemButton.addActionListener(e -> {
        System.out.println("Item button clicked: " + details);
        indeksItem = i;
        if (selectedItemButton != null) {
            selectedItemButton.setBackground(null);
            selectedItemButton.setOpaque(false);
        }
        selectedItemButton = itemButton;
        itemButton.setBackground(Color.WHITE);
        itemButton.setOpaque(true);
    });

    return itemButton;
}


private void useItem(Item item, int turn, Player player) {
    if (item.getName().equals("Jamu Kencur") || item.getName().equals("Jamu Kuat") || item.getName().equals("Susu Kambing")) {
        item.useItem(monsterPlayer, turn, player);
    } 
    if(item.getName().equals("Lidah Buzzer")) {
        item.useItem(monsterBattle, turn, player);
    }

    switch(item.getName()){
        case "Jamu Kuat":
            curItem = item;
            break;
        case "Susu Kambing":
            curItem = item;
            break;
        case "Lidah Buzzer":
            curItem = item;
            break;
    }

    updateHpPanel(monsterHpPanel, monsterBattle.getHealthPoint(), monsterBattle.getCurrentMaxHealthPoint(), 0, 4);
    updateHpPanel(playerHpPanel, monsterPlayer.getHealthPoint(), monsterPlayer.getCurrentMaxHealthPoint(), 0, 4);
}



private JPanel createHpPanel(String name, int currentHp, int maxHp, int color, Monster monster) {
    JPanel hpPanel = new JPanel();
    hpPanel.setLayout(new BorderLayout());
    hpPanel.setOpaque(false);

    JLabel nameLabel = new JLabel(name + ", Level: " + monster.getLevel());
    nameLabel.setFont(new Font("Public Pixel", Font.ITALIC, 15));
    nameLabel.setForeground(color == 0 ? Color.WHITE : Color.BLACK);

    JLabel healthLabel = new JLabel("Health : " + currentHp + "/" + maxHp);
    healthLabel.setFont(new Font("Public Pixel", Font.ITALIC, 8));
    healthLabel.setForeground(color == 0 ? Color.WHITE : Color.BLACK); 
    healthLabel.setName("healthpoint");

    JProgressBar hpBar = new JProgressBar(0, maxHp);
    hpBar.setValue(currentHp);
    hpBar.setForeground(Color.GREEN);
    hpBar.setBackground(Color.RED);
    hpBar.setString(currentHp + "/" + maxHp);
    hpBar.setStringPainted(true);
    hpBar.setName("hpBar"); 

    hpPanel.add(hpBar, BorderLayout.CENTER);
    hpPanel.add(healthLabel, BorderLayout.SOUTH);
    hpPanel.add(nameLabel, BorderLayout.NORTH);

    if (hpPanel == null) {
        hpPanel = new JPanel();
    }

    return hpPanel;
}


private void updateHpPanel(JPanel hpPanel, int currentHp, int maxHp, int monsterOrPlayer, int attackType) {
    ImageIcon[] effectIcons = new ImageIcon[2]; // Array untuk menyimpan dua ikon efek
    JLabel effectLabel = new JLabel();

    switch (attackType) {
        case 1:
            Image uhuy = new ImageIcon("asset/BattleEffect/EFECTOS506-ezgif.com-gif-maker.gif").getImage();
            Image afterAttack = new ImageIcon("asset/BattleEffect/bam.gif").getImage();
            int scaledWidth = 200;
            int scaledHeight = 200;

            BufferedImage resizedImg = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(uhuy, 0, 0, scaledWidth, scaledHeight, null);
            g2.dispose();

            BufferedImage resizedImgAfterAttack = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g3 = resizedImgAfterAttack.createGraphics();
            g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g3.drawImage(afterAttack, 0, 0, scaledWidth, scaledHeight, null);
            g3.dispose();

            effectIcons[0] = new ImageIcon(resizedImg);
            effectIcons[1] = new ImageIcon(resizedImgAfterAttack);
            effectLabel.setIcon(effectIcons[0]); 

            if (effectIcons[0] != null) {
                final int startX;
                final int startY;
                final int endX;
                final int endY;
    
                if (monsterOrPlayer == 0) { // Player attacks Monster
                    startX = monsterPlayerPanel.getX();
                    startY = monsterPlayerPanel.getY();
                    endX = monsterDungeonPanel.getX();
                    endY = monsterDungeonPanel.getY();
                } else { // Monster attacks Player
                    startX = monsterDungeonPanel.getX();
                    startY = monsterDungeonPanel.getY();
                    endX = monsterPlayerPanel.getX();
                    endY = monsterPlayerPanel.getY();
                }
    
                effectLabel.setBounds(startX, startY, effectIcons[0].getIconWidth(), effectIcons[0].getIconHeight());
                panelBG.add(effectLabel, JLayeredPane.PALETTE_LAYER);
                panelBG.revalidate();
                panelBG.repaint();
    
                final int totalSteps = 10;
                final int deltaX = (endX - startX) / totalSteps;
                final int deltaY = (endY - startY) / totalSteps;
    
                Timer moveTimer = new Timer(10, new ActionListener() {
                    int currentStep = 0;
                    int x = startX;
                    int y = startY;
    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (currentStep < totalSteps) {
                            x += deltaX;
                            y += deltaY;
                            effectLabel.setLocation(x, y);
                            // hpPanel.repaint();
                            // hpPanel.remove(effectLabel);
                            currentStep++;
                        } else {
                            ((Timer) e.getSource()).stop();
                            // hpPanel.revalidate();
                            // hpPanel.repaint();
                            effectLabel.setIcon(effectIcons[1]); 
                            System.out.println("z :"+panelBG.getComponentZOrder(effectLabel));
                            panelBG.setComponentZOrder(monsterDungeonPanel, panelBG.getComponentZOrder(effectLabel)-1);
                            // hpPanel.remove(effectLabel);
                            Timer afterAttackTimer = new Timer(2000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    panelBG.remove(effectLabel);
                                    panelBG.revalidate();
                                    panelBG.repaint();
                                }
                            });
                            afterAttackTimer.setRepeats(false);
                            afterAttackTimer.start();
                        }
                    }
                });
                moveTimer.start();
            }

            break;
            case 2:
                Image uhuy2 = new ImageIcon("path").getImage();
                Image afterAttack2 = new ImageIcon("path").getImage();
                int scaledWidth2 = 200;
                int scaledHeight2 = 200;

                BufferedImage resizedImg2 = new BufferedImage(scaledWidth2, scaledHeight2, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2_2 = resizedImg2.createGraphics();
                g2_2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2_2.drawImage(uhuy2, 0, 0, scaledWidth2, scaledHeight2, null);
                g2_2.dispose();

                BufferedImage resizedImgAfterAttack2 = new BufferedImage(scaledWidth2, scaledHeight2, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g3_2 = resizedImgAfterAttack2.createGraphics();
                g3_2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g3_2.drawImage(afterAttack2, 0, 0, scaledWidth2, scaledHeight2, null);
                g3_2.dispose();

                effectIcons[0] = new ImageIcon(resizedImg2);
                effectIcons[1] = new ImageIcon(resizedImgAfterAttack2);
                effectLabel.setIcon(effectIcons[0]);

                if (effectIcons[0] != null) {
                    final int startX;
                    final int startY;
                    final int endX;
                    final int endY;
        
                    if (monsterOrPlayer == 0) { // Player attacks Monster
                        startX = monsterPlayerPanel.getX();
                        startY = monsterPlayerPanel.getY();
                        endX = monsterDungeonPanel.getX();
                        endY = monsterDungeonPanel.getY();
                    } else { // Monster attacks Player
                        startX = monsterDungeonPanel.getX();
                        startY = monsterDungeonPanel.getY();
                        endX = monsterPlayerPanel.getX();
                        endY = monsterPlayerPanel.getY();
                    }
        
                    effectLabel.setBounds(startX, startY, effectIcons[0].getIconWidth(), effectIcons[0].getIconHeight());
                    panelBG.add(effectLabel, JLayeredPane.PALETTE_LAYER);
                    panelBG.revalidate();
                    panelBG.repaint();
        
                    final int totalSteps = 10;
                    final int deltaX = (endX - startX) / totalSteps;
                    final int deltaY = (endY - startY) / totalSteps;
        
                    Timer moveTimer = new Timer(10, new ActionListener() {
                        int currentStep = 0;
                        int x = startX;
                        int y = startY;
        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (currentStep < totalSteps) {
                                x += deltaX;
                                y += deltaY;
                                effectLabel.setLocation(x, y);
                                // hpPanel.repaint();
                                // hpPanel.remove(effectLabel);
                                currentStep++;
                            } else {
                                ((Timer) e.getSource()).stop();
                                // hpPanel.revalidate();
                                // hpPanel.repaint();
                                effectLabel.setIcon(effectIcons[1]); 
                                System.out.println("z :"+panelBG.getComponentZOrder(effectLabel));
                                panelBG.setComponentZOrder(monsterDungeonPanel, panelBG.getComponentZOrder(effectLabel)-1);
                                // hpPanel.remove(effectLabel);
                                Timer afterAttackTimer = new Timer(2000, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panelBG.remove(effectLabel);
                                        panelBG.revalidate();
                                        panelBG.repaint();
                                    }
                                });
                                afterAttackTimer.setRepeats(false);
                                afterAttackTimer.start();
                            }
                        }
                    });
                    moveTimer.start();
                }
                break;
            case 3:
            Image uhuy3 = new ImageIcon("path").getImage();
            Image afterAttack3 = new ImageIcon("path").getImage();
            int scaledWidth3 = 200;
            int scaledHeight3 = 200;

            BufferedImage resizedImg3 = new BufferedImage(scaledWidth3, scaledHeight3, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2_3 = resizedImg3.createGraphics();
            g2_3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_3.drawImage(uhuy3, 0, 0, scaledWidth3, scaledHeight3, null);
            g2_3.dispose();

            BufferedImage resizedImgAfterAttack3 = new BufferedImage(scaledWidth3, scaledHeight3, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g3_3 = resizedImgAfterAttack3.createGraphics();
            g3_3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g3_3.drawImage(afterAttack3, 0, 0, scaledWidth3, scaledHeight3, null);
            g3_3.dispose();

            effectIcons[0] = new ImageIcon(resizedImg3);
            effectIcons[1] = new ImageIcon(resizedImgAfterAttack3);
            effectLabel.setIcon(effectIcons[0]);

            if (effectIcons[0] != null) {
                final int startX;
                final int startY;
                final int endX;
                final int endY;
    
                if (monsterOrPlayer == 0) { // Player attacks Monster
                    startX = monsterPlayerPanel.getX();
                    startY = monsterPlayerPanel.getY();
                    endX = monsterDungeonPanel.getX();
                    endY = monsterDungeonPanel.getY();
                } else { // Monster attacks Player
                    startX = monsterDungeonPanel.getX();
                    startY = monsterDungeonPanel.getY();
                    endX = monsterPlayerPanel.getX();
                    endY = monsterPlayerPanel.getY();
                }
    
                effectLabel.setBounds(startX, startY, effectIcons[0].getIconWidth(), effectIcons[0].getIconHeight());
                panelBG.add(effectLabel, JLayeredPane.PALETTE_LAYER);
                panelBG.revalidate();
                panelBG.repaint();
    
                final int totalSteps = 10;
                final int deltaX = (endX - startX) / totalSteps;
                final int deltaY = (endY - startY) / totalSteps;
    
                Timer moveTimer = new Timer(10, new ActionListener() {
                    int currentStep = 0;
                    int x = startX;
                    int y = startY;
    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (currentStep < totalSteps) {
                            x += deltaX;
                            y += deltaY;
                            effectLabel.setLocation(x, y);
                            // hpPanel.repaint();
                            // hpPanel.remove(effectLabel);
                            currentStep++;
                        } else {
                            ((Timer) e.getSource()).stop();
                            // hpPanel.revalidate();
                            // hpPanel.repaint();
                            effectLabel.setIcon(effectIcons[1]); 
                            System.out.println("z :"+panelBG.getComponentZOrder(effectLabel));
                            panelBG.setComponentZOrder(monsterDungeonPanel, panelBG.getComponentZOrder(effectLabel)-1);
                            // hpPanel.remove(effectLabel);
                            Timer afterAttackTimer = new Timer(2000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    panelBG.remove(effectLabel);
                                    panelBG.revalidate();
                                    panelBG.repaint();
                                }
                            });
                            afterAttackTimer.setRepeats(false);
                            afterAttackTimer.start();
                        }
                    }
                });
                moveTimer.start();
            }
            break;
    }

    if (hpPanel != null) {
        Component[] components = hpPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if ("healthpoint".equals(label.getName())) {
                    System.out.println("Updating health label: " + currentHp + "/" + maxHp);
                    label.setText("Health : " + currentHp + "/" + maxHp);
                    break;
                }
            } else if (component instanceof JProgressBar) {
                JProgressBar hpBar = (JProgressBar) component;
                if ("hpBar".equals(hpBar.getName())) {
                    System.out.println("Updating HP bar: " + currentHp + "/" + maxHp);
                    hpBar.setMaximum(maxHp);
                    hpBar.setValue(currentHp);
                    hpBar.setString(currentHp + "/" + maxHp);
                    hpBar.setStringPainted(true);
                }
            }
        }
    } else {
        System.err.println("hpPanel is null");
    }
}

public boolean isDead(Monster monster) {
    return monster.getHealthPoint() <= 0;
}


private Monster randomDungeon(){
    Random random = new Random();
    return Monster.get(random.nextInt(dungeon.monsterLenght()));
}

private JButton createPokemonButton(String image, String details, int i) {
    JButton pokemonButton = new JButton();
    
    pokemonButton.setLayout(new BorderLayout());

    JLabel imageLabel = new JLabel(new ImageIcon(image));
    JLabel detailsLabel = new JLabel("<html>" + details.replace("\n", "<br>") + "</html>");

    pokemonButton.add(imageLabel, BorderLayout.WEST);
    pokemonButton.add(detailsLabel, BorderLayout.CENTER);
    pokemonButton.setOpaque(false);
    pokemonButton.setBorder(BorderFactory.createEmptyBorder());
    pokemonButton.setContentAreaFilled(false);
    pokemonButton.setFocusable(false);

    pokemonButton.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (selectedPokemonButton != pokemonButton) {
                pokemonButton.setBackground(Color.LIGHT_GRAY);
                pokemonButton.setOpaque(true);
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (selectedPokemonButton != pokemonButton) {
                pokemonButton.setBackground(null);
                pokemonButton.setOpaque(false);
            }
        }
    });

    pokemonButton.addActionListener(e -> {
        System.out.println("Pokemon button clicked: " + details);
        indeksMonku = i;

        // Reset the background of the previously selected button
        if (selectedPokemonButton != null) {
            selectedPokemonButton.setBackground(null);
            selectedPokemonButton.setOpaque(false);
        }    // Set the current button as the selected one
        selectedPokemonButton = pokemonButton;
        pokemonButton.setBackground(Color.WHITE);
        pokemonButton.setOpaque(true);
    });

    return pokemonButton;
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
        Monster monster = new AirType("kehed", 1, 4, "asset/Charmander/charmander.gif");
        Monster monster2 = new AirType("dehek", 1, 4, "asset/rhyhorn/rhyhorn.gif");
        Monster monster3 = new AirType("heked", 1, 4, "asset/Squirtle/squirtle.gif");
        Monster monster4 = new AirType("edehek", 1, 4, "asset/vanillite/vanillite.gif");
        Item item = new BuffPotion("Jamu Kuat", "COMMON");
        Monster[] monsters = {monster};
        Item[] rewards = {item};
        Dungeon dungeon = new Dungeon("Mystic Cave", monsters, rewards, 1, "asset/den4zwg-45a7fe9e-d38a-417c-815c-3e56972adf62.jpg", "asset/wizard1.gif", "Sapi");
        Player player = new Player("Hero", dungeon, "asset/wizard.gif");
        monster2.setAttackPower(900);
        player.catchMonster(monster);
        player.catchMonster(monster2);
        ((AirType)player.getMonsters().get(1)).setElementalSkills("Gust");
        ((AirType)player.getMonsters().get(1)).setElementalSkills("Air Slash");
        player.catchMonster(monster3);
        player.catchMonster(monster4);
        ItemSeller itemSeller = new ItemSeller("p", "p",  new HomeBase("p"));
        player.setCoin(9000);
        player.buyItem(itemSeller.getItem("Jamu Kencur", "COMMON"), 3, itemSeller);
        player.buyItem(itemSeller.getItem("Ludah Buzzer", "COMMON"), 3, itemSeller);
        player.buyItem(itemSeller.getItem("Susu Kambing", "RARE"), 3, itemSeller);

        SwingUtilities.invokeLater(() -> new DungeonGUI(dungeon, player));
    }
}

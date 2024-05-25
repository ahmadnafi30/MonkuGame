package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Entity.Locations.HomeBase;
import Entity.Locations.Shop;
import Entity.Monster.*;
import Entity.NPC.NPC;
import Entity.Player.Player;
import app.Monku;

public class HomeBaseGUI extends JFrame implements ActionListener {
    private CardLayout dialogText;
    private JPanel dialogTextPanel;
    private ArrayList<Component> dialogues = new ArrayList<>();

    public HomeBaseGUI() {
        JFrame frame = new JFrame("Monku Games");
        Monku.player.setLocation(Monku.homeBase);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));
        frame.setVisible(true);

        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/IMG-20231127-WA0029 (1).png");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        frame.setContentPane(panelBG);
        Template.showNameLoc(Monku.player.getLocationPlayer(), panelBG, 100, 700, 900, 340, 10);

        JPanel professor = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/professor.gif");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        professor.setBounds(70, 510, 298 / 2, 295 / 2);
        panelBG.add(professor);

        dialogText = new CardLayout();
        dialogTextPanel = new JPanel(dialogText);
        JPanel dialogBox = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/dialog box with pokeball.png");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        JButton mapButton = Template.mapButton(panelBG, frame);
        mapButton.addActionListener(e -> {
            createDialogCard("Selamat Berpetualang!");
            dialogText.next(dialogTextPanel);
            Timer timer = new Timer(100, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try{
                       new MapGUI();
                       frame.dispose();
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
               } 
            });
            timer.start();
            timer.setRepeats(false);;
        });

        dialogBox.setBounds(3, 450, 980, 265);
        dialogTextPanel.setBounds(120, 550, dialogBox.getWidth() - 160, 265);
        dialogTextPanel.setOpaque(false);
        dialogBox.setOpaque(false);
        panelBG.add(dialogTextPanel);
        panelBG.add(dialogBox);

        createDialogCard("<html><p style=\"margin-left: 39px\">Selamat datang kembali </p><p style=\"margin-left: 39px\">" + Monku.player.getName() + "!</p></html>");        
        createDialogCard("<html><p style=\"margin-left: 39px\">" + Monku.player.getName() + ",</p><p style=\"margin-left: 39px\">Apa yang ingin</p><p style=\"margin-left: 39px\"> kamu lakukan?</p></html>");
        

        // Create an invisible button to capture clicks and switch dialogs
        JButton invisibleButton = new JButton();
        invisibleButton.setBounds(dialogBox.getBounds());
        invisibleButton.setOpaque(false);
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setBorderPainted(false);
        invisibleButton.addActionListener(e -> {
            int cardCount = getCardPosition();
            dialogText.next(dialogTextPanel);
            System.out.println(cardCount);
            if (cardCount == 0 || cardCount == 1) {
                showOptions(invisibleButton, panelBG, frame);
                invisibleButton.setEnabled(false);
            }
            if (isLastCard()) {
                Monku.player.printDetailPlayer();
                // Transition to new scene
                cardCount = 1;
                
            } else {
                dialogText.next(dialogTextPanel);
            }
        });

        panelBG.add(invisibleButton);
        frame.setVisible(true);
    }
    private void showOptions(JButton invis, JPanel panelBG, JFrame frame) {
        String[] options = {"Save Game", "Heal Monku", "Evolve Monku", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Apa yang ingin kamu lakukan?",
                "Pilih opsi",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                createDialogCard("Saving game...");

                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveGame(panelBG, frame, invis);
                    }
                });
                timer.start();
                break;
            case 1:
                createDialogCard("Pilih monster yang ingin kamu heal");
                monkuChoicesHeal(panelBG, frame, invis);
                break;
            case 2:
                createDialogCard("Pilih monster yang ingin kamu evolve");
                monkuChoicesEvolve(panelBG, frame, invis);
                break;
            default:
                // Cancel or closed dialog
                break;
        }
    }

    public void saveGame(JPanel panelBG, JFrame frame, JButton invis) {
        ((HomeBase)Monku.player.getLocationPlayer()).interactWithPlayer(Monku.player, 3, null);
        createDialogCard("Game berhasill disimpan!");
        dialogText.next(dialogTextPanel);
        dialogText.removeLayoutComponent(getComponent(getCardPosition()));
    }

    public void monkuChoicesHeal(JPanel panelBG, JFrame frame, JButton invis) {
        int middle = 450;
        int i = 0;
        ArrayList<JButton> monsters = new ArrayList<>();
    
        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            Monster monsterPlyr = Monku.player.getMonsters().get(j);
            String name = Monku.player.getMonsters().get(j).getName();
            System.out.println(monsterPlyr.getImage());
            ButtonWithIcon monsterButton = Template.createButtonWithGIF(panelBG, monsterPlyr.getImage(), 140, 140, middle + i, 250);
            monsters.add(monsterButton.getButton());
            int pos = middle + i;
            i += 50;
            JButton monster = monsters.get(j);
            monster.setVisible(true);
            int indeksMonku = j;
    
            monsters.get(j).addActionListener(e -> {
                ((HomeBase)Monku.player.getLocationPlayer()).interactWithPlayer(Monku.player, 1, Monku.player.getMonsters().get(indeksMonku));
                JLabel heal = new JLabel();
                heal.setOpaque(false);
                panelBG.add(heal);
                heal.setBounds(pos, 250, 140, 140);
                panelBG.setComponentZOrder(heal, 0); // Set heal to be the top component
                heal.setVisible(true);
    
                // Timer to remove heal effect after a delay
                Timer timer = new Timer(2000, event -> {
                    panelBG.remove(heal);
                    panelBG.revalidate();
                    panelBG.repaint();
                    createDialogCard("Darah "+name + " telah penuh!");
                    dialogText.next(dialogTextPanel);
                    dialogText.removeLayoutComponent(getComponent(getCardPosition()));
                    invis.setEnabled(true);
                    monster.setVisible(false);
                });
                timer.setRepeats(false);
                timer.start();
            });
            panelBG.add(monsters.get(j));
        }
    }

    public void blink(JButton monster) {
        // Create a timer to handle the blinking effect
        Timer timer = new Timer(200, new ActionListener() {
            private boolean visible = false;
            private int count = 0;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= 10) {
                    ((Timer) e.getSource()).stop();
                    monster.setVisible(true);
                    return;
                }
                monster.setVisible(visible);
                visible = !visible;
                count++;
            }
        });
        timer.start();
    }
    
    public void monkuChoicesEvolve(JPanel panelBG, JFrame frame, JButton invis) {
        int middle = 450;
        int i = 0;
        ArrayList<JButton> monsters = new ArrayList<>();
        
        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            Monster monsterPlyr = Monku.player.getMonsters().get(j);
            ButtonWithIcon monsterButton = Template.createButtonWithGIF(panelBG, monsterPlyr.getImage(), 140, 140, middle + i, 250);
            monsters.add(monsterButton.getButton());
            int pos = middle + i;
            i += 50;
            JButton monster = monsters.get(j);
            
            monsters.get(j).addActionListener(e -> {
                String[] options = {"AIR", "FIRE", "WATER", "EARTH", "ICE"};
                String choice = (String) JOptionPane.showInputDialog(null, "Pilih element evolve", "Evolve", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                Monku.professor.evolvePokemon(monsterPlyr, choice);
                JLabel heal = new JLabel();
                heal.setIcon(new ImageIcon("asset/healEffect.gif"));
                heal.setOpaque(false);
                panelBG.add(heal);
                heal.setBounds(pos, 250, 140, 140);
                panelBG.setComponentZOrder(heal, 0); // Set heal to be the top component
                heal.setVisible(true);
                System.out.println(monsterPlyr.getImage());
                blink(monster);
                
                // Timer to remove heal effect after a delay
                Timer timer = new Timer(2000, event -> {
                    panelBG.remove(heal);
                    panelBG.revalidate();
                    panelBG.repaint();
                    createDialogCard(monsterPlyr.getName() + " \ntelah berevolusi!");
                    dialogText.next(dialogTextPanel);
                    invis.setEnabled(true);
                    monster.setIcon(new ImageIcon(monsterPlyr.getImage()));
                    monster.setVisible(false);
                });
                timer.setRepeats(false);
                timer.start();
            });
    
            panelBG.add(monsters.get(j));
        }
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

    private void removeDialog(Component dialog) {
        dialogTextPanel.remove(getComponent(dialog));
        dialogues.remove(dialog);
    }
    public Component getComponent(Component dialog) {
        for(Component comp : dialogues){
            if(comp == dialog){
                return comp;
            }
        }
        return null;
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

    private void newScene() {
        SwingUtilities.invokeLater(() -> {
            try {
                new MapGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void createDialogCard(String text) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Public Pixel", Font.BOLD, 27));
        label.setForeground(Color.BLACK); 
        label.setVisible(true);
        panel.add(label);
        dialogTextPanel.add(panel);
        dialogues.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Action performed!", "Peringatan", JOptionPane.INFORMATION_MESSAGE);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeBaseGUI::new);
        Monku.player.catchMonster(new FireType("Charmander", 1, 4, "asset/Charmander/charmander.gif"));
        Monku.player.getMonsters().get(0).setLevel(20);
    }

}

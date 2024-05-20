package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import Entity.Locations.HomeBase;
import Entity.Monster.Monster;
import Entity.NPC.NPC;
import Entity.Player.Player;
import app.Monku;

public class HomeBaseGUI extends JFrame implements ActionListener {
    private CardLayout dialogText;
    private JPanel dialogTextPanel;

    public HomeBaseGUI(int loadornew) {
        JFrame frame = new JFrame("Monku Games");
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

        dialogBox.setBounds(3, 450, 980, 265);
        dialogTextPanel.setBounds(120, 500, dialogBox.getWidth() - 160, 265);
        dialogTextPanel.setOpaque(false);
        panelBG.add(dialogTextPanel);
        panelBG.add(dialogBox);

        if (loadornew == 2) {
            createDialogCard("<html><p style=\"margin-left: 20px\">Hello " + Monku.player.getName() + "!</p>Apa yang ingin kamu lakukan?</html>");
        }

        // Create an invisible button to capture clicks and switch dialogs
        JButton invisibleButton = new JButton();
        invisibleButton.setBounds(dialogBox.getBounds());
        invisibleButton.setOpaque(false);
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setBorderPainted(false);
        invisibleButton.addActionListener(e -> {
            int cardCount = getCardPosition();
            if (cardCount == 0) {
                showOptions(invisibleButton, panelBG, frame);
                invisibleButton.setEnabled(false);
            }
            if (isLastCard()) {
                Monku.player.printDetailPlayer();
                // Transition to new scene
                newScene();
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
                // Monku.saveGame();
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

    public void monkuChoicesHeal(JPanel panelBG, JFrame frame, JButton invis) {
        int middle = 450;
        int i = 0;
        ArrayList<JButton> monsters = new ArrayList<>();
    
        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            String name = Monku.player.getMonsters().get(j).getName();
            ButtonWithIcon monsterButton = Template.createButtonWithGIF(panelBG, "asset/" + name + ".gif", 140, 140, middle + i, 250);
            monsters.add(monsterButton.getButton());
            int pos = middle + i;
            i += 50;
            JButton monster = monsters.get(j);
    
            monsters.get(j).addActionListener(e -> {
                Monku.professor.healPokemon(Monku.player, name);
                JLabel heal = new JLabel();
                heal.setIcon(new ImageIcon("asset/healEffect.gif"));
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
                    invis.setEnabled(true);
                    monster.setVisible(false);
                });
                timer.setRepeats(false);
                timer.start();
            });
    
            panelBG.add(monsters.get(j));
        }
    }

    public void monkuChoicesEvolve(JPanel panelBG, JFrame frame, JButton invis) {
        int middle = 450;
        int i = 0;
        ArrayList<JButton> monsters = new ArrayList<>();
    
        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            String name = Monku.player.getMonsters().get(j).getName();
            ButtonWithIcon monsterButton = Template.createButtonWithGIF(panelBG, "asset/" + name + ".gif", 140, 140, middle + i, 250);
            monsters.add(monsterButton.getButton());
            int pos = middle + i;
            i += 50;
            JButton monster = monsters.get(j);
            Monster monsterPlyr = Monku.player.getMonsters().get(j);
    
            monsters.get(j).addActionListener(e -> {
                Monku.professor.evolvePokemon(monsterPlyr, "AIR");
                JLabel heal = new JLabel();
                heal.setIcon(new ImageIcon("asset/healEffect.gif"));
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
                    invis.setEnabled(true);
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
        // Logic to transition to the new scene
        JOptionPane.showMessageDialog(this, "Switching to new scene!");
        // Example: Switch to a new JFrame or change content
        // JFrame newFrame = new JFrame("New Scene");
        // newFrame.setSize(1000, 750);
        // newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // newFrame.setLocationRelativeTo(null);
        // newFrame.setVisible(true);
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

}

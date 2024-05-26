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
    private ArrayList<JButton> monsters = new ArrayList<>();
    // private JPanel monsterPanel = new JPanel();
    // private JScrollPane scrollPane = new JScrollPane(monsterPanel);
    
    public HomeBaseGUI() {
        //monsterPanel.setLayout(new BoxLayout(monsterPanel, BoxLayout.X_AXIS));
        JFrame frame = new JFrame("Monku Games");
        Monku.player.setLocation(Monku.homeBase);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));
        frame.setVisible(true);
        //monsterPanel.setVisible(true);
        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/IMG-20231127-WA0029 (1).png");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        //scrollPane.setBounds(340, 160, 300, 250);
        //scrollPane.setOpaque(false);
        
        //panelBG.add(scrollPane);
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
                    try {
                        new MapGUI();
                        frame.dispose();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            timer.start();
            timer.setRepeats(false);
            ;
        });

        dialogBox.setBounds(3, 450, 980, 265);
        dialogTextPanel.setBounds(120, 550, dialogBox.getWidth() - 160, 265);
        dialogTextPanel.setOpaque(false);
        dialogBox.setOpaque(false);
        panelBG.add(dialogTextPanel);
        panelBG.add(dialogBox);

        createDialogCard(
                "<html><p style=\"margin-left: 39px\">Selamat datang kembali </p><p style=\"margin-left: 39px\">"
                        + Monku.player.getName() + "!</p></html>");
        createDialogCard("<html><p style=\"margin-left: 39px\">" + Monku.player.getName()
                + ",</p><p style=\"margin-left: 39px\">Apa yang ingin</p><p style=\"margin-left: 39px\"> kamu lakukan?</p></html>");

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
            System.out.println(isLastCard());

            if (cardCount == 0) {
                createDialogCard("<html><p style=\"margin-left: 39px\">" + Monku.player.getName()
                        + ",</p><p style=\"margin-left: 39px\">Apa yang ingin</p><p style=\"margin-left: 39px\"> kamu lakukan?</p></html>");
                dialogText.next(dialogTextPanel);
                monsters.forEach(g -> {
                    panelBG.remove(g);
                    System.out.println(g + "removed");
                });
                panelBG.repaint();
                panelBG.revalidate();
                showOptions(invisibleButton, panelBG, frame);
            } else {
                dialogText.next(dialogTextPanel);
                removeAllDialogCards(); // This line removes all dialog cards correctly.
                
                createDialogCard("<html><p style=\"margin-left: 39px\">" + Monku.player.getName()
                        + ",</p><p style=\"margin-left: 39px\">Apa yang ingin</p><p style=\"margin-left: 39px\"> kamu lakukan?</p></html>");
                dialogText.first(dialogTextPanel); // Reset to the first card
                monsters.forEach(g -> {
                    panelBG.remove(g);
                    System.out.println(g + "removed");
                });
                panelBG.repaint();
                panelBG.revalidate();
            }

            SwingUtilities.updateComponentTreeUI(frame);
        });
        panelBG.add(invisibleButton);
        frame.setVisible(true);
    }

    private void showOptions(JButton invis, JPanel panelBG, JFrame frame) {
        String[] options = { "Save Game", "Heal Monku", "Evolve Monku", "Cancel" };
        int choice = JOptionPane.showOptionDialog(
                this,
                "Apa yang ingin kamu lakukan?",
                "Pilih opsi",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                createDialogCard("Saving game...");
                dialogText.next(dialogTextPanel);
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveGame(panelBG, frame, invis);
                    }
                });
                timer.start();
                timer.setRepeats(false);
                break;
            case 1:
                createDialogCard(
                        "<html><p style=\"margin-left: 39px\">Pilih monster yang </p><p style=\"margin-left: 39px\">ingin kamu heal</p></html>");
                dialogText.next(dialogTextPanel);
                monkuChoicesHeal(panelBG, frame, invis);
                break;
            case 2:
                createDialogCard(
                        "<html><p style=\"margin-left: 39px\">Pilih monster yang </p><p style=\"margin-left: 39px\">ingin kamu evolve</p></html>");
                dialogText.next(dialogTextPanel);
                monkuChoicesEvolve(panelBG, frame, invis);
                break;
            default:
                // Cancel or closed dialog
                break;
        }
    }

    public void saveGame(JPanel panelBG, JFrame frame, JButton invis) {
        ((HomeBase) Monku.player.getLocationPlayer()).interactWithPlayer(Monku.player, 3, null);
        createDialogCard("<html><p style=\"margin-left: 39px\">Game berhasil disimpan!</p></html>");

        dialogText.next(dialogTextPanel);
    }

    public void monkuChoicesHeal(JPanel panelBG, JFrame frame, JButton invis) {
        //monsterPanel.setVisible(true);
        int middle = 450;
        int i = 0;
        
        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            final int curIndex = j;
            Monster monsterPlyr = Monku.player.getMonsters().get(curIndex);
            System.out.println(monsterPlyr.getImage());
            int pos = middle + (i*100);
            ButtonWithIcon monsterButton = Template.createButtonWithGIF(panelBG, monsterPlyr.getImage(), 140, 140,
                    pos, 250);
            monsters.add(monsterButton.getButton());
            i++;
            JButton monster = monsters.get(curIndex);
            monster.setText(monsterPlyr.getName());
            monster.setVisible(true);
            System.out.println(monster);
            panelBG.add(monster);
            panelBG.revalidate();
            panelBG.repaint();
            monster.addActionListener(e -> {
                int desc = JOptionPane.showConfirmDialog(panelBG, monsterPlyr.displayDetailMonsterReturn(), "Detail", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, monsterButton.getIcon());
                if (desc != JOptionPane.OK_OPTION) {
                    return;
                }
                Monku.professor.healPokemon(Monku.player, monsterPlyr.getName());
                Monku.player.getMonsters().get(curIndex).getElementalAttacks().forEach(l ->{
                    l.setQuantity(l.getMaxQuantity());
                });
                JLabel heal = new JLabel();
                heal.setIcon(new ImageIcon("asset/healEffect.gif"));
                heal.setOpaque(false);
                panelBG.add(heal);
                heal.setBounds(pos, 250, 140, 140);
                panelBG.setComponentZOrder(heal, 0); // Set heal to be the top component
                heal.setVisible(true);
                System.out.println(monsterPlyr.getImage());

                // Timer to remove heal effect after a delay
                Timer timer = new Timer(2000, event -> {
                    panelBG.remove(heal);
                    panelBG.revalidate();
                    panelBG.repaint();
                    createDialogCard("<html><p style=\"margin-left: 39px\">Darah " + monsterPlyr.getName()
                            + "</p><p style=\"margin-left: 39px\">telah penuh!</p></html>");
                    dialogText.next(dialogTextPanel);
                    removeDialogCardByText("<html><p style=\"margin-left: 39px\">Darah " + monsterPlyr.getName()
                            + "</p><p style=\"margin-left: 39px\">telah penuh!</p></html>\"");
                    invis.setEnabled(true);
                    monster.setIcon(new ImageIcon(monsterPlyr.getImage()));
                    monster.setVisible(false);
                    monsters.forEach(f -> {
                        f.setVisible(false);
                    });
                    monsters.removeAll(monsters);
                });
                timer.setRepeats(false);
                timer.start();
                monsterPlyr.displayDetailMonster();
            });
        }
        panelBG.revalidate();
        panelBG.repaint();
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

        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            Monster monsterPlyr = Monku.player.getMonsters().get(j);
            Holder<Monster> monsterHolder = new Holder<>(monsterPlyr);
            ButtonWithIcon monsterButton = Template.createButtonWithGIF(panelBG, monsterPlyr.getImage(), 140, 140,
                    middle + i, 250);
            monsters.add(monsterButton.getButton());
            int pos = middle + i;
            i += 100;
            JButton monster = monsters.get(j);
            
            // monsterPanel.add(monster);
            // monsterPanel.revalidate();
            // monsterPanel.repaint();
            SwingUtilities.updateComponentTreeUI(frame);
            monster.setVisible(true);
            final int currentIndex = j; // Create a final copy of j

            monsters.get(currentIndex).addActionListener(e -> {
                int desc = JOptionPane.showConfirmDialog(panelBG, monsterPlyr.displayDetailMonsterReturn(), "Detail", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, monsterButton.getIcon());
                if (desc != JOptionPane.OK_OPTION) {
                    return;
                }
                String[] options = { "AIR", "FIRE", "WATER", "EARTH", "ICE" };
                String choice = (String) JOptionPane.showInputDialog(null, "Pilih element evolve", "Evolve",
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (choice != null) {
                    boolean success = Monku.professor.evolvePokemon(monsterPlyr, choice);
                    Monster currentMonster = monsterHolder.getValue();
                    if (success) {
                        Monster evolvedMonster = currentMonster.changeMonsterClass(); // Use the final copy index
                        Monku.player.getMonsters().set(currentIndex, evolvedMonster); // Update the monster in player's list
                        monsterHolder.setValue(evolvedMonster); // Update the holder with the new monster

                        Monku.player.getMonsters().get(currentIndex).getElementalAttacks().forEach(l ->{
                            l.setQuantity(l.getMaxQuantity());
                        });
                        JLabel evolve = new JLabel();
                        System.out.println(choice);
                        monsterPlyr.changeMonsterClass();
                        evolve.setIcon(new ImageIcon("asset/evolveFx.gif"));
                        evolve.setOpaque(false);
                        panelBG.add(evolve);
                        evolve.setBounds(pos, 250, 140, 140);
                        panelBG.setComponentZOrder(evolve, 0); // Set evolve to be the top component
                        evolve.setVisible(true);
                        System.out.println(monsterPlyr.getImage());
                        System.out.println(monsterPlyr.getClass());
                        blink(monster);

                        // Timer to remove evolve effect after a delay
                        Timer timer = new Timer(2000, event -> {
                            panelBG.remove(evolve);
                            panelBG.revalidate();
                            panelBG.repaint();
                            createDialogCard("<html><p style=\"margin-left: 39px\">" + monsterPlyr.getName()
                                    + "</p><p style=\"margin-left: 39px\">telah berevolusi!</p></html>");
                            dialogText.next(dialogTextPanel);
                            invis.setEnabled(true);
                            monster.setIcon(new ImageIcon(monsterPlyr.getImage()));
                            monster.setVisible(false);
                            System.out.println(Monku.player.getMonsters());
                        });
                        timer.setRepeats(false);
                        timer.start();
                        monsterPlyr.displayDetailMonster();
                        monsters.removeAll(monsters);
                    } else {
                        invis.setEnabled(true);
                        monster.setIcon(new ImageIcon(monsterPlyr.getImage()));
                        monster.setVisible(false);
                        createDialogCard("<html><p style=\"margin-left: 39px\">Evolusi gagal. Coba lagi.</p></html>");
                        dialogText.next(dialogTextPanel);
                    }
                }
            });

            panelBG.add(monsters.get(j));
        }
        panelBG.revalidate();
        panelBG.repaint();
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

    private void removeDialogCard(int index) {
        if (index >= 0 && index < dialogTextPanel.getComponentCount()) {
            Component componentToRemove = dialogTextPanel.getComponent(index);
            dialogTextPanel.remove(componentToRemove);
            dialogues.remove(componentToRemove);
            dialogTextPanel.revalidate();
            dialogTextPanel.repaint();
        } else {
            System.out.println("Invalid index. Unable to remove dialog card.");
        }
    }

    public void removeDialogCardByText(String text) {
        int index = findDialogCardIndexByText(text);
        if (index != -1) {
            removeDialogCard(index);
        } else {
            System.out.println("Dialog card with the specified text not found.");
        }
    }

    public Component getComponent(Component dialog) {
        for (Component comp : dialogues) {
            if (comp == dialog) {
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

    private void createDialogCard(String text) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Public Pixel", Font.BOLD, 27));
        label.setForeground(Color.BLACK);
        label.setVisible(true);
        panel.add(label);
        dialogTextPanel.add(panel);
        dialogues.add(panel);
    }

    private int findDialogCardIndexByText(String text) {
        for (int i = 0; i < dialogues.size(); i++) {
            if (dialogues.get(i) instanceof JPanel) {
                JPanel panel = (JPanel) dialogues.get(i);
                for (Component component : panel.getComponents()) {
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        System.out.println(label.getText());
                        if (label.getText().contains(text)) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void removeAllDialogCards() {
        dialogTextPanel.removeAll();
        dialogues.clear();
        dialogTextPanel.revalidate();
        dialogTextPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Action performed!", "Peringatan", JOptionPane.INFORMATION_MESSAGE);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeBaseGUI::new);
        Monku.player.catchMonster(new FireType("Charmander", 2, 3));
        Monku.player.catchMonster(new EarthType("Squirtle", 1, 3));
        Monku.player.getMonsters().get(0).setLevel(40);
        System.out.println(Monku.player.getMonsters().get(0).getClass());
    }

}

package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    private JButton selectedItemButton = null;
    // private int indeksItem = -1;
    String[] skillsAirName = {
            "Gust",
            "Air Slash",
            "Hurricane",
            "Aerial Ace",
            "Sky Attack",
            "Air Cutter",
            "Fly"
    };
    String[] skillsFireName = {
            "Ember",
            "Flame Thrower",
            "Fire Blast",
            "Fire Spin",
            "Heat Wave",
            "Inferno",
            "Flame Charge",
            "Overheat"
    };
    String[] skillsEarthName = {
            "Tackle",
            "Mud-Slap",
            "Earthquake",
            "Dig",
            "Mud Shot",
            "Sand Tomb",
            "Magnitude",
            "Earth Power"
    };
    String[] skillsIceName = {
            "Ice Beam",
            "Hydro Pump",
            "Blizzard",
            "Frost Breath",
            "Icicle Spear",
            "Avalanche",
            "Ice Fang",
            "Icy Wind",
            "Ice Shard"
    };
    String[] skillsWaterName = {
            "Bubble",
            "Water Gun",
            "Aqua Jet",
            "Surf",
            "Hydro Pump",
            "Waterfall",
            "Aqua Tail",
            "Scald"
    };

    // private JPanel monsterPanel = new JPanel();
    // private JScrollPane scrollPane = new JScrollPane(monsterPanel);

    public HomeBaseGUI() {
        Template.stopMusic();
        // monsterPanel.setLayout(new BoxLayout(monsterPanel, BoxLayout.X_AXIS));
        JFrame frame = new JFrame("Monku Games");
        Monku.player.setLocation(Monku.homeBase);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("asset/Screenshot 2024-05-15 192702.png");
        frame.setIconImages(Collections.singletonList(icon.getImage()));
        frame.setVisible(true);
        // monsterPanel.setVisible(true);
        JPanel panelBG = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("asset/IMG-20231127-WA0029 (1).png");
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // panelBG.add(scrollPane);
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
        Template.playMusic("asset/Music/The Pokémon Lab.wav");
    }

    private void showOptions(JButton invis, JPanel panelBG, JFrame frame) {
        String[] options = { "Save Game", "Heal Monku", "Evolve Monku", "Level Up Monku", "Cancel" };
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
            case 3:
                createDialogCard(
                        "<html><p style=\"margin-left: 39px\">Pilih monster yang </p><p style=\"margin-left: 39px\">ingin kamu level up</p></html>");
                dialogText.next(dialogTextPanel);
                monkuChoicesLevelUp(panelBG, frame, invis);
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

    public void monkuChoicesLevelUp(JPanel panelBG, JFrame frame, JButton invis) {
        int middle = 450;
        int i = 0;

        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            final int curIndex = j;
            Monster monsterPlyr = Monku.player.getMonsters().get(curIndex);
            System.out.println(monsterPlyr.getImage());
            int pos = middle + (i * 100);
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
            String[] attributes = {
                    "Health Point",
                    "Attack Power",
                    "Special Attack Power",
                    "Elemental Attack Power",
                    "Defense Power"
            };
            monster.addActionListener(e -> {
                int desc = JOptionPane.showConfirmDialog(panelBG, monsterPlyr.displayDetailMonsterReturn(), "Detail",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, monsterButton.getIcon());
                if (desc != JOptionPane.OK_OPTION) {
                    return;
                }
                if (monsterPlyr.getExperiencePoint() < Monster.EXP_MAX) {
                    createDialogCard(
                            "<html><p style=\"margin-left: 39px\">Anda tidak punya exp</p><p style=\"margin-left: 39px\">yang cukup!</p></html>");
                    dialogText.next(dialogTextPanel);
                    return;
                }
                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(new File("asset/lvlup.jpg"));
                } catch (IOException er) {
                    er.printStackTrace();
                    return;
                }

                // Resize the image
                int newWidth = 100; // desired width
                int newHeight = 100; // desired height
                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                // Create a new ImageIcon with the resized image
                ImageIcon resizedIcon = new ImageIcon(resizedImage);

                int choice = JOptionPane.showOptionDialog(null, "Pilih atribut yang ingin di level up",
                        "Level Up", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        resizedIcon, attributes,
                        attributes[0]);
                boolean success = Monku.professor.levelUpMonku(Monku.player.getMonsters().get(curIndex),
                        attributes[choice]);
                if (!success)
                    return;
                Monku.player.getMonsters().get(curIndex).getElementalAttacks().forEach(l -> {
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
                    createDialogCard("<html><p style=\"margin-left: 39px\">" + monsterPlyr.getName()
                            + "</p><p style=\"margin-left: 39px\">berhasil di level up!</p></html>");
                    dialogText.next(dialogTextPanel);
                    invis.setEnabled(true);
                    monster.setIcon(new ImageIcon(monsterPlyr.getImage()));
                    monster.setVisible(false);
                    monsters.forEach(f -> {
                        f.setVisible(false);
                    });
                    int display = JOptionPane.showConfirmDialog(panelBG,
                            Monku.player.getMonsters().get(curIndex).displayDetailMonsterReturn(),
                            "Detail", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon(Monku.player.getMonsters().get(curIndex).getImage()));
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

    public void monkuChoicesHeal(JPanel panelBG, JFrame frame, JButton invis) {
        // monsterPanel.setVisible(true);
        int middle = 450;
        int i = 0;

        for (int j = 0; j < Monku.player.getMonsters().size(); j++) {
            final int curIndex = j;
            Monster monsterPlyr = Monku.player.getMonsters().get(curIndex);
            System.out.println(monsterPlyr.getImage());
            int pos = middle + (i * 100);
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
                int desc = JOptionPane.showConfirmDialog(panelBG, monsterPlyr.displayDetailMonsterReturn(), "Detail",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, monsterButton.getIcon());
                if (desc != JOptionPane.OK_OPTION) {
                    return;
                }
                Monku.professor.healPokemon(Monku.player, monsterPlyr.getName());
                Monku.player.getMonsters().get(curIndex).getElementalAttacks().forEach(l -> {
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
                int desc = JOptionPane.showConfirmDialog(panelBG, monsterPlyr.displayDetailMonsterReturn(), "Detail",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, monsterButton.getIcon());
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
                        System.out.println("evolved : " + evolvedMonster.getClass());
                        Monku.player.getMonsters().set(currentIndex, evolvedMonster); // Update the monster in player's
                                                                                      // list
                        monsterHolder.setValue(evolvedMonster); // Update the holder with the new monster
                        System.out.println("player : " + Monku.player.getMonsters().get(currentIndex).getClass());

                        Monku.player.getMonsters().get(currentIndex).getElementalAttacks().forEach(l -> {
                            l.setQuantity(l.getMaxQuantity());
                        });

                        System.out.println(monsterPlyr.getClass());

                        int choice2 = JOptionPane.showOptionDialog(null,
                                "Apakah mau menambah/mengganti elemental skill?", "Pergantian atau Pertambahan",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                new String[] { "Pertambahan", "Pergantian", "Tetap" }, "Pertambahan");
                        System.out.println(choice);
                        if (choice2 == 0) {
                            if (monsterPlyr.getElementalAttacks().size() < 2) {
                                tambahSkill(choice, Monku.player.getMonsters().get(currentIndex));
                                JLabel newMonster = new JLabel(new ImageIcon(monsterPlyr.getImage()));
                                animasiEvolve(monsterPlyr, newMonster, monster, panelBG, invis, pos);
                                monsterPlyr.setAttackPower(monsterPlyr.getCurrentMaxAttackPower());
                        monsterPlyr.setHealthPoint(monsterPlyr.getCurrentMaxHealthPoint());
                        monsterPlyr.setSpcAttackPower(monsterPlyr.getCurrentMaxSpcAttackPower());
                        monsterPlyr.setDefensePower(monsterPlyr.getMaxDefensePower());
                                Timer timer1 = new Timer(2500, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int display = JOptionPane.showConfirmDialog(panelBG,
                                                Monku.player.getMonsters().get(currentIndex)
                                                        .displayDetailMonsterReturn(),
                                                "Detail", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                new ImageIcon(Monku.player.getMonsters().get(currentIndex).getImage()));
                                        if (display == JOptionPane.OK_OPTION) {
                                            panelBG.remove(newMonster);
                                            panelBG.revalidate();
                                            panelBG.repaint();
                                        } else {
                                            panelBG.remove(newMonster);
                                            panelBG.revalidate();
                                            panelBG.repaint();
                                        }
                                    }
                                });
                                timer1.start();
                                timer1.setRepeats(false);
                            } else {
                                JOptionPane.showMessageDialog(null, "Jumlah Elemental Skills Maksimal 2", "Gagal",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } else if (choice2 == 1) {
                            String[] changeChoices = new String[monsterPlyr.getElementalAttacks().size()];
                            for (int k = 0; k < monsterPlyr.getElementalAttacks().size(); k++) {
                                changeChoices[k] = monsterPlyr.getElementalAttacks().get(k).getNama();
                            }
                            monsterPlyr.setAttackPower(monsterPlyr.getCurrentMaxAttackPower());
                        monsterPlyr.setHealthPoint(monsterPlyr.getCurrentMaxHealthPoint());
                        monsterPlyr.setSpcAttackPower(monsterPlyr.getCurrentMaxSpcAttackPower());
                        monsterPlyr.setDefensePower(monsterPlyr.getMaxDefensePower());
                            int changedSkill = JOptionPane.showOptionDialog(null,
                                    "Pilih Elemental Skill yang Mau Diubah", "Pilih Elemental Skill",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, changeChoices, 0);
                            gantiSkill(changedSkill, Monku.player.getMonsters().get(currentIndex).getELementTypeStr(),
                                    Monku.player.getMonsters().get(currentIndex));
                            JLabel newMonster = new JLabel(new ImageIcon(monsterPlyr.getImage()));
                            animasiEvolve(monsterPlyr, newMonster, monster, panelBG, invis, pos);
                            Timer timer1 = new Timer(2500, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int display = JOptionPane.showConfirmDialog(panelBG,
                                            Monku.player.getMonsters().get(currentIndex).displayDetailMonsterReturn(),
                                            "Detail", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE,
                                            new ImageIcon(Monku.player.getMonsters().get(currentIndex).getImage()));
                                    if (display == JOptionPane.OK_OPTION) {
                                        panelBG.remove(newMonster);
                                        panelBG.revalidate();
                                        panelBG.repaint();
                                    } else {
                                        panelBG.remove(newMonster);
                                        panelBG.revalidate();
                                        panelBG.repaint();
                                    }
                                    dialogText.next(dialogTextPanel);
                                }
                            });
                            timer1.start();
                            timer1.setRepeats(false);
                        } else {
                            JLabel newMonster = new JLabel(new ImageIcon(monsterPlyr.getImage()));
                            animasiEvolve(monsterPlyr, newMonster, monster, panelBG, invis, pos);
                            monsterPlyr.setAttackPower(monsterPlyr.getCurrentMaxAttackPower());
                        monsterPlyr.setHealthPoint(monsterPlyr.getCurrentMaxHealthPoint());
                        monsterPlyr.setSpcAttackPower(monsterPlyr.getCurrentMaxSpcAttackPower());
                        monsterPlyr.setDefensePower(monsterPlyr.getMaxDefensePower());
                            Timer timer1 = new Timer(2500, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int display = JOptionPane.showConfirmDialog(panelBG,
                                            Monku.player.getMonsters().get(currentIndex).displayDetailMonsterReturn(),
                                            "Detail", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE,
                                            new ImageIcon(Monku.player.getMonsters().get(currentIndex).getImage()));
                                    if (display == JOptionPane.OK_OPTION) {
                                        panelBG.remove(newMonster);
                                        panelBG.revalidate();
                                        panelBG.repaint();
                                    } else {
                                        panelBG.remove(newMonster);
                                        panelBG.revalidate();
                                        panelBG.repaint();
                                    }
                                }
                            });
                            timer1.start();
                            timer1.setRepeats(false);
                        }
                        createDialogCard("<html><p style=\"margin-left: 39px\">" + monsterPlyr.getName()
                                + " telah berevolusi!</p></html>");
                        monsterPlyr.setAttackPower(monsterPlyr.getCurrentMaxAttackPower());
                        monsterPlyr.setHealthPoint(monsterPlyr.getCurrentMaxHealthPoint());
                        monsterPlyr.setSpcAttackPower(monsterPlyr.getCurrentMaxSpcAttackPower());
                        monsterPlyr.setDefensePower(monsterPlyr.getMaxDefensePower());
                        dialogText.next(dialogTextPanel);
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

    public void gantiSkill(int index, String choice, Monster monsterPlyr) {
        if (choice.equalsIgnoreCase("AIR")) {
            System.out.println("Air : " + ((AirType) monsterPlyr).changeElementalSkills(index,
                    skillsAirName[new Random().nextInt(skillsAirName.length)]));
        } else if (choice.equalsIgnoreCase("FIRE")) {
            System.out.println("Fire : " + ((FireType) monsterPlyr).changeElementalSkills(index,
                    skillsFireName[new Random().nextInt(skillsFireName.length)]));
        } else if (choice.equalsIgnoreCase("WATER")) {
            ((WaterType) monsterPlyr).changeElementalSkills(index,
                    skillsWaterName[new Random().nextInt(skillsWaterName.length)]);
        } else if (choice.equalsIgnoreCase("EARTH")) {
            ((EarthType) monsterPlyr).changeElementalSkills(index,
                    skillsEarthName[new Random().nextInt(skillsEarthName.length)]);
        } else if (choice.equalsIgnoreCase("ICE")) {
            ((IceType) monsterPlyr).changeElementalSkills(index,
                    skillsIceName[new Random().nextInt(skillsIceName.length)]);
        }
    }

    public void tambahSkill(String choice, Monster monsterPlyr) {
        if (choice.equals("AIR")) {
            ((AirType) monsterPlyr).addElementalSkills(skillsAirName[new Random().nextInt(skillsAirName.length)]);
        } else if (choice.equals("FIRE")) {
            ((FireType) monsterPlyr).addElementalSkills(skillsFireName[new Random().nextInt(skillsFireName.length)]);
        } else if (choice.equals("WATER")) {
            ((WaterType) monsterPlyr).addElementalSkills(skillsWaterName[new Random().nextInt(skillsWaterName.length)]);
        } else if (choice.equals("EARTH")) {
            ((EarthType) monsterPlyr).addElementalSkills(skillsEarthName[new Random().nextInt(skillsEarthName.length)]);
        } else if (choice.equals("ICE")) {
            ((IceType) monsterPlyr).addElementalSkills(skillsIceName[new Random().nextInt(skillsIceName.length)]);
        }
    }

    public void pilihSkills() {
        JOptionPane.showInputDialog(null, "Pilih skill", "Pilih Skill", JOptionPane.QUESTION_MESSAGE);
    }

    private void animasiEvolve(Monster monsterPlyr, JLabel newMonster, JButton monster, JPanel panelBG, JButton invis,
            int pos) {
        JLabel evolve = new JLabel();
        evolve.setIcon(new ImageIcon("asset/evolveFx.gif"));
        evolve.setOpaque(false);
        panelBG.add(evolve);
        evolve.setBounds(pos, 250, 140, 140);
        panelBG.setComponentZOrder(evolve, 0); // Set evolve to be the top component
        evolve.setVisible(true);
        blink(monster);
        // Timer to remove evolve effect after a delay
        Timer timer = new Timer(2000, event -> {
            panelBG.remove(evolve);
            panelBG.revalidate();
            panelBG.repaint();
            dialogText.next(dialogTextPanel);
            invis.setEnabled(true);
            monster.setVisible(false);
            panelBG.remove(monster);
            panelBG.add(newMonster);
            panelBG.revalidate();
            panelBG.repaint();
            newMonster.setVisible(true);
            newMonster.setBounds(pos, 250, 140, 140);
            panelBG.setComponentZOrder(newMonster, 0);
            System.out.println(Monku.player.getMonsters());
            createDialogCard("<html><p style=\"margin-left: 39px\">" + monsterPlyr.getName()
                    + "</p><p style=\"margin-left: 39px\">telah berevolusi!</p></html>");
        });
        timer.setRepeats(false);
        timer.start();
        monsterPlyr.displayDetailMonster();
        monsters.removeAll(monsters);
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
        Monku.player.getMonsters().get(0).setExperiencePoint(1000);
        Monku.player.getMonsters().get(0).setLevel(40);
        Monku.player.getMonsters().get(0).displayDetailMonster();
        System.out.println(((FireType) Monku.player.getMonsters().get(0)).addElementalSkills("Ember"));
        Monku.player.catchMonster(new IceType("Squirtle", 1, 3, "asset/squirtle/squirtle.gif"));
        System.out.println(Monku.player.getMonsters().get(0).getClass());
        Monku.player.getMonsters().get(1).setLevel(40);
    }

}

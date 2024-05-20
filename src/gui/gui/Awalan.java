package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;

import Entity.Locations.HomeBase;
import Entity.Monster.Monster;
import Entity.NPC.NPC;
import Entity.Player.Player;
import app.Monku;

public class Awalan extends JFrame implements ActionListener {
    private CardLayout dialogText;
    private JPanel dialogTextPanel;

    public Awalan(int loadornew) {
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
        dialogTextPanel.setOpaque(false);
        panelBG.add(dialogTextPanel);

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
        panelBG.add(dialogBox);

        if (Monku.player == null) {
            Monku.player = new Player(); // Ensure the player object is initialized
        }

        if (loadornew == 1) {
            createDialogCard("<html><p style=\"margin-left: 30px\">Halo Kamu!<br>Siapa namamu?</p></html>", 40, 220, 536, 600, 100);
            // Setelah menambahkan kartu baru, tambahkan pembaruan posisi
            dialogTextPanel.revalidate();
            dialogTextPanel.repaint();

        } else if (loadornew == 2) {
            createDialogCard("<html><p style=\"margin-left: 30px\">Hello " + Monku.player.getName() + " !<br>Welcome back!</p></html>", 30, 220, 536, 700, 100);
        }

        // Create an invisible button to capture clicks and switch dialogs
        JButton invisibleButton = new JButton();
        invisibleButton.setBounds(dialogBox.getBounds());
        invisibleButton.setOpaque(false);
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setBorderPainted(false);
        invisibleButton.addActionListener(e -> {
            int cardCount = getCardPosition();
            System.out.println(cardCount);
            if (cardCount == 0 && loadornew == 1) {
                String result = getInput(frame);
                if (result != null && !result.isEmpty()) {
                    Monku.player.setName(result);
                    createDialogCard("<html><p style=\"margin-left: 130px\">Halo " + Monku.player.getName() + ",<br>senang berkenalan denganmu<br>Kamu mau pilih monku yang mana?</p></html>", 20, 220, 536, 700, 100);
                    createDialogCard("Selamat berpetualang " + Monku.player.getName() +"!", 30, 220, 536, 700, 100);
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
        frame.setVisible(true);
    }

    public String getInput(JFrame frame) {
        String result = (String) JOptionPane.showInputDialog(
            frame,
            "Masukkan namamu",
            "Nama Player",
            JOptionPane.QUESTION_MESSAGE,
            null,
            null,
            "SIAPA?");
        if (result == null || result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan namamu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return getInput(frame);
        }
        return result;
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
                e.printStackTrace();
            }
        });
    }

    private void createDialogCard(String text, int size, int x, int y, int width, int height) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Public Pixel", Font.BOLD, size));
        label.setForeground(Color.BLACK);
        label.setVisible(true);
        label.setBounds(x, y, width, height);
        System.out.println("label =" + label.getBounds());
        panel.setBounds(x, y, width, height);
        System.out.println("panel =" + panel.getBounds());
        dialogTextPanel.setBounds(x, y, width, height); 
        System.out.println(dialogTextPanel.getBounds());
        dialogTextPanel.add(panel);
        panel.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Action performed!", "Peringatan", JOptionPane.INFORMATION_MESSAGE);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Awalan(1);
        });
    }
}

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import Entity.Locations.HomeBase;
import Entity.Monster.Monster;
import Entity.NPC.NPC;
import Entity.Player.Player;

public class Awalan extends JFrame implements ActionListener {
    private CardLayout dialogText;
    private JPanel dialogTextPanel;

    public Awalan(NPC prof, Player player, Monster monku) {
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
        createDialogCard("<html><p style=\"margin-left: 20px\">Halo Kamu!</p>Siapa namamu?</html>");
        createDialogCard("Ini adalah dialog 2");
        createDialogCard("Ini adalah dialog 3");
        panelBG.add(dialogTextPanel);
        panelBG.add(dialogBox);

        // Create an invisible button to capture clicks and switch dialogs
        JButton invisibleButton = new JButton();
        invisibleButton.setBounds(dialogBox.getBounds());
        invisibleButton.setOpaque(false);
        invisibleButton.setContentAreaFilled(false);
        invisibleButton.setBorderPainted(false);
        final JLabel label = new JLabel();
        invisibleButton.addActionListener(e -> {
            int cardCount = getCardPosition();
            if (cardCount == 0) {
                String result = getInput(frame);
                if (result != null && !result.isEmpty()) {
                    player.setName(result);
                }
            }
            if (isLastCard()) {
                player.printDetailPlayer();
                // Transition to new scene
                newScene();
            } else {
                dialogText.next(dialogTextPanel);
            }
        });

        panelBG.add(label);
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
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(this, "Action performed!", "Peringatan", JOptionPane.INFORMATION_MESSAGE);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

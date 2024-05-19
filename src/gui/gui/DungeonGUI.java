package gui;

import Entity.Locations.Dungeon;
import Entity.Monster.Monster;
import Entity.Player.Player;
import Entity.Item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Random;

public class DungeonGUI extends JFrame {
    private Dungeon dungeon;
    private Player player;

    private JTextArea dungeonDetailsArea;
    private Timer battleTimer;

    public DungeonGUI(Dungeon dungeon, Player player) {

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
                ImageIcon originalIcon = new ImageIcon(dungeon.getImages());
                Image originalImage = originalIcon.getImage();
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        frame.setContentPane(panelBG);
        
        setupBattleTimer();
    }

    private void displayDungeonDetails() {
        dungeonDetailsArea.setText("");
        dungeonDetailsArea.append("Dungeon: " + dungeon.getLocationName() + "\n");
        dungeonDetailsArea.append("Level: " + dungeon.getLevel() + "\n");
        dungeonDetailsArea.append("Monsters in Dungeon:\n");
        for (Monster monster : dungeon.getMonsters()) {
            dungeonDetailsArea.append("- " + monster.getName() + "\n");
        }
        dungeonDetailsArea.append("Rewards in Dungeon:\n");
        for (Item reward : dungeon.getRewards()) {
            dungeonDetailsArea.append("- " + reward.name + "\n");
        }
    }

    private void setupBattleTimer() {
        Random random = new Random();
        int delay = random.nextInt(10000) + 5000; 
        battleTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triggerRandomBattle();
                battleTimer.setInitialDelay(random.nextInt(10000) + 5000);
                battleTimer.restart();
            }
        });
        battleTimer.setRepeats(false);
        battleTimer.start();
    }

    private void triggerRandomBattle() {
        int monsterIndex = new Random().nextInt(dungeon.getMonsters().size());
        startBattle(monsterIndex);
        displayDungeonDetails(); 
    }

    private void startBattle(int monsterIndex) {
        Monster enemy = dungeon.getMonsters().get(monsterIndex);
        Monster playerMonster = player.deployMonster(monsterIndex);

        if (playerMonster == null) {
            JOptionPane.showMessageDialog(this, "No monster available for battle!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Battle begins between " + playerMonster.getName() + " and " + enemy.getName() + "!", "Battle Start", JOptionPane.INFORMATION_MESSAGE);

        while (playerMonster.getHealthPoint() > 0 && enemy.getHealthPoint() > 0) {
            performPlayerTurn(playerMonster, enemy);
            if (enemy.getHealthPoint() <= 0) {
                JOptionPane.showMessageDialog(this, playerMonster.getName() + " wins the battle!", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
                player.winBattle();
                dungeon.collectReward(player, dungeon.getRandomReward());
                break;
            }
            performEnemyTurn(playerMonster, enemy);
            if (playerMonster.getHealthPoint() <= 0) {
                JOptionPane.showMessageDialog(this, enemy.getName() + " wins the battle!", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    private void performPlayerTurn(Monster playerMonster, Monster enemy) {
        // int damage = playerMonster.;
        // enemy.takeDamage(damage);
        // JOptionPane.showMessageDialog(this, playerMonster.getName() + " attacks " + enemy.getName() + " for " + damage + " damage!", "Player Turn", JOptionPane.INFORMATION_MESSAGE);
    }

    private void performEnemyTurn(Monster playerMonster, Monster enemy) {
        // int damage = enemy.attack();
        // playerMonster.takeDamage(damage);
        // JOptionPane.showMessageDialog(this, enemy.getName() + " attacks " + playerMonster.getName() + " for " + damage + " damage!", "Enemy Turn", JOptionPane.INFORMATION_MESSAGE);
    }

    private class BattleButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int monsterIndex = new Random().nextInt(dungeon.getMonsters().size());
            startBattle(monsterIndex);
            displayDungeonDetails(); 
        }
    }

    private class CollectRewardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Item reward = dungeon.getRandomReward();
            dungeon.collectReward(player, reward);
            displayDungeonDetails();
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dungeon.exitDungeon();
            JOptionPane.showMessageDialog(DungeonGUI.this, "You have exited the dungeon.", "Exit", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); 
        }
    }

    public static void main(String[] args) {

        Monster[] monsters = {};
        Item[] rewards = {};
        Dungeon dungeon = new Dungeon("Mystic Cave", monsters, rewards, 1, "path/to/images");
        Player player = new Player("Hero", dungeon, "w"); 

        SwingUtilities.invokeLater(() -> {
            new DungeonGUI(dungeon, player).setVisible(true);
        });
    }
}

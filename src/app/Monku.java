package app;

import gui.MonkuGamesGUI;  
import javax.swing.SwingUtilities;

public class Monku {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MonkuGamesGUI());
    }
}

package app;

import gui.gui.*;  
import javax.swing.SwingUtilities;

public class Monku {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Homepage());
    }
}

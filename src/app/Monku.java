package app;


import javax.swing.SwingUtilities;
import gui.*;

public class Monku {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Homepage());
    }
}

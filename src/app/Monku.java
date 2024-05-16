package app;


import java.io.IOException;

import javax.swing.SwingUtilities;
import gui.*;

public class Monku {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Homepage();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}

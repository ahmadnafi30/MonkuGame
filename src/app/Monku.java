package app;

import java.io.*;

import javax.swing.SwingUtilities;

import Entity.progressManagement;
import Entity.Player.*;
import Entity.Item.*;
import Entity.Locations.*;
import Entity.Locations.HomeBase;
import Entity.Monster.*;
import Entity.NPC.*;
import gui.*;

public class Monku {
    public static void main(String[] args) throws IOException {
        
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

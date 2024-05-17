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
        
        Player p = new Player("Raka", new HomeBase("junction"));
        p.catchMonster(new FireType("Barok", 1, 4));
        progressManagement file = new progressManagement("./save_files/save.txt");
        file.savingProgress();
    }

}

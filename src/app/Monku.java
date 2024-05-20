package app;

import java.io.*;

import javax.swing.SwingUtilities;

// import Entity.ProgressManagement;
import Entity.Player.*;
import Entity.Item.*;
import Entity.Locations.*;
import Entity.Locations.HomeBase;
import Entity.Monster.*;
import Entity.NPC.*;
import gui.*;

public class Monku {

    public static Player player = new Player("", new HomeBase("HomeBase"), "");;
    public static Monster monku;
    public static ProfessorPokemon professor = new ProfessorPokemon("Einstein", "Professor Monku");
    public static ItemSeller shopKeeper = new ItemSeller("Bowo", "ShopKeeper", new HomeBase("HomeBase"));
    public static Locations loc;
    public static Item item;
    // public static ProgressManagement progress;

    public static void main(String[] args) throws IOException {
        //TODO: bikin konstruktor kosong buat setiap kelas
        player = new Player("", new HomeBase("HomeBase"), "");
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

package app;

import java.io.*;

import javax.swing.SwingUtilities;

// import Entity.ProgressManagement;
import Entity.Player.*;
import Entity.Item.*;
import Entity.Locations.*;
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
    public static Item buffPotionCommon = new BuffPotion("Buff Potion", "COMMON");
    public static Item buffPotionRare = new BuffPotion("Buff Potion", "RARE");
    public static Item buffPotionEpic = new BuffPotion("Buff Potion", "EPIC");

    public static Item defensivePotionCommon = new DefensivePotion("Defensive Potion", "COMMON");
    public static Item defensivePotionRare = new DefensivePotion("Defensive Potion", "RARE");
    public static Item defensivePotionEpic = new DefensivePotion("Defensive Potion", "EPIC");

    public static Item healthPotionCommon = new HealthPotion("Health Potion", "COMMON");
    public static Item healthPotionRare = new HealthPotion("Health Potion", "RARE");
    public static Item healthPotionEpic = new HealthPotion("Health Potion", "EPIC");

    public static Item poisonCommon = new PoisonPotion("Poison", "COMMON");
    public static Item poisonRare = new PoisonPotion("Poison", "RARE");
    public static Item poisonEpic = new PoisonPotion("Poison", "EPIC");

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

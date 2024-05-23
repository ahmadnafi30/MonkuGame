package app;

import java.io.*;

import javax.swing.SwingUtilities;

// import Entity.ProgressManagement;
import Entity.Player.*;
import Entity.DataStorage;
import Entity.Item.*;
import Entity.Locations.*;
import Entity.Monster.*;
import Entity.NPC.*;
import gui.*;

public class Monku {

    public static Monster[] monsters = {new AirType("kehed", 4, 4, "asset/rhyhorn.gif"), new AirType("kehed", 4, 4, "asset/squirtle.gif"), new AirType("kehed", 4, 4, "asset/squirtle.gif"), new AirType("kehed", 4, 4, "asset/squirtle.gif")};
    public static Item items[] = {new BuffPotion(null, "COMMON"), new BuffPotion(null, "RARE"), new BuffPotion(null, "EPIC"), new DefensivePotion(null, "COMMON"), new DefensivePotion(null, "RARE"), new DefensivePotion(null, "EPIC"), new HealthPotion(null, "COMMON"), new HealthPotion(null, "RARE"), new HealthPotion(null, "EPIC"), new PoisonPotion(null, "COMMON"), new PoisonPotion(null, "RARE"), new PoisonPotion(null, "EPIC"), new Teleportation(null, null),};
    public static Player player = new Player("", new HomeBase("HomeBase"), "");
    public static Monster monku;
    public static ProfessorPokemon professor = new ProfessorPokemon("Einstein", "Professor Monku");
    public static ItemSeller shopKeeper = new ItemSeller("Bowo", "ShopKeeper", new HomeBase("HomeBase"));
    public static Locations homeBase = new HomeBase("Lab");
    public static Locations dungeon = new Dungeon("Dungeon", monsters, items, 0, null, null, null);
    public static Locations shop = new Shop("Rumah Penyihir", shopKeeper);
    public static Locations map;

    // public static ProgressManagement progress;

    public static void main(String[] args) throws IOException {
        //TODO: bikin konstruktor kosong buat setiap kelas
        player = new Player("", homeBase, "");
        SwingUtilities.invokeLater(() -> {
            try {
                new Homepage();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    public static void loadGame(){
        try {
            FileInputStream filein = new FileInputStream("save_files/saveGames.txt");
            BufferedInputStream bis = new BufferedInputStream(filein);
            ObjectInputStream in = new ObjectInputStream(bis);

            DataStorage dStorage = (DataStorage)in.readObject();
            Player playerload = new Player(dStorage.name, dStorage.level, dStorage.exp, dStorage.inventory, dStorage.coin, dStorage.monsters, dStorage.timePlayed, dStorage.startTime, homeBase, dStorage.image);
            Monku.player = playerload;
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("salah input output");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

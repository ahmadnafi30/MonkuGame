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
    public static Locations dungeon = new Dungeon("Dungeon", monsters, items, 4, null, null, null);
    public static Locations shop = new Shop("Rumah Penyihir", shopKeeper);
    public static Locations map;

    public static Monster[] monsters1 = {new AirType("Pigdeot", 4, 4, "asset/Dungeon/Obsidian Fortress lvl1/pigdeot/pidgeot.gif"), new WaterType("Kingdra", 4, 4, "asset/Dungeon/Obsidian Fortress lvl1/Kingdra/kingdra.gif"), new EarthType("Golem", 4, 4, "asset/Dungeon/Obsidian Fortress lvl1/golem/golem.gif"), new FireType("Arcanine", 4, 4, "asset/Dungeon/Obsidian Fortress lvl1/arcanine/arcanine.gif"), new IceType("Glaceon", 4, 4, "asset/Dungeon/Obsidian Fortress lvl1/glaceon/glaceon.gif")};
    public static Locations obsidianDungeon = new Dungeon("Obsidian Fortress", monsters1, items, 1, "asset/Dungeon/Obsidian Fortress lvl1/bg-elite4drake.jpg", "asset/Dungeon/Obsidian Fortress lvl1/chuck.png", "Chuck");

    public static Monster[] monsters2 = {new AirType("Noctowl", 4, 4, "asset/Dungeon/Inferno Abyss lvl2/noctowl/noctowl.gif"), new WaterType("Milotic", 4, 4, "asset/Dungeon/Inferno Abyss lvl2/milotic/milotic.gif"), new EarthType("Rhyperior", 4, 4, "asset/Dungeon/Inferno Abyss lvl2/Rhyperior/rhyperior.gif"), new FireType("Arcanine", 4, 4, "asset/Dungeon/Inferno Abyss lvl2/infernape/infernape.gif"), new IceType("Weavile", 4, 4, "asset/Dungeon/Inferno Abyss lvl2/Weavile/weavile.gif")};
    public static Locations infernoDungeon = new Dungeon("Inferno Abyss", monsters2, items, 2, "asset/Dungeon/Inferno Abyss lvl2/bg-icecave.jpg", "asset/Dungeon/Inferno Abyss lvl2/byron.png", "Byron");

    public static Monster[] monsters3 = {new AirType("Crobat", 4, 4, "asset/Dungeon/Crypt of Whispers lvl3/Crobat/crobat.gif"), new WaterType("Lapras", 4, 4, "asset/Dungeon/Crypt of Whispers lvl3/Lapras/lapras.gif"), new EarthType("Hippowdon", 4, 4, "asset/Dungeon/Crypt of Whispers lvl3/Hippowdon/hippowdon.gif"), new FireType("Chandelure", 4, 4, "asset/Dungeon/Crypt of Whispers lvl3/Chandelure/chandelure.gif"), new IceType("Beartic", 4, 4, "asset/Dungeon/Crypt of Whispers lvl3/Beartic/beartic.gif")};
    public static Locations cryptDungeon = new Dungeon("Crypt of Whispers", monsters3, items, 3, "asset/Dungeon/Crypt of Whispers lvl3/bg-earthycave.jpg", "asset/Dungeon/Crypt of Whispers lvl3/bruno-gen1rb.png", "Bruno");

    public static Monster[] monsters4 = {new AirType("Staraptor", 4, 4, "asset/Dungeon/Dungeon of Eternal Night lvl4/Staraptor/staraptor.gif"), new WaterType("Staraptor", 4, 4, "asset/Dungeon/Dungeon of Eternal Night lvl4/Staraptor/staraptor.gif"), new EarthType("Krookodile", 4, 4, "asset/Dungeon/Dungeon of Eternal Night lvl4/Krookodile/krookodile.gif"), new FireType("Typhlosion", 4, 4, "asset/Dungeon/Dungeon of Eternal Night lvl4/Typhlosion/typhlosion.gif"), new IceType("Walrein", 4, 4, "asset/Dungeon/Dungeon of Eternal Night lvl4/Walrein/walrein.gif")};
    public static Locations eternalDungeon = new Dungeon("Dungeon of Eternal Night", monsters4, items, 4, "asset/Dungeon/Dungeon of Eternal Night lvl4/bg-leaderwallace.jpg", "asset/Dungeon/Dungeon of Eternal Night lvl4/brycenman.png", "Brycenman");

    public static Monster[] monsters5 = {new AirType("Aerodactyl", 4, 4, "asset/Dungeon/Temple of Doom lvl5/aerodactyl/aerodactyl.gif"), new WaterType("Gyarados", 4, 4, "asset/Dungeon/Temple of Doom lvl5/gyarados/gyarados.gif"), new EarthType("Aggron", 4, 4, "asset/Dungeon/Temple of Doom lvl5/aggron/aggron.gif"), new FireType("Blaziken", 4, 4, "asset/Dungeon/Temple of Doom lvl5/Blaziken/blaziken.gif"), new IceType("Abomasnow", 4, 4, "asset/Dungeon/Temple of Doom lvl5/Abomasnow/abomasnow.gif")};
    public static Locations doomDungeon = new Dungeon("Temple of Doom", monsters5, items, 5, "asset/Dungeon/Temple of Doom lvl5/bg-dampcave.jpg", "asset/Dungeon/Temple of Doom lvl5/cueball-gen1rb.png", "Cueball");

    public static void main(String[] args) throws IOException {
        //TODO: bikin konstruktor kosong buat setiap kelas
        player = new Player("", homeBase, "");
        SwingUtilities.invokeLater(() -> {
            try {
                new Homepage();
            } catch (IOException e) {
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

// package Entity.Player;

// import java.io.BufferedInputStream;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.ObjectInputStream;

// import Entity.DataStorage;
// import Entity.Item.BuffPotion;
// import Entity.Item.Item;
// import Entity.Locations.HomeBase;
// import Entity.Locations.Locations;
// import Entity.Monster.AirType;
// import Entity.Monster.FireType;
// import Entity.Monster.Monster;
// import Entity.NPC.*;

// public class main {
    
//     public static void main(String[] args) {
//         Locations homebase = new HomeBase("junction");
//         Player player = new Player("Raka", homebase, "");
//         // player.printDetailPlayer();
//         Item buff = new BuffPotion("raka", "common");
//         // player.buyItem(buff, 3);
//         // player.printDetailPlayer();
//         // player.addCoin(100);
//         // player.printDetailPlayer();
//         // Monster raka = new AirType("RAKA", 1, 2);
//         player.catchMonster(raka);
//         player.getLocationPlayer();
//         ((HomeBase)homebase).interactWithPlayer(player, 3, raka);


//         try {
//             FileInputStream filein = new FileInputStream("save_files/saveGames.txt");
//             BufferedInputStream bis = new BufferedInputStream(filein);
//             ObjectInputStream in = new ObjectInputStream(bis);

//             DataStorage dStorage = (DataStorage)in.readObject();
//             Player playerload = new Player(dStorage.name, dStorage.level, dStorage.exp, dStorage.inventory, dStorage.coin, dStorage.monsters, dStorage.timePlayed, dStorage.startTime, homebase, dStorage.image);
//             in.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//             System.out.println("salah input output");
//         } catch (ClassNotFoundException e){
//             e.printStackTrace();
//         }




//         // player.printDetailPlayer();

//         // // Monster nagi = new FireType("Barok", 1, 4);

//         // Monster sekarang = player.deployMonster(0);

//         // player.checkMonsters();
//         // System.out.println();
//         // // nagi.basicAttack(sekarang);
//         // player.checkMonsters();

//         // raka.setLevel(20);
//         // raka.displayDetailMonster();
        
//         // NPC prof = new ProfessorPokemon("Heru", "Professor Monku");
//         // ((ProfessorPokemon) prof).healPokemon(player, raka.getName());
//         // ((ProfessorPokemon) prof).evolvePokemon(raka, "fire");
//         // raka = raka.changeMonsterClass();
//         // System.out.println(raka.getMonster());

//         // raka.displayDetailMonster();
//     }
// }

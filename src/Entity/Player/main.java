package Entity.Player;

import Entity.Item.BuffPotion;
import Entity.Item.Item;
import Entity.Locations.HomeBase;
import Entity.Locations.Locations;
import Entity.Monster.AirType;
import Entity.Monster.FireType;
import Entity.Monster.Monster;
import Entity.NPC.*;

public class main {
    
    public static void main(String[] args) {
        Locations hombase = new HomeBase("junction");
        Player player = new Player("Raka", hombase);
        // player.printDetailPlayer();
        Item buff = new BuffPotion("raka", "common");
        // player.buyItem(buff, 3);
        // player.printDetailPlayer();
        // player.addCoin(100);
        // player.printDetailPlayer();
        Monster raka = new AirType("RAKA", 1, 2);
        player.catchMonster(raka);
        // player.printDetailPlayer();

        Monster nagi = new FireType("Barok", 1, 4);

        Monster sekarang = player.deployMonster(0);

        player.checkMonsters();
        System.out.println();
        nagi.basicAttack(sekarang);
        player.checkMonsters();

        raka.setLevel(20);
        raka.displayDetailMonster();
        
        NPC prof = new ProfessorPokemon("Heru", "Professor Monku");
        ((ProfessorPokemon) prof).healPokemon(player, raka.getName());
        raka.displayDetailMonster();
        ((ProfessorPokemon) prof).evolvePokemon(raka, raka.getElementType()[0].toString());
        raka.displayDetailMonster();
        raka.
        System.out.println(raka.monsterProperty());
    }
}

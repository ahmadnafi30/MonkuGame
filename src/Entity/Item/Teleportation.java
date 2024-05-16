package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.*;
import Entity.Locations.*;
import java.util.*;

public class Teleportation extends Item{

    private Locations destination;
    private int chance;
    
    public Teleportation(String name, int price, String rarity, int duration, Locations location) {
        super(name, "Can Teleport You Anywhere", price, rarity, duration);
        switch (super.rarity) {
            case COMMON:
                chance = 20;
                price = 7;
                break;
            
            case RARE:
                chance = 50;
                price = 39;
                break;
            
            case EPIC:
                chance = 99;
                price = 88;
                break;
            default:
                break;
        }
    }

    @Override
    public void printDetailItemm() {
        System.out.println("""
                Name: """ + super.name + """
                Rarity: """ + super.rarity + """
                Functionality: """ + super.functionality + """
                Price: """ + super.price + """
                """);
    }

    @Override
    public void useItem(Monster monster, int turn, Player player) {
        Random rand = new Random();
        boolean success = (rand.nextInt(100) <= chance ? true : false);
        if(!success) {
            System.out.println("Teleportation failed");
            return;
        } else{
            // player.tp;
        }
    }

    @Override
    public void itemRanOut(Monster monster, Player player) {
        
    }
}

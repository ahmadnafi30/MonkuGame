package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.*;
import app.Monku;
import Entity.Locations.*;
import java.util.*;

public class Teleportation extends Item{

    private Locations destination;
    private int chance;
    
    public Teleportation(String name, String rarity, Locations location) {
        super(name, "Can Teleport You Anywhere", 0, rarity, 0, "asset/potions/Teleportation.png");
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
    public String printDetailItemm() {
        return("""
                \nName: """ + super.name + """
                \nRarity: """ + super.rarity + """
                \nFunctionality: """ + super.functionality + """
                \nPrice: """ + super.price + """
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
            player.setLocation(Monku.homeBase);
        }
    }

    public boolean useItem(Player player) {
        Random rand = new Random();
        boolean success = (rand.nextInt(100) <= chance ? true : false);
        if(!success) {
            System.out.println("Teleportation failed");
            return false;
        } else{
            player.setLocation(Monku.homeBase);
            return true;
        }
    }

    @Override
    public void itemRanOut(Monster monster, Player player) {
        
    }

}

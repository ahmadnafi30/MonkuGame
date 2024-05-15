package Entity.Locations;

import java.util.ArrayList;

import Entity.Item.Item;
import Entity.Monster.Monster;

public class Dungeon extends Locations {
    private ArrayList<Monster> monsters;
    private ArrayList<Item> rewards;

    public Dungeon(String locationName, Monster[] monsters, Item[] rewards) {
        super(locationName);
        this.monsters = new ArrayList<>();
        this.rewards = new ArrayList<>();
    }

    @Override
    public void printDetailLocation() {
        System.out.println("Dungeon: " + locationName);
        System.out.println("Monsters in Dungeon:");
        for (Monster monster : monsters) {
            monster.displayDetailMonster();
        }
        System.out.println("Rewards in Dungeon:");
        for (Item reward : rewards) {
            reward.printDetailItemm();
        }
    }

    public void exitDungeon() {
        System.out.println("Exiting the dungeon...");
        // Implementation of exiting the dungeon
    }

    public void collectReward() {
        System.out.println("Collecting rewards...");
        // Implementation of collecting rewards
    }
}

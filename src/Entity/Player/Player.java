package Entity.Player;

import Entity.Item.Item;
import Entity.Monster.Monster;

public class Player {

    // Attributes
    private String name;
    private int level;
    private Item[] inventory;
    private int coin;
    private Monster[] monsters;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.inventory = new Item[10]; // Example array size
        this.coin = 0;
        this.monsters = new Monster[6]; // Example array size for storing Monster team
    }

    // Method to buy an item
    public Item buyItem(String nameItem) {
        return null;
        // Logic to buy an item
    }

    // Method to check the player's inventory
    public void checkInventory() {
        // Logic to display player's inventory
    }

    // Method to add coins to the player's wallet
    public void addCoin(int amount) {
        // Logic to add coins
    }

    // Method to check the player's Monster team
    public void checkPokemon() {
        // Logic to display player's Monster team
    }

    // Method to catch a Monster
    public void catchPokemon(Monster pokemon) {
        // Logic to catch a Monster and add it to the team
    }

    // Method to sell an item
    public Item sellItem(String nameItem) {
        return null;
        // Logic to sell an item
    }

    // Method to enter a dungeon
    // public Dungeon enterDungeon() {
    //     return null;
    //     // Logic to enter a dungeon and return a Dungeon object
    // }

    // Additional methods can be added as needed
}

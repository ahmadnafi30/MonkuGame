package Entity.NPC;

import Entity.ItemInteract;
import Entity.Item.Item;

public class ItemSeller extends NPC implements ItemInteract {
    Item[] items;
    public ItemSeller(String name, String job) {
        super(name, job);
        //TODO Auto-generated constructor stub
    }

    public void checkItem() {
        // Logic to check available items
    }

    // Method to sell an item
    public void sellItem(String item) {
        // Logic to sell the item
    }

    // Method to buy an item
    public void buyItem(String item) {
        // Logic to buy the item
    }
}

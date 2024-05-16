package Entity.NPC;

import Entity.ItemInteract;
import Entity.Item.Item;
import Entity.Player.Player;

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
    public void sellItem(int item, Player player, int quantity) {
        player.buyItem(items[item-1], quantity);
    }

    // Method to buy an item
    public void buyItem(int item, Player player, int quantity) {
        player.sellItem(items[item-1], quantity);
    }

    @Override
    public void checkInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkInventory'");
    }

    @Override
    public void sellItem(Item itemsell, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sellItem'");
    }

    @Override
    public void buyItem(Item itembuy, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyItem'");
    }

    // public void buyItem(int item, int quantity) {
    //     items[item-1];
    
    //     throw new UnsupportedOperationException("Unimplemented method 'buyItem'");
    // }


}

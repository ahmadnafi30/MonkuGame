package Entity.NPC;

import java.util.HashMap;
import java.util.Map;

import Entity.ItemInteract;
import Entity.Item.BuffPotion;
import Entity.Item.DefensivePotion;
import Entity.Item.HealthPotion;
import Entity.Item.Item;
import Entity.Item.PoisonPotion;
import Entity.Item.Teleportation;
import Entity.Locations.*;
import Entity.Player.Player;

public class ItemSeller extends NPC implements ItemInteract {
    private Map<Item, Integer> inventory;
    private int coin;
    
    public ItemSeller(String name, String job, Locations homeBase) {
        super(name, job);
        setItem(homeBase);
    }
    
    public ItemSeller(String name, String job, int coin, Locations homeBase) {
        super(name, job);
        setItem(homeBase);
        this.coin = coin;
    }

    public void setItem(Locations homeBase){
        inventory = new HashMap<>();
        inventory.put(new BuffPotion("Jamu Kuat", "COMMON"), 20);
        inventory.put(new BuffPotion("Jamu Kuat", "RARE"), 12);
        inventory.put(new BuffPotion("Jamu Kuat", "EPIC"), 6);
        inventory.put(new DefensivePotion("Susu Kambing", "COMMON"), 20);
        inventory.put(new DefensivePotion("Susu Kambing", "RARE"), 14);
        inventory.put(new DefensivePotion("Susu Kambing", "EPIC"), 8);
        inventory.put(new HealthPotion("Jamu Kencur", "COMMON"), 20);
        inventory.put(new HealthPotion("Jamu Kencur", "RARE"), 15);
        inventory.put(new HealthPotion("Jamu Kencur", "EPIC"), 10);
        inventory.put(new PoisonPotion("Ludah Buzzer", "COMMON"), 18);
        inventory.put(new PoisonPotion("Ludah Buzzer", "RARE"), 12);
        inventory.put(new PoisonPotion("Ludah Buzzer", "EPIC"), 6);
        inventory.put(new Teleportation("BECAK", homeBase), 20);
    }
    
    public boolean hasItem(Item item) {
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(item) > 0) {
                return true;
            }
        }
        return false;
    }
    
    // Method to sell an item
    @Override
    public void sellItem(Item item, int quantity, Object buyer) {}
    
    // Method to buy an item
    @Override
    public void buyItem(Item item, int quantity, Object seller) {}

    @Override
    public void checkInventory() {
        inventory.forEach((item, quantity) -> {
            printItemDetails(item);
            System.out.println("Quantity: " + quantity);
        });
        System.out.println("Isi Inventory (" + inventory.size() + ")");
        
    }

    public void setItemQuantity(Item item, int quantity) {
        inventory.put(item, inventory.get(item) + quantity);
    }

    private void printItemDetails(Item item) {
        if (item instanceof BuffPotion) {
            ((BuffPotion) item).printDetailItemm();
        } else if (item instanceof DefensivePotion) {
            ((DefensivePotion) item).printDetailItemm();
        } else if (item instanceof HealthPotion) {
            ((HealthPotion) item).printDetailItemm();
        } else if (item instanceof PoisonPotion) {
            ((PoisonPotion) item).printDetailItemm();
        } else if (item instanceof Teleportation) {
            ((Teleportation) item).printDetailItemm();
        }
        System.out.println();
    }

    public int getCoin() {
        return this.coin;
    }
    
    public void setCoin(int coin) {
        this.coin += coin;
    }

    public Map<Item, Integer> getInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInventory'");
    }
    
}

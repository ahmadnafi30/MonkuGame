package Entity;

import Entity.Item.Item;
import Entity.NPC.ItemSeller;

public interface ItemInteract {
    void checkInventory();
    void sellItem(Item itemsell, int quantity, Object buyer);
    void buyItem(Item itembuy, int quantity, Object seller);
}

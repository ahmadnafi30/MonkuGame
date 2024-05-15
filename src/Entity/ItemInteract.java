package Entity;

import Entity.Item.Item;

public interface ItemInteract {
    void checkInventory();
    void sellItem(Item itemsell, int quantity);
    void buyItem(Item itembuy, int quantity);
}

package Entity;

import Entity.Item.Item;

public interface Battle {
    void basicAttack();
    void specialAttack();
    void elementalAttack();
    void useItem(Item item);
    void flee();
}

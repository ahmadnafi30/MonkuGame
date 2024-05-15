package Entity;

import Entity.Item.Item;
import Entity.Monster.*;

public interface Battle {
    void basicAttack(Monster enemy);
    void specialAttack(Monster enemy);
    void elementalAttack(Monster enemy, ElementalAttack elementalAttack);
    void useItem(Item item);
    void flee();
}

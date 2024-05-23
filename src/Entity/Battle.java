package Entity;

import Entity.Item.Item;
import Entity.Monster.*;
import Entity.Player.Player;

public interface Battle {
    void basicAttack(Monster enemy);
    void specialAttack(Monster enemy);
    void elementalAttack(Monster enemy, ElementalAttack elementalAttack);
    void useItem(Item item, Monster enemy, int turn, Player player);
    void flee();
}

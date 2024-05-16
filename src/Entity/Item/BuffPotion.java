package Entity.Item;

import Entity.Monster.*;
import Entity.Player.*;

public class BuffPotion extends Item {
    
    private int attackPowerAmount;
    private int oldAttackPower;

    public BuffPotion(String name, String rarity) {
        super(name, "Gives you bonus attack power", 0, rarity, 0);
        switch (super.rarity) {
            case COMMON:
                attackPowerAmount = 5;
                duration = 1;
                price = 10;
                break;
            
            case RARE:
                attackPowerAmount = 10;
                duration = 2;
                price = 50;
                break;
            
            case EPIC:
                attackPowerAmount = 20;
                duration = 3;
                price = 100;
                break;
            default:
                break;
        }
    }

    @Override
    public void printDetailItemm() {
        System.out.println("""
                Name: """ + super.name + """
                \nRarity: """ + super.rarity + """
                \nFunctionality: """ + super.functionality + """
                \nPrice: """ + super.price + """
                \nAttack Power: """ + attackPowerAmount + """
                \nDuration: """ + duration + """
                \nPrice: """ + price + """
                """);
    }

    @Override
    public void useItem(Monster monster, int turn, Player player) {
        if(turn == 0) oldAttackPower = monster.getAttackPower();
        if(turn == duration) {
            System.out.println(name + " has expired");
            itemRanOut(monster, null);
            return;
        }
        monster.setDefensePower(oldAttackPower + attackPowerAmount);
    }

    @Override
    public void itemRanOut(Monster monster, Player player) {
        monster.setAttackPower(oldAttackPower);
    }
    
}

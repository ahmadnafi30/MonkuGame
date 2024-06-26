package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class HealthPotion extends Item {

    private int healthAmount;

    public HealthPotion(String name, String rarity) {
        super(name, "Gives you bonus health", 0, rarity, 0, "asset/potions/HealPotion.png");
        switch (super.rarity) {
            case COMMON:
                healthAmount = 4;
                duration = 1;
                price = 7;
                break;
            
            case RARE:
                healthAmount = 8;
                duration = 1;
                price = 15;
                break;
            
            case EPIC:
                healthAmount = 15;
                duration = 1;
                price = 30;
                break;
            default:
                break;
        }
    }

    @Override
    public String printDetailItemm() {
        return("""
                Name: """ + super.name + """
                \nRarity: """ + super.rarity + """
                \nFunctionality: """ + super.functionality + """
                \nPrice: """ + super.price + """
                \nAttack Power: """ + healthAmount + """
                \nDuration: """ + duration + """
                \nPrice: """ + price + """
                """);
    }

    @Override
    public void useItem(Monster monster, int turn, Player player) {
        monster.setHealthPoint(monster.getHealthPoint() + healthAmount);
    }

    @Override
    public void itemRanOut(Monster monster, Player player) {}
    
}

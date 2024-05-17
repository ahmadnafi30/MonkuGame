package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class HealthPotion extends Item {

    private int healthAmount;

    public HealthPotion(String name, String rarity) {
        super(name, "Gives you bonus health", 0, rarity, 0);
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
    public void printDetailItemm() {
        System.out.println("""
                Name: """ + super.name + """
                Rarity: """ + super.rarity + """
                Functionality: """ + super.functionality + """
                Price: """ + super.price + """
                Attack Power: """ + healthAmount + """
                Duration: """ + duration + """
                Price: """ + price + """
                """);
    }

    @Override
    public void useItem(Monster monster, int turn, Player player) {
        monster.setHealthPoint(monster.getHealthPoint() + healthAmount);
    }

    @Override
    public void itemRanOut(Monster monster, Player player) {}
    
}

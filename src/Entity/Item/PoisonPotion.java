package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class PoisonPotion extends Item{

    private int poisonAmount;
    

    public PoisonPotion(String name, int price, String rarity, int duration) {
        super(name, "Give enemy passive damage", price, rarity, duration);
        switch (super.rarity) {
            case COMMON:
                poisonAmount = 3;
                duration = 2;
                price = 13;
                break;
            
            case RARE:
                poisonAmount = 5;
                duration = 3;
                price = 39;
                break;
            
            case EPIC:
                poisonAmount = 10;
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
                Rarity: """ + super.rarity + """
                Functionality: """ + super.functionality + """
                Price: """ + super.price + """
                Poison DMG Per Round: """ + poisonAmount + """
                Duration: """ + duration + """
                Price: """ + price + """
                """);
    }

    @Override
    public void useItem(Monster monster, int turn, Player player) {
        if(turn == duration) {
            System.out.println(name + " has expired");
            return;
        }
        monster.setHealthPoint(monster.getHealthPoint() - poisonAmount);
    }

    @Override
    public void itemRanOut(Monster monster, Player player) {}
    
}

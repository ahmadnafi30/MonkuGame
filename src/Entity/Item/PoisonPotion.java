package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class PoisonPotion extends Item{

    private int poisonAmount;
    

    public PoisonPotion(String name, String rarity) {
        super(name, "Give enemy passive damage", 0, rarity, 0, "asset/potions/PoisonPotion.png");
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
    public String printDetailItemm() {
        return("""
                \nName: """ + super.name + """
                \nRarity: """ + super.rarity + """
                \nFunctionality: """ + super.functionality + """
                \nPrice: """ + super.price + """
                \nPoison DMG Per Round: """ + poisonAmount + """
                \nDuration: """ + duration + """
                \nPrice: """ + price + """
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

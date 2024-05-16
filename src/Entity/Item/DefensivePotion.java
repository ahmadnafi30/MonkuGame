package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class DefensivePotion extends Item{

    private int defencePowerAmount;
    private int oldDefencePower;
    

    public DefensivePotion(String name, int price, String rarity, int duration) {
        super(name, "Gives you bonus attack power", price, rarity, duration);
        switch (super.rarity) {
            case COMMON:
                defencePowerAmount = 3;
                duration = 1;
                price = 7;
                break;
            
            case RARE:
                defencePowerAmount = 8;
                duration = 2;
                price = 39;
                break;
            
            case EPIC:
                defencePowerAmount = 12;
                duration = 3;
                price = 88;
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
                Attack Power: """ + defencePowerAmount + """
                Duration: """ + duration + """
                Price: """ + price + """
                """);
    }

    @Override
    public void useItem(Monster monster, int turn, Player player) {
        if(turn == 0) oldDefencePower = monster.getDefensePower();
        if(turn == duration) {
            System.out.println(name + " has expired");
            itemRanOut(monster, null);
            return;
        }
        monster.setDefensePower(oldDefencePower + defencePowerAmount);
    }

    @Override
    public void itemRanOut(Monster monster, Player player) {
        monster.setDefensePower(oldDefencePower);
    }
    
}

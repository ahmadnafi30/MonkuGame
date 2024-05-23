package Entity.Item;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class DefensivePotion extends Item{

    private int defencePowerAmount;
    private int oldDefencePower;
    

    public DefensivePotion(String name, String rarity) {
        super(name, "Gives you bonus attack power", 0, rarity, 0, "asset/potions/DefensePotion.png");
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
    public String printDetailItemm() {
        return("""
                Name: """ + super.name + """
                \nRarity: """ + super.rarity + """
                \nFunctionality: """ + super.functionality + """
                \nPrice: """ + super.price + """
                \nAttack Power: """ + defencePowerAmount + """
                \nDuration: """ + duration + """
                \nPrice: """ + price + """
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

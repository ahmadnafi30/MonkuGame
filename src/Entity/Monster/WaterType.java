package Entity.Monster;
import java.util.ArrayList;
import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class WaterType extends Monster {
    private List<ElementalAttack> choices = new ArrayList<ElementalAttack>();
    public WaterType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "WATER", maxMonsterPhase, image);
        choices.add(new ElementalAttack("Bubble", 40, ElementType.WATER, 3));
        choices.add(new ElementalAttack("Water Gun", 40, ElementType.WATER, 3));
        choices.add(new ElementalAttack("Aqua Jet", 60, ElementType.WATER, 2));
        choices.add(new ElementalAttack("Surf", 90, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Hydro Pump", 110, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Waterfall", 80, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Aqua Tail", 90, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Scald", 80, ElementType.WATER, 2));
    }
    

    public WaterType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "WATER", maxMonsterPhase);
    }
    public List<ElementalAttack> getChoices() {
        return choices;
    }

    public void setElementalSkills(String name){

    }

    public WaterType(Monster waterType) {
        super(waterType);
    }
    
    @Override
    public void basicAttack(Monster enemy) {
        enemy.getAttacked("basic", this, null);
    }

    @Override
    public void specialAttack(Monster enemy) {
        enemy.getAttacked("special", this, null);
    }

    @Override
    public void elementalAttack(Monster enemy, ElementalAttack elementalAttack) {
        enemy.getAttacked("elemental", this, elementalAttack);
    }

    @Override
    public void useItem(Item item, Monster enemy, int turn, Player player) {
        item.useItem(enemy, turn, player);
    }

    @Override
    public void flee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flee'");
    }
}

package Entity.Monster;
import java.util.List;

import Entity.Item.Item;

public class WaterType extends Monster {

    public WaterType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "WATER", maxMonsterPhase);
    }

    public WaterType(String name, int level, int experiencePoint, ElementType[] elementType, List<ElementalAttack> elementalAttacks, int healthPoint, int attackPower, int spcAttackPower, int elemAttackPower, int defensePower, int maxHealthPoint, int maxAttackPower, int maxSpcAttackPower, int maxElemAttackPower, int maxDefensePower, int monsterPhase, int maxMonsterPhase, int currentMaxHealthPoint, int currentMaxAttackPower, int currentMaxSpcAttackPower, int currentMaxElemAttackPower, int currentMaxDefensePower){
        super(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    }

    // New ability: Bubble
    public void bubble() {
        elementalAttacks.add(new ElementalAttack("Bubble", 40, ElementType.WATER));
    }

    // New ability: Water Gun
    public void waterGun() {
        elementalAttacks.add(new ElementalAttack("Water Gun", 40, ElementType.WATER));
    }

    // New ability: Aqua Jet
    public void aquaJet() {
        elementalAttacks.add(new ElementalAttack("Aqua Jet", 60, ElementType.WATER));
    }

    // New ability: Surf
    public void surf() {
        elementalAttacks.add(new ElementalAttack("Surf", 90, ElementType.WATER));
    }

    // New ability: Hydro Pump
    public void hydroPump() {
        elementalAttacks.add(new ElementalAttack("Hydro Pump", 110, ElementType.WATER));
    }

    // New ability: Waterfall
    public void waterfall() {
        elementalAttacks.add(new ElementalAttack("Waterfall", 80, ElementType.WATER));
    }

    // New ability: Aqua Tail
    public void aquaTail() {
        elementalAttacks.add(new ElementalAttack("Aqua Tail", 90, ElementType.WATER));
    }

    // New ability: Scald
    public void scald() {
        elementalAttacks.add(new ElementalAttack("Scald", 80, ElementType.WATER));
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
    public void useItem(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }

    @Override
    public void flee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flee'");
    }
}

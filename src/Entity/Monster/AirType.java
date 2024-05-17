package Entity.Monster;

import java.util.List;

import Entity.Item.Item;

public class AirType extends Monster {

    public AirType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "AIR", maxMonsterPhase);
    }

    public AirType(String name, int level, int experiencePoint, ElementType[] elementType, List<ElementalAttack> elementalAttacks, int healthPoint, int attackPower, int spcAttackPower, int elemAttackPower, int defensePower, int maxHealthPoint, int maxAttackPower, int maxSpcAttackPower, int maxElemAttackPower, int maxDefensePower, int monsterPhase, int maxMonsterPhase, int currentMaxHealthPoint, int currentMaxAttackPower, int currentMaxSpcAttackPower, int currentMaxElemAttackPower, int currentMaxDefensePower){
        super(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    }

    // New ability: Gust
    public void gust() {
        elementalAttacks.add(new ElementalAttack("Gust", 40, ElementType.AIR));
    }

    // New ability: Air Slash
    public void airSlash() {
        elementalAttacks.add(new ElementalAttack("Air Slash", 75, ElementType.AIR));
    }

    // New ability: Hurricane
    public void hurricane() {
        elementalAttacks.add(new ElementalAttack("Hurricane", 110, ElementType.AIR));
    }

    // New ability: Aerial Ace
    public void aerialAce() {
        elementalAttacks.add(new ElementalAttack("Aerial Ace", 60, ElementType.AIR));
    }

    // New ability: Sky Attack
    public void skyAttack() {
        elementalAttacks.add(new ElementalAttack("Sky Attack", 140, ElementType.AIR));
    }

    // New ability: Air Cutter
    public void airCutter() {
        elementalAttacks.add(new ElementalAttack("Air Cutter", 60, ElementType.AIR));
    }

    // New ability: Fly
    public void fly() {
        elementalAttacks.add(new ElementalAttack("Fly", 90, ElementType.AIR));
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
    public void useItem(Item item) {}

    @Override
    public void flee() {}
}

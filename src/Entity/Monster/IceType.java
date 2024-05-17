package Entity.Monster;

import java.util.List;

import Entity.Item.Item;

public class IceType extends Monster{
    
    public IceType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "ICE", maxMonsterPhase);
    }

    public IceType(String name, int level, int experiencePoint, ElementType[] elementType, List<ElementalAttack> elementalAttacks, int healthPoint, int attackPower, int spcAttackPower, int elemAttackPower, int defensePower, int maxHealthPoint, int maxAttackPower, int maxSpcAttackPower, int maxElemAttackPower, int maxDefensePower, int monsterPhase, int maxMonsterPhase, int currentMaxHealthPoint, int currentMaxAttackPower, int currentMaxSpcAttackPower, int currentMaxElemAttackPower, int currentMaxDefensePower){
        super(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    }
    //Glalie
    public void iceBeam(){
        elementalAttacks.add(new ElementalAttack("Ice Beam", 90, ElementType.ICE));
    }

    //Cloyster
    public void hydroPump(){
        elementalAttacks.add(new ElementalAttack("Hydro Pump", 110, ElementType.ICE));
    }

    // New ability: Blizzard
    public void blizzard() {
        elementalAttacks.add(new ElementalAttack("Blizzard", 120, ElementType.ICE));
    }

    // New ability: Frost Breath
    public void frostBreath() {
        elementalAttacks.add(new ElementalAttack("Frost Breath", 60, ElementType.ICE));
    }

    // New ability: Icicle Spear
    public void icicleSpear() {
        elementalAttacks.add(new ElementalAttack("Icicle Spear", 25, ElementType.ICE)); // Typically hits 2-5 times
    }

    // New ability: Avalanche
    public void avalanche() {
        elementalAttacks.add(new ElementalAttack("Avalanche", 60, ElementType.ICE)); // Power doubles if the user has taken damage in the same turn
    }

    // New ability: Ice Fang
    public void iceFang() {
        elementalAttacks.add(new ElementalAttack("Ice Fang", 65, ElementType.ICE));
    }

    // New ability: Icy Wind
    public void icyWind() {
        elementalAttacks.add(new ElementalAttack("Icy Wind", 55, ElementType.ICE));
    }

    // New ability: Ice Shard
    public void iceShard() {
        elementalAttacks.add(new ElementalAttack("Ice Shard", 40, ElementType.ICE)); // Typically a priority move
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

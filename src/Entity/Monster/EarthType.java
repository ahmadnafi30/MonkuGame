package Entity.Monster;

import Entity.Item.Item;

public class EarthType extends Monster {

    public EarthType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "EARTH", maxMonsterPhase);
    }

    // New ability: Tackle
    public void tackle() {
        elementalAttacks.add(new ElementalAttack("Tackle", 40, ElementType.EARTH));
    }

    // New ability: Mud-Slap
    public void mudSlap() {
        elementalAttacks.add(new ElementalAttack("Mud-Slap", 55, ElementType.EARTH));
    }

    // New ability: Earthquake
    public void earthquake() {
        elementalAttacks.add(new ElementalAttack("Earthquake", 100, ElementType.EARTH));
    }

    // New ability: Dig
    public void dig() {
        elementalAttacks.add(new ElementalAttack("Dig", 80, ElementType.EARTH));
    }

    // New ability: Mud Shot
    public void mudShot() {
        elementalAttacks.add(new ElementalAttack("Mud Shot", 55, ElementType.EARTH));
    }

    // New ability: Sand Tomb
    public void sandTomb() {
        elementalAttacks.add(new ElementalAttack("Sand Tomb", 35, ElementType.EARTH));
    }

    // New ability: Magnitude
    public void magnitude() {
        elementalAttacks.add(new ElementalAttack("Magnitude", 70, ElementType.EARTH));
    }

    // New ability: Earth Power
    public void earthPower() {
        elementalAttacks.add(new ElementalAttack("Earth Power", 90, ElementType.EARTH));
    }

    @Override
    public void basicAttack(Monster enemy) {
        enemy.getAttacked("basic", enemy, null);
    }

    @Override
    public void specialAttack(Monster enemy) {
        enemy.getAttacked("special", enemy, null);
    }

    @Override
    public void elementalAttack(Monster enemy, ElementalAttack elementalAttack) {
        enemy.getAttacked("elemental", enemy, elementalAttack);
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

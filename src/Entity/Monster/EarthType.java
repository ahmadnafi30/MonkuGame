package Entity.Monster;

import java.util.ArrayList;
import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class EarthType extends Monster {
    List<ElementalAttack> choices = new ArrayList<ElementalAttack>();
    public EarthType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "EARTH", maxMonsterPhase, image);
        choices.add(new ElementalAttack("Tackle", 40, ElementType.EARTH));
        choices.add(new ElementalAttack("Mud-Slap", 55, ElementType.EARTH));
        choices.add(new ElementalAttack("Earthquake", 100, ElementType.EARTH));
        choices.add(new ElementalAttack("Dig", 80, ElementType.EARTH));
        choices.add(new ElementalAttack("Mud Shot", 55, ElementType.EARTH));
        choices.add(new ElementalAttack("Sand Tomb", 35, ElementType.EARTH));
        choices.add(new ElementalAttack("Magnitude", 70, ElementType.EARTH));
        choices.add(new ElementalAttack("Earth Power", 90, ElementType.EARTH));
    }

    public EarthType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "EARTH", maxMonsterPhase);
    }

    public EarthType(Monster earthType){
        super(earthType);
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

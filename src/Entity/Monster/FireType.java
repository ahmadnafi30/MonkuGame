package Entity.Monster;

import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class FireType extends Monster {

    public FireType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "FIRE", maxMonsterPhase, image);
    }
    public FireType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "FIRE", maxMonsterPhase);
    }
    public FireType(Monster fireType){
        super(fireType);
    }

    // New ability: Ember
    public void ember() {
        elementalAttacks.add(new ElementalAttack("Ember", 40, ElementType.FIRE));
    }

    // New ability: Flame Thrower
    public void flameThrower() {
        elementalAttacks.add(new ElementalAttack("Flame Thrower", 90, ElementType.FIRE));
    }

    // New ability: Fire Blast
    public void fireBlast() {
        elementalAttacks.add(new ElementalAttack("Fire Blast", 110, ElementType.FIRE));
    }

    // New ability: Fire Spin
    public void fireSpin() {
        elementalAttacks.add(new ElementalAttack("Fire Spin", 35, ElementType.FIRE));
    }

    // New ability: Heat Wave
    public void heatWave() {
        elementalAttacks.add(new ElementalAttack("Heat Wave", 95, ElementType.FIRE));
    }

    // New ability: Inferno
    public void inferno() {
        elementalAttacks.add(new ElementalAttack("Inferno", 100, ElementType.FIRE));
    }

    // New ability: Flame Charge
    public void flameCharge() {
        elementalAttacks.add(new ElementalAttack("Flame Charge", 50, ElementType.FIRE));
    }

    // New ability: Overheat
    public void overheat() {
        elementalAttacks.add(new ElementalAttack("Overheat", 130, ElementType.FIRE));
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

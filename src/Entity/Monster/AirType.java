package Entity.Monster;

import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class AirType extends Monster {

    public AirType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "AIR", maxMonsterPhase, image);
    }

    public AirType(Monster monster){
        super(monster);
    }

    public void changeElement(){

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
    public void useItem(Item item, Monster enemy, int turn, Player player) {
        item.useItem(enemy, turn, player);
    }
    @Override
    public void flee() {}
}

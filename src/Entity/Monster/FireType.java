package Entity.Monster;

import java.util.ArrayList;
import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class FireType extends Monster {

    List<ElementalAttack> choices = new ArrayList<ElementalAttack>();
    public FireType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "FIRE", maxMonsterPhase, image);
        choices.add(new ElementalAttack("Ember", 40, ElementType.FIRE, 3));
        choices.add(new ElementalAttack("Flame Thrower", 90, ElementType.FIRE, 1));
        choices.add(new ElementalAttack("Fire Blast", 110, ElementType.FIRE, 1));
        choices.add(new ElementalAttack("Fire Spin", 35, ElementType.FIRE, 4));
        choices.add(new ElementalAttack("Heat Wave", 95, ElementType.FIRE, 2));
        choices.add(new ElementalAttack("Inferno", 100, ElementType.FIRE, 1));
        choices.add(new ElementalAttack("Flame Charge", 50, ElementType.FIRE, 2));
        choices.add(new ElementalAttack("Overheat", 130, ElementType.FIRE, 1));
    }

    public String setElementalSkills(String name){
        if (this.elementalAttacks.size() == 2) {
            return "Elemental attack sudah penuh";
        }
        for (int i = 0; i < choices.size(); i++) {
            if (choices.get(i).getNama().equalsIgnoreCase(name)) {
                this.elementalAttacks.add(choices.get(i));
                return choices.get(i).getNama() + " berhasil ditambahkan";
            }
        }
        return "Elemental attack tidak ditemukan";
    }
    
    public FireType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "FIRE", maxMonsterPhase);
    }
    public FireType(Monster fireType){
        super(fireType);
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

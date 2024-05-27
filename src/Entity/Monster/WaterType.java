package Entity.Monster;

import java.util.ArrayList;
import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class WaterType extends Monster {
    private List<ElementalAttack> choices = new ArrayList<ElementalAttack>();

    public WaterType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "WATER", maxMonsterPhase, image);
        addChoices();
    }

    public WaterType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "WATER", maxMonsterPhase);
        addChoices();
    }

    public WaterType(Monster waterType) {
        super(waterType);
        addChoices();
    }

    public void addChoices() {
        choices.add(new ElementalAttack("Bubble", 40, ElementType.WATER, 3));
        choices.add(new ElementalAttack("Water Gun", 40, ElementType.WATER, 3));
        choices.add(new ElementalAttack("Aqua Jet", 60, ElementType.WATER, 2));
        choices.add(new ElementalAttack("Surf", 90, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Hydro Pump", 110, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Waterfall", 80, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Aqua Tail", 90, ElementType.WATER, 1));
        choices.add(new ElementalAttack("Scald", 80, ElementType.WATER, 2));
    }

    public List<ElementalAttack> getChoices() {
        return choices;
    }

    public String addElementalSkills(String name) {
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

    public String changeElementalSkills(int indexToBeChanged, String nameChange){
        for (int i = 0; i < choices.size(); i++) {
            if (choices.get(i).getNama().equalsIgnoreCase(nameChange)) {
                this.elementalAttacks.set(indexToBeChanged, choices.get(i));
                System.out.println("Elemental attack berhasil diganti");
                return elementalAttacks.get(indexToBeChanged) + " berhasil diganti";
            }
        }
        return "Elemental attack tidak ditemukan";
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

package Entity.Monster;

import java.util.ArrayList;
import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class IceType extends Monster {
    List<ElementalAttack> choices = new ArrayList<>();

    public IceType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "ICE", maxMonsterPhase, image);
        addChoices();
    }

    public IceType(Monster iceType) {
        super(iceType);
        addChoices();
    }

    public IceType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "ICE", maxMonsterPhase);
        addChoices();
    }

    public void addChoices() {
        elementalAttacks.add(new ElementalAttack("Ice Beam", 90, ElementType.ICE, 1));
        elementalAttacks.add(new ElementalAttack("Hydro Pump", 110, ElementType.ICE, 1));
        elementalAttacks.add(new ElementalAttack("Blizzard", 120, ElementType.ICE, 1));
        elementalAttacks.add(new ElementalAttack("Frost Breath", 60, ElementType.ICE, 3));
        elementalAttacks.add(new ElementalAttack("Icicle Spear", 25, ElementType.ICE, 4));
        elementalAttacks.add(new ElementalAttack("Avalanche", 60, ElementType.ICE, 2));
        elementalAttacks.add(new ElementalAttack("Ice Fang", 65, ElementType.ICE, 2));
        elementalAttacks.add(new ElementalAttack("Icy Wind", 55, ElementType.ICE, 3));
        elementalAttacks.add(new ElementalAttack("Ice Shard", 40, ElementType.ICE, 3));
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

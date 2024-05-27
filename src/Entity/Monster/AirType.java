package Entity.Monster;

import java.util.ArrayList;
import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class AirType extends Monster {

    List<ElementalAttack> choices = new ArrayList<ElementalAttack>();    
    public AirType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "AIR", maxMonsterPhase, image);
        addChoices();
    }

    public AirType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "AIR", maxMonsterPhase);
        addChoices();
    }
    
    public AirType(Monster monster){ 
        super(monster);
        addChoices();
    }
    public List<ElementalAttack> getChoices() {
        return choices;
    }

    public void addChoices(){
        choices.add(new ElementalAttack("Gust", 40, ElementType.AIR, 3));
        choices.add(new ElementalAttack("Air Slash", 75, ElementType.AIR, 1));
        choices.add(new ElementalAttack("Hurricane", 110, ElementType.AIR, 1));
        choices.add(new ElementalAttack("Aerial Ace", 60, ElementType.AIR, 2));
        choices.add(new ElementalAttack("Sky Attack", 140, ElementType.AIR, 1));
        choices.add(new ElementalAttack("Air Cutter", 60, ElementType.AIR, 2));
        choices.add(new ElementalAttack("Fly", 90, ElementType.AIR, 1));
    }
    public String addElementalSkills(String name){
        if (this.elementalAttacks.size() == 2) {
            return "Elemental attack sudah penuh";
        }
        for (int i = 0; i < choices.size(); i++) {
            if (choices.get(i).getNama().equalsIgnoreCase(name)) {
                this.elementalAttacks.add(choices.get(i));
                System.out.println("Elemental attack berhasil ditambahkan");
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
    public void flee() {}
}

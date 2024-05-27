package Entity.Monster;

import java.util.ArrayList;
import java.util.List;

import Entity.Item.Item;
import Entity.Player.Player;

public class EarthType extends Monster {
    List<ElementalAttack> choices = new ArrayList<ElementalAttack>();
    public EarthType(String name, int monsterPhase, int maxMonsterPhase, String image) {
        super(name, monsterPhase, "EARTH", maxMonsterPhase, image);
        addChoices();
    }
    
    
    public EarthType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "EARTH", maxMonsterPhase);
        addChoices();
    }
    
    public EarthType(Monster earthType){
        super(earthType);
        addChoices();
    }

    public void addChoices(){
        choices.add(new ElementalAttack("Tackle", 40, ElementType.EARTH,3));
        choices.add(new ElementalAttack("Mud-Slap", 55, ElementType.EARTH, 3));
        choices.add(new ElementalAttack("Earthquake", 100, ElementType.EARTH, 1));
        choices.add(new ElementalAttack("Dig", 80, ElementType.EARTH, 1));
        choices.add(new ElementalAttack("Mud Shot", 55, ElementType.EARTH, 2));
        choices.add(new ElementalAttack("Sand Tomb", 35, ElementType.EARTH, 4));
        choices.add(new ElementalAttack("Magnitude", 70, ElementType.EARTH, 2));
        choices.add(new ElementalAttack("Earth Power", 90, ElementType.EARTH, 1));
    }
    public String addElementalSkills(String name){
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
    public List<ElementalAttack> getChoices() {
        return choices;
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

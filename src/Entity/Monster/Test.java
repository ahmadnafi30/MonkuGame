package Entity.Monster;

import java.util.ArrayList;
import java.util.List;


import Entity.Monster.*;

public class Test {
    public static void main(String[] args) {
        Monster monster = new FireType("Raka", 1, 4);
        ((FireType)monster).ember();
        monster.setLevel(20);
        monster.displayDetailMonster();
        String[] properties = monster.monsterProperty().split("\n");
        for(int i = 0 ; i < properties.length ; i++){
            System.out.println(i + "  "+properties[i]);
        }
        monster.evolution("air");
        monster = monster.changeMonsterClass();
        System.out.println(monster.getMonster());
        monster.displayDetailMonster();
        
    }
}

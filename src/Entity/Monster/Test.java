package Entity.Monster;

import java.util.ArrayList;
import java.util.List;


import Entity.Monster.*;

public class Test {
    public static void main(String[] args) {
        // Create an instance of the Monster class
        Monster monster = new IceType("Nafi", 1, 4);
        monster.setLevel(20);
        monster.displayDetailMonster();
        ((IceType) monster).iceBeam();
        ((IceType) monster).hydroPump();
        monster.evolution("Fire");
        monster.displayDetailMonster();
        System.out.println();
        Monster enemy = new FireType("Raka", 1, 4);
        ((FireType) enemy).ember();
        ((FireType) enemy).flameThrower();
        enemy.displayDetailMonster();
        // Access the attributes

        for(int i = 0; i < 5; i++){
            monster.basicAttack(enemy);
            enemy.basicAttack(monster);
            enemy.elementalAttack(monster, enemy.elementalAttacks.get(0));
            System.out.println(enemy.getHealthPoint());
        }

        monster.healingHp(1000);
        monster.setLevel(20);
        monster.displayDetailMonster();
        String elementNow = monster.getElementType().toString();
        monster.evolution(elementNow);
        String[] monsterProperties = monster.monsterProperty().split("\n");
        for(int i = 0 ; i < monsterProperties.length ; i++){
            System.out.println(i + "  "+monsterProperties[i]);
        }
        
    }           

    // // public static Monster loadMonster(String[] attributes){
    //     String name = attributes[0];
    //     int level = Integer.parseInt(attributes[1]);
    //     int experiencePoint = Integer.parseInt(attributes[2]);
    //     ElementType eT = Enum.valueOf(ElementType.class, attributes[3]);
    //     ElementType elementType[] = new ElementType[]{eT};
    //     List<ElementalAttack> elementalAttacks = new ArrayList<>();
    //     int healthPoint = Integer.parseInt(attributes[4]);
    //     int attackPower;
    //     int spcAttackPower;
    //     int elemAttackPower;
    //     int defensePower;
    //     int maxHealthPoint;
    //     int maxAttackPower;
    //     int maxSpcAttackPower;
    //     int maxElemAttackPower;
    //     int maxDefensePower;
    //     int monsterPhase;
    //     int maxMonsterPhase;
    //     int currentMaxHealthPoint;
    //     int currentMaxAttackPower;
    //     int currentMaxSpcAttackPower;
    //     int currentMaxElemAttackPower;
    //     int currentMaxDefensePower;
    //     for(int i = 0; i < attributes.length; i++){
            
    //     }
    //     switch (elementType[0]) {
    //         case WATER:
    //             return new WaterType(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    //         case FIRE:
    //             return new FireType(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    //         case ICE:
    //             return new IceType(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    //         case AIR:
    //             return new AirType(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    //         case EARTH:
    //             return new EarthType(name, level, experiencePoint, elementType, elementalAttacks, healthPoint, attackPower, spcAttackPower, elemAttackPower, defensePower, maxHealthPoint, maxAttackPower, maxSpcAttackPower, maxElemAttackPower, maxDefensePower, monsterPhase, maxMonsterPhase, currentMaxHealthPoint, currentMaxAttackPower, currentMaxSpcAttackPower, currentMaxElemAttackPower, currentMaxDefensePower);
    //         default:
    //             return null;
    //     }
    // }
}

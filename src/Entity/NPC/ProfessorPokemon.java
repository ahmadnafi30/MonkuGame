package Entity.NPC;

import java.util.Scanner;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class ProfessorPokemon extends NPC {
    
    public ProfessorPokemon(String name, String job) {
        super(name, job);
    }

    public void dialogWithPlayer() {
        System.out.println("Hello, I am Professor " + name + ". How can I assist you?");
    }

    public void healPokemon(Player player, String monsterName) {
        for (Monster monster : player.getMonsters()) {
            if (monster.getName().equals(monsterName)) {
                monster.setHealthPoint(monster.getMaxHealthPoint());
                System.out.println("Professor " + name + ": Your monster " + monster.getName() + " is now fully healed.");
                return;
            }
        }
        System.out.println("Professor " + name + ": No such monster in your inventory.");
    }

    public boolean levelUpMonku(Monster monster, String lvlUpAttribute){
        if(monster.getExperiencePoint() < 100) return false;
        if(lvlUpAttribute.equalsIgnoreCase("Health Point")){
            monster.levelUp(2, 0, 0, 0, 0);
            return true;
        } else if (lvlUpAttribute.equalsIgnoreCase("Attack Power")){
            monster.levelUp(0, 2, 0, 0, 0);
            return true;
        } else if (lvlUpAttribute.equalsIgnoreCase("Special Attack Power")){
            monster.levelUp(0, 0, 2, 0, 0);
            return true;
        } else if (lvlUpAttribute.equalsIgnoreCase("Elemental Attack Power")){
            monster.levelUp(0, 0, 0, 2, 0);
            return true;
        } else if (lvlUpAttribute.equalsIgnoreCase("Defense Power")){
            monster.levelUp(0, 0, 0, 0, 2);
            return true;
        }
        return false;
    }

    public boolean evolvePokemon(Monster monster, String element) {
        if (monster.evolution(element)) {
            monster = monster.changeMonsterClass();
            System.out.println(monster.getMonster());
            System.out.println("Professor " + name + ": Your monster " + monster.getName() + " has evolved!");
            System.out.println("Apakah kamu ingin mengubah skill element-nya? (y/n)");
            String answer = "n";
            if(answer.equals("y")){
                System.out.println("Mau mengubah element attack yang mana?");
                switch (monster.getELementTypeStr()) {
                    case "FIRE":
                        System.out.println("""
                            1. Ember, 40 Power
                            2. Flame Thrower, 90 Power
                            3. Fire Blast, 110 Power
                            4. Fire Spin, 35 Power
                            5. Heat Wave, 95 Power
                            6. Inferno, 100 Power
                            7. Flame Charge, 50 Power
                            8. Overheat, 130 Power
                                """);
                        break;
                    
                    case "ICE":
                        System.out.println("""
                            1. Ice Beam, 90 Power
                            2. Hydro Pump, 110 Power
                            3. Blizzard, 120 Power
                            4. Frost Breath, 60 Power
                            5. Icicle Spear, 25 Power
                            6. Avalanche, 60 Power
                            7. Ice Fang, 65 Power
                            8. Icy Wind, 55 Power
                            9. Ice Shard, 40 Power
                                """);
                        break;

                    case "WATER":
                        System.out.println("""
                            1. Bubble, 40 Power
                            2. Water Gun, 40 Power
                            3. Aqua Jet, 60 Power
                            4. Surf, 90 Power
                            5. Hydro Pump, 110 Power
                            6. Waterfall, 80 Power
                            7. Aqua Tail, 90 Power
                            8. Scald, 80 Power                            
                                """);
                        break;

                    case "AIR":
                        System.out.println("""
                            1. Gust, 40 Power
                            2. Air Slash, 75 Power
                            3. Hurricane, 110 Power
                            4. Aerial Ace, 60 Power
                            5. Sky Attack, 140 Power
                            6. Air Cutter, 60 Power
                            7. Fly, 90 Power
                                """);
                        break;

                    case "EARTH":
                        System.out.println("""
                            1. Tackle, 40 Power
                            2. Mud-Slap, 55 Power
                            3. Earthquake, 100 Power
                            4. Dig, 80 Power
                            5. Mud Shot, 55 Power
                            6. Sand Tomb, 35 Power
                            7. Magnitude, 70 Power
                            8. Earth Power, 90 Power
                                """);
                        break;

                    default:
                        break;
                }
                return true;
            }
        } else {
            System.out.println("Professor " + name + ": This monster cannot evolve.");
            return false;
        }
        return false;
    }

    // @Override
    public void doJob() {
        // Implementation of professor's job, if necessary
    }
}

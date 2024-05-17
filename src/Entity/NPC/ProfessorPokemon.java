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

    public void healPokemon(Monster monster) {
        monster.setHealthPoint(monster.getMaxHealthPoint());
        System.out.println("Professor " + name + ": Your monster " + monster.getName() + " is now fully healed.");
    }

    public void evolvePokemon(Monster monster, String element) {
        if (monster.evolution(element)) {
            monster.evolution(job);
            System.out.println("Professor " + name + ": Your monster " + monster.getName() + " has evolved!");
        } else {
            System.out.println("Professor " + name + ": This monster cannot evolve.");
        }
    }

    // @Override
    public void doJob() {
        // Implementation of professor's job, if necessary
    }
}

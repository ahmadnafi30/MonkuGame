package Entity.Locations;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import Entity.DataStorage;
import Entity.Item.Item;
import Entity.Monster.Monster;
import Entity.NPC.ProfessorPokemon;
import Entity.Player.Player;

public class HomeBase extends Locations {
    private ProfessorPokemon professor;

    public HomeBase(String locationName) {
        super(locationName);
        professor = new ProfessorPokemon("Professor Oak", "Pokemon Professor");
    }

    @Override
    public void printDetailLocation() {
        System.out.println("Home Base: " + locationName);
        System.out.println("This is your safe haven where you can rest, recover, and prepare for your next adventure.");
        System.out.println("Professor Oak is here to assist you with anything you need.");
    }

    public void interactWithPlayer(Player player, int choice, Monster monster) {
        switch (choice) {
            case 1:
                int monsterIndex = -1;
                for (Monster foundMonster : player.getMonsters()) {
                    monsterIndex++;
                    if (foundMonster.getName().equals(monster.getName())) {
                        break;
                    }
                }
                healMonsters(player, monsterIndex);
                break;
            case 2:
                monsterIndex = -1;
                for (Monster foundMonster : player.getMonsters()) {
                    monsterIndex++;
                    if (foundMonster.getName().equals(monster.getName())) {
                        break;
                    }
                }
                evolveMonsters(player, monsterIndex);
                break;
            case 3:
                saveGame(player);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void healMonsters(Player player, int monsterIndex) {
        if (monsterIndex >= 0 && monsterIndex < player.getMonsters().size()) {
            Monster monster = player.getMonsters().get(monsterIndex);
            professor.healPokemon(player, monster.getName());
            System.out.println("Professor: Your monster " + monster.getName() + " is now fully healed.");
        } else {
            System.out.println("Professor: Invalid monster selection.");
        }
    }

    private void evolveMonsters(Player player, int monsterIndex) {
        if (monsterIndex >= 0 && monsterIndex < player.getMonsters().size()) {
            Monster monster = player.getMonsters().get(monsterIndex);
            professor.evolvePokemon(monster, monster.getELementTypeStr());
            System.out.println("Professor: Your monster " + monster.getName() + " has evolved!");
        } else {
            System.out.println("Professor: Invalid monster selection.");
        }
    }

    private void saveGame(Player player) {
        System.out.println("Professor: Saving your game...");
        try {
            FileOutputStream fileOut = new FileOutputStream("saveGames.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(bos);
    
            DataStorage storage = new DataStorage();
            
            storage.name = player.getName();
            storage.level = player.getLevel();
            storage.exp = player.getExp();
            storage.inventory = player.getInventory();
            storage.coin = player.getCoin();
            storage.monsters = player.getMonsters();
            storage.timePlayed = player.getTimePlayed();
            storage.startTime = player.getStartTime();
            storage.locationPlayer = player.getLocationPlayer();
            storage.image = player.getImage();
            out.writeObject(storage);
            out.close();
            bos.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gagal save games: " + e.getMessage());
            return;
        }
        System.out.println("Professor: Your game has been saved successfully.");
    }

    
}

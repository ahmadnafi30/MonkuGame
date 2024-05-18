package Entity.Locations;

import java.util.Scanner;

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

    public void interactWithPlayer(Player player) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("Professor: Welcome, " + player.getName() + "! How can I assist you today?");
        System.out.println("1. Heal Monsters");
        System.out.println("2. Evolve Monsters");
        System.out.println("3. Save Game");
        System.out.println("4. Search for Pokémon");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");

        choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Professor: Please select the monster you want to heal:");
                player.checkMonsters();
                System.out.print("Select monster: ");
                int monsterIndex = scanner.nextInt();
                scanner.nextLine(); // consume newline
                healMonsters(player, monsterIndex);
                break;
            case "2":
                System.out.println("Professor: Please select the monster you want to evolve:");
                player.checkMonsters();
                System.out.print("Select monster: ");
                monsterIndex = scanner.nextInt();
                scanner.nextLine(); // consume newline
                evolveMonsters(player, monsterIndex);
                break;
            case "3":
                saveGame(player);
                break;
            case "4":
                searchForPokemon(player);
                break;
            case "5":
                System.out.println("Professor: Take care, and come back anytime you need help!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        scanner.close();
    }

    private void healMonsters(Player player, int monsterIndex) {
        if (monsterIndex >= 0 && monsterIndex < player.getMonsters().size()) {
            Monster monster = player.getMonsters().get(monsterIndex);
            professor.healPokemon(monster);
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
        // Implement game saving logic
        System.out.println("Professor: Your game has been saved successfully.");
    }

    private void searchForPokemon(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Professor: Let's search for some Pokémon!");
        System.out.print("Enter the type of Pokémon you are looking for: ");
        String pokemonType = scanner.nextLine();

        // Simulate searching for Pokémon
        System.out.println("Professor: Searching for " + pokemonType + " type Pokémon...");
        try {
            Thread.sleep(2000); // Simulate a delay in searching
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        boolean found = new java.util.Random().nextBoolean(); // Randomly decide if a Pokémon is found
        if (found) {
            System.out.println("Professor: Congratulations! You found a rare " + pokemonType + " Pokémon!");
            // Add logic to add the found Pokémon to the player's collection
        } else {
            System.out.println("Professor: Sorry, no " + pokemonType + " type Pokémon were found. Try again later.");
        }
        scanner.close();
    }
}

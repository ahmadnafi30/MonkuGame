package Entity.Player;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Entity.Battle;
import Entity.ItemInteract;
import Entity.Item.*;
import Entity.Locations.Locations;
// import Entity.Locations.HomeBase;
import Entity.Monster.*;
import Entity.NPC.*;

public class Player implements ItemInteract, Battle {

    private String name;
    private int level;
    private int exp;
    private Map<Item, Integer> inventory;
    private int coin;
    private ArrayList<Monster> monsters;
    private final int MAX_CAPACITY = 15;
    private Duration timePlayed;
    private Instant startTime;
    private Locations locationPlayer;
    private String image;

    public Player(String name, Locations locationPlayer, String image) {
        this.name = name;
        this.level = 1;
        this.coin = 100;
        this.exp = 0;
        this.inventory = new HashMap<>();
        this.monsters = new ArrayList<>();
        this.timePlayed = Duration.ZERO;
        this.startTime = Instant.now();
        this.locationPlayer = locationPlayer;
        this.image = image;
    }

    public void enterLocation(Locations enter){
        this.locationPlayer = enter;
    }


        public void getReward(Item reward) {
            if (inventoryIsFull() || inventorySize() + 1 > MAX_CAPACITY) {
                System.out.println("Inventory penuh. Tidak bisa mendapatkan item.");
            } else {
                inventory.put(reward, inventory.getOrDefault(reward, 0) + 1);
                System.out.println("Item " + reward.name + " berhasil ditambahkan ke inventory.");
                printItemDetails(reward);
            }
        }
        
    

    public Duration updateTimePlayed() {
        this.timePlayed = Duration.between(this.startTime, Instant.now());
        return timePlayed;
    }

    private int inventorySize() {
        return inventory.values().stream().mapToInt(Integer::intValue).sum();
    }

    public boolean inventoryIsFull() {
        return inventorySize() >= MAX_CAPACITY;
    }

    private void incrementExp(int amount) {
        this.exp += amount;
        System.out.println("EXP bertambah " + amount + ", total EXP: " + exp);
        while (exp >= 100) {
            exp -= 100;
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        System.out.println("Selamat! Level anda naik menjadi " + level);
    }

    @Override
    public void buyItem(Item itembuy, int quantity) {
        if (itembuy.price * quantity > coin) {
            System.out.println("Koin tidak cukup");
        } else if (inventoryIsFull() || inventorySize() + quantity > MAX_CAPACITY) {
            int availableSpace = MAX_CAPACITY - inventorySize();
            System.out.println("Inventory penuh. Hanya bisa membeli " + availableSpace + " item.");
        } else {
            coin -= itembuy.price * quantity;
            inventory.put(itembuy, inventory.getOrDefault(itembuy, 0) + quantity);
            System.out.println("Item " + itembuy.name + " berhasil dibeli dan ditambahkan ke inventory");
            System.out.println("Sisa koin saat ini: " + coin);

            printItemDetails(itembuy);
            incrementExp(5); // Add exp for buying item
        }
    }

    private void printItemDetails(Item item) {
        if (item instanceof BuffPotion) {
            ((BuffPotion) item).printDetailItemm();
        } else if (item instanceof DefensivePotion) {
            ((DefensivePotion) item).printDetailItemm();
        } else if (item instanceof HealthPotion) {
            ((HealthPotion) item).printDetailItemm();
        } else if (item instanceof PoisonPotion) {
            ((PoisonPotion) item).printDetailItemm();
        } else if (item instanceof Teleportation) {
            ((Teleportation) item).printDetailItemm();
        }
        System.out.println();
    }

    @Override
    public void checkInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Tidak ada item apapun didalam tas (0/" + MAX_CAPACITY + ")");
        } else {
            inventory.forEach((item, quantity) -> {
                printItemDetails(item);
                System.out.println("Quantity: " + quantity);
            });
            System.out.println("Isi Inventory (" + inventorySize() + "/" + MAX_CAPACITY + ")");
        }
    }

    public void addCoin(int amount) {
        this.coin += amount;
        System.out.println("Koin anda bertambah menjadi " + this.coin);
    }

    public void checkMonsters() {
        if (monsters.isEmpty()) {
            System.out.println("Tidak ada monster dalam daftar.");
        } else {
            monsters.forEach(Monster::displayDetailMonster);
        }
    }

    public void catchMonster(Monster monster) {
        this.monsters.add(monster);
        System.out.println("Monster " + monster.getName() + " berhasil dijinakkan");
        monster.displayDetailMonster();
    }

    public Monster deployMonster(int nomor){
        if(monsters.isEmpty()) {
            System.out.println("Tidak ada monster yang dapat digunakan.");
        } else {
            monsters.forEach(Monster::displayDetailMonster);
        }

        Monster depoloy = monsters.get(nomor);

        return depoloy;
    }


    // // Method untuk memilih aksi oleh player
    // private int chooseAction() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("Choose your action:");
    //     System.out.println("1. Basic Attack");
    //     System.out.println("2. Special Attack");
    //     System.out.println("3. Elemental Attack");
    //     System.out.println("4. Use Item");
    //     System.out.println("5. Flee");
    //     System.out.print("Enter your choice: ");
    //     return scanner.nextInt();
    // }

    
    @Override
    public void sellItem(Item itemsell, int quantity) {
        if (inventory.containsKey(itemsell) && inventory.get(itemsell) >= quantity) {
            inventory.put(itemsell, inventory.get(itemsell) - quantity);
            coin += itemsell.price * quantity;
            if (inventory.get(itemsell) == 0) {
                inventory.remove(itemsell);
            }
            System.out.println("Item " + itemsell.name + " berhasil dijual");
            System.out.println("Koin saat ini: " + coin);
            incrementExp(5); // Add exp for selling item
        } else {
            System.out.println("Tidak memiliki item tersebut dalam jumlah yang cukup untuk dijual");
        }
    }

    @Override
    public void basicAttack(Monster enemy) {
        System.out.println("Player " + name + " uses a basic attack!");
        // Implementation of basic attack
    }

    @Override
    public void specialAttack(Monster enemy) {
        System.out.println("Player " + name + " uses a special attack!");
        // Implementation of special attacksssssss
    }

    @Override
    public void elementalAttack(Monster enemy, ElementalAttack elementalAttack) {
    }

    

    @Override
    public void useItem(Item item) {
        if (inventory.containsKey(item) && inventory.get(item) > 0) {
            System.out.println("Player " + name + " uses " + item.name + "("+ item.rarity +")" + "!");
            inventory.put(item, inventory.get(item) - 1);
        } else {
            System.out.println("Item not available in inventory.");
        }
    }

    @Override
    public void flee() {
        for (Item item : inventory.keySet()) {
            if (item instanceof Teleportation) {
                useItem(item);
                System.out.println("Player " + name + " flees from the battle!");
                return;
            }
        }
    }

    public void winBattle() {
        System.out.println("Player " + name + " wins the battle!");
        incrementExp(10); // Add exp for winning a battle
    }

    public void printDetailPlayer() {
        System.out.println("Player Details:");
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("Experience: " + exp);
        System.out.println("Coins: " + coin);
        System.out.println("Time Played: " + updateTimePlayed().toMinutes() + " minutes");
        System.out.println("Inventory Size: " + inventorySize() + "/" + MAX_CAPACITY);
        System.out.println("Monsters: " +  monsters.size());
    
    }
    
    public void printDialogWithNPC(NPC npc){
        System.out.println("Player " + name + " is talking to " + npc.getName() + "!");
        if (npc instanceof ProfessorPokemon) {
            
        } else if (npc instanceof ItemSeller) {
            
        }
    } 

    public String playerProperty(){
        return name + "\n" + level + "\n" + exp + "\n" + coin + "\n" + updateTimePlayed().toMinutes() + "\n" + inventorySize() + "\n" + monsters;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    public int getCoin() {
        return coin;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public Duration getTimePlayed() {
        return timePlayed;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Locations getLocationPlayer() {
        return locationPlayer;
    }
}
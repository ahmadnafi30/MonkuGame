package Entity.Locations;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Entity.Item.Item;
import Entity.Monster.Monster;
import Entity.Player.Player;

public class Dungeon extends Locations {
    private ArrayList<Monster> monsters;
    private ArrayList<Item> rewards;
    private int level;
    private String images;
    private String guardsImages;
    private String guardsName;
    private String music;

    public Dungeon(String locationName, Monster[] monsters, Item[] rewards, int level, String images, String guardsImages, String guardsName, String music) {
        super(locationName);
        this.monsters = new ArrayList<>();
        for (Monster monster : monsters) {
            this.monsters.add(monster);
        }
        this.rewards = new ArrayList<>();
        for (Item reward : rewards) {
            this.rewards.add(reward);
        }
        this.level = level;
        this.images = images;
        this.guardsImages = guardsImages;
        this.guardsName = guardsName;
    }


    public String getMusic(){
        return this.music;
    }



    public int monsterLenght(){
        int count = -1;
        for (Monster monster : monsters) {
            count++;
        }
        return count;
    }

    // Getters and setters
    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public ArrayList<Item> getRewards() {
        return this.rewards;
    }

    public void setRewards(ArrayList<Item> rewards) {
        this.rewards = rewards;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImages() {
        return this.images;
    }

    public String getGuardImage() {
        return this.guardsImages;
    }

    public String getGuardName() {
        return this.guardsName;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public void printDetailLocation() {
        System.out.println("Dungeon: " + locationName);
        System.out.println("Monsters in Dungeon:");
        for (Monster monster : monsters) {
            monster.displayDetailMonster();
        }
        System.out.println("Rewards in Dungeon:");
        for (Item reward : rewards) {
            reward.printDetailItemm();
        }
    }

    public void exitDungeon() {
        System.out.println("Exiting the dungeon...");
    }

    public void collectReward(Player player, Item rewardItem) {
        System.out.println("Collecting rewards...");
        player.getReward(rewardItem);
    }

    public void detailLocation() {
        System.out.println("Dungeon: " + locationName);
        System.out.println("Welcome to the Dungeon of " + locationName + "!");
        System.out.println("Here, you can embark on thrilling adventures, exploring every nook and cranny of this mysterious place.");
        System.out.println("Beware! Ferocious monsters lurk in the shadows, ready to pounce at any moment.");
        System.out.println("But don't be discouraged, brave explorer. For within this dungeon, great rewards await those who dare to venture deep.");
        System.out.println("Treasures, powerful artifacts, and untold riches can be yours if you survive the perils that lie ahead.");
        System.out.println("Stay vigilant, keep your wits about you, and prepare for an adventure like no other!");
    }

    public void battle(Player player, int monsterIndex) {
        Scanner in = new Scanner(System.in);
        if (monsterIndex < 0 || monsterIndex >= monsters.size()) {
            System.out.println("Invalid monster index.");
            return;
        }

        Random random = new Random();
        Monster enemy = monsters.get(random.nextInt(monsters.size()));
        Monster playerMonster = player.deployMonster(monsterIndex);

        if (playerMonster == null) {
            System.out.println("No monster deployed for battle.");
            return;
        }

        System.out.println("Battle begins between " + playerMonster.getName() + " and " + enemy.getName() + "!");

        while (playerMonster.getHealthPoint() > 0 && enemy.getHealthPoint() > 0) {
            System.out.println("Your turn");
            System.out.println("""
                    1. Basic Attack
                    2. Special Attack
                    3. Elemental Attack
                    """);
            int pilihanAttack = in.nextInt();

            switch (pilihanAttack) {
                case 1:
                    player.basicAttack(enemy);
                    break;
                case 2:
                    player.specialAttack(enemy);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            if (enemy.getHealthPoint() <= 0) {
                System.out.println(playerMonster.getName() + " wins the battle!");
                player.winBattle();
                collectReward(player, getRandomReward());
                return;
            }

            System.out.println(enemy.getName() + " attacks!");
            int randomAttackMonster = random.nextInt(3);
            switch (randomAttackMonster) {
                case 0:
                    enemy.basicAttack(playerMonster);
                    break;
                case 1:
                    enemy.specialAttack(playerMonster);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid attack choice for monster.");
            }

            if (playerMonster.getHealthPoint() <= 0) {
                System.out.println(enemy.getName() + " wins the battle!");
                return;
            }
        }

        in.close();
    }

    public Item getRandomReward() {
        Random random = new Random();
        return rewards.get(random.nextInt(rewards.size()));
    }

    public Monster getRandomMonster() {
        Random random = new Random();
        return monsters.get(random.nextInt(monsters.size()));
    }

    public String getName() {
        return this.locationName;
    }

    public String getDifficultyLevel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDifficultyLevel'");
    }
}

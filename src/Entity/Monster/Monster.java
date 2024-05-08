package Entity.Monster;

public class Monster {

    // Attributes
    private String name;
    private int level;
    private int experiencePoint;
    private ElementType[] elementType;
    private int healthPoint;
    private int attackPower;
    private int spcAttackPower;
    private int elemAttackPower;
    private int defensePower;

    // Constructor
    public Monster(String name) {
        this.name = name;
        this.level = 1;
        this.experiencePoint = 0;
        // Initialize other attributes as needed
    }

    // Methods

    // Method to handle evolution
    public void evolution(String character) {
        // Logic for evolution
    }

    // Method to level up
    public void levelUp() {
        // Logic to increase level and update stats
    }

    // Method to heal HP
    public void healingHp() {
        // Logic to restore HP
    }

    // Method to display details of the Pokemon
    public void displayDetailMonster() {
        // Display all attributes of the Pokemon
    }

    // Additional methods can be added as needed

    // Nested enum for Pokemon element types
    public enum ElementType {
        FIRE,
        WATER,
        GRASS,
        ELECTRIC,
        // Add more elements as needed
    }
}

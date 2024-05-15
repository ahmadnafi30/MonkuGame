package Entity.Monster;
import java.util.*;

public abstract class Monster {

    // Attributes
    private String name;
    private int level;
    private int experiencePoint;
    private ElementType[] elementType;
    protected List<ElementalAttack> elementalAttacks;
    private int healthPoint;
    private int attackPower;
    private int spcAttackPower;
    private int elemAttackPower;
    private int defensePower;
    private int maxHealthPoint;
    private int maxAttackPower;
    private int maxSpcAttackPower;
    private int maxElemAttackPower;
    private int maxDefensePower;
    private int monsterPhase; 
    private int maxMonsterPhase;

    // Constructor
    public Monster(String name, int monsterPhase, String elementType, int maxMonsterPhase) {
        this.elementalAttacks = new ArrayList<>();
        this.name = name;
        this.level = 1;
        this.experiencePoint = 0;
        this.monsterPhase = monsterPhase;
        changeElementType(elementType);
        
        this.maxMonsterPhase = maxMonsterPhase;
        setAttributesMax(monsterPhase);

        // Initialize the current values to some defaults if needed, e.g., halfway between min and max
        this.healthPoint = maxHealthPoint / 2;
        this.attackPower = maxAttackPower / 2;
        this.spcAttackPower = maxSpcAttackPower / 2;
        this.elemAttackPower = maxElemAttackPower / 2;
        this.defensePower = maxDefensePower / 2;
    }

    //will be critical if 2 and 1 if not
    public int dmgFormula(Monster monster, int critikal, String attackType, ElementalAttack elementalAttack) {
        switch (attackType) {
            case "basic":
                return (((((monster.level * critikal)/5 + 2) * monster.attackPower * 1/monster.defensePower) + 2)/50);
            case "special":
                return (((((monster.level * critikal)/5 + 2) * monster.spcAttackPower * 1/monster.defensePower) + 2)/50);
            case "elemental":
                return (((((monster.level * critikal)/5 + 2) * elementalAttack.power * 1/monster.defensePower) + 2)/50);        
            default:
                return -1;
        }
    }
    public void getAttacked(String attackType, Monster monster, ElementalAttack elementalAttack) {
        switch (attackType.toLowerCase()) {
            case "basic":
                healthPoint -= dmgFormula(monster, attackPower, attackType, null);
                break;
            
            case "special":
                healthPoint -= dmgFormula(monster, spcAttackPower, attackType, null);
                break;
            
            case "elemental":
                healthPoint -= dmgFormula(monster, elemAttackPower, attackType, elementalAttack);
                break;

            default:
                break;
        }
        System.out.println(name + " took " + dmgFormula(monster, attackPower, attackType, null) + " damage!");
    }

    public void setAttributesMax(int monsterPhase){
        if(monsterPhase == maxMonsterPhase) {
            return;
        }
        Random rand = new Random();

        switch (monsterPhase) {
            case 1:
                this.maxHealthPoint = 20 + rand.nextInt(31); // Random number between 20 and 50
                this.maxAttackPower = 10 + rand.nextInt(21); // Random number between 10 and 30
                this.maxSpcAttackPower = 10 + rand.nextInt(21);
                this.maxElemAttackPower = 10 + rand.nextInt(21);
                this.maxDefensePower = 10 + rand.nextInt(21);
                break;
            case 2:
                this.maxHealthPoint = 40 + rand.nextInt(41); // Random number between 40 and 80
                this.maxAttackPower = 20 + rand.nextInt(21); // Random number between 20 and 40
                this.maxSpcAttackPower = 20 + rand.nextInt(21);
                this.maxElemAttackPower = 20 + rand.nextInt(21);
                this.maxDefensePower = 20 + rand.nextInt(21);
                break;
            case 3:
                this.maxHealthPoint = 60 + rand.nextInt(41); // Random number between 60 and 100
                this.maxAttackPower = 30 + rand.nextInt(31); // Random number between 30 and 60
                this.maxSpcAttackPower = 30 + rand.nextInt(31);
                this.maxElemAttackPower = 30 + rand.nextInt(31);
                this.maxDefensePower = 30 + rand.nextInt(31);
                break;
            case 4:
                this.maxHealthPoint = 80 + rand.nextInt(61); // Random number between 80 and 140
                this.maxAttackPower = 40 + rand.nextInt(41); // Random number between 40 and 80
                this.maxSpcAttackPower = 40 + rand.nextInt(41);
                this.maxElemAttackPower = 40 + rand.nextInt(41);
                this.maxDefensePower = 40 + rand.nextInt(41);
                break;
            case 5:
                this.maxHealthPoint = 100 + rand.nextInt(101); // Random number between 100 and 200
                this.maxAttackPower = 50 + rand.nextInt(51); // Random number between 50 and 100
                this.maxSpcAttackPower = 50 + rand.nextInt(51);
                this.maxElemAttackPower = 50 + rand.nextInt(51);
                this.maxDefensePower = 50 + rand.nextInt(51);
                break;
            default:
                throw new IllegalArgumentException("Invalid monster phase: " + monsterPhase);
        }
    }

    public void changeElementType(String elementType) {
        switch (elementType) {
            case "ICE":
                this.elementType = new ElementType[]{ElementType.ICE};
                break;
            
            case "FIRE":
                this.elementType = new ElementType[]{ElementType.FIRE};
                break;
            
            case "WATER":
                this.elementType = new ElementType[]{ElementType.WATER};
                break;
            
            case "AIR":
                this.elementType = new ElementType[]{ElementType.AIR};
                break;
            
            case "EARTH":
                this.elementType = new ElementType[]{ElementType.EARTH};
                break;
            default:
                break;
        }
    }

    // Method to handle evolution
    public void evolution(String element) {
        if (monsterPhase >= maxMonsterPhase) return;
        changeElementType(element);
        setAttributesMax(monsterPhase);
        switch (monsterPhase) {
            case 1:
                evolveAttributes(1.1, 1.5);
                break;
            case 2:
                evolveAttributes(1.3, 1.7);
                break;
            case 3:
                evolveAttributes(1.3, 1.9);
                break;
            case 4:
                evolveAttributes(1.5, 2.0);
                break;
            default:
                throw new IllegalArgumentException("Invalid monster phase: " + monsterPhase);
        }
        setAttributesMax(monsterPhase);
        monsterPhase++;
        experiencePoint = 0;
    }

    private void evolveAttributes(double minMultiplier, double maxMultiplier) {
        Random rand = new Random();
        double multiplier = minMultiplier + (maxMultiplier - minMultiplier) * rand.nextDouble();
        this.healthPoint = (int) (this.healthPoint * multiplier);
        this.attackPower = (int) (this.attackPower * multiplier);
        this.spcAttackPower = (int) (this.spcAttackPower * multiplier);
        this.elemAttackPower = (int) (this.elemAttackPower * multiplier);
        this.defensePower = (int) (this.defensePower * multiplier);

        // Ensure attributes do not exceed their respective maximums
        this.healthPoint = Math.min(this.healthPoint, maxHealthPoint);
        this.attackPower = Math.min(this.attackPower, maxAttackPower);
        this.spcAttackPower = Math.min(this.spcAttackPower, maxSpcAttackPower);
        this.elemAttackPower = Math.min(this.elemAttackPower, maxElemAttackPower);
        this.defensePower = Math.min(this.defensePower, maxDefensePower);
    }

    // Method to level up
    public void levelUp(int healthPoint, int attackPower, int spcAttackPower, int elemAttackPower, int defensePower) {
        if (level % 20 == 0) {
            System.out.println("Level sudah maksimal. Monster anda sudah bisa berevolusi!");
            return;
        }
        this.healthPoint += healthPoint;
        this.attackPower += attackPower;
        this.spcAttackPower += spcAttackPower;
        this.elemAttackPower += elemAttackPower;
        this.defensePower += defensePower;
        this.level++;
        this.experiencePoint = 0;

        // Ensure attributes do not exceed their respective maximums
        this.healthPoint = Math.min(this.healthPoint, maxHealthPoint);
        this.attackPower = Math.min(this.attackPower, maxAttackPower);
        this.spcAttackPower = Math.min(this.spcAttackPower, maxSpcAttackPower);
        this.elemAttackPower = Math.min(this.elemAttackPower, maxElemAttackPower);
        this.defensePower = Math.min(this.defensePower, maxDefensePower);
    }

    // Method to heal HP
    public void healingHp(int amountHeal) {
        if (healthPoint == maxHealthPoint) {
            System.out.println("You already have full health!");
            return;
        }
        this.healthPoint = Math.min(this.healthPoint + amountHeal, maxHealthPoint);
    }

    // Method to display details of the Pokemon
    public void displayDetailMonster() {
        System.out.println("Name: " + name);
        System.out.println("Level: " + level);
        System.out.println("Experience Point: " + experiencePoint);
        System.out.println("Health Point: " + healthPoint);
        System.out.println("Attack Power: " + attackPower);
        System.out.println("Special Attack Power: " + spcAttackPower);
        System.out.println("Defense Power: " + defensePower);
        elementalAttacks.forEach(attribute -> {
            System.out.println();
            System.out.println(attribute.nama);
            System.out.println("Power: "+attribute.power);
            System.out.println("Element: "+attribute.element);
        });
    }

    // Nested enum for Pokemon element types
    public enum ElementType {
        FIRE,
        WATER,
        AIR,
        EARTH,
        ICE
    }

    // Getter and setter methods

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperiencePoint() {
        return this.experiencePoint;
    }

    public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public ElementType[] getElementType() {
        return this.elementType;
    }

    public void setElementType(ElementType[] elementType) {
        this.elementType = elementType;
    }

    public int getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getSpcAttackPower() {
        return this.spcAttackPower;
    }

    public void setSpcAttackPower(int spcAttackPower) {
        this.spcAttackPower = spcAttackPower;
    }

    public int getElemAttackPower() {
        return this.elemAttackPower;
    }

    public void setElemAttackPower(int elemAttackPower) {
        this.elemAttackPower = elemAttackPower;
    }

    public int getDefensePower() {
        return this.defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    public int getMonsterPhase() {
        return this.monsterPhase;
    }

    public void setMonsterPhase(int monsterPhase) {
        this.monsterPhase = monsterPhase;
    }
}

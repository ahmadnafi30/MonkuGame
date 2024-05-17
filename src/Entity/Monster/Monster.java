package Entity.Monster;
import java.util.*;
import Entity.Battle;

public abstract class Monster implements Battle {
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
    private int currentMaxHealthPoint;
    private int currentMaxAttackPower;
    private int currentMaxSpcAttackPower;
    private int currentMaxElemAttackPower;
    private int currentMaxDefensePower;

    // Constructor for loading game
    public Monster(String name, int level, int experiencePoint, ElementType[] elementType, List<ElementalAttack> elementalAttacks, int healthPoint, int attackPower, int spcAttackPower, int elemAttackPower, int defensePower, int maxHealthPoint, int maxAttackPower, int maxSpcAttackPower, int maxElemAttackPower, int maxDefensePower, int monsterPhase, int maxMonsterPhase, int currentMaxHealthPoint, int currentMaxAttackPower, int currentMaxSpcAttackPower, int currentMaxElemAttackPower, int currentMaxDefensePower) {
        this.name = name;
        this.level = level;
        this.experiencePoint = experiencePoint;
        this.elementType = elementType;
        //this.elementalAttacks = new ArrayList<>().addAll();;
        this.healthPoint = healthPoint;
        this.attackPower = attackPower;
        this.spcAttackPower = spcAttackPower;
        this.elemAttackPower = elemAttackPower;
        this.defensePower = defensePower;
        this.maxHealthPoint = maxHealthPoint;
        this.maxAttackPower = maxAttackPower;
        this.maxSpcAttackPower = maxSpcAttackPower;
        this.maxElemAttackPower = maxElemAttackPower;
        this.maxDefensePower = maxDefensePower;
        this.monsterPhase = monsterPhase;
        this.maxMonsterPhase = maxMonsterPhase;
        this.currentMaxHealthPoint = currentMaxHealthPoint;
        this.currentMaxAttackPower = currentMaxAttackPower;
        this.currentMaxSpcAttackPower = currentMaxSpcAttackPower;
        this.currentMaxElemAttackPower = currentMaxElemAttackPower;
        this.currentMaxDefensePower = currentMaxDefensePower;
    }

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
        this.currentMaxHealthPoint = maxHealthPoint / 2;
        this.attackPower = maxAttackPower / 2;
        this.currentMaxAttackPower = maxAttackPower / 2;
        this.spcAttackPower = maxSpcAttackPower / 2;
        this.currentMaxSpcAttackPower = maxSpcAttackPower / 2;
        this.elemAttackPower = maxElemAttackPower / 2;
        this.currentMaxElemAttackPower = maxElemAttackPower / 2;
        this.defensePower = maxDefensePower / 2;
        this.currentMaxDefensePower = maxDefensePower / 2;
    }

    // cek jika attacker memimiliki elemen yang efektif dengan musuh
    public boolean checkWeakness(ElementType elementType) {
        switch (elementType) {
            case WATER:
                if(this.elementType[0] == ElementType.FIRE) {
                    return true;
                } else return false;
            case FIRE:
                if(this.elementType[0] == ElementType.ICE) {
                    return true;
                } else return false;
            case ICE:
                if(this.elementType[0] == ElementType.AIR) {
                    return true;
                } else return false;
            case AIR:
                if(this.elementType[0] == ElementType.EARTH) {
                    return true;
                } else return false;
            case EARTH:
                if(this.elementType[0] == ElementType.WATER) {
                    return true;
                } else return false;
            default:
                break;
        }
        return false;
    }

    //will be critical if 2 and 1 if not
    //monster yang dimaksud adalah monster yang memberikan dmg (attacker)
    private int dmgFormula(Monster attacker, int critikal, String attackType, ElementalAttack elementalAttack) {
        Random rand = new Random();
        double dmg = 0;
        int random = rand.nextInt(10);
        int multiplier = 1;
        if(elementalAttack != null)
        if(checkWeakness(elementalAttack.element)) {
            System.out.println(elementalAttack.nama +" is Effective!");
            multiplier = 2;
        }
        switch (attackType) {
            case "basic": 
                dmg = (((((2*attacker.level*critikal)+2)/5)*attacker.attackPower*(1/attacker.defensePower))/50)+2;
                System.out.println(dmg);
                return (int)dmg * random;
            case "special":
                dmg = (((((2*attacker.level*critikal)+2)/5)*attacker.spcAttackPower*(1/attacker.defensePower))/50)+2;
                return (int)dmg * random;
            case "elemental":
                dmg = (((((2*attacker.level*critikal)+2)/5)*(elementalAttack.power*(attacker.elemAttackPower/5))*(1/attacker.defensePower))/50)+2;
                return (int)dmg * random * multiplier;        
            default:
                return -1;
        }
    }

    //Si monster di kelas ini yang terkena damage
    //return true jika monster meninggal
    public boolean getAttacked(String attackType, Monster attacker, ElementalAttack elementalAttack) {
        /*
         * "basic" for basic attack
         * "special" for special attack
         * "elemental" for elemental attack
         */

        int dmg = 0;
        int critical = 1;
        Random rand = new Random();
        if(rand.nextInt(100) < 35) {    
            critical = 2;
        }
        switch (attackType.toLowerCase()) {
            case "basic":
                dmg = dmgFormula(attacker, critical, attackType, null);
                healthPoint -= dmg; 
                break;
            
            case "special":
                healthPoint -= dmg = dmgFormula(attacker, critical, attackType, null);
                break;
            
            case "elemental":
                healthPoint -= dmg = dmgFormula(attacker, critical, attackType, elementalAttack);
                break;

            default:
                break;
        }
        if(critical == 2) {
            System.out.println("Critical hit!");
        }
        System.out.println(name + " took " + dmg + " damage!");
        if(healthPoint <= 0) {
            System.out.println(name + " has been defeated!");
            return true;
        }
        return false;
    }

    private void setAttributesMax(int monsterPhase){
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

    private void changeElementType(String elementType) {
        switch (elementType.toUpperCase()) {
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
    public boolean evolution(String element) {
        if (monsterPhase >= maxMonsterPhase){ 
            System.out.println("Monster sudah mencapai fase maksimal!");
            return false;
        }
        if(level < 20){
            System.out.println("Level monster tidak mencukupi untuk melakukan evolusi!");
            return false;
        }
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
        return true;
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
        this.healthPoint = Math.min(this.healthPoint + amountHeal, currentMaxHealthPoint);
    }

    // Method to display details of the Pokemon
    public void displayDetailMonster() {
        System.out.println("Name: " + name + " (" + elementType[0] +")");
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

    public int getMaxHealthPoint(){
        return maxHealthPoint;
    }

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

    public ElementType getElementType() {
        return this.elementType[0];
    }

    public void setElementType(ElementType[] elementType) {
        this.elementType = elementType;
    }

    public int getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        if(healthPoint > currentMaxHealthPoint){
            this.healthPoint = currentMaxHealthPoint;
            return;
        } else if(healthPoint < 0) {
            healthPoint = 0;
            return;
        }
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

    public int getMaxAttackPower() {
        return maxAttackPower;
    }

    public int getMaxSpcAttackPower() {
        return maxSpcAttackPower;
    }

    public int getMaxElemAttackPower() {
        return maxElemAttackPower;
    }

    public int getMaxDefensePower() {
        return maxDefensePower;
    }

    public int getMaxMonsterPhase() {
        return maxMonsterPhase;
    }

    public int getCurrentMaxHealthPoint() {
        return currentMaxHealthPoint;
    }

    public int getCurrentMaxAttackPower() {
        return currentMaxAttackPower;
    }

    public int getCurrentMaxSpcAttackPower() {
        return currentMaxSpcAttackPower;
    }

    public int getCurrentMaxElemAttackPower() {
        return currentMaxElemAttackPower;
    }

    public int getCurrentMaxDefensePower() {
        return currentMaxDefensePower;
    }

    public void setCurrentMaxHealthPoint(int currentMaxHealthPoint) {
        this.currentMaxHealthPoint = currentMaxHealthPoint;
    }

    public void setCurrentMaxAttackPower(int currentMaxAttackPower) {
        this.currentMaxAttackPower = currentMaxAttackPower;
    }

    public void setCurrentMaxSpcAttackPower(int currentMaxSpcAttackPower) {
        this.currentMaxSpcAttackPower = currentMaxSpcAttackPower;
    }

    public void setCurrentMaxElemAttackPower(int currentMaxElemAttackPower) {
        this.currentMaxElemAttackPower = currentMaxElemAttackPower;
    }

    public void setCurrentMaxDefensePower(int currentMaxDefensePower) {
        this.currentMaxDefensePower = currentMaxDefensePower;
    }

    public String monsterProperty() {
        StringBuilder properties = new StringBuilder();
        Object[] attributes = {
            getName(),
            getLevel(),
            getExperiencePoint(),
            getElementType().toString(),
            getHealthPoint(),
            getAttackPower(),
            getSpcAttackPower(),
            getElemAttackPower(),
            getDefensePower(),
            getMaxHealthPoint(),
            getMaxAttackPower(),
            getMaxSpcAttackPower(),
            getMaxElemAttackPower(),
            getMaxDefensePower(),
            getMonsterPhase(),
            getMaxMonsterPhase(),
            getCurrentMaxHealthPoint(),
            getCurrentMaxAttackPower(),
            getCurrentMaxSpcAttackPower(),
            getCurrentMaxElemAttackPower(),
            getCurrentMaxDefensePower()
        };

        for (Object attribute : attributes) {
            properties.append(attribute).append("\n");
        }

        for (ElementalAttack attack : elementalAttacks) {
            properties.append(attack.getNama()).append("\n")
                      .append(attack.getPower()).append("\n")
                      .append(attack.getElement()).append("\n");
        }

        return properties.toString();
    }
}

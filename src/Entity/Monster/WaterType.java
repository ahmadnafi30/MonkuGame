package Entity.Monster;

public class WaterType extends Monster {

    public WaterType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "WATER", maxMonsterPhase);
    }

    // New ability: Bubble
    public void bubble() {
        elementalAttacks.add(new ElementalAttack("Bubble", 40, ElementType.WATER));
    }

    // New ability: Water Gun
    public void waterGun() {
        elementalAttacks.add(new ElementalAttack("Water Gun", 40, ElementType.WATER));
    }

    // New ability: Aqua Jet
    public void aquaJet() {
        elementalAttacks.add(new ElementalAttack("Aqua Jet", 60, ElementType.WATER));
    }

    // New ability: Surf
    public void surf() {
        elementalAttacks.add(new ElementalAttack("Surf", 90, ElementType.WATER));
    }

    // New ability: Hydro Pump
    public void hydroPump() {
        elementalAttacks.add(new ElementalAttack("Hydro Pump", 110, ElementType.WATER));
    }

    // New ability: Waterfall
    public void waterfall() {
        elementalAttacks.add(new ElementalAttack("Waterfall", 80, ElementType.WATER));
    }

    // New ability: Aqua Tail
    public void aquaTail() {
        elementalAttacks.add(new ElementalAttack("Aqua Tail", 90, ElementType.WATER));
    }

    // New ability: Scald
    public void scald() {
        elementalAttacks.add(new ElementalAttack("Scald", 80, ElementType.WATER));
    }
}

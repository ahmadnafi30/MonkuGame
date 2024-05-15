package Entity.Monster;

public class AirType extends Monster {

    public AirType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "AIR", maxMonsterPhase);
    }

    // New ability: Gust
    public void gust() {
        elementalAttacks.add(new ElementalAttack("Gust", 40, ElementType.AIR));
    }

    // New ability: Air Slash
    public void airSlash() {
        elementalAttacks.add(new ElementalAttack("Air Slash", 75, ElementType.AIR));
    }

    // New ability: Hurricane
    public void hurricane() {
        elementalAttacks.add(new ElementalAttack("Hurricane", 110, ElementType.AIR));
    }

    // New ability: Aerial Ace
    public void aerialAce() {
        elementalAttacks.add(new ElementalAttack("Aerial Ace", 60, ElementType.AIR));
    }

    // New ability: Sky Attack
    public void skyAttack() {
        elementalAttacks.add(new ElementalAttack("Sky Attack", 140, ElementType.AIR));
    }

    // New ability: Air Cutter
    public void airCutter() {
        elementalAttacks.add(new ElementalAttack("Air Cutter", 60, ElementType.AIR));
    }

    // New ability: Fly
    public void fly() {
        elementalAttacks.add(new ElementalAttack("Fly", 90, ElementType.AIR));
    }
}

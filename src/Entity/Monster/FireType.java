package Entity.Monster;

public class FireType extends Monster {

    public FireType(String name, int monsterPhase, int maxMonsterPhase) {
        super(name, monsterPhase, "FIRE", maxMonsterPhase);
    }

    // New ability: Ember
    public void ember() {
        elementalAttacks.add(new ElementalAttack("Ember", 40, ElementType.FIRE));
    }

    // New ability: Flame Thrower
    public void flameThrower() {
        elementalAttacks.add(new ElementalAttack("Flame Thrower", 90, ElementType.FIRE));
    }

    // New ability: Fire Blast
    public void fireBlast() {
        elementalAttacks.add(new ElementalAttack("Fire Blast", 110, ElementType.FIRE));
    }

    // New ability: Fire Spin
    public void fireSpin() {
        elementalAttacks.add(new ElementalAttack("Fire Spin", 35, ElementType.FIRE));
    }

    // New ability: Heat Wave
    public void heatWave() {
        elementalAttacks.add(new ElementalAttack("Heat Wave", 95, ElementType.FIRE));
    }

    // New ability: Inferno
    public void inferno() {
        elementalAttacks.add(new ElementalAttack("Inferno", 100, ElementType.FIRE));
    }

    // New ability: Flame Charge
    public void flameCharge() {
        elementalAttacks.add(new ElementalAttack("Flame Charge", 50, ElementType.FIRE));
    }

    // New ability: Overheat
    public void overheat() {
        elementalAttacks.add(new ElementalAttack("Overheat", 130, ElementType.FIRE));
    }
}

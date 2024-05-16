package Entity.Monster;

public class Test {
    public static void main(String[] args) {
        // Create an instance of the Monster class
        Monster monster = new IceType("Nafi", 1, 4);
        ((IceType) monster).iceBeam();
        ((IceType) monster).hydroPump();
        monster.displayDetailMonster();

        System.out.println();
        Monster enemy = new FireType("Raka", 1, 4);
        ((FireType) enemy).ember();
        ((FireType) enemy).flameThrower();
        enemy.displayDetailMonster();
        // Access the attributes

        for(int i = 0; i < 5; i++){
            enemy.elementalAttack(monster, enemy.elementalAttacks.get(0));
            System.out.println(enemy.getHealthPoint());
        }
    }           
}

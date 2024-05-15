package Entity.Monster;

public class Test {
    public static void main(String[] args) {
        // Create an instance of the Monster class
        Monster monster = new IceType("Nafi", 1, 4);
        ((IceType) monster).iceBeam();
        ((IceType) monster).hydroPump();
        monster.displayDetailMonster();

        System.out.println();
        Monster monster2 = new FireType("Raka", 1, 4);
        ((FireType) monster2).ember();
        ((FireType) monster2).flameThrower();
        monster2.displayDetailMonster();
        // Access the attributes

        for(int i = 0; i < 5; i++){
            monster2.basicAttack(monster2);
            monster.displayDetailMonster();
            monster2.displayDetailMonster();
        }
    }           
}

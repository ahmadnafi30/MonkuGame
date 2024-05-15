package Entity.Monster;

public class Test {
    public static void main(String[] args) {
        // Create an instance of the Monster class
        Monster monster = new IceType("Nafi", 1, 4);
        ((IceType) monster).iceBeam();
        ((IceType) monster).hydroPump();
        monster.displayDetailMonster();
        // Access the attributes
    }           
}

package Entity.NPC;

import Entity.Monster.Monster;
import Entity.Player.Player;

public class ProfessorPokemon extends NPC{

    public ProfessorPokemon(String name, String job) {
        super(name, job);
        //TODO Auto-generated constructor stub
    }

    public void dialogWithPlayer(){
        System.out.println("Hello, aku professor " +  name + " apa yang bisa kami bantu?");
    }

    // public void healPokemon(Player player, String monster){
    //     for (Monster monster1 : player.getMonsters()) {
    //         if (monster1.getName().equals(monster)) {
    //             monster1.healingHp();
    //             System.out.println("Professor " + name + ": Sekarang monster " + monster1.getName() +" sudah pulih");
    //             return;
    //         }
    //     }

    //     System.out.println("Professor " + name +": Tidak ada monster di inventory mu");
    // }


    public void doJob() {
        
    }
    
}

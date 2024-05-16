package Entity.Locations;

import java.util.Scanner;

import Entity.Monster.*;
import Entity.NPC.ProfessorPokemon;
import Entity.Player.Player;

//buat method cari pokemon
public class HomeBase extends Locations {
    private ProfessorPokemon professor;

    public HomeBase(String locationName) {
        super(locationName);
        professor = new ProfessorPokemon("Professor Oak", "Pokemon Professor");
    }

    @Override
    public void printDetailLocation() {
        System.out.println("Home Base: " + locationName);
        System.out.println("Professor:");
        // professor.displayDetailProfessor();
    }

    public void interactWithPlayer(Player player) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        
        System.out.println("Professor: Selamat datang, " + player.getName() + "! Ada yang bisa saya bantu?");
        System.out.println("1. Memulihkan Monster");
        System.out.println("2. Meningkatkan Level Monster");
        System.out.println("3. Menyimpan Permainan");
        System.out.println("4. Keluar");
        System.out.print("Pilihan Anda: ");
        
        choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
            System.out.println("Professor: Silahkan pilih monster yang ingin Anda pulihkan:");
            player.checkMonsters();
            System.out.print("Pilih monster: ");
            String monster = scanner.nextLine();
            healMonsters(player, monster);
            break;
            case "2":
                saveGame(player);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }
    
    //mungkin parameternya lebih baik menggunakan kelas Monster deh biar akses monsternya lebih gampang
    private void healMonsters(Player player, String monster) {
        System.out.println("Professor: Sedang menyembuhkan monster...");
        // professor.healPokemon(player, monster);
        // System.out.println("Professor: Monster Anda sekarang dalam kondisi sehat.");
    }

    // iterasi monster yang ingin evolusi, lalu lakukan evolusi
    // private void EvolveMonsters(Player player) {
        //     System.out.println("Professor: Sedang meningkatkan level monster...");
    //     // Implementasi peningkatan level monster
    //     System.out.println("Professor: Level monster Anda telah ditingkatkan.");
    // }

    private void saveGame(Player player) {
        System.out.println("Professor: Menyimpan permainan...");
        // Implementasi penyimpanan permainan
        System.out.println("Professor: Permainan Anda berhasil disimpan.");
    }
}

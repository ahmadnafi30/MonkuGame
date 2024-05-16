package Entity.Locations;

import java.util.InputMismatchException;
import java.util.Scanner;

import Entity.Item.Item;
import Entity.NPC.ItemSeller;
import Entity.Player.Player;

public class Shop extends Locations {
    private ItemSeller seller;

    public Shop(String locationName, ItemSeller seller) {
        super(locationName);
        this.seller = seller;
    }

    @Override
    public void printDetailLocation() {
        System.out.println("Shop: " + locationName);
        System.out.println("Welcome to the shop!");
        seller.checkInventory();
    }

    public void buyItem(Player player) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Seller: What would you like to buy?");
            seller.checkInventory();

            System.out.print("Enter item number to buy: ");
            int pilihitem = in.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = in.nextInt();

            seller.sellItem(pilihitem, player, quantity);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            in.next(); // Clear the invalid input
        }
    }

    public void sellItem(Player player) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Seller: What items do you have to sell?");
            player.checkInventory();

            System.out.print("Enter item number to sell: ");
            int pilihitem = in.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = in.nextInt();

            seller.buyItem(pilihitem, player, quantity);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            in.next(); // Clear the invalid input
        }
    }

    public void doEverything(Player player) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Seller: Everything is for sale!");
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Item");
            System.out.print("Enter your choice: ");
            int pilihan = in.nextInt();

            switch (pilihan) {
                case 1:
                    buyItem(player);
                    break;
                case 2:
                    sellItem(player);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            in.next(); // Clear the invalid input
        }
        System.out.println("Seller: Come back soon!");
    }

    public void detailLocation() {
        System.out.println("Welcome to " + locationName + "!!!");
        System.out.println("""
            As you step into the bustling " + locationName + ", the air is filled with the enticing aromas of exotic goods and the lively chatter of traders and adventurers alike.
            The shop is a vibrant hub of activity, with shelves overflowing with glittering treasures, powerful potions, and rare artifacts.
            Colorful banners hang from the ceiling, each proclaiming the latest deals and special offers.
            The shopkeeper, with a wide smile and a twinkle in their eye, stands ready to assist you in your quest for the perfect item.
            Take your time to explore every corner, for in this shop, there's always something new and exciting waiting to be discovered.
            Whether you're here to buy or sell, " + locationName + " promises a shopping experience like no other, where every transaction brings you one step closer to your next great adventure!
            """);
    }
}

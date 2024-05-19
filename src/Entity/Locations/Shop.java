package Entity.Locations;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
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

    public void sellItem(int itemIndex, Player player, int quantity) {
    Map<Item, Integer> playerInventory = player.getInventory();
    List<Item> items = new ArrayList<>(playerInventory.keySet());
    if (itemIndex >= 1 && itemIndex <= items.size()) {
        Item itemToSell = items.get(itemIndex - 1);
        player.sellItem(itemToSell, quantity, this.seller);
    } else {
        System.out.println("Invalid item index.");
    }
}


    public void buyItem(int itemIndex, Player player, int quantity) {
        ItemSeller seller = this.seller; 
        Map<Item, Integer> sellerInventory = seller.getInventory();
        List<Item> items = new ArrayList<>(sellerInventory.keySet());
        if (itemIndex >= 1 && itemIndex <= items.size()) {
        Item itemToBuy = items.get(itemIndex - 1);
        player.buyItem(itemToBuy, quantity, seller);
        }else {
        System.out.println("Invalid item index.");
        }
    }


    public void doEverything(Player player, int whatdoyouwant, int itemIndex, int quantity) {
        try {
            switch (whatdoyouwant) {
                case 1:
                    buyItem(itemIndex, player, quantity);
                    break;
                case 2:
                    sellItem(itemIndex, player, quantity);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
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

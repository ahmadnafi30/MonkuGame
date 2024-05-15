package Entity.Locations;

import Entity.NPC.ItemSeller;

public class Shop extends Locations {
    private ItemSeller seller;

    public Shop(String locationName, ItemSeller seller) {
        super(locationName);
        this.seller = seller;
    }

    @Override
    public void printDetailLocation() {
        System.out.println("Shop: " + locationName);
        System.out.println("Seller:");
        // seller.displayDetailSeller();
    }
}

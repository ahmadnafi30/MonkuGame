package Entity.Item;

public abstract class Item {
    public String name;
    String functionality;
    public int price;
    String rarity;

    public Item(String name, String functionality, int price, String rarity) {
        this.name = name;
        this.functionality = functionality;
        this.price = price;
        this.rarity = rarity;
    }

    public abstract void printDetailItemm();
    
}

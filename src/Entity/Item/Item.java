package Entity.Item;

import java.io.Serializable;

import Entity.Item.Item.Rarity;
import Entity.Monster.Monster;
import Entity.Player.Player;

public abstract class Item implements Serializable{
    public String name;
    public String functionality;
    public int price;
    public Rarity rarity;
    public int duration;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionality() {
        return this.functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRarity() {
        return this.rarity.toString();
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    //durasi per putaran

    public Item(String name, String functionality, int price, String rarity, int duration) {
        this.name = name;
        this.functionality = functionality;
        this.price = price;
        this.duration = duration;
        switch (rarity.toLowerCase()) {
            case "common":
                this.rarity = Rarity.COMMON;
                break;
            case "rare":
                this.rarity = Rarity.RARE;
                break;
            case "epic":
                this.rarity = Rarity.EPIC;
                break;
            default:
                System.out.println("There is no such rarity as " + rarity);
                break;
        }
    }

    public abstract String printDetailItemm();
    public abstract void useItem(Monster monster, int turn, Player player);
    public abstract void itemRanOut(Monster monster, Player player);

    enum Rarity{
        COMMON,
        RARE,
        EPIC
    }
}

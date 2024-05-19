package Entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

import Entity.Item.Item;
import Entity.Locations.Locations;
import Entity.Monster.Monster;

public class DataStorage implements Serializable {
    public String name;
    public int level;
    public int exp;
    public Map<Item, Integer> inventory;
    public int coin;
    public ArrayList<Monster> monsters;
    public Duration timePlayed;
    public Instant startTime;
    public Locations locationPlayer;
    public String image;
}

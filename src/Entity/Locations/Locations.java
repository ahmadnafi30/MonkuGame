package Entity.Locations;

public abstract class Locations {
    String locationName;

    public Locations(String locationName) {
        this.locationName = locationName;
    }

    public abstract void printDetailLocation();
}

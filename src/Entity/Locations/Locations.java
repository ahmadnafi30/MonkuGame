package Entity.Locations;

import java.io.Serializable;

public abstract class Locations implements Serializable {
    String locationName;

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Locations(String locationName) {
        this.locationName = locationName;
    }

    public abstract void printDetailLocation();
}

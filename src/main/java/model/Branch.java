package model;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String location;
    private String city;
    private List<Vehicle> vehicles;
    private List<Equipment> equipment;

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    private void addVehicle(Vehicle v) {
        this.vehicles.add(v);
    }

    public Branch(String location, String city) {
        this.location = location;
        this.city = city;
        this.vehicles = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }
}

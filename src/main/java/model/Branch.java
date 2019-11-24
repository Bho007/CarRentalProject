package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(location, branch.location) &&
                Objects.equals(city, branch.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, city);
    }
}

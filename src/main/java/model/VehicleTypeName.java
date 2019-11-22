package model;

public enum VehicleTypeName {
    ECONOMY("Economy"),
    COMPACT("Compact"),
    MIDSIZE("Midsize"),
    STANDARD("Standard"),
    FULLSIZE("Fullsize"),
    SUV("SUV"),
    TRUCK("Truck");
    
    private String name;
    
    VehicleTypeName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

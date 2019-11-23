package model;

import java.rmi.dgc.VMID;

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

    public static VehicleTypeName toVechicleTypeName(String input) {
        switch (input.toLowerCase()) {
            case "economy":
                return ECONOMY;
            case "compact":
                return COMPACT;
            case "midsize":
                return MIDSIZE;
            case "standard":
                return STANDARD;
            case "fullsize":
                return FULLSIZE;
            case "suv":
                return SUV;
            case "truck":
                return TRUCK;
            default:
                return null;
        }
    }
}


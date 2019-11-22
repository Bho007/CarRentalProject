package main;

import model.Vehicle;

import java.util.List;

public interface Database {
    List<Vehicle> getVehicles(String type, String location, String from, String to);
}

package main;

import model.Vehicle;

import java.util.List;

public interface Database {
    DatabaseResponse<List<Vehicle>> getVehicles(String type, String location, String from, String to);
    DatabaseResponse<?> sendQuery(String query);
}

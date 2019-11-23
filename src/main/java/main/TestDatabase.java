package main;

import model.Vehicle;
import model.VehicleStatus;
import model.VehicleType;
import model.VehicleTypeName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestDatabase implements Database {
    @Override
    public DatabaseResponse<List<Vehicle>> getVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(123, "L123", "ford", "g", "2019", "red", 100, VehicleStatus.FOR_RENT, null, VehicleTypeName.FULLSIZE));
        return new TestDatabaseResponse<List<Vehicle>>("test query", true, "success test response", vehicles);
    }
    
    @Override
    public DatabaseResponse<String> generateDailyRentalReport() {
        return null;
    }
    
    @Override
    public DatabaseResponse<String> generateDailyBranchRentalReport(String branch) {
        return null;
    }
    
    @Override
    public DatabaseResponse<String> generateDailyReturnReport() {
        return null;
    }
    
    @Override
    public DatabaseResponse<String> generateDailyBranchReturnReport(String branch) {
        return null;
    }
    
    @Override
    public DatabaseResponse<?> sendQuery(String query) {
        return null;
    }
}

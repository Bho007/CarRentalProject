package main;

import model.Vehicle;
import model.VehicleType;
import model.VehicleTypeName;

import java.time.LocalDateTime;
import java.util.List;

public interface Database {
    DatabaseResponse<List<Vehicle>> getVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to);
    DatabaseResponse<String> generateDailyRentalReport();
    DatabaseResponse<String> generateDailyBranchRentalReport(String branch);
    DatabaseResponse<String> generateDailyReturnReport();
    DatabaseResponse<String> generateDailyBranchReturnReport(String branch);
    DatabaseResponse<?> sendQuery(String query);
}

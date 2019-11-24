package main;

import model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface Database {
    // parameters can be null
    DatabaseResponse<List<Vehicle>> getVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to);

    DatabaseResponse<Vehicle> getVehicle(String rentalID);

    // return human readable string
    DatabaseResponse<String> generateDailyRentalReport();
    DatabaseResponse<String> generateDailyBranchRentalReport(String location, String city);
    DatabaseResponse<String> generateDailyReturnReport();
    DatabaseResponse<String> generateDailyBranchReturnReport(String location, String city);

    DatabaseResponse<Boolean> locationExists(String location);
    DatabaseResponse<Boolean> branchExists(String location, String city);

    DatabaseResponse<Boolean> customerExists(String driversLicense);
    DatabaseResponse<Customer> getCustomer(String driversLicense);

    // return anything (default to empty string?) - if customer cannot be added, set success == false
    // return error message with what went wrong in the case of failure
    DatabaseResponse<?> addCustomer(String phone, String name, String address, String driversLicense);

    // return confirmation number, if type == null, reserve any type of vehicle
    // return error message with what went wrong in the case of failure
    DatabaseResponse<Reservation> reserveVehicle(String driversLicense, String phoneNumber, VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to);

    DatabaseResponse<Reservation> getReservationByConfirmationNumber(String confirmationNumber);
    DatabaseResponse<Reservation> getReservationByPhoneNumber(String phoneNumber);

    // return confirmation number, if type == null, rent any type of vehicle
    // return error message with what went wrong in the case of failure
    DatabaseResponse<Rental> rentVehicle(String driversLicense, String phone, String confirmationNumber, VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to, String creditCardNumber, String expiryMonth, String expiryYear, String creditCardType);

    DatabaseResponse<Rental> getRental(String id);

    // only deviation: front-end computes the cost instead of the backend
    DatabaseResponse<String> returnVehicle(String rentalID, String location, LocalDateTime time, String odometer, boolean gasTankIsFull, int cost);

    // type is not null
    DatabaseResponse<Integer> getHourlyRate(VehicleTypeName type);
    DatabaseResponse<Integer> getDailyRate(VehicleTypeName type);
    DatabaseResponse<Integer> getWeeklyRate(VehicleTypeName type);

    DatabaseResponse<?> sendQuery(String query);
}
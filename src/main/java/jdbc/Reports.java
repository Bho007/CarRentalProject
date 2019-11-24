package jdbc;

import main.DatabaseResponse;
import main.TestDatabaseResponse;
import model.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static jdbc.Query.*;

public class Reports {
    private static final Date TODAY = new Date(Calendar.getInstance().getTimeInMillis());
    private static Connection conn;
    private static PreparedStatement stmt;

    public static DatabaseResponse<String> getDailyReturns() {
        return getDailyReturns(null);
    }

    public static DatabaseResponse<String> getDailyReturns(Branch b) {
        boolean success = false;
        RentalReport report = new RentalReport();

        String vehicleQuery = "SELECT * FROM Vehicle v INNER JOIN Rent r ON v.vlicense = r.vlicense WHERE fromdate = ? " +
                ((b == null) ? "" : "AND v.location = ? AND v.city = ? ") + "ORDER BY vtname ASC";
        String vtGroupQuery = "SELECT COUNT(*) AS total, vtname FROM (" + vehicleQuery + ") vehicles GROUP BY vtname";
        String branchGroupQuery = "SELECT COUNT(*) AS total, location, city FROM (" + vehicleQuery + ") vehicles GROUP BY (location, city)";

        String vehQueryStringPost = "";
        String vtGroupQueryStringPost = "";
        String branchGroupQueryStringPost = "";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement(vehicleQuery);

            stmt.setDate(1, TODAY);
            if (b != null) {
                stmt.setString(2, b.getLocation());
                stmt.setString(3, b.getCity());
            }

            vehQueryStringPost = stmt.toString();

            ResultSet vehicleRs = stmt.executeQuery(vehicleQuery);

            while (vehicleRs.next()) {
                int vid = vehicleRs.getInt("vid");
                String vLicense = vehicleRs.getString("vlicense");
                String make = vehicleRs.getString("make");
                String model = vehicleRs.getString("model");
                Integer yearInt = vehicleRs.getInt("year");
                String year = yearInt.toString();
                String color = vehicleRs.getString("color");
                int odometer = vehicleRs.getInt("odometer");

                VehicleStatus vehicleStatus = VehicleStatus.toStatus(vehicleRs.getString("vehicleStatus"));
                VehicleTypeName vtName = VehicleTypeName.toVehicleTypeName(vehicleRs.getString("vtname"));

                Vehicle v = new Vehicle(vid, vLicense, make, model, year, color, odometer, vehicleStatus, null, vtName);

                report.addVehicle(v);
            }
            vehicleRs.close();

            stmt = conn.prepareStatement(vtGroupQuery);
            stmt.setDate(1, TODAY);
            if (b != null) {
                stmt.setString(2, b.getLocation());
                stmt.setString(3, b.getCity());
            }

            vtGroupQueryStringPost = stmt.toString();

            ResultSet vtRs = stmt.executeQuery();

            while (vtRs.next()) {
                VehicleTypeName vtName = VehicleTypeName.toVehicleTypeName(vtRs.getString("vtname"));
                int count = vtRs.getInt("total");

                report.addVTRentalCount(vtName, count);
            }

            vtRs.close();

            stmt = conn.prepareStatement(branchGroupQuery);
            stmt.setDate(1, TODAY);
            if (b != null) {
                stmt.setString(2, b.getLocation());
                stmt.setString(3, b.getCity());
            }

            branchGroupQueryStringPost = stmt.toString();

            ResultSet branchRs = stmt.executeQuery();

            while (branchRs.next()) {
                String location = branchRs.getString("location");
                String city = branchRs.getString("city");
                Branch branch = new Branch(location, city);

                int total = branchRs.getInt("total");

                report.addBranchRentalCount(branch, total);
            }
            branchRs.close();

            stmt.close();

        } catch (SQLException e) {
            return new TestDatabaseResponse<>( vehQueryStringPost + "; \n" + vtGroupQueryStringPost + "; \n" + branchGroupQueryStringPost, false, e.getMessage(), "");
        }

        String queryStatement = vehQueryStringPost + "; \n" + vtGroupQueryStringPost + "; \n" + branchGroupQueryStringPost;

        return new TestDatabaseResponse<>(queryStatement, true, null, report.toString());
    }

    public static DatabaseResponse<String> getDailyRentals() {
        return getDailyRentals(null);
    }

    public static DatabaseResponse<String> getDailyRentals(Branch b) {
        return null;
    }
}

class RentalReport {
    List<Vehicle> vehicles;
    Map<Branch, Integer> branchRentals;
    Map<VehicleTypeName, Integer> vtRentals;

    RentalReport() {
        this.vehicles = new ArrayList<>();
        this.branchRentals = new HashMap<>();
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void addBranchRentalCount(Branch b, int count) {
        branchRentals.put(b, count);
    }

    public void addVTRentalCount(VehicleTypeName vt, int count) {

    }

}

class ReturnReport {

}
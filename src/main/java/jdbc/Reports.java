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

        ReturnReport report = new ReturnReport();

        String vehicleQuery = "SELECT * FROM Vehicle v INNER JOIN Rent r ON v.vlicense = r.vlicense INNER JOIN Return rt ON r.rid = rt.rid" +
                " WHERE rt.date = ? " +
                ((b == null) ? "" : "AND v.location = ? AND v.city = ? ") + "ORDER BY (location, city), vtname ASC";
        String vtGroupQuery = "SELECT SUM(value) AS revenue, COUNT(*) AS total, vtname FROM (" + vehicleQuery + ") vehicles GROUP BY vtname";
        String branchGroupQuery = "SELECT SUM(value) AS revenue, COUNT(*) AS total, location, city FROM (" + vehicleQuery + ") vehicles GROUP BY (location, city)";

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

            ResultSet vehicleRs = stmt.executeQuery();

            while (vehicleRs.next()) {
                int vid = vehicleRs.getInt("vid");
                String vLicense = vehicleRs.getString("vlicense");
                String make = vehicleRs.getString("make");
                String model = vehicleRs.getString("model");
                Integer yearInt = vehicleRs.getInt("year");
                String year = yearInt.toString();
                String color = vehicleRs.getString("color");
                int odometer = vehicleRs.getInt("odometer");

                VehicleStatus vehicleStatus = VehicleStatus.toStatus(vehicleRs.getString("status"));
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
                int revenue = vtRs.getInt("revenue");

                report.addVTReturn(vtName, revenue, count);
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

                int revenue = branchRs.getInt("revenue");
                int total = branchRs.getInt("total");

                report.addBranchReturn(branch, revenue, total);
            }
            branchRs.close();

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return new ReportDatabaseResponse( vehQueryStringPost + "; \n" + vtGroupQueryStringPost + "; \n" + branchGroupQueryStringPost, false, e.getMessage(), e.getMessage());
        }

        String queryStatement = vehQueryStringPost + "; \n" + vtGroupQueryStringPost + "; \n" + branchGroupQueryStringPost;

        return new ReportDatabaseResponse(queryStatement, true, report.toString(), report.toString());
    }

    public static DatabaseResponse<String> getDailyRentals() {
        return getDailyRentals(null);
    }

    public static DatabaseResponse<String> getDailyRentals(Branch b) {
        RentalReport report = new RentalReport();

        String vehicleQuery = "SELECT * FROM Vehicle v INNER JOIN Rent r ON v.vlicense = r.vlicense WHERE fromdate = ? " +
                ((b == null) ? "" : "AND v.location = ? AND v.city = ? ") + "ORDER BY (location, city), vtname ASC";
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

            ResultSet vehicleRs = stmt.executeQuery();

            while (vehicleRs.next()) {
                int vid = vehicleRs.getInt("vid");
                String vLicense = vehicleRs.getString("vlicense");
                String make = vehicleRs.getString("make");
                String model = vehicleRs.getString("model");
                Integer yearInt = vehicleRs.getInt("year");
                String year = yearInt.toString();
                String color = vehicleRs.getString("color");
                int odometer = vehicleRs.getInt("odometer");

                VehicleStatus vehicleStatus = VehicleStatus.toStatus(vehicleRs.getString("status"));
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
            e.printStackTrace();
            return new ReportDatabaseResponse( vehQueryStringPost + "; \n" + vtGroupQueryStringPost + "; \n" + branchGroupQueryStringPost, false, e.getMessage(), e.getMessage());
        }

        String queryStatement = vehQueryStringPost + "; \n" + vtGroupQueryStringPost + "; \n" + branchGroupQueryStringPost;

        return new ReportDatabaseResponse(queryStatement, true, report.toString(), report.toString());
    }
}

class RentalReport {
    List<Vehicle> vehicles;
    Map<Branch, Integer> branchRentals;
    Map<VehicleTypeName, Integer> vtRentals;

    RentalReport() {
        this.vehicles = new ArrayList<>();
        this.branchRentals = new HashMap<>();
        this.vtRentals = new HashMap<>();
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void addBranchRentalCount(Branch b, int count) {
        branchRentals.put(b, count);
    }

    public void addVTRentalCount(VehicleTypeName vt, int count) {
        vtRentals.put(vt, count);
    }

    @Override
    public String toString() {

        if (vehicles.size() == 0) {
            return "No vehicles rented today";
        }

        String str = "VEHICLES RENTED OUT TODAY: \n";

        for (Vehicle v: vehicles) {
            str += v.toString() + "\n";
        }

        str += "\n";

        str += "VEHICLES RENTED OUT BY TYPE: \n";
        int total = 0;
        for (VehicleTypeName vtname: vtRentals.keySet()) {
            str += vtname + ": " + vtRentals.get(vtname) + "\n";
            total += vtRentals.get(vtname);
        }

        str += "\n";


        str += "VEHICLES RENTED OUT BY BRANCH: \n";

        for (Branch b: branchRentals.keySet()) {
            str += b.toString() + ": " + branchRentals.get(b) + "\n";
        }

        str += "\n";


        str += "TOTAL NEW RENTALS TODAY: " + total;

        return str;
    }
}



class ReturnReport {
    List<Vehicle> vehicles;
    Map<Branch, List<Integer>> branchReturns;
    Map<VehicleTypeName, List<Integer>> vtReturns;

    ReturnReport() {
        vehicles = new ArrayList<>();
        branchReturns = new HashMap<>();
        vtReturns = new HashMap<>();
    }

    public String toString() {

        if (vehicles.size() == 0) {
            return "No vehicles returned today";
        }
        String str = "VEHICLES RETURNED TODAY: \n";

        for (Vehicle v: vehicles) {
            str += v.toString() + "\n";
        }

        str += "\n";


        str += "VEHICLES RETURNED AND REVENUE BY TYPE: \n";
        int totalReturned = 0;
        int totalRevenue = 0;

        for (VehicleTypeName vtname: vtReturns.keySet()) {
            str += vtname + ": Returned: " + vtReturns.get(vtname).get(1) +  " Revenue: $" + vtReturns.get(vtname).get(0)
        + "\n";
            totalReturned += vtReturns.get(vtname).get(1);
            totalRevenue += vtReturns.get(vtname).get(0);
        }
        str += "\n";


        str += "VEHICLES RETURNED AND REVENUE BY BRANCH: \n";

        for (Branch b: branchReturns.keySet()) {
            str += b.toString() + ": Returned: " + branchReturns.get(b).get(1) +  " Revenue: $" + branchReturns.get(b).get(0)
                    + "\n";
        }
        str += "\n";

        str += "TOTAL RETURNS TODAY: " + totalReturned + "\n";
        str += "TOTAL REVENUE TODAY: $" + totalRevenue;


        return str;
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void addBranchReturn(Branch b, int revenue, int count) {
        List<Integer> list = new ArrayList<>();
        list.add(revenue);
        list.add(count);
        branchReturns.put(b, list);
    }

    public void addVTReturn(VehicleTypeName vt, int revenue, int count) {
        List<Integer> list = new ArrayList<>();
        list.add(revenue);
        list.add(count);
        vtReturns.put(vt, list);
    }
}

class ReportDatabaseResponse implements DatabaseResponse<String> {
    private String query;
    private boolean success;
    private String response;
    private String value;

    public ReportDatabaseResponse(String query, boolean success, String response, String value) {
        this.query = query;
        this.success = success;
        this.response = response;
        this.value = value;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getResponse() {
        return response;
    }

    @Override
    public String getValue() {
        return value;
    }
}
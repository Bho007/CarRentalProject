package jdbc;

//import model.TimePeriod;

import main.Database;
import main.DatabaseResponse;
import main.TestDatabaseResponse;
import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

// TODO: prepared statement

public class Query implements Database {

    // Database URL
    public static final String DB_URL = "jdbc:postgresql://34.94.14.233:5432/postgres";

    //  Database credentials
    public static final String USER = "postgres";
    public static final String PASS = "hunter2";

    private static Connection conn;

    private static final Query QUERY_INSTANCE = new Query();

    private Query() {
        try {
            Class.forName("org.postgresql.Driver");
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Query getInstance() {
        return QUERY_INSTANCE;
    }


    private ResultSet askQuery(PreparedStatement stmt, String query) {
        try {
            return stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public DatabaseResponse<List<Vehicle>> getVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        boolean success = true;
        List<Vehicle> returnList = new ArrayList<>();
        String query = "SELECT v.* \n" +
                "FROM public.vehicle v, public.rent r\n" +
                "WHERE v.status = \'for_rent\' and  r.vid = v.vid";

        if (type != null) {
            query += " and v.vtname = " + type.getName();
        }
        if (location != null && !location.equals("")) {
            query += " and v.location = ?";// + location;
        }

        // logic for date and time
        if (from != null && to != null) {
            Date toDate = Date.valueOf(to.toLocalDate());
            Time toTime = Time.valueOf(to.toLocalTime());
            Date fromDate = Date.valueOf(from.toLocalDate());
            Time fromTime = Time.valueOf(from.toLocalTime());
            query += " and (((not r.fromDate >= " + fromDate.toString() + ") or r.fromTime >= " + fromTime.toString() +
                    ") or ((not r.toDate <= " + toDate.toString() + ") or r.toTime <= + " + toTime.toString() + "))";
        } else if (from != null) {
            Date fromDate = Date.valueOf(from.toLocalDate());
            Time fromTime = Time.valueOf(from.toLocalTime());
            query += " and ((not r.fromDate >= " + fromDate.toString() + ") or r.fromTime >= " + fromTime.toString() + ")";
        } else if (to != null) {
            Date toDate = Date.valueOf(to.toLocalDate());
            Time toTime = Time.valueOf(to.toLocalTime());
            query += " and ((not r.fromDate <= " + toDate.toString() + ") or r.fromTime <= " + toTime.toString() + ")";
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            if (query.contains("?")) {
                stmt.setString(1, location);
            }
            ResultSet rs = askQuery(stmt, query);
            assert (rs != null);
            while (rs.next()) {
                VehicleStatus status = VehicleStatus.toStatus(rs.getString("status"));
                VehicleTypeName vehicleTypeName = VehicleTypeName.toVechicleTypeName(rs.getString("vtname"));
                Vehicle vehicle = new Vehicle(rs.getInt("vid"), rs.getString("vlicense"),
                        rs.getString("make"), rs.getString("model"),
                        rs.getString("year"), rs.getString("color"),
                        rs.getInt("odomoter"), status, null, vehicleTypeName);
                returnList.add(vehicle);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            success = false;
        }

        return new TestDatabaseResponse<>(query, success, null, returnList);

    }

    @Override
    public DatabaseResponse<List<>> generateDailyRentalReport() {
        return new ;
    }

    @Override
    public DatabaseResponse<String> generateDailyBranchRentalReport(Branch b) {
        return null;
    }

    @Override
    public DatabaseResponse<String> generateDailyReturnReport() {
        return null;
    }

    @Override
    public DatabaseResponse<String> generateDailyBranchReturnReport(Branch b) {
        return null;
    }

    @Override
    public DatabaseResponse<?> sendQuery(String query) {
        return null;
    }
}

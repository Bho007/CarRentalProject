package jdbc;

import model.TimePeriod;
import model.Vehicle;
import model.VehicleStatus;
import model.VehicleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// TODO: prepared statement

public class Query {

    // Database URL
    public static final String DB_URL = "jdbc:postgresql://34.94.14.233:5432/postgres";

    //  Database credentials
    public static final String USER = "postgres";
    public static final String PASS = "hunter2";

    public static Connection conn;
    public static Statement stmt;


    public static ResultSet askQuery(String query) {
        try {
            return stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method for querying for all available vehicles
     *
     * @return All the available vehicles found
     */
    public static List<Vehicle> getAllAvailableVehicles(VehicleType vt, String location, TimePeriod timePeriod) {
        List<Vehicle> returnList = new ArrayList<>();

        String query = "SELECT v.* \n" +
                "FROM public.vehicle v\n" +
                "WHERE v.status = \'for_rent\'";



        if (vt != null) {
            query = query + " AND v.vtname = " + vt.getVtname();
        }
        if (location != null && !location.equals("")) {
            query = query + " AND v.location = " + location;
        }
        if (timePeriod != null) {
            // TODO: implement time period
        }

        try {
            ResultSet rs = askQuery(query);
            while (rs.next()) {
                VehicleStatus status = VehicleStatus.toStatus(rs.getString("status").toUpperCase());
                VehicleType type = (VehicleType) rs.getObject("vtname");
                Vehicle vehicle = new Vehicle(rs.getInt("vid"), rs.getString("vlicense"),
                        rs.getString("make"), rs.getString("model"),
                        rs.getString("year"), rs.getString("color"),
                        rs.getInt("odomoter"), status, type);
                returnList.add(vehicle);
            }
            //stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //askQuery(query);
        return returnList;
    }


}

package jdbc;

//import model.TimePeriod;

import model.Vehicle;
import model.VehicleStatus;
import model.VehicleType;
import model.VehicleTypeName;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public static PreparedStatement stmt;


    private static ResultSet askQuery(String query) {
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
    private List<Vehicle> getAllAvailableVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        List<Vehicle> returnList = new ArrayList<>();

        String query = "SELECT v.* \n" +
                "FROM public.vehicle v, public.rent r\n" +
                "WHERE v.status = \'for_rent\' and  r.vid = v.vid";

        if (type != null) {
            query += " and v.vtname = " + type.getName();
        }
        if (location != null && !location.equals("")) {
            query += " and v.location = ?";// + location;
            try {
                stmt.setString(1, location);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

        // type, location, fromTime, toTime, fromDate, toDate

        //TODO - uncomment
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, location);
            //stmt.executeUpdate();
            ResultSet rs = askQuery(query);
            assert (rs != null);
            while (rs != null && rs.next()) {
                Date date = rs.getDate("");
//                VehicleStatus status = VehicleStatus.toStatus(rs.getString("status").toUpperCase());
//                VehicleType type1 = (VehicleType) rs.getObject("vtname");
//                Vehicle vehicle = new Vehicle(rs.getInt("vid"), rs.getString("vlicense"),
//                        rs.getString("make"), rs.getString("model"),
//                        rs.getString("year"), rs.getString("color"),
//                        rs.getInt("odomoter"), status, type1);
                //returnList.add(vehicle);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //askQuery(query);
        return returnList;
    }


}

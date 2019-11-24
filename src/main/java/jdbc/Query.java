package jdbc;

//import model.TimePeriod;

import main.Database;
import main.DatabaseResponse;
import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Query implements Database {

    // Database URL
    private static final String DB_URL = "jdbc:postgresql://34.94.14.233:5432/postgres";

    //  Database credentials
    private static final String USER = "postgres";
    private static final String PASS = "hunter2";

    private static final String ERROR = "unable to retrieve the requested info";
    private static final String SUCCESS = "success";

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
        assert (QUERY_INSTANCE != null);
        return QUERY_INSTANCE;
    }


//    private ResultSet askQuery(PreparedStatement stmt, String query) {
//        try {
//            return stmt.executeQuery(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    private String getResponse(boolean var) {
        return var ? SUCCESS : ERROR;
    }

    @Override
    public DatabaseResponse<List<Vehicle>> getVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        boolean success = locationExists(location).getValue();
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

        if (success) {
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                if (query.contains("?")) {
                    stmt.setString(1, location);
                }
                ResultSet rs = stmt.executeQuery();
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
        }
        return new VehicleListResponse(query, success, getResponse(success), returnList);

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
    public DatabaseResponse<Boolean> locationExists(String location) {
        boolean ret = false;
        String query = "SELECT b.*\n" +
                "FROM public.branch b\n" +
                "WHERE b.location = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, location);
            ResultSet resultSet = stmt.executeQuery();
            ret = resultSet.next();
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BooleanResponse(query, ret, getResponse(ret), ret);
    }

    @Override
    public DatabaseResponse<Boolean> customerExists(String driversLicense) {
        boolean ret = false;
        String query = "SELECT c.dlicense\n" +
                "FROM public.customer c\n" +
                "WHERE c.dlicense = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, driversLicense);
            ResultSet resultSet = stmt.executeQuery();
            ret = resultSet.next();
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BooleanResponse(query, ret, getResponse(ret), ret);
    }

    @Override
    public DatabaseResponse<Customer> getCustomer(String driversLicense) {
        Customer ret = null;
        boolean success = false;
        String query = "SELECT c.*\n" +
                "FROM public.customer c\n" +
                "WHERE c.dlicense = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, driversLicense);
            ResultSet resultSet = stmt.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                ret = new Customer(resultSet.getInt("cellphone"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getString("license"));
                counter++;
            }
            success = counter == 1;
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new CustomerResponse(query, success, getResponse(success), ret);
    }

    @Override
    public DatabaseResponse<?> addCustomer(String phone, String name, String address, String driversLicense) {
        String query = "insert into public.customer (cellphone, name, address, dlicense) values (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(phone));
            stmt.setString(2, name);
            stmt.setString(3, address);
            stmt.setString(4, driversLicense);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DatabaseResponse<Object>() {
            @Override
            public String getQuery() {
                return null;
            }

            @Override
            public boolean isSuccess() {
                return true;
            }

            @Override
            public String getResponse() {
                return null;
            }

            @Override
            public Object getValue() {
                return null;
            }
        };
    }

    @Override
    public DatabaseResponse<String> reserveVehicle(VehicleTypeName type, String dlicense, LocalDateTime from, LocalDateTime to) {
        boolean success = customerExists(dlicense).getValue();
        String query = "insert into public.reservation (vtname, cellphone, fromdate, fromtime, todate, totime) values (?,?,?,?,?,?)";
        if (success) {
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, type.getName());
                stmt.setString(2, dlicense);
                stmt.setDate(3, Date.valueOf(from.toLocalDate()));
                stmt.setTime(4, Time.valueOf(from.toLocalTime()));
                stmt.setDate(5, Date.valueOf(to.toLocalDate()));
                stmt.setTime(6, Time.valueOf(to.toLocalTime()));
                success = stmt.executeUpdate() > 0;
                stmt.close();
                return new StringResponse(query, success, getResponse(success), getReservationNumber(type, dlicense, from, to));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new StringResponse(query, false, ERROR, null);
    }

    private String getReservationNumber(VehicleTypeName type, String dlicense, LocalDateTime from, LocalDateTime to) {
        String confno = "";
        boolean success = customerExists(dlicense).getValue();
        String query = "SELECT  r.confno \n" +
                "FROM public.reservation r \n" +
                "WHERE r.vtname= ? and r.dlicense = ? and r.fromdate = ? and r.fromtime = ? and r.todate = ? and r.totime = ?";
        if (success) {
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, type.getName());
                stmt.setString(2, dlicense);
                stmt.setDate(3, Date.valueOf(from.toLocalDate()));
                stmt.setTime(4, Time.valueOf(from.toLocalTime()));
                stmt.setDate(5, Date.valueOf(to.toLocalDate()));
                stmt.setTime(6, Time.valueOf(to.toLocalTime()));
                ResultSet resultSet = stmt.executeQuery();
                int counter = 0;
                while (resultSet.next()) {
                    confno = resultSet.getString("confno");
                    counter++;
                }
                assert (counter == 1);
                resultSet.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return confno;
    }

    @Override
    public DatabaseResponse<Reservation> getReservationByConfirmationNumber(String confirmationNumber) {
        Reservation res = null;
        String query = "SELECT  r.* \n" +
                "FROM public.reservation r \n" +
                "WHERE r.confno = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(confirmationNumber));
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while (rs.next()) {
                Date fromDate = rs.getDate("fromDate");
                Time fromTime = rs.getTime("fromtime");
                Date toDate = rs.getDate("toDate");
                Time toTime = rs.getTime("toTime");
                LocalDateTime from = LocalDateTime.parse(fromDate.toString() + fromTime.toString());
                LocalDateTime to = LocalDateTime.parse(toDate.toString() + toTime.toString());
                res = new Reservation(rs.getInt("confno"),
                        VehicleTypeName.toVechicleTypeName(rs.getString("vtname")),
                        rs.getLong("cellphone"), from, to);
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ReservationResponse(query, res != null, getResponse(res != null), res);
    }

    @Override
    public DatabaseResponse<Reservation> getReservationByPhoneNumber(String phoneNumber) {
        Reservation res = null;
        String query = "SELECT  r.* \n" +
                "FROM public.reservation r \n" +
                "WHERE r.cellphone = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, Long.parseLong(phoneNumber));
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while (rs.next()) {
                Date fromDate = rs.getDate("fromDate");
                Time fromTime = rs.getTime("fromtime");
                Date toDate = rs.getDate("toDate");
                Time toTime = rs.getTime("toTime");
                LocalDateTime from = LocalDateTime.parse(fromDate.toString() + fromTime.toString());
                LocalDateTime to = LocalDateTime.parse(toDate.toString() + toTime.toString());
                res = new Reservation(rs.getInt("confno"),
                        VehicleTypeName.toVechicleTypeName(rs.getString("vtname")),
                        rs.getLong("cellphone"), from, to);
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ReservationResponse(query, res != null, getResponse(res != null), res);
    }

    @Override
    public DatabaseResponse<String> rentVehicle(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public DatabaseResponse<Rental> getRental(String id) {
        return null;
    }

    @Override
    public DatabaseResponse<String> returnVehicle(VehicleTypeName type, String location, LocalDateTime time,
                                                  String odometer, boolean gasTankIsFull, int cost) {
        return null;
    }

    @Override
    public DatabaseResponse<Integer> getHourlyRate(VehicleTypeName type) {
        return null;
    }

    @Override
    public DatabaseResponse<Integer> getDailyRate(VehicleTypeName type) {
        return null;
    }

    @Override
    public DatabaseResponse<Integer> getWeeklyRate(VehicleTypeName type) {
        return null;
    }

    @Override
    public DatabaseResponse<?> sendQuery(String query) {
        return null;
    }


    private class StringResponse implements DatabaseResponse<String> {

        private String query;
        private boolean success;
        private String response;
        private String value;

        StringResponse(String query, boolean success, String response, String value) {
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

    private class IntegerResponse implements DatabaseResponse<Integer> {

        private String query;
        private boolean success;
        private String response;
        private int value;

        IntegerResponse(String query, boolean success, String response, int value) {
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
        public Integer getValue() {
            return value;
        }
    }


    private class ReservationResponse implements DatabaseResponse<Reservation> {

        private String query;
        private boolean success;
        private String response;
        private Reservation value;

        ReservationResponse(String query, boolean success, String response, Reservation value) {
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
        public Reservation getValue() {
            return value;
        }
    }

    private class CustomerResponse implements DatabaseResponse<Customer> {

        private String query;
        private boolean success;
        private String response;
        private Customer value;

        CustomerResponse(String query, boolean success, String response, Customer value) {
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
        public Customer getValue() {
            return value;
        }
    }

    private class RentalResponse implements DatabaseResponse<Rental> {

        private String query;
        private boolean success;
        private String response;
        private Rental value;

        RentalResponse(String query, boolean success, String response, Rental value) {
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
        public Rental getValue() {
            return value;
        }
    }

    private class BooleanResponse implements DatabaseResponse<Boolean> {

        private String query;
        private boolean success;
        private String response;
        private boolean value;

        BooleanResponse(String query, boolean success, String response, boolean value) {
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
        public Boolean getValue() {
            return value;
        }
    }

    private class VehicleListResponse implements DatabaseResponse<List<Vehicle>> {

        private String query;
        private boolean success;
        private String response;
        private List<Vehicle> value;

        VehicleListResponse(String query, boolean success, String response, List<Vehicle> value) {
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
        public List<Vehicle> getValue() {
            return value;
        }
    }

}

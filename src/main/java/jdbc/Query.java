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

    private static final String ERROR = "Unable to retrieve the requested info";
    private static final String SUCCESS = "Success";

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
                "WHERE v.status = \'for_rent\' and  r.vlicense = v.vlicense";

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
        boolean ret;
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
            //StackTrace();
            ret = false;
        }
        return new BooleanResponse(query, ret, getResponse(ret), ret);
    }

    @Override
    public DatabaseResponse<Boolean> customerExists(String driversLicense) {
        boolean ret;
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
            ret = false;
        }
        return new BooleanResponse(query, ret, getResponse(ret), ret);
    }

    @Override
    public DatabaseResponse<Customer> getCustomer(String driversLicense) {
        Customer ret = null;
        boolean success;
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
            success = false;
        }
        return new CustomerResponse(query, success, getResponse(success), ret);
    }

    private Customer getCustomer(Long phoneNumber) {
        Customer ret = null;
        String query = "SELECT c.*\n" +
                "FROM public.customer c\n" +
                "WHERE c.cellphone = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, phoneNumber);
            ResultSet resultSet = stmt.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                ret = new Customer(resultSet.getInt("cellphone"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getString("license"));
                counter++;
            }
            assert (counter == 1);
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            //success = false;
        }
        return ret;
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
            return new DatabaseResponse<>() {
                @Override
                public String getQuery() {
                    return null;
                }

                @Override
                public boolean isSuccess() {
                    return false;
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

        return new DatabaseResponse<>() {
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
                //
            }
        }
        return new StringResponse(query, false, ERROR, null);
    }

    private String getReservationNumber(VehicleTypeName type, String dlicense, LocalDateTime from, LocalDateTime to) {
        String confno = "";
        boolean success = customerExists(dlicense).getValue();
        Customer customer = getCustomer(dlicense).getValue();
        String query = "SELECT  r.confno \n" +
                "FROM public.reservation r \n" +
                "WHERE r.vtname= ? and r.cellphone = ? and r.fromdate = ? and r.fromtime = ? and r.todate = ? and r.totime = ?";
        if (success) {
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, type.getName());
                stmt.setLong(2, customer.getCellPhone());
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
            //
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
                LocalDateTime from = LocalDateTime.parse(fromDate.toString() + " " + fromTime.toString());
                LocalDateTime to = LocalDateTime.parse(toDate.toString() + " " + toTime.toString());
                res = new Reservation(rs.getInt("confno"),
                        VehicleTypeName.toVechicleTypeName(rs.getString("vtname")),
                        rs.getLong("cellphone"), from, to);
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            //
        }
        return new ReservationResponse(query, res != null, getResponse(res != null), res);
    }

    @Override
    public DatabaseResponse<Rental> rentVehicle(String driversLicense, String phone, String confirmatioNumber,
                                                VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to,
                                                String creditCardNumber, String expiryMonth, String expiryYear, String creditCardType) {

        boolean success = locationExists(location).getValue();
        String query = "";
        List<Vehicle> vehicles = getVehicles(type, location, from, to).getValue();
        success = success && !vehicles.isEmpty();
        Rental rental = null;
        if (success) {
            Vehicle tobeRented = vehicles.get(0);
            updateVehicleStatus(tobeRented, VehicleStatus.RENTED);
            query = "insert into public.rent (vlicense, fromdate, fromtime, todate, totime, odometer, cardname, cardno, expdate, " +
                    "confno, cellphone) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tobeRented.getvLicense());
                stmt.setDate(2, Date.valueOf(from.toLocalDate()));
                stmt.setTime(3, Time.valueOf(from.toLocalTime()));
                stmt.setDate(4, Date.valueOf(to.toLocalDate()));
                stmt.setTime(5, Time.valueOf(to.toLocalTime()));
                stmt.setInt(6, tobeRented.getOdometer());
                stmt.setString(7, creditCardType);
                stmt.setLong(8, Long.parseLong(creditCardNumber));
                stmt.setDate(9, Date.valueOf(expiryMonth + "/" + expiryYear));
                stmt.setInt(10, Integer.parseInt(confirmatioNumber));
                stmt.setLong(11, Long.parseLong(phone));
                success = stmt.executeUpdate() > 0;
                rental = new Rental(getRID(phone, confirmatioNumber, type, location, from, to, creditCardNumber,
                        expiryMonth, expiryYear, creditCardType,tobeRented), tobeRented, getCustomer(Long.parseLong(phone)), from, to,
                        tobeRented.getOdometer(), creditCardType, creditCardNumber, Date.valueOf(expiryMonth + "/" + expiryYear),
                        getReservationByPhoneNumber(phone).getValue(), null);
                stmt.close();
            } catch (SQLException e) {
                success = false;
            }
        }

        return new RentalResponse(query, success, getResponse(success), rental);
    }

    private void updateVehicleStatus(Vehicle vehicle, VehicleStatus newStatus) {
        String query = "UPDATE public.vehicle\n" +
                "SET status = \'rented\'\n" +
                "WHERE vid = ? and vlicense = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, vehicle.getVid());
            stmt.setString(2, vehicle.getvLicense());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getRID(String phone, String confirmatioNumber, VehicleTypeName type,
                       String location, LocalDateTime from, LocalDateTime to, String creditCardNumber,
                       String expiryMonth, String expiryYear, String creditCardType, Vehicle vehicle) {
        int ret = -1;
        String query = "SELECT r.rid\n" +
                "FROM public.rent r\n" +
                "WHERE r.vlicense = ? and r.cellphone = ? and r.confno = ? and r.totime = ? and r.todate = ? and " +
                "r.fromtime = ? and r.fromdate = ? and r.odometer = ? and r.cardname = ? and " +
                "r.cardno = ? and r.expdate = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, vehicle.getvLicense());
            stmt.setLong(2, Long.parseLong(phone));
            stmt.setInt(3, Integer.parseInt(confirmatioNumber));
            stmt.setTime(4, Time.valueOf(to.toLocalTime()));
            stmt.setDate(5, Date.valueOf(to.toLocalDate()));
            stmt.setTime(6, Time.valueOf(from.toLocalTime()));
            stmt.setDate(7, Date.valueOf(from.toLocalDate()));
            stmt.setInt(8, vehicle.getOdometer());
            stmt.setString(9, creditCardType);
            stmt.setLong(10, Long.parseLong(creditCardNumber));
            stmt.setDate(11, Date.valueOf(expiryMonth + "/" + expiryYear));
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while(rs.next()) {
                ret = rs.getInt("rid");
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public DatabaseResponse<Rental> getRental(String id) {
        Rental rental = null;
        String query = "SELECT  r.* \n" +
                "FROM public.rent r \n" +
                "WHERE r.rid = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while (rs.next()) {
                Date fromDate = rs.getDate("fromDate");
                Time fromTime = rs.getTime("fromtime");
                Date toDate = rs.getDate("toDate");
                Time toTime = rs.getTime("toTime");
                LocalDateTime from = LocalDateTime.parse(fromDate.toString() + " " + fromTime.toString());
                LocalDateTime to = LocalDateTime.parse(toDate.toString() + " " + toTime.toString());
                String vlicense = rs.getString("vlicense");
                Long phone = rs.getLong("cellphone");
                Vehicle vehicle = getVehicle(vlicense).getValue();

                rental = new Rental(Integer.parseInt(id), vehicle, getCustomer(phone), from, to, vehicle.getOdometer(),
                        rs.getString("cardname"), String.valueOf(rs.getLong("cardno")),
                        rs.getDate("expdate"), getReservationByPhoneNumber(String.valueOf(phone)).getValue(),
                        null);
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            //
        }
        return new RentalResponse(query, rental != null, getResponse(rental != null), rental);

    }

    @Override
    public VehicleResponse getVehicle(String vlicense) {
        String query = "SELECT v.* \n" +
                "FROM public.vehicle v" +
                "WHERE v.vlicence = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, vlicense);
            ResultSet rs = stmt.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                VehicleStatus status = VehicleStatus.toStatus(rs.getString("status"));
                VehicleTypeName vehicleTypeName = VehicleTypeName.toVechicleTypeName(rs.getString("vtname"));
                Vehicle returnV = new Vehicle(rs.getInt("vid"), rs.getString("vlicense"),
                        rs.getString("make"), rs.getString("model"),
                        rs.getString("year"), rs.getString("color"),
                        rs.getInt("odomoter"), status, null, vehicleTypeName);
                return new VehicleResponse(query, true, getResponse(true), returnV);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new VehicleResponse(query, false, getResponse(false), null);
    }

    @Override
    public DatabaseResponse<String> returnVehicle(String rentalID, String location, LocalDateTime time, String odometer,
                                                  boolean gasTankIsFull, int cost) {
        boolean success = locationExists(location).getValue();

        // UPDATE VEHICLE STATUS
        Vehicle v = getVehicle(getRental(rentalID).getValue().getVehicle().getvLicense()).getValue();
        updateVehicleStatus(v, v.getStatus());

        String query = "insert into public.return (rid, date, time, odometer, fulltank, value) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(rentalID));
            stmt.setDate(2, Date.valueOf(time.toLocalDate()));
            stmt.setTime(3, Time.valueOf(time.toLocalTime()));
            stmt.setInt(4, Integer.parseInt(odometer));
            stmt.setBoolean(5, gasTankIsFull);
            stmt.setInt(6, cost);
            success = stmt.executeUpdate() > 0;
            stmt.close();
        } catch (SQLException e) {
            success = false;
        }
        return new StringResponse(query, success, getResponse(success), getResponse(success));
    }

    @Override
    public DatabaseResponse<Integer> getHourlyRate(VehicleTypeName type) {
        return getAllRates(type, "hrate");
    }

    @Override
    public DatabaseResponse<Integer> getDailyRate(VehicleTypeName type) {
        return getAllRates(type, "drate");
    }

    @Override
    public DatabaseResponse<Integer> getWeeklyRate(VehicleTypeName type) {
        return getAllRates(type, "wrate");
    }

    @Override
    public DatabaseResponse<?> sendQuery(String query) {
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            Object ret = stmt.executeQuery();
            return new DatabaseResponse<>() {
                @Override
                public String getQuery() {
                    return query;
                }

                @Override
                public boolean isSuccess() {
                    return true;
                }

                @Override
                public String getResponse() {
                    return SUCCESS;
                }

                @Override
                public Object getValue() {
                    return ret;
                }
            };
        } catch (SQLException e) {
            return new DatabaseResponse<>() {
                @Override
                public String getQuery() {
                    return query;
                }

                @Override
                public boolean isSuccess() {
                    return false;
                }

                @Override
                public String getResponse() {
                    return ERROR;
                }

                @Override
                public Object getValue() {
                    return null;
                }
            };
        }
    }

    private IntegerResponse getAllRates(VehicleTypeName type, String rateVar) {
        String query = "SELECT vt." + rateVar + "\n" +
                "FROM public.vehicletype vt \n" +
                "WHERE vt.vtname = ?" +
                "LIMIT 1";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, type.getName());
            ResultSet rs = stmt.executeQuery();
            int ret = rs.getInt(rateVar);
            rs.close();
            stmt.close();
            return new IntegerResponse(query, true, getResponse(true), ret);
        } catch (SQLException e) {
            return new IntegerResponse(query, false, getResponse(false), -1);
        }
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

    private class VehicleResponse implements DatabaseResponse<Vehicle> {

        private String query;
        private boolean success;
        private String response;
        private Vehicle value;

        VehicleResponse(String query, boolean success, String response, Vehicle value) {
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
        public Vehicle getValue() {
            return value;
        }
    }
}

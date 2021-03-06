package jdbc;

//import model.TimePeriod;

import main.Database;
import main.DatabaseResponse;
import main.UIController;
import model.*;

import javax.print.DocFlavor;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Query implements Database {

    // Database URL
    public static final String DB_URL = "jdbc:postgresql://34.94.14.233:5432/postgres";

    //  Database credentials
    public static final String USER = "postgres";
    public static final String PASS = "hunter2";

    private static final String ERROR = "Unable to retrieve the requested info: SQL ERROR";
    private static final String SUCCESS = "Success";

    private static Connection conn;

    private static final Query QUERY_INSTANCE = new Query();
    private static final UIController UI_CONTROLLER = new UIController();

    //private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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


//    private String getResponse(boolean var) {
//        return var ? SUCCESS : ERROR;
//    }

    @Override
    public DatabaseResponse<List<Vehicle>> getVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        boolean success = locationExists(location).getValue();
        String response = SUCCESS;
        List<Vehicle> returnList = new ArrayList<>();
        String query = "SELECT v.* \n" +
                "FROM public.vehicle v\n" +
                "WHERE v.status = \'for_rent\'";

        if (type != null) {
            query += " and v.vtname = \'" + type.getName() + "\'";
        }
        if (location != null && !location.equals("")) {
            query += " and v.location = ?";// + location;
        }

//        // logic for date and time
//        if (from != null && to != null) {
//            Date toDate = Date.valueOf(to.toLocalDate());
//            Time toTime = Time.valueOf(to.toLocalTime());
//            Date fromDate = Date.valueOf(from.toLocalDate());
//            Time fromTime = Time.valueOf(from.toLocalTime());
//            query += " and (((not r.fromDate >= \'" + fromDate.toString() + "\') or r.fromTime >= \'" + fromTime.toString() +
//                    "\') or ((not r.toDate <= \'" + toDate.toString() + "\') or r.toTime <= + \'" + toTime.toString() + "\'))";
//        } else if (from != null) {
//            Date fromDate = Date.valueOf(from.toLocalDate());
//            Time fromTime = Time.valueOf(from.toLocalTime());
//            query += " and ((not r.fromDate >= \'" + fromDate.toString() + "\') or r.fromTime >= \'" + fromTime.toString() + "\')";
//        } else if (to != null) {
//            Date toDate = Date.valueOf(to.toLocalDate());
//            Time toTime = Time.valueOf(to.toLocalTime());
//            query += " and ((not r.fromDate <= \'" + toDate.toString() + "\') or r.fromTime <= \'" + toTime.toString() + "" +
//                    "\')";
//        }

        if (success) {
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                if (query.contains("?")) {
                    stmt.setString(1, location);
                }
                query = stmt.toString();
                ResultSet rs = stmt.executeQuery();
                assert (rs != null);
                while (rs.next()) {
                    VehicleStatus status = VehicleStatus.toStatus(rs.getString("status"));
                    VehicleTypeName vehicleTypeName = VehicleTypeName.toVehicleTypeName(rs.getString("vtname"));
                    Vehicle vehicle = new Vehicle(rs.getInt("vid"), rs.getString("vlicense"),
                            rs.getString("make"), rs.getString("model"),
                            rs.getString("year"), rs.getString("color"),
                            rs.getInt("odometer"), status, null, vehicleTypeName);
                    returnList.add(vehicle);
                }
                rs.close();
                stmt.close();

            } catch (SQLException e) {
                success = false;
                response = ERROR;
            }
        } else {
            response = "invalid location";
        }
        List<Vehicle> dQ = getRentedVehicles(type, location, from, to);
        if (returnList.size() == dQ.size()) {
            response = "no available vehicles";
        }
        returnList.removeAll(dQ);
        return new VehicleListResponse(query, success, response, returnList);
    }

    private List<Vehicle> getRentedVehicles(VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to) {
        boolean success = locationExists(location).getValue();
        List<Vehicle> returnList = new ArrayList<>();
        String query = "SELECT v.* \n" +
                "FROM public.vehicle v, public.rent r\n" +
                "WHERE v.vlicense = r.vlicense";

        if (type != null) {
            query += " and v.vtname = \'" + type.getName() + "\'";
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
            query += " and (((not r.fromDate >= \'" + fromDate.toString() + "\') or r.fromTime >= \'" + fromTime.toString() +
                    "\') or ((not r.toDate <= \'" + toDate.toString() + "\') or r.toTime <= + \'" + toTime.toString() + "\'))";
        } else if (from != null) {
            Date fromDate = Date.valueOf(from.toLocalDate());
            Time fromTime = Time.valueOf(from.toLocalTime());
            query += " and ((not r.fromDate >= \'" + fromDate.toString() + "\') or r.fromTime >= \'" + fromTime.toString() + "\')";
        } else if (to != null) {
            Date toDate = Date.valueOf(to.toLocalDate());
            Time toTime = Time.valueOf(to.toLocalTime());
            query += " and ((not r.fromDate <= \'" + toDate.toString() + "\') or r.fromTime <= \'" + toTime.toString() + "" +
                    "\')";
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
                    VehicleTypeName vehicleTypeName = VehicleTypeName.toVehicleTypeName(rs.getString("vtname"));
                    Vehicle vehicle = new Vehicle(rs.getInt("vid"), rs.getString("vlicense"),
                            rs.getString("make"), rs.getString("model"),
                            rs.getString("year"), rs.getString("color"),
                            rs.getInt("odometer"), status, null, vehicleTypeName);
                    returnList.add(vehicle);
                }
                rs.close();
                stmt.close();

            } catch (SQLException e) {
                //success = false;
            }
        }
        UI_CONTROLLER.logResponse(new VehicleListResponse(query, success, returnList.toString(), returnList));
        return returnList;
    }

    @Override
    public DatabaseResponse<String> generateDailyRentalReport() {
        return Reports.getDailyRentals();
    }

    @Override
    public DatabaseResponse<String> generateDailyBranchRentalReport(Branch branch) {
        return Reports.getDailyRentals(branch);
    }


//    @Override
//    public DatabaseResponse<String> generateDailyBranchRentalReport(String branch) {
//        return null;
//    }

    @Override
    public DatabaseResponse<String> generateDailyReturnReport() {
        return Reports.getDailyReturns();
    }

    @Override
    public DatabaseResponse<String> generateDailyBranchReturnReport(Branch branch) {
        return Reports.getDailyReturns(branch);
    }

//    @Override
//    public DatabaseResponse<String> generateDailyBranchReturnReport(String branch) {
//        return null;
//    }

    @Override
    public DatabaseResponse<Boolean> locationExists(String location) {
        boolean ret = false;
        boolean success = true;
        String response = SUCCESS;
        String query = "SELECT b.*\n" +
                "FROM public.branch b\n" +
                "WHERE b.location = ?";
        if (location == null || location.equals("")) {
            return new BooleanResponse(query, true, response, true);
        }
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, location);
            query = stmt.toString();
            ResultSet resultSet = stmt.executeQuery();
            ret = resultSet.next();
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            response = ERROR;
            success = false;
        }
        return new BooleanResponse(query, success, response, ret);
    }


    @Override
    public DatabaseResponse<Boolean> customerExists(Long phone) {
        String response = SUCCESS;
        boolean success = true;
        boolean ret = false;
        String query = "SELECT c.cellphone\n" +
                "FROM public.customer c\n" +
                "WHERE c.cellphone = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, phone);
            query = stmt.toString();
            ResultSet resultSet = stmt.executeQuery();
            ret = resultSet.next();
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                response = "already exists";
                success = true;
            } else {
                response = ERROR;
                success = false;
            }
        }
        return new BooleanResponse(query, success, response, ret);
    }

    @Override
    public DatabaseResponse<Boolean> branchExists(String location, String city) {
        boolean ret = false;
        boolean success = true;
        String response = SUCCESS;
        String query = "SELECT b.*\n" +
                "FROM public.branch b\n" +
                "WHERE b.location = ? and b.city = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, location);
            stmt.setString(2, city);
            query = stmt.toString();
            ResultSet resultSet = stmt.executeQuery();
            ret = resultSet.next();
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            //StackTrace();
            response = ERROR;
            success = false;
        }
        return new BooleanResponse(query, success, response, ret);
    }


    @Override
    public DatabaseResponse<Boolean> customerExists(String driversLicense) {
        String response = SUCCESS;
        boolean success = true;
        boolean ret = false;
        String query = "SELECT c.dlicense\n" +
                "FROM public.customer c\n" +
                "WHERE c.dlicense = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, driversLicense);
            query = stmt.toString();
            ResultSet resultSet = stmt.executeQuery();
            ret = resultSet.next();
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                response = "already exists";
                success = true;
            } else {
                response = ERROR;
                success = false;
            }
        }
        return new BooleanResponse(query, success, response, ret);
    }

    @Override
    public DatabaseResponse<Customer> getCustomer(String driversLicense) {
        String response = SUCCESS;
        Customer ret = null;
        boolean success;
        String query = "SELECT c.*\n" +
                "FROM public.customer c\n" +
                "WHERE c.dlicense = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, driversLicense);
            query = stmt.toString();
            ResultSet resultSet = stmt.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                ret = new Customer(resultSet.getLong("cellphone"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getString("dlicense"));
                counter++;
            }
            success = counter == 1;
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            success = false;
            response = ERROR;
        }
        return new CustomerResponse(query, success, response, ret);
    }

    private Customer getCustomer(Long phoneNumber) {
        boolean success = true;
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
                ret = new Customer(resultSet.getLong("cellphone"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getString("dlicense"));
                counter++;
            }
            assert (counter == 1);
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            success = false;
        }
        UI_CONTROLLER.logResponse(new CustomerResponse(query, success, String.valueOf(success), ret));
        return ret;
    }

    @Override
    public DatabaseResponse<?> addCustomer(String phone, String name, String address, String driversLicense) {
        String query = "insert into public.customer (cellphone, name, address, dlicense) values (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, Long.parseLong(phone));
            stmt.setString(2, name);
            stmt.setString(3, address);
            stmt.setString(4, driversLicense);
            query = stmt.toString();
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            return new StringResponse(query, false, ERROR, "");
        }

        return new StringResponse(query, true, SUCCESS, "");
    }

    private boolean createTimePeriod(LocalDateTime from, LocalDateTime to) {
        boolean success = false;
        Date fromDate = Date.valueOf(from.toLocalDate());
        Time fromTime = Time.valueOf(from.toLocalTime());
        Date toDate = Date.valueOf(to.toLocalDate());
        Time toTime = Time.valueOf(to.toLocalTime());
        String query = "insert into public.timeperiod (fromdate, fromtime, todate, totime) values (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDate(1, fromDate);
            stmt.setTime(2, fromTime);
            stmt.setDate(3, toDate);
            stmt.setTime(4, toTime);
            success = stmt.executeUpdate() > -1;
        } catch (SQLException e) {
            success = e.getSQLState().equals("23505");
        }
        UI_CONTROLLER.logResponse(new BooleanResponse(query, success, success ? SUCCESS : ERROR, success));
        return success;
    }

    @Override
    public DatabaseResponse<Reservation> reserveVehicle(String driversLicense, String phoneNumber, VehicleTypeName type,
                                                        String location, LocalDateTime from, LocalDateTime to) {
        String response;// = SUCCESS;
        boolean success = customerExists(driversLicense).getValue() && createTimePeriod(from, to);
        String query = "insert into public.reservation (vtname, cellphone, fromdate, fromtime, todate, totime) values (?,?,?,?,?,?)";
        if (success) {
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, type.getName());
                stmt.setLong(2, Long.parseLong(phoneNumber));
                stmt.setDate(3, Date.valueOf(from.toLocalDate()));
                stmt.setTime(4, Time.valueOf(from.toLocalTime()));
                stmt.setDate(5, Date.valueOf(to.toLocalDate()));
                stmt.setTime(6, Time.valueOf(to.toLocalTime()));
                query = stmt.toString();
                success = stmt.executeUpdate() > 0;
                stmt.close();
                response = "Your confno is : " +
                        getReservationByPhoneNumber(phoneNumber).getValue().getConfNo();
                return new ReservationResponse(query, success, response, getReservationByPhoneNumber(phoneNumber).getValue());
            } catch (SQLException e) {
                success = false;
                response = ERROR;
            }
        } else {
            response = "customer does not exist or time period does not exist";
        }
        return new ReservationResponse(query, success, response, null);
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
        UI_CONTROLLER.logResponse(new StringResponse(query, success, success ? SUCCESS : ERROR, confno));
        return confno;
    }

    @Override
    public DatabaseResponse<Reservation> getReservationByConfirmationNumber(String confirmationNumber) {
        String response = SUCCESS;
        Reservation res = null;
        String query = "SELECT  r.* \n" +
                "FROM public.reservation r \n" +
                "WHERE r.confno = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(confirmationNumber));
            query = stmt.toString();
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while (rs.next()) {
                Date fromDate = rs.getDate("fromDate");
                Time fromTime = rs.getTime("fromtime");
                Date toDate = rs.getDate("toDate");
                Time toTime = rs.getTime("toTime");
                LocalDateTime from = LocalDateTime.of(fromDate.toLocalDate(), fromTime.toLocalTime());
                LocalDateTime to = LocalDateTime.of(toDate.toLocalDate(), toTime.toLocalTime());
                res = new Reservation(rs.getInt("confno"),
                        VehicleTypeName.toVehicleTypeName(rs.getString("vtname")),
                        rs.getLong("cellphone"), from, to);
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            response = ERROR;
        }
        if (response.equals(ERROR)) {
            response = res == null ? "could not retrieve the reservation" : SUCCESS;
        }
        return new ReservationResponse(query, res != null, response, res);
    }

    @Override
    public DatabaseResponse<Reservation> getReservationByPhoneNumber(String phoneNumber) {
        String response = SUCCESS;
        Reservation res = null;
        String query = "SELECT  r.* \n" +
                "FROM public.reservation r \n" +
                "WHERE r.cellphone = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, Long.parseLong(phoneNumber));
            query = stmt.toString();
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while (rs.next()) {
                Date fromDate = rs.getDate("fromDate");
                Time fromTime = rs.getTime("fromtime");
                Date toDate = rs.getDate("toDate");
                Time toTime = rs.getTime("toTime");
                LocalDateTime from = LocalDateTime.of(fromDate.toLocalDate(), fromTime.toLocalTime());
                LocalDateTime to = LocalDateTime.of(toDate.toLocalDate(), toTime.toLocalTime());
                res = new Reservation(rs.getInt("confno"),
                        VehicleTypeName.toVehicleTypeName(rs.getString("vtname")),
                        rs.getLong("cellphone"), from, to);
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            response = ERROR;
        }
        if (response.equals(SUCCESS)) {
            response = res == null ? "could not retrieve the reservation" : SUCCESS;
        }
        return new ReservationResponse(query, res != null, response, res);
    }

    @Override
    public DatabaseResponse<Rental> rentVehicle(String driversLicense, String phone, String confirmationNumber,
                                                VehicleTypeName type, String location, LocalDateTime from, LocalDateTime to,
                                                String creditCardNumber, String expiryMonth, String expiryYear, String creditCardType) {
        String response = SUCCESS;
        boolean success = locationExists(location).getValue() && createTimePeriod(from, to);
        String query = "";
        List<Vehicle> vehicles = getVehicles(type, location, from, to).getValue();
        success = success && !vehicles.isEmpty();
        Rental rental = null;
        if (success) {
            Vehicle tobeRented = vehicles.get(0);
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
                LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(expiryYear), Integer.parseInt(expiryMonth), 1, 4, 5);
                stmt.setDate(9, Date.valueOf(ldt.toLocalDate()));
                stmt.setInt(10, Integer.parseInt(confirmationNumber));
                stmt.setLong(11, Long.parseLong(phone));
                query = stmt.toString();
                success = stmt.executeUpdate() > 0;
                rental = new Rental(getRID(phone, confirmationNumber, type, location, from, to, creditCardNumber,
                        expiryMonth, expiryYear, creditCardType, tobeRented), tobeRented, getCustomer(Long.parseLong(phone)), from, to,
                        tobeRented.getOdometer(), creditCardType, creditCardNumber, Date.valueOf(ldt.toLocalDate()),
                        getReservationByPhoneNumber(phone).getValue(), null);
                stmt.close();
                if (!updateVehicleStatus(tobeRented, VehicleStatus.RENTED)) {
                    success = false;
                    response = "vehicle status update failed";
                }
            } catch (SQLException e) {
                success = false;
                response = ERROR;
            }
        } else {
            response = "invalid location or no available vehicles";
        }

        return new RentalResponse(query, success, response, rental);
    }

    private boolean updateVehicleStatus(Vehicle vehicle, VehicleStatus newStatus) {
        boolean success = false;
        String query = "UPDATE public.vehicle\n" +
                "SET status = \'rented\'\n" +
                "WHERE vid = ? and vlicense = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, vehicle.getVid());
            stmt.setString(2, vehicle.getvLicense());
            stmt.executeUpdate();
            stmt.close();
            success = true;
        } catch (SQLException e) {
            //
        }
        UI_CONTROLLER.logResponse(new BooleanResponse(query, success, success ? SUCCESS : ERROR, success));
        return success;
    }

    private int getRID(String phone, String confirmationNumber, VehicleTypeName type,
                       String location, LocalDateTime from, LocalDateTime to, String creditCardNumber,
                       String expiryMonth, String expiryYear, String creditCardType, Vehicle vehicle) {
        boolean success = false;
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
            stmt.setInt(3, Integer.parseInt(confirmationNumber));
            stmt.setTime(4, Time.valueOf(to.toLocalTime()));
            stmt.setDate(5, Date.valueOf(to.toLocalDate()));
            stmt.setTime(6, Time.valueOf(from.toLocalTime()));
            stmt.setDate(7, Date.valueOf(from.toLocalDate()));
            stmt.setInt(8, vehicle.getOdometer());
            stmt.setString(9, creditCardType);
            stmt.setLong(10, Long.parseLong(creditCardNumber));
            LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(expiryYear), Integer.parseInt(expiryMonth), 1, 4, 5);
            stmt.setDate(11, Date.valueOf(ldt.toLocalDate()));
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while (rs.next()) {
                ret = rs.getInt("rid");
                counter++;
            }
            assert (counter == 1);
            rs.close();
            stmt.close();
            success = true;
        } catch (SQLException e) {
            //
        }
        UI_CONTROLLER.logResponse(new IntegerResponse(query, success, success ? SUCCESS : ERROR, ret));
        return ret;
    }

    @Override
    public DatabaseResponse<Rental> getRental(String id) {
        String response = SUCCESS;
        Rental rental = null;
        String query = "SELECT  r.* \n" +
                "FROM public.rent r \n" +
                "WHERE r.rid = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(id));
            query = stmt.toString();
            ResultSet rs = stmt.executeQuery();
            int counter = 0;
            while (rs.next()) {
                Date fromDate = rs.getDate("fromDate");
                Time fromTime = rs.getTime("fromtime");
                Date toDate = rs.getDate("toDate");
                Time toTime = rs.getTime("toTime");
                LocalDateTime from = LocalDateTime.of(fromDate.toLocalDate(), fromTime.toLocalTime());
                LocalDateTime to = LocalDateTime.of(toDate.toLocalDate(), toTime.toLocalTime());
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
            response = ERROR;
        }
        return new RentalResponse(query, rental != null, response, rental);

    }

    @Override
    public VehicleResponse getVehicle(String vlicense) {
        String response = SUCCESS;
        boolean success = true;
        String query = "SELECT v.* \n" +
                "FROM public.vehicle v \n" +
                "WHERE v.vlicense = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, vlicense);
            query = stmt.toString();
            ResultSet rs = stmt.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                VehicleStatus status = VehicleStatus.toStatus(rs.getString("status"));
                VehicleTypeName vehicleTypeName = VehicleTypeName.toVehicleTypeName(rs.getString("vtname"));
                Vehicle returnV = new Vehicle(rs.getInt("vid"), rs.getString("vlicense"),
                        rs.getString("make"), rs.getString("model"),
                        rs.getString("year"), rs.getString("color"),
                        rs.getInt("odometer"), status, null, vehicleTypeName);
                return new VehicleResponse(query, true, response, returnV);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            success = false;
            response = ERROR;
        }
        if (success) {
            response = "did not find vehicle";
        }
        return new VehicleResponse(query, success, response, null);
    }

    @Override
    public DatabaseResponse<String> returnVehicle(String rentalID, String location, LocalDateTime time, String odometer,
                                                  boolean gasTankIsFull, int cost) {
        boolean success = locationExists(location).getValue();
        String response = SUCCESS;
        Vehicle v = getVehicle(getRental(rentalID).getValue().getVehicle().getvLicense()).getValue();

        String query = "insert into public.return (rid, date, time, odometer, fulltank, value) values (?, ?, ?, ?, ?, ?)";
        if (success) {
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(rentalID));
                stmt.setDate(2, Date.valueOf(time.toLocalDate()));
                stmt.setTime(3, Time.valueOf(time.toLocalTime()));
                stmt.setInt(4, Integer.parseInt(odometer));
                stmt.setBoolean(5, gasTankIsFull);
                stmt.setInt(6, cost);
                query = stmt.toString();
                success = stmt.executeUpdate() > 0;
                stmt.close();
            } catch (SQLException e) {
                if (!e.getSQLState().equals("23505")) {
                    success = false;
                    response = ERROR;
                } else {
                    success = false;
                    response = "vehicle already returned";
                }
            }
        } else {
            response = "invalid location";
        }
        // update VEHICLE STATUS
        if (updateVehicleStatus(v, v.getStatus())) {
            return new StringResponse(query, success, response, response);
        } else {
            return new StringResponse(query, true, "vehicle status update failed", "");
        }
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
            query = stmt.toString();
            ResultSet ret = stmt.executeQuery();
            List<List<String>> str = resultSetString(ret);
            ret.close();
            return new StringResponse(query, true, str.toString(), str.toString());
        } catch (SQLException e) {
            return new StringResponse(query, false, ERROR, null);
        }
    }

    private List<List<String>> resultSetString(ResultSet rs) {
        List<List<String>> list = new ArrayList<>();
        try {
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                int counter = cols;
                List<String> arr = new ArrayList<>();
                while (counter--> 1) {
                    arr.add(rs.getString(counter));
                }
                list.add(arr);
            }
        } catch (SQLException e) {
            System.out.println("issue");
            //return list;
        }
        return list;
    }

    private IntegerResponse getAllRates(VehicleTypeName type, String rateVar) {
        //String response = SUCCESS;
        //boolean success = false;
        String query = "SELECT vt." + rateVar + "\n" +
                "FROM public.vehicletype vt \n" +
                "WHERE vt.vtname = ?\n" +
                "LIMIT 1";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, type.getName());
            query = stmt.toString();
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int ret = rs.getInt(rateVar);
            rs.close();
            stmt.close();
            UI_CONTROLLER.logResponse(new IntegerResponse(query, true, SUCCESS, ret));
            return new IntegerResponse(query, true, SUCCESS, ret);
        } catch (SQLException e) {
            UI_CONTROLLER.logResponse(new IntegerResponse(query, false, ERROR, -1));
            return new IntegerResponse(query, false, ERROR, -1);
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

//    private class ReservationResponse implements DatabaseResponse<Reservation> {
//
//        private String query;
//        private boolean success;
//        private String response;
//        private Reservation value;
//
//        ReservationResponse(String query, boolean success, String response, Reservation value) {
//            this.query = query;
//            this.success = success;
//            this.response = response;
//            this.value = value;
//        }
//
//        @Override
//        public String getQuery() {
//            return query;
//        }
//
//        @Override
//        public boolean isSuccess() {
//            return success;
//        }
//
//        @Override
//        public String getResponse() {
//            return response;
//        }
//
//        @Override
//        public Reservation getValue() {
//            return value;
//        }
//    }
}
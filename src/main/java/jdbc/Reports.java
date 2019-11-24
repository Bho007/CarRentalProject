package jdbc;

import model.*;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static jdbc.Query.*;

public class Reports {
    private static final Date TODAY = new Date(Calendar.getInstance().getTimeInMillis());
    private static Connection conn;
    private static PreparedStatement stmt;

    public static List<Return> getDailyReturns() {
        return getDailyReturns(null);
    }

    public static List<Return> getDailyReturns(Branch b) {
        List<Return> todaysReturns = new ArrayList<>();
        String query = "SELECT ret.*, r.vid FROM Return ret INNER JOIN Rent r ON ret.rid = r.rid " +
                " WHERE date = ? " + ((b == null) ? "" : " AND r.vid IN (SELECT vid FROM Vehicle WHERE veh.location = ? AND veh.city = ?) ");
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement(query);

            stmt.setDate(1, TODAY);
            if (b != null) {
                stmt.setString(2, b.getLocation());
                stmt.setString(3, b.getCity());
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                int rid = rs.getInt("rid");
                Date date = rs.getDate("date");
                Time time = rs.getTime("time");
                int odometer = rs.getInt("odometer");
                boolean fullTank = rs.getBoolean("fulltank");
                int value = rs.getInt("value");

                Return r = new Return(rid, date, time, odometer, fullTank, value);

                todaysReturns.add(r);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todaysReturns;
    }

    public static List<Rental> getDailyRentals() {
        return getDailyRentals(null);
    }

    public static List<Rental> getDailyRentals(Branch b) {
        List<Rental> todaysRentals = new ArrayList<>();
        String query = "SELECT r.*, c.* FROM Rent r INNER JOIN Customer c ON r.cellphone = c.cellphone " +
                "WHERE r.fromdate = ? " + ((b == null) ? "" : " AND r.vid IN (SELECT vid FROM vehicle veh WHERE " +
                "veh.location = ? AND veh.city = ?" );
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement(query);

            stmt.setDate(1, TODAY);

            if (b != null) {
                stmt.setString(2, b.getLocation());
                stmt.setString(3, b.getCity());
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                int rid = rs.getInt("rid");
                int vid = rs.getInt("vid");
                Customer c = new Customer(rs.getLong("cellphone"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("dlicense"));
                TimePeriod t = new TimePeriod(rs.getDate("fromdate"), rs.getTime("fromtime"),
                        rs.getDate("todate"), rs.getTime("totime"));
                int odometer = rs.getInt("odometer");
                String cardname = rs.getString("cardname");
                String cardNo = rs.getString("cardno");
                Date expDate = rs.getDate("expdate");

                Rental r = new Rental(rid, vid, c, t, odometer, cardname, cardNo, expDate);

                int confNo = rs.getInt("confno");

                if (!rs.wasNull()) {
                    String confQuery = "SELECT * FROM reservation r WHERE confno = ?";
                    PreparedStatement confStatement = conn.prepareStatement(confQuery);

                    confStatement.setInt(1, confNo);

                    ResultSet reservationRs = confStatement.executeQuery();

                    if (reservationRs.next()) {
                        reservationRs.first();

                        String vtName = reservationRs.getString("vtname");
                        VehicleTypeName vt = VehicleTypeName.valueOf(vtName);
                        Date fromDate = reservationRs.getDate("fromdate");
                        Time fromTime = reservationRs.getTime("fromtime");

                        Date toDate = reservationRs.getDate("todate");
                        Time toTime = reservationRs.getTime("totime");

                        Reservation reservation = new Reservation(confNo, vt, c, fromDate, fromTime, toDate, toTime);

                        String eTypeQuery = "SELECT * FROM EType WHERE etname IN (SELECT eid FROM reserve_includes" +
                                " WHERE rid = ?";

                        PreparedStatement eTypeStatement = conn.prepareStatement(eTypeQuery);

                        eTypeStatement.setInt(1, confNo);

                        ResultSet eTypeRs = eTypeStatement.executeQuery();

                        while (eTypeRs.next()) {
                            String etname = eTypeRs.getString("etname");
                            int drate = eTypeRs.getInt("drate");
                            int hrate = eTypeRs.getInt("hrate");

                            EquipType et = new EquipType(etname, drate, hrate);

                            reservation.addETypeReserved(et);
                        }

                        r.addReservation(reservation);
                    }
                }

                String equipmentQuery = "SELECT * FROM rent_includes r INNER JOIN equipment e ON r.eid = e.eid " +
                        "INNER JOIN equiptype t ON t.etname = e.etname " +
                        "WHERE r.rid = ?";

                PreparedStatement eqStmt = conn.prepareStatement(equipmentQuery);
                eqStmt.setInt(1, rid);
                ResultSet eqRs = eqStmt.executeQuery();

                while (eqRs.next()) {
                    int eid = eqRs.getInt("eid");

                    String etName = eqRs.getString("etname");
                    int drate = eqRs.getInt("drate");
                    int hrate = eqRs.getInt("hrate");

                    EquipType et = new EquipType(etName, drate, hrate);

                    Equipment e = new Equipment(eid, EquipmentStatus.RENTED, et);

                    r.addEquipment(e);

                }

                todaysRentals.add(r);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todaysRentals;
    }
}

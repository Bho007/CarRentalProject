package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Rental {
    private int rid;
    private Vehicle vehicle;
    private Customer customer;
    private LocalDateTime from;
    private LocalDateTime to;
    private int odometer;
    private String cardName;
    private String cardNo;
    private Date cardExpiryDate;

    private Reservation reservation;
    private Return rentalReturn;
    private List<Equipment> equipment;

    public Rental(int rid, Vehicle vehicle, Customer customer, LocalDateTime from, LocalDateTime to, int odometer, String cardName, String cardNo, Date cardExpiryDate, Reservation reservation, List<Equipment> equipment) {
        this.rid = rid;
        this.vehicle = vehicle;
        this.customer = customer;
        this.from = from;
        this.to = to;
        this.odometer = odometer;
        this.cardName = cardName;
        this.cardNo = cardNo;
        this.cardExpiryDate = cardExpiryDate;
        this.equipment = equipment;
        this.reservation = reservation;
    }

    public void addReservation(Reservation r) {
        this.reservation = r;
    }

    public void addReturn(Return r) {
        this.rentalReturn = r;
    }

    public int getRid() {
        return rid;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {return to;}

    public int getOdometer() {
        return odometer;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public Date getCardExpiryDate() {
        return cardExpiryDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }
}

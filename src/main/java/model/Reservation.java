package model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int confNo;
    private VehicleTypeName vtName;
    private Customer customer;
    private Date fromDate;
    private Time fromTime;
    private Date toDate;
    private Time toTime;
    private List<EquipType> equipmentTypesReserved;

    public Date getFromDate() {
        return fromDate;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public Date getToDate() {
        return toDate;
    }

    public Time getToTime() {
        return toTime;
    }

    public int getConfNo() {
        return confNo;
    }

    public List<EquipType> getEquipmentTypesReserved() {
        return equipmentTypesReserved;
    }

    private void addEquipTypes(EquipType et) {
        this.equipmentTypesReserved.add(et);
    }

    public VehicleTypeName getVtName() {
        return vtName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addETypeReserved(EquipType et) {
        equipmentTypesReserved.add(et);
    }

    public Reservation(int confNo, VehicleTypeName vtName, Customer customer, Date fromDate, Time fromTime, Date toDate, Time toTime) {
        this.confNo = confNo;
        this.vtName = vtName;
        this.customer = customer;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.equipmentTypesReserved = new ArrayList<>();
    }
}

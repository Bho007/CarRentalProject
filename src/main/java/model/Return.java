package model;

import java.sql.Date;
import java.sql.Time;

public class Return {
    private int rid;
    private Date date;
    private Time time;
    private int odometer;
    private boolean fullTank;
    private int value;

    public int getRid() {
        return rid;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getOdometer() {
        return odometer;
    }

    public boolean isFullTank() {
        return fullTank;
    }

    public int getValue() {
        return value;
    }

    public Return(int rid, Date date, Time time, int odometer, boolean fullTank, int value) {
        this.rid = rid;
        this.date = date;
        this.time = time;
        this.odometer = odometer;
        this.fullTank = fullTank;
        this.value = value;
    }
}

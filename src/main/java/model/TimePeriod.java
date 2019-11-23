package model;

import java.sql.Time;
import java.sql.Date;

public class TimePeriod {
    private Date fromDate;
    private Time fromTime;
    private Date toDate;
    private Time toTime;

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

    public TimePeriod(Date fromDate, Time fromTime, Date toDate, Time toTime) {
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }
}

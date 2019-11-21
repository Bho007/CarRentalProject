CREATE TABLE Reservation (
    confNo INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (start with 1 increment by 1),
    vtname TEXT REFERENCES VehicleType(vtname) ON DELETE CASCADE NOT NULL,
    cellphone BIGINT REFERENCES Customer(cellphone) ON DELETE CASCADE NOT NULL,
    fromDate DATE,
    fromTime TIME,
    toDate DATE,
    toTime TIME,

    CONSTRAINT fk_timeperiod
        FOREIGN KEY (fromDate, fromTime, toDate, toTime)
        REFERENCES timeperiod
)
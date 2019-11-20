CREATE TABLE TimePeriod (
    fromDate DATE,
    fromTime TIME,
    toDate DATE CHECK (toDate >= fromDate),
    toTime TIME,

    PRIMARY KEY (fromDate, fromTime, toDate, toTime)
)
CREATE TABLE Rent (
    rid INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (start with 1 increment by 1),
    vid INT REFERENCES vehicle(vid),
    fromDate DATE NOT NULL,
    fromTime TIME NOT NULL,
    toDate DATE NOT NULL,
    toTime TIME NOT NULL,
    odometer INT,
    cardName TEXT,
    cardNo BIGINT,
    ExpDate DATE,
    confNo INT REFERENCES Reservation(confNo)
)
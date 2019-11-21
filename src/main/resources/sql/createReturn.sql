CREATE TABLE Return (
    rid INT PRIMARY KEY REFERENCES Rent(rid) ON DELETE CASCADE,
    date DATE,
    time TIME,
    odometer INT,
    fulltank BOOL,
    value INT
)
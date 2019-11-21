CREATE TABLE Reserve_Includes (
    confNo INT REFERENCES Reservation(confNo) ON DELETE CASCADE NOT NULL ,
    etName TEXT REFERENCES EquipType(etname) ON DELETE CASCADE NOT NULL,

    PRIMARY KEY (confNo, etName)
)
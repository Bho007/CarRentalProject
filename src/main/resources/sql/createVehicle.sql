create type vehicle_status as ENUM ('for_rent', 'for_sale');

create table vehicle (
    vid INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (start with 1 increment by 1),
    vlicense TEXT,
    make TEXT,
    model TEXT,
    year TEXT,
    color TEXT,
    odometer INT,
    status vehicle_status,
    vtname TEXT REFERENCES vehicleType(vtname) ON DELETE CASCADE NOT NULL,
    location TEXT,
    city TEXT,

    CONSTRAINT fk_vehicle
        FOREIGN KEY (location, city)
        REFERENCES branch
)
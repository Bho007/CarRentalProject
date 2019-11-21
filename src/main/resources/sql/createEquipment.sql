CREATE TYPE equipment_status AS ENUM ('available', 'rented', 'not_available');

CREATE TABLE equipment (
    eid INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (start with 1 increment by 1),
    etname TEXT,
    status equipment_status,
    location TEXT,
    city TEXT,

    CONSTRAINT fk_equipment
        FOREIGN KEY (location, city)
        REFERENCES branch
)
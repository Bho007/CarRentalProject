CREATE TABLE EforV (
    etname TEXT REFERENCES EquipType(etname),
    vtname TEXT REFERENCES VehicleType(vtname)
)
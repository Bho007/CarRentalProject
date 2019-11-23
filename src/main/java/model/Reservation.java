package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int confNo;
    private VehicleType vtName;
    private int cellPhone;
    private LocalDateTime from;
    private LocalDateTime to;
    private List<EquipType> equipmentTypesReserved;

    public int getConfNo() {
        return confNo;
    }

    public List<EquipType> getEquipmentTypesReserved() {
        return equipmentTypesReserved;
    }

    private void addEquipTypes(EquipType et) {
        this.equipmentTypesReserved.add(et);
    }

    public VehicleType getVtName() {
        return vtName;
    }

    public int getCellPhone() {
        return cellPhone;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {return to;}

    public Reservation(int confNo, VehicleType vtName, int cellPhone, LocalDateTime from, LocalDateTime to) {
        this.confNo = confNo;
        this.vtName = vtName;
        this.cellPhone = cellPhone;
        this.from = from;
        this.to = to;
        this.equipmentTypesReserved = new ArrayList<>();
    }
}

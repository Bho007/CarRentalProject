package model;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int confNo;
    private VehicleType vtName;
    private int cellPhone;
    private TimePeriod timePeriod;
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

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public Reservation(int confNo, VehicleType vtName, int cellPhone, TimePeriod timePeriod) {
        this.confNo = confNo;
        this.vtName = vtName;
        this.cellPhone = cellPhone;
        this.timePeriod = timePeriod;
        this.equipmentTypesReserved = new ArrayList<>();
    }
}

package model;

public class Equipment {
    private int eid;
    private EquipmentStatus status;
    private EquipType etName;

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    public void setEtName(EquipType etName) {
        this.etName = etName;
    }

    public int getEid() {
        return eid;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public EquipType getEtName() {
        return etName;
    }

    public Equipment(int eid, EquipmentStatus status, EquipType etName) {
        this.eid = eid;
        this.status = status;
        this.etName = etName;
    }
}

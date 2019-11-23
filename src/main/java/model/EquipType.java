package model;

public class EquipType {
    String etname;
    int drate;
    int hrate;

    public void setDrate(int drate) {
        this.drate = drate;
    }

    public String getEtname() {
        return etname;
    }

    public int getDrate() {
        return drate;
    }

    public int getHrate() {
        return hrate;
    }

    public void setHrate(int hrate) {
        this.hrate = hrate;
    }

    public EquipType(String etname, int drate, int hrate) {
        this.etname = etname;
        this.drate = drate;
        this.hrate = hrate;
    }
}

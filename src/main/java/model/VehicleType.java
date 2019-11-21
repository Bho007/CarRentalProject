package model;

import java.util.ArrayList;
import java.util.List;

public class VehicleType {
    private String vtname;
    private String features;
    private int wrate;
    private int drate;
    private int hrate;
    private int wirate;
    private int dirate;
    private int hirate;
    private int krate;
    private List<EquipType> equipment;

    public String getVtname() {
        return vtname;
    }

    public String getFeatures() {
        return features;
    }

    public int getWrate() {
        return wrate;
    }

    public int getDrate() {
        return drate;
    }

    public int getHrate() {
        return hrate;
    }

    public int getWirate() {
        return wirate;
    }

    public int getDirate() {
        return dirate;
    }

    public int getHirate() {
        return hirate;
    }

    public int getKrate() {
        return krate;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public void setWrate(int wrate) {
        this.wrate = wrate;
    }

    public void setDrate(int drate) {
        this.drate = drate;
    }

    public void setHrate(int hrate) {
        this.hrate = hrate;
    }

    public void setWirate(int wirate) {
        this.wirate = wirate;
    }

    public void setDirate(int dirate) {
        this.dirate = dirate;
    }

    public void setHirate(int hirate) {
        this.hirate = hirate;
    }

    public void setKrate(int krate) {
        this.krate = krate;
    }

    public VehicleType(String vtname, String features, int wrate, int drate, int hrate, int wirate, int dirate, int hirate, int krate) {
        this.vtname = vtname;
        this.features = features;
        this.wrate = wrate;
        this.drate = drate;
        this.hrate = hrate;
        this.wirate = wirate;
        this.dirate = dirate;
        this.hirate = hirate;
        this.krate = krate;
        this.equipment = new ArrayList<>();
    }
}

package model;

public class Vehicle
{
    public Vehicle(int vid, String vLicense, String make, String model, String year, String color, int odometer, VehicleStatus status, VehicleType vtName) {
        this.vid = vid;
        this.vLicense = vLicense;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.odometer = odometer;
        this.status = status;
        this.vtName = vtName;
    }

    private int vid;

    public int getVid() {
        return vid;
    }

    public String getvLicense() {
        return vLicense;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public VehicleType getVtName() {
        return vtName;
    }

    private String vLicense;
    private String make;
    private String model;
    private String year;
    private String color;
    private int odometer;
    private VehicleStatus status;
    private VehicleType vtName;
}

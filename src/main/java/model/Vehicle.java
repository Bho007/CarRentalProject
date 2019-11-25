package model;

public class Vehicle {
    private int vid;
    private String vLicense;
    private String make;
    private String model;
    private String year;
    private String color;
    private int odometer;
    private VehicleStatus status;
    private VehicleType vtName;
    private VehicleTypeName vehicleTypeName;
    
    public Vehicle(int vid, String vLicense, String make, String model, String year, String color, int odometer, VehicleStatus status, VehicleType vtName, VehicleTypeName vehicleTypeName) {
        this.vid = vid;
        this.vLicense = vLicense;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.odometer = odometer;
        this.status = status;
        this.vtName = vtName;
        this.vehicleTypeName = vehicleTypeName;
    }
    
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

    @Override
    public String toString() {
        return "Vehicle ID: " + vid +
                ", License Plate #: " + vLicense +
                ", Make: " + make +
                ", Model: " + model +
                ", Year: " + year  +
                ", Color: " + color +
                ", Odometer: " + odometer +
                ", Status: " + (status.equals(VehicleStatus.FOR_RENT) ? "For Rent" : status.equals(VehicleStatus.RENTED) ? "Rented" : status.equals(VehicleStatus.MAINTENANCE) ? "Under Maintenance" : "For Sale") +
                ", Vehicle Type: " + vehicleTypeName;
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
    
    public VehicleTypeName getVehicleTypeName() {
        return vehicleTypeName;
    }
}

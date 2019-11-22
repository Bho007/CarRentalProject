package model;

public enum VehicleStatus {
    FOR_RENT,
    FOR_SALE,
    MAINTENANCE,
    RENTED;

    public static VehicleStatus toStatus(String input) {
        switch(input.toUpperCase()) {
            case "FOR_RENT":
                return FOR_RENT;
            case "FOR_SALE":
                return FOR_SALE;
            case "MAINTENANCE":
                return MAINTENANCE;
            case "RENTED":
                return RENTED;
            default:
                return null;
        }
    }
}

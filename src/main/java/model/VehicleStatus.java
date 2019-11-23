package model;

public enum VehicleStatus {
    FOR_RENT,
    FOR_SALE,
    MAINTENANCE,
    RENTED;

    public static VehicleStatus toStatus(String input) {
        switch (input.toLowerCase()) {
            case "for_rent":
                return FOR_RENT;
            case "for_sale":
                return FOR_SALE;
            case "maintenance":
                return MAINTENANCE;
            case "rented":
                return RENTED;
            default:
                return null;
        }
    }
}

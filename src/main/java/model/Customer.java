package model;

public class Customer {
    private long cellPhone;
    private String name;
    private String address;
    private String dlicense;

    public Customer(long cellPhone, String name, String address, String dlicense) {
        this.cellPhone = cellPhone;
        this.name = name;
        this.address = address;
        this.dlicense = dlicense;
    }

    public long getCellPhone() {
        return cellPhone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDlicense() {
        return dlicense;
    }


}

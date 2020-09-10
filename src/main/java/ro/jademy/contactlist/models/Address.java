package ro.jademy.contactlist.models;

public class Address {

    private String streetName;
    private String streetNo;
    private int doorNo;
    private int floorNo;
    private String city;
    private String country;

    public Address(String streetName, String streetNo, int doorNo, int floorNo, String city, String country) {
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.doorNo = doorNo;
        this.floorNo = floorNo;
        this.city = city;
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return  streetName + "/" + streetNo + "/" + doorNo + "/" + floorNo + "/" + city + "/" +  country;
    }
}

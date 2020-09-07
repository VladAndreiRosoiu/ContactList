package ro.jademy.contactlist.models;

public class PhoneNumber {

    private String phoneNumber;     // 747123456
    private String countryCode;     // +40 - RO ; +44 - GB

    public PhoneNumber(String phoneNumber) {  // constructor with default countryCode
        this.phoneNumber = phoneNumber;       // for GB contacts
        this.countryCode = "+44";
    }

    public PhoneNumber(String countryCode, String phoneNumber) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

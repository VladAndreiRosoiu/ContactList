package ro.jademy.contactlist.models;

import java.util.Objects;

public class PhoneNumber {

    private String phoneNumber;     // 747123456
    private String countryCode;     // +40 - RO

    public PhoneNumber(String phoneNumber) {  // constructor with default countryCode
        this.phoneNumber = phoneNumber;       // for Romanian contacts
        this.countryCode = "+44";
    }

    public PhoneNumber(String phoneNumber, String countryCode) {
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, countryCode);
    }
}

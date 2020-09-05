package ro.jademy.contactlist.models;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class PhoneNumber {

    private String phoneNumber;     // 747123456
    private String countryCode;     // +40 - RO

    public PhoneNumber(String phoneNumber) {  // constructor with default countryCode
        this.countryCode = "+44";
        this.phoneNumber = phoneNumber;       // for Romanian contacts
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

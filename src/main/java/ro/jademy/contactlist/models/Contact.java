package ro.jademy.contactlist.models;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class Contact implements Comparable<Contact> {

    private int id;                                            // for internal operations only (not being displayed)
    //private String title;                                    // ex: Mr., Mrs., Ms., Miss, Prof., Dr., Eng.;
    private String firstName;
    private String lastName;
    private String email;
    private Company company;
    private PhoneNumber phoneNumber;
    private Group group;
    private Address address;
    private Date birthDate;

    public Contact(int id,  String firstName, String lastName, String email, Company company,
                   PhoneNumber phoneNumber, Group group, Address address, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.address = address;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  /*  public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return  StringUtils.substring(firstName,0,1)+
                StringUtils.center(firstName, 15, " ") +
                StringUtils.center(lastName, 15, " ") +
                StringUtils.center(phoneNumber.getCountryCode(), 1, " ") +
                StringUtils.center(String.valueOf(phoneNumber.getPhoneNumber()), 15, " ");
    }


    @Override
    public int compareTo(Contact otherContact) {
       return this.firstName.compareTo(otherContact.firstName);
    }
}

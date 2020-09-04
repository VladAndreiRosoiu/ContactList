package ro.jademy.contactlist.models;

public class Company {

    private String name;
    private String jobTitle;
    private Address address;

    public Company(String name) {   // only for the cases when the user
        this.name = name;           // doesn't want to specify an address
    }

    public Company(String name, String jobTitle) { // only for cases
        this.name = name;                          // when the user doesn't want to specify an address
        this.jobTitle = jobTitle;
    }

    public Company(String name, String jobTitle, Address address) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

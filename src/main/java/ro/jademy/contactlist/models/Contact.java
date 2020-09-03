package ro.jademy.contactlist.models;

public class Contact {

    private String firstName;
    private String lastName;
    private String email;
    private Company company;
    //private Group contactGroup;
    private int id;

//    public Contact() {
//    }

    public Contact(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id=id;
        //this.contactGroup = Group.MY_CONTACTS;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

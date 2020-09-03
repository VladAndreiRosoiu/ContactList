package ro.jademy.contactlist.models;

import java.util.Map;

public class PhoneBook {

    private Map<PhoneNumber, Contact> phoneBookMap;

    public PhoneBook(Map<PhoneNumber,Contact> phoneBookMap){
        this.phoneBookMap=phoneBookMap;
    }


}

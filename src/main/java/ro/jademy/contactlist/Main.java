package ro.jademy.contactlist;

import ro.jademy.contactlist.models.Contact;
import ro.jademy.contactlist.models.PhoneBook;
import ro.jademy.contactlist.services.IOService;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        //IOService.generateContacts();
        try {
            Set<Contact> contacts = new TreeSet<>(IOService.readFile());
            PhoneBook phoneBook = new PhoneBook(contacts);
            phoneBook.initiatePhoneBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



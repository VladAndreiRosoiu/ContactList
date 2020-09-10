package ro.jademy.contactlist;

import ro.jademy.contactlist.models.Contact;
import ro.jademy.contactlist.models.PhoneBook;
import ro.jademy.contactlist.services.IOService;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    private final static String CONTACTS_FILE = "contacts.csv";

    public static void main(String[] args) {

        try {
            Set<Contact> contacts = new TreeSet<>(IOService.readFile());
            PhoneBook phoneBook = new PhoneBook(contacts, CONTACTS_FILE);
            phoneBook.initiatePhoneBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



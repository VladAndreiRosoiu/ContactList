package ro.jademy.contactlist;

import ro.jademy.contactlist.models.Contact;
import ro.jademy.contactlist.models.PhoneBook;
import ro.jademy.contactlist.services.IOService;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {


    private final static File contactsFile=new File("contacts.csv");

    public static void main(String[] args) {

        try {
            IOService ioService=new IOService();
            Set<Contact> contacts = new TreeSet<>(ioService.readFile(contactsFile));
            PhoneBook phoneBook = new PhoneBook(contacts, contactsFile);
            phoneBook.initiatePhoneBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



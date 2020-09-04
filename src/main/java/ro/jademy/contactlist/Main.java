package ro.jademy.contactlist;

import ro.jademy.contactlist.data.DataProvider;
import ro.jademy.contactlist.models.Contact;

public class Main {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("  FIRST NAME      LAST NAME      PHONE NUMBER");
        for (Contact contact: DataProvider.contacts()) {
            System.out.println("_________________________________________________");
            System.out.println(contact);
        }
    }
}

package ro.jademy.contactlist;

import ro.jademy.contactlist.data.DataProvider;
import ro.jademy.contactlist.models.Contact;

public class Main {

    public static void main(String[] args) {

        for (Contact contact: DataProvider.contacts()) {
            System.out.println("_________________________________________________");
            System.out.println();
            System.out.println(contact);
        }
    }
}

package ro.jademy.contactlist;

import ro.jademy.contactlist.data.DataProvider;
import ro.jademy.contactlist.models.PhoneBook;

public class Main {

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook(DataProvider.contacts());
        phoneBook.initiatePhoneBook();

        }
    }


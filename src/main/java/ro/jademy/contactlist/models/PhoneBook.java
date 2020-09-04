package ro.jademy.contactlist.models;


import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PhoneBook {
    //TODO
    // Phone Book MAIN MENU
    // 1. Display contacts (firstName, lastName, phoneNumber)
    // 2. Select contact (By index (A-Z) -> create a subset of first letter of the contact, and after that by firstName)
    // Contact Menu
    // 1. Edit contact         -----> sub menu To edit all attributes (Return to Contact Menu)
    // 2. Remove contact
    // 3. Add to black list
    // 4. Add to Favorites
    // 5. Return to Phone Book
    // 3. Search by
    // 1. firstName
    // 2. lastName
    // 3. phoneNumber
    // 4. Return to Phone Book
    // 4. Add new Contact
    // 5. Exit App

    private Set<Contact> contacts;
    private Scanner sc = new Scanner(System.in);
    private Contact searchedContact;

    public PhoneBook(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void openPhoneBook() {
        do {
            showMenu();
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    displayAllContacts();
                    break;
                case 2:
                    //select contact
                    //this method should first ask for a letter, the first letter of first name then return a set bases on that letter
                    //after, to select a contact the user should input the first name of that contact
                    //if there are more than one firstName that matches input, user should be prompted to enter the lastname of the contact
                    Set<Contact> tempContactSet= getContactsSubGroupBasedOnFirstLetter();
                    searchContactByFirstName(tempContactSet);
                    displaySearchedContactInfo();
                    editContact();
                    break;
                case 3:
                    //search contact
                    break;
                case 4:
                    //add new contact
                    break;
                case 5:
                    //exit app
                    System.exit(0);
            }

        } while (true);
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("1 - Display all contacts");
        System.out.println("2 - Select contact");
        System.out.println("3 - Search contact");
        System.out.println("4 - Add new contact");
        System.out.println("5 - Exit phonebook");
        System.out.println("Please enter option:");
    }

    private void showContactSubMenu() {
        System.out.println("1 - Edit contact");
        System.out.println("2 - Remove contact");
        System.out.println("3 - Add contact to blacklist");
        System.out.println("4 - Add contact to Favorites");
        System.out.println("5 - Return");
        System.out.println("Please enter option:");
    }

    private void showEditSubMenu() {
        //TODO sub menu To edit all attributes (Return to Contact Menu)
    }

    private void showSearchMenu() {
        System.out.println("1 - Search contact by first name");
        System.out.println("1 - Search contact by last name");
        System.out.println("1 - Search contact by phone number");
        System.out.println("1 - Return");
        System.out.println("Please enter option:");
    }

    private void displayAllContacts(){
        System.out.println();
        System.out.println("  FIRST NAME      LAST NAME      PHONE NUMBER");
        for (Contact contact: contacts) {
            System.out.println("_________________________________________________");
            System.out.println(contact);
        }
    }

    private Set<Contact> getContactsSubGroupBasedOnFirstLetter(){
        System.out.println("Please filter contact list by entering first letter of the first name");
        String input=sc.next();
        Set<Contact> subGroupContactSet=new TreeSet<>();
        for(Contact contact:contacts){
            if (contact.getFirstName().substring(0,1).equals(input.toUpperCase())){
                subGroupContactSet.add(contact);
            }
        }
        for (Contact contact:subGroupContactSet){
            System.out.println(contact);
        }
        return subGroupContactSet;
    }

    private void searchContactByFirstName(Set<Contact> tempContactSet){
        System.out.println("Please enter first name:");
        String input=sc.next();
        for (Contact contact: tempContactSet){
            if (contact.getFirstName().toLowerCase().equals(input.toLowerCase())){
                searchedContact=contact;
            }
        }

    }

    private void displaySearchedContactInfo(){
        System.out.println("Contact "+searchedContact.getFirstName() + searchedContact.getLastName());
        System.out.println("Phone number : " + searchedContact.getPhoneNumber().getCountryCode() + searchedContact.getPhoneNumber().getPhoneNumber());
        System.out.println("Company : " + searchedContact.getCompany().getName());
        System.out.println("Email : " + searchedContact.getEmail());
        System.out.println("Address : City " + searchedContact.getAddress().getCity());
        System.out.println("Group : "+ searchedContact.getGroup());
    }

    private void editContact(){
        System.out.println("Started editing contact");
    }
}

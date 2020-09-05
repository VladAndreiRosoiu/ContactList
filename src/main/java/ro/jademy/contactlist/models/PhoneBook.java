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
                    Set<Contact> tempContactSet = getContactsSubGroupBasedOnFirstLetter();
                    searchContactByFirstName(tempContactSet);
                    displaySearchedContactInfo();
                    showContactSubMenu();
                    option = sc.nextInt();
                    doContactSubMenu(option);
                    break;
                case 3:
                    //search contact
                    showSearchMenu();
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
        System.out.println("Edit Menu:");
        System.out.println("1 - Edit first name");
        System.out.println("2 - Edit last name");
        System.out.println("3 - Edit email");
        System.out.println("4 - Edit company");
        System.out.println("5 - Edit phone number");
        System.out.println("6 - Edit contact group");
        System.out.println("7 - Edit address");
        System.out.println("8 - Edit birth date");
        System.out.println("9 - Return");
        System.out.println("Please enter option:");
    }

    private void showSearchMenu() {
        System.out.println("1 - Search contact by first name");
        System.out.println("1 - Search contact by last name");
        System.out.println("1 - Search contact by phone number");
        System.out.println("1 - Return");
        System.out.println("Please enter option:");
    }

    private void displayAllContacts() {
        System.out.println();
        System.out.println("  FIRST NAME      LAST NAME      PHONE NUMBER");
        for (Contact contact : contacts) {
            System.out.println("_________________________________________________");
            System.out.println(contact);
        }
    }

    private Set<Contact> getContactsSubGroupBasedOnFirstLetter() {
        System.out.println("Please filter contact list by entering first letter of the first name");
        String input = sc.next();
        Set<Contact> subGroupContactSet = new TreeSet<>();
        for (Contact contact : contacts) {
            if (contact.getFirstName().substring(0, 1).equals(input.toUpperCase())) {
                subGroupContactSet.add(contact);
            }
        }
        for (Contact contact : subGroupContactSet) {
            System.out.println(contact);
        }
        return subGroupContactSet;
    }

    private void searchContactByFirstName(Set<Contact> tempContactSet) {
        System.out.println("Please enter first name:");
        String input = sc.next();
        for (Contact contact : tempContactSet) {
            if (contact.getFirstName().toLowerCase().equals(input.toLowerCase())) {
                searchedContact = contact;
            }
        }

    }

    private void displaySearchedContactInfo() {
        System.out.println("Contact " + searchedContact.getFirstName() + searchedContact.getLastName());
        System.out.println("Phone number : " + searchedContact.getPhoneNumber().getCountryCode() + searchedContact.getPhoneNumber().getPhoneNumber());
        System.out.println("Company : " + searchedContact.getCompany().getName());
        System.out.println("Email : " + searchedContact.getEmail());
        System.out.println("Address : City " + searchedContact.getAddress().getCity());
        System.out.println("Group : " + searchedContact.getGroup());
    }

    private void doEditContact(int option){
        String input;
        switch (option){
            case 1:
                System.out.println("Selected contact first name is :"+searchedContact.getFirstName());
                System.out.println("Please enter new first name:");
                input = sc.next();
                searchedContact.setFirstName(input);
                System.out.println("Selected contact first name has been updated to " + searchedContact.getFirstName());
                break;
            case 2:
                System.out.println("Selected contact last name is :"+searchedContact.getLastName());
                System.out.println("Please enter new last name:");
                input = sc.next();
                searchedContact.setLastName(input);
                System.out.println("Selected contact first name has been updated to " + searchedContact.getLastName());
                break;
            case 3:
                System.out.println("Selected contact email address is :"+searchedContact.getEmail());
                System.out.println("Please enter new email address:");
                input = sc.nextLine();
                searchedContact.setEmail(input);
                System.out.println("Selected contact email address has been updated to " + searchedContact.getEmail());
                break;
            case 4:
                System.out.println("Selected contact company name is :"+searchedContact.getCompany().getName());
                System.out.println("Please enter new company name:");
                input = sc.nextLine();
                searchedContact.getCompany().setName(input);
                System.out.println("Selected contact company name has been updated to " + searchedContact.getCompany().getName());
                break;
            case 5:
                System.out.println("Selected contact phone number is :"+searchedContact.getPhoneNumber().getPhoneNumber());
                System.out.println("Please enter new phone number :");
                input = sc.nextLine();
                searchedContact.getPhoneNumber().setPhoneNumber(input);
                System.out.println("Selected contact phone number has been updated to " + searchedContact.getPhoneNumber().getPhoneNumber());
                break;
            case 6:
                System.out.println("Selected contact group is :"+searchedContact.getGroup());
                //TODO
                break;
            case 7:
                System.out.println("Selected contact address is : " + searchedContact.getAddress().getCity());
                //TODO
                break;
            case 8:
                System.out.println("Selected contact birth date is " + searchedContact.getBirthDate());
                //TODO
                break;
            case 9:
                System.out.println("Returning back to main menu!");
                break;
            default:
                break;
        }
    }

    private void doContactSubMenu(int option) {
        switch (option) {
            case 1:
                //edit contact
                showEditSubMenu();
                int option2=sc.nextInt();
                doEditContact(option2);
                break;
            case 2:
                if (contacts.contains(searchedContact)) {
                    contacts.remove(searchedContact);
                    System.out.println("Contact removed!");
                    searchedContact = null;
                    break;
                }
            case 3:
                //add to blacklist - create a blacklist set and store it, later this set can be displayed --- add option in menu
                break;
            case 4:
                searchedContact.setGroup(Group.FAVORITE);
                System.out.println("Contact added to favorites!");
                break;
            case 5:
                break;
            default:
                System.out.println("Option not found!");
                break;
        }
    }


}

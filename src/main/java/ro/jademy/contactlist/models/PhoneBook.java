package ro.jademy.contactlist.models;

import ro.jademy.contactlist.data.DataProvider;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    private static final Scanner INPUT = new Scanner(System.in);
    private Set<Contact> contacts;
    private Contact searchForContact;

    public PhoneBook(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void initiatePhoneBook() {
        do {
            displayMainMenu();
            byte option = INPUT.nextByte();
            switch (option) {
                case 1: // Display all contacts
                    displayAllContacts();
                    break;
                case 2: // Select a contact
                    searchForContactByFirstName(getContactsByFirstLetter());
                    displaySearchForContactInfo();
                    displayContactMenu();
                    System.out.println("Enter an option:");
                    option = INPUT.nextByte();
                    editContact(option);
                    break;
                case 3: // Search a contact
                    displaySearchMenu();
                    System.out.println("Enter an option:");
                    option = INPUT.nextByte();
                    searchContact(option);
                    displaySearchForContactInfo();
                    displayEditMenu();
                    System.out.println("Enter an option:");
                    option = INPUT.nextByte();
                    contactMenu(option);
                    break;
                case 4: // Add new contact
                    addNewContact();
                    break;
                case 5: // Exit app
                    System.exit(0);
                default: // For invalid inputs
                    System.out.println("Invalid input. Please, choose between [1-5] only!");
            }
        } while (true);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~ All menu's printing methods ~~~~~~~~~~~~~~~~~~~~~~~~

    private void displayMainMenu() {
        System.out.println("+---------------------------------------------+");
        System.out.println("|                 PHONE BOOK                  |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("         +-------  MAIN MENU  -------+         ");
        System.out.println("         |    1. Display Contacts    |         ");
        System.out.println("         |    2. Select Contact      |         ");
        System.out.println("         |    3. Search Contact      |         ");
        System.out.println("         |    4. Add new Contact     |         ");
        System.out.println("         |    5. Exit Phone Book     |         ");
        System.out.println("         +---------------------------+         ");
    }

    private void displayAllContacts() {
        System.out.println();
        System.out.println("  FIRST NAME      LAST NAME      PHONE NUMBER");
        for (Contact contact : DataProvider.contacts()) {
            System.out.println("_________________________________________________");
            System.out.println(contact);
        }
    }

    private void displaySearchForContactInfo() {
        System.out.println("Contact: " + searchForContact.getFirstName() + " " + searchForContact.getLastName());
        System.out.println("Phone Number: " + searchForContact.getPhoneNumber().getCountryCode() + " " +
                searchForContact.getPhoneNumber().getPhoneNumber());
        System.out.println("Company: " + searchForContact.getCompany().getName());
        System.out.println("E-mail: " + searchForContact.getEmail());
        System.out.println("Address - City: " + searchForContact.getAddress().getCity());
        System.out.println("Group: " + searchForContact.getGroup());

    }

    private void displayContactMenu() { // This is a subMenu to 'Select Contact' menu option from Main Menu
        System.out.println("+---------------------------------------------+");
        System.out.println("|                 PHONE BOOK                  |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("        +------- CONTACT MENU  -------+        ");
        System.out.println("        |  1. Edit Contact            |        ");
        System.out.println("        |  2. Remove Contact          |        ");
        System.out.println("        |  3. Add to Black List       |        ");
        System.out.println("        |  4. Remove from Black List  |        ");
        System.out.println("        |  5. Add to Favorites        |        ");
        System.out.println("        |  6. Remove from Favorites   |        ");
        System.out.println("        |  7. Return to Main Menu     |        ");
        System.out.println("        +-----------------------------+        ");
    }

    private void displaySearchMenu() { // This is a subMenu to 'Search Contact' menu option from Main Menu
        System.out.println("+---------------------------------------------+");
        System.out.println("|                 PHONE BOOK                  |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("         +------  SEARCH MENU  ------+         ");
        System.out.println("         |  1. By First Name         |         ");
        System.out.println("         |  2. By Last Name          |         ");
        System.out.println("         |  3. By Phone Number       |         ");
        System.out.println("         |  4. Return to Main Menu   |         ");
        System.out.println("         +---------------------------+         ");
    }

    private void displayEditMenu() {     // This is a subMenu to 'Edit Contact' menu option from Contact Menu
        System.out.println("+---------------------------------------------+");
        System.out.println("|                 PHONE BOOK                  |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("        +--------  EDIT MENU  --------+        ");
        System.out.println("        |  1. First Name              |        ");
        System.out.println("        |  2. Last Name               |        ");
        System.out.println("        |  3. E-mail                  |        ");
        System.out.println("        |  4. Company                 |        ");
        System.out.println("        |  5. Phone Number            |        ");
        System.out.println("        |  6. Group                   |        ");
        System.out.println("        |  7. Address                 |        ");
        System.out.println("        |  8. Birthdate               |        ");
        System.out.println("        |  9. Return to Contact Menu  |        ");
        System.out.println("        +-----------------------------+        ");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~ All searching methods ~~~~~~~~~~~~~~~~~~~~~~~~

    private void searchContact(byte option) {
        switch (option) {
            case 1: // Search by first name
                searchForContactByFirstName(contacts);
                break;
            case 2: // Search by last name
                searchForContactByLastName();
                break;
            case 3: // Search by phone number
                searchForContactByPhoneNumber();
                break;
            case 4: // Return to Main Menu
                break;
            default: // For invalid inputs
                System.out.println("Invalid input. Please, choose between [1-4] only!");
        }
    }

    private void getSearchedContact(Optional<Contact> contactOptional) {
        if (contactOptional.isPresent()) {
            searchForContact = contactOptional.get();
            System.out.println("Contact found!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    private void searchForContactByFirstName(Set<Contact> tempContactSet) {
        System.out.println("First name:");
        String firstName = INPUT.next().toLowerCase();
        Optional<Contact> contactOptional = contacts.stream()
                .filter(contact -> contact.getFirstName().toLowerCase().equalsIgnoreCase(firstName)).findAny();
        getSearchedContact(contactOptional);
    }

    private void searchForContactByLastName() {
        System.out.println("Last name:");
        String lastName = INPUT.next().toLowerCase();
        Optional<Contact> contactOptional = contacts.stream()
                .filter(contact -> contact.getLastName().toLowerCase().equalsIgnoreCase(lastName)).findAny();
        getSearchedContact(contactOptional);
    }

    private void searchForContactByPhoneNumber() {
        System.out.println("Phone number:");
        INPUT.skip("\n");
        String phoneNumber = INPUT.nextLine();
        Optional<Contact> contactOptional = contacts.stream()
                .filter(contact -> contact.getPhoneNumber().getPhoneNumber().equals(phoneNumber)).findAny();
        getSearchedContact(contactOptional);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~ All contact editing methods ~~~~~~~~~~~~~~~~~~~~~~~~

    private void editContact(byte option) {
        switch (option) {
            case 1: // edit first name
                System.out.println("You have selected: " + searchForContact.getFirstName());
                System.out.println("New First Name:");
                String anotherFirstName = INPUT.next();
                searchForContact.setFirstName(anotherFirstName);
                System.out.println("Updated to: " + searchForContact.getFirstName());
                break;
            case 2: // edit last name
                System.out.println("You have selected: " + searchForContact.getLastName());
                System.out.println("New Last Name:");
                String anotherLastName = INPUT.next();
                searchForContact.setLastName(anotherLastName);
                System.out.println("Updated to: " + searchForContact.getLastName());
                break;
            case 3: // edit e-mail
                System.out.println("You have selected: " + searchForContact.getEmail());
                System.out.println("New E-mail:");
                String anotherEmail = INPUT.next();
                searchForContact.setEmail(anotherEmail);
                System.out.println("Updated to: " + searchForContact.getEmail());
                break;
            case 4: // edit company name
                System.out.println("You have selected: " + searchForContact.getCompany().getName());
                System.out.println("New Company Name:");
                String anotherCompanyName = INPUT.nextLine();
                searchForContact.getCompany().setName(anotherCompanyName);
                System.out.println("Updated to: " + searchForContact.getCompany().getName());
                break;
            case 5: // edit phone number
                System.out.println("You have selected: " + searchForContact.getPhoneNumber().getPhoneNumber());
                System.out.println("New Phone Number:");
                String anotherPhoneNo = INPUT.nextLine();
                searchForContact.getPhoneNumber().setPhoneNumber(anotherPhoneNo);
                System.out.println("Updated to: " + searchForContact.getPhoneNumber().getPhoneNumber());
                break;
            case 6: // edit group
                System.out.println("You have selected: " + searchForContact.getGroup());
                //TODO
                break;
            case 7: // edit address
                System.out.println("You have selected: " + searchForContact.getAddress().getCity());
                //TODO
                break;
            case 8: // edit birthDate
                System.out.println("You have selected: " + searchForContact.getBirthDate());
                //TODO
                break;
            case 9: // Return to Contact Menu
                break;
            default: // For invalid inputs
                System.out.println("Invalid input. Please, choose between [1-9] only!");
        }
    }

    private void contactMenu(byte option) {
        switch (option) {
            case 1: // Edit Contact
                displayEditMenu();
                byte input = INPUT.nextByte();
                editContact(input);
                break;
            case 2: // Remove Contact
                if (contacts.contains(searchForContact)) {
                    contacts.remove(searchForContact);
                    System.out.println("Contact deleted!");
                    searchForContact = null;
                    break;
                }
            case 3: // Add to Black List
                //TODO
                 /*
                 creating a set that store the contact.
                 If the Black list is empty, display the current contact added
                 If not, display all contacts existing in the entire Black List
                 */
                break;
            case 4: // Remove from Black List
                //TODO
                // deleting a contact from set
                break;
            case 5: // Add to Favorites
                searchForContact.setGroup(Group.FAVORITE);
                System.out.println("Contact added to Favorites!");
                break;
            case 6: // Removed from Favorites
                searchForContact.setGroup(Group.MY_CONTACTS);
                System.out.println("Contact removed from Favorites!");
                break;
            case 7: // Return to Main Menu
                break;
            default: // For invalid inputs
                System.out.println("Invalid input. Please, choose between [1-7] only!");
        }
    }

    private void addNewContact() {
        Contact addNewContact = new Contact();
        System.out.println("Country Code:");
        String countryCode = INPUT.next();
        System.out.println("Phone Number:");
        String newPhoneNumber = INPUT.next();
        addNewContact.setPhoneNumber(new PhoneNumber(countryCode, newPhoneNumber));
        System.out.println("First Name:");
        String newFirstName = INPUT.next();
        addNewContact.setFirstName(newFirstName);
        System.out.println("Last Name:");
        String newLastName = INPUT.next();
        addNewContact.setLastName(newLastName);
        contacts.add(addNewContact);
    }

    private Set<Contact> getContactsByFirstLetter() {
        System.out.println("Enter the first letter of the first name:");
        String letter = INPUT.next();
        Set<Contact> contactSubSet = contacts.stream()
                .filter(contact -> contact.getFirstName().substring(0, 1).toLowerCase().equalsIgnoreCase(letter))
                .collect(Collectors.toCollection(TreeSet::new));
        contactSubSet.forEach(System.out::println);
        return contactSubSet;
    }
}

package ro.jademy.contactlist.models;

import org.apache.commons.lang3.StringUtils;
import ro.jademy.contactlist.customexceptions.ValidateInput;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {

    private static Scanner INPUT = new Scanner(System.in);
    private File blackListFile = new File("blackList.csv");
    private final Set<Contact> blackList = new TreeSet<>();
    private final Set<Contact> contacts;
    private Contact searchForContact;
    private final String fileName;

    public PhoneBook(Set<Contact> contacts, String fileName) {
        this.contacts = contacts;
        this.fileName = fileName;
    }

    public void initiatePhoneBook() {
        do {
            displayMainMenu();
            try {
                byte option = INPUT.nextByte();
                switch (option) {
                    case 1: // Display all contacts
                        displayAllContacts();
                        break;
                    case 2: // Select a contact
                        searchForContactByFirstName(getContactsByFirstLetter());
                        contactMenu();
                        break;
                    case 3: // Search a contact
                        displaySearchMenu();
                        searchContact();
                        displayContactMenu();
                        contactMenu();
                        break;
                    case 4: // Add new contact
                        addNewContact();
                        break;
                    case 5: // Show Black List
                        removeFromBlackList();
                        break;
                    case 6: // Exit app
                        deleteBlackListFile();
                        try {
                            writeFile(contacts, fileName);
                        } catch (IOException e) {
                            System.out.println("File not found!");
                        }
                        System.exit(0);
                        break;
                    default: // For invalid inputs
                        System.out.println("Invalid input. Please, choose between [1-6] only!");
                }
            } catch (InputMismatchException mismatchException) {
                System.out.println("Invalid input. Please, choose only the displayed options!");
                INPUT = new Scanner(System.in); // to break the loop
            }
        } while (true);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~ All menu's printing methods ~~~~~~~~~~~~~~~~~~~~~~~~

    private void displayMainMenu() {
        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|                 PHONE BOOK                  |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("         +-------  MAIN MENU  -------+         ");
        System.out.println("         |    1. Display Contacts    |         ");
        System.out.println("         |    2. Select Contact      |         ");
        System.out.println("         |    3. Search Contact      |         ");
        System.out.println("         |    4. Add new Contact     |         ");
        System.out.println("         |    5. Show Black List     |         ");
        System.out.println("         |    6. Exit Phone Book     |         ");
        System.out.println("         +---------------------------+         ");
    }

    private void displayAllContacts() {

        System.out.println();
        getHeader();
        for (Contact contact : contacts) {
            System.out.println("_________________________________________________");
            System.out.println(contact);
        }
    }

    private void displaySearchForContactInfo() {
        System.out.println("\nContact: " + searchForContact.getFirstName() + " " + searchForContact.getLastName());
        System.out.println("Phone Number: " + searchForContact.getPhoneNumber().getCountryCode() + " " +
                searchForContact.getPhoneNumber().getPhoneNumber());
        System.out.println("Company: " + searchForContact.getCompany().getName() +
                " | Job Title: " + searchForContact.getCompany().getJobTitle());
        System.out.println("Company Address: " + searchForContact.getCompany().getAddress().getStreetName() +
                " " + searchForContact.getCompany().getAddress().getStreetNo());
        if (searchForContact.getCompany().getAddress().getFloorNo() >= 4) {
            System.out.println("Floor: " + searchForContact.getCompany().getAddress().getFloorNo() + "th");
        } else if (searchForContact.getCompany().getAddress().getFloorNo() == 3) {
            System.out.println("Floor: " + searchForContact.getCompany().getAddress().getFloorNo() + "rd");
        } else if (searchForContact.getCompany().getAddress().getFloorNo() == 2) {
            System.out.println("Floor: " + searchForContact.getCompany().getAddress().getFloorNo() + "nd");
        } else if (searchForContact.getCompany().getAddress().getFloorNo() == 1) {
            System.out.println("Floor: " + searchForContact.getCompany().getAddress().getFloorNo() + "st");
        } else {
            System.out.println("Ground Floor");
        }
        System.out.println("E-mail: " + searchForContact.getEmail());
        System.out.println("Address - City: " + searchForContact.getAddress().getCity());
        System.out.println("Address - Country: " + searchForContact.getAddress().getCountry());
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
        System.out.println("        |  4. Add to Favorites        |        ");
        System.out.println("        |  5. Remove from Favorites   |        ");
        System.out.println("        |  6. Return to Main Menu     |        ");
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

    private void searchContact() {
        try {
            System.out.println("Enter an option:");
            byte option = INPUT.nextByte();
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
                    initiatePhoneBook();
                    break;
                default: // For invalid inputs
                    System.out.println("Invalid input. Please, choose between [1-4] only!");
                    displaySearchMenu();
                    searchContact();
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Invalid input. Please, choose between [1-4] only!");
            INPUT=new Scanner(System.in);
            displaySearchMenu();
            searchContact();
        }
    }

    private void searchForContactByFirstName(Set<Contact> tempContactSet) {
        System.out.println("First name:");
        String firstName = INPUT.next().toLowerCase();
        Optional<Contact> contactOptional = tempContactSet.stream()
                .filter(contact -> contact.getFirstName().equalsIgnoreCase(firstName)).findAny();
        if (contactOptional.isPresent()) {
            searchForContact = contactOptional.get();
            System.out.println("\nContact found!");
            displaySearchForContactInfo();
        } else {
            System.out.println("\nContact not found!");
        }
    }

    private void searchForContactByLastName() {
        System.out.println("Last name:");
        String lastName = INPUT.next().toLowerCase();
        Optional<Contact> contactOptional = contacts.stream()
                .filter(contact -> contact.getLastName().equalsIgnoreCase(lastName)).findAny();
        if (contactOptional.isPresent()) {
            searchForContact = contactOptional.get();
            System.out.println("Contact found!");
            displaySearchForContactInfo();
        } else {
            System.out.println("Contact not found!");
        }
    }

    private void searchForContactByPhoneNumber() {
        System.out.println("Phone number:");
        INPUT.skip("\n");
        String phoneNumber = INPUT.nextLine();
        Optional<Contact> contactOptional = contacts.stream()
                .filter(contact -> contact.getPhoneNumber().getPhoneNumber().equals(phoneNumber)).findAny();
        if (contactOptional.isPresent()) {
            searchForContact = contactOptional.get();
            System.out.println("Contact found!");
            displaySearchForContactInfo();
        } else {
            System.out.println("Contact not found!");
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~ All contact editing methods ~~~~~~~~~~~~~~~~~~~~~~~~

    private void editContact() {
        try {
            byte option = INPUT.nextByte();
            switch (option) {
                case 1: // edit first name
                    System.out.println("You have selected: " + searchForContact.getFirstName());
                    contacts.remove(searchForContact);
                    System.out.println("New First Name:");
                    String anotherFirstName = INPUT.next();
                    validateInput(anotherFirstName);
                    searchForContact.setFirstName(anotherFirstName);
                    System.out.println("Updated to: " + searchForContact.getFirstName());
                    contacts.add(searchForContact);
                    break;
                case 2: // edit last name
                    System.out.println("You have selected: " + searchForContact.getLastName());
                    System.out.println("New Last Name:");
                    String anotherLastName = INPUT.next();
                    validateInput(anotherLastName);
                    searchForContact.setLastName(anotherLastName);
                    System.out.println("Updated to: " + searchForContact.getLastName());
                    break;
                case 3: // edit e-mail
                    System.out.println("You have selected: " + searchForContact.getEmail());
                    System.out.println("New E-mail:");
                    String anotherEmail = INPUT.next();
                    validateInput(anotherEmail);
                    searchForContact.setEmail(anotherEmail);
                    System.out.println("Updated to: " + searchForContact.getEmail());
                    break;
                case 4: // edit company name
                    System.out.println("You have selected: " + searchForContact.getCompany().getName());
                    System.out.println("New Company Name:");
                    String anotherCompanyName = INPUT.nextLine();
                    validateInput(anotherCompanyName);
                    searchForContact.getCompany().setName(anotherCompanyName);
                    System.out.println("Updated to: " + searchForContact.getCompany().getName());
                    break;
                case 5: // edit phone number
                    System.out.println("You have selected: " + searchForContact.getPhoneNumber().getPhoneNumber());
                    System.out.println("New Phone Number:");
                    String anotherPhoneNo = INPUT.nextLine();
                    //TODO validateInput in case of a new number
                    searchForContact.getPhoneNumber().setPhoneNumber(anotherPhoneNo);
                    System.out.println("Updated to: " + searchForContact.getPhoneNumber().getPhoneNumber());
                    break;
                case 6: // edit group
                    System.out.println("Current group is: " + searchForContact.getGroup());
                    System.out.println("New Group:");
                    String groupName = INPUT.next();
                    //TODO validateInput
                    if (groupName.equalsIgnoreCase(Group.FAMILY.getGroupName())) {
                        searchForContact.setGroup(Group.FAMILY);
                    } else if (groupName.equalsIgnoreCase(Group.FRIENDS.getGroupName())) {
                        searchForContact.setGroup(Group.FRIENDS);
                    } else if (groupName.equalsIgnoreCase(Group.WORK.getGroupName())) {
                        searchForContact.setGroup(Group.WORK);
                    } else {
                        System.out.println("This group doesn't exist!");
                    }
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
                    displayEditMenu();
                    editContact();
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Invalid input. Please, choose between [1-9] only!");
            INPUT=new Scanner(System.in);
            displayEditMenu();
            editContact();
        }
    }

    private void contactMenu() {
        try {
            displayContactMenu();
            System.out.println("Enter an option:");
            byte option = INPUT.nextByte();
            switch (option) {
                case 1: // Edit Contact
                    displayEditMenu();
                    editContact();
                    break;
                case 2: // Remove Contact
                    if (contacts.contains(searchForContact)) {
                        contacts.remove(searchForContact);
                        System.out.println("Contact deleted!");
                        searchForContact = null;
                    }
                    break;
                case 3: // Add to Black List
                    if (blackList.isEmpty() && !blackListFile.exists()) {
                        createBlackListFile();
                    }
                    contacts.remove(searchForContact);
                    blackList.add(searchForContact);
                    System.out.println(searchForContact.getFirstName() + " was added to black list");
                    try {
                        writeFile(blackList, String.valueOf(blackListFile));
                    } catch (IOException e) {
                        System.out.println("Could not write file!");
                    }
                    searchForContact = null;
                    break;
                case 4: // Add to Favorites
                    searchForContact.setGroup(Group.FAVORITE);
                    System.out.println("Contact added to Favorites!");
                    break;
                case 5: // Removed from Favorites
                    searchForContact.setGroup(Group.MY_CONTACTS);
                    System.out.println("Contact removed from Favorites!");
                    break;
                case 6: // Return to Main Menu
                    initiatePhoneBook();
                    break;
                default: // For invalid inputs
                    System.out.println("Invalid input. Please, choose between [1-6] only!");
                    contactMenu();
            }
        }catch (InputMismatchException exception){
            System.out.println("Invalid input. Please, choose between [1-6] only!");
            INPUT=new Scanner(System.in);
            contactMenu();
        }
    }


    private void addNewContact() {
        try {
            System.out.println("Country Code:");
            String countryCode = INPUT.next();
            validateInput(countryCode);
            System.out.println("Phone Number:");
            String newPhoneNumber = INPUT.next();
            validateInput(newPhoneNumber);
            PhoneNumber phoneNumber = new PhoneNumber(countryCode, newPhoneNumber);
            validatePhoneNumber(phoneNumber);
            System.out.println("First Name:");
            String newFirstName = INPUT.next();
            validateInput(newFirstName);
            System.out.println("Last Name:");
            String newLastName = INPUT.next();
            validateInput(newLastName);
            contacts.add(new Contact((contacts.size() + 1), newFirstName, newLastName, phoneNumber));
        } catch (ValidateInput validation) {
            System.out.println(validation.getMessage());
            addNewContact();
        }
    }

    private Set<Contact> getContactsByFirstLetter() {
        System.out.println("Enter the first letter of the first name:");
        String letter = INPUT.next();
        Set<Contact> contactSubSet = contacts.stream()
                .filter(contact -> contact.getFirstName().substring(0, 1).equalsIgnoreCase(letter))
                .collect(Collectors.toCollection(TreeSet::new));
        contactSubSet.forEach(System.out::println);
        return contactSubSet;
    }

    private void createBlackListFile() {
        try {
            blackListFile.createNewFile();
            System.out.println("File has been created!");
        } catch (IOException e) {
            System.out.println("File could not create" + e);
        }
    }

    private void deleteBlackListFile() {
        if (blackList.isEmpty() && blackListFile.exists()) {
            blackListFile.delete();
        }
    }

    private void removeFromBlackList() {
        try {
            readFile(blackList, String.valueOf(blackListFile));
            if (blackList.isEmpty()) {
                System.out.println("Black List is empty!");
                deleteBlackListFile();
            } else {
                getHeader();
                blackList.forEach(System.out::println);
                System.out.println("Enter first name of the contact you want to remove:");
                String firstName = INPUT.next();
                Optional<Contact> optionalContact = blackList.stream()
                        .filter(contact -> contact.getFirstName().equalsIgnoreCase(firstName)).findAny();
                if (optionalContact.isPresent()) {
                    System.out.println("Removed " + optionalContact.get().getFirstName() + " from Black List");
                    blackList.remove(optionalContact.get());
                    contacts.add(optionalContact.get());
                    try {
                        writeFile(blackList, String.valueOf(blackListFile));
                    } catch (IOException e) {
                        System.out.println("Could not write file!");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file!");
        }
    }

    private void getHeader() {
        System.out.println(StringUtils.center("  FIRST NAME", 15, " ") +
                StringUtils.center("  LAST NAME", 16, " ") +
                StringUtils.center(" PHONE NUMBER", 16, " "));
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~ Input validation ~~~~~~~~~~~~~~~~~~~~~~~~

    private void validateInput(String input) throws ValidateInput {
        char[] inputChars = input.toCharArray();
        for (Character character : inputChars) {
            if (character == ',') {
                throw new ValidateInput("Character \",\" is denied!");
            } else if (character == '/') {
                throw new ValidateInput("Character \"/\" is denied!");
            }
        }
    }

    private void validatePhoneNumber(PhoneNumber phoneNumber) throws ValidateInput {
        if (phoneNumber.getCountryCode().charAt(0) != '+') {
            throw new ValidateInput("Country code must start with '+' sign!");
        }

        char[] countryCodeChars = phoneNumber.getCountryCode().substring(1).toCharArray();
        for (Character character : countryCodeChars) {
            if (!Character.isDigit(character)) {
                throw new ValidateInput("Country code must contain only digits after '+' sign!");
            }
        }

        phoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber().replace(" ", ""));
        char[] chars = phoneNumber.getPhoneNumber().toCharArray();
        for (Character character : chars) {
            if (!Character.isDigit(character)) {
                throw new ValidateInput("Phone number must contain only digits!");
            }
        }
        System.out.println("Phone number entered correctly!");
    }

    private String fileHeader() {
        return "ID,FIRST_NAME,LAST_NAME,EMAIL," +
                "COMPANY_NAME/JOB_TITLE/ADDRESS_STREET/ADDRESS_NO/" +
                "ADDRESS_DOOR_NO/ADDRESS_FLOOR_NO/ADDRESS_CITY/" +
                "ADDRESS_COUNTRY,COUNTRY_CODE/PHONE_NUMBER,GROUP,ADDRESS_STREET/" +
                "ADDRESS_NO/ADDRESS_DOOR_NO/ADDRESS_FLOOR_NO/ADDRESS_CITY/ADDRESS_COUNTRY/BIRTHDATE";

    }

    public void writeFile(Set<Contact> setList, String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(fileHeader());
        writer.newLine();
        for (Contact contact : setList) {
            String string = contact.getId() + "," + contact.getFirstName() + "," + contact.getLastName() +
                    "," + contact.getEmail() + "," + contact.getCompany().getName() +
                    "/" + contact.getCompany().getJobTitle() + "/" + contact.getCompany().getAddress().getStreetName() +
                    "/" + contact.getCompany().getAddress().getStreetNo() +
                    "/" + contact.getCompany().getAddress().getDoorNo() +
                    "/" + contact.getCompany().getAddress().getFloorNo() +
                    "/" + contact.getCompany().getAddress().getCity() +
                    "/" + contact.getCompany().getAddress().getCountry() +
                    "," + contact.getPhoneNumber().getCountryCode() + "/" + contact.getPhoneNumber().getPhoneNumber() +
                    "," + contact.getGroup().getGroupName() + "," + contact.getAddress().getStreetName() +
                    "/" + contact.getAddress().getStreetNo() + "/" + contact.getAddress().getDoorNo() +
                    "/" + contact.getAddress().getFloorNo() + "/" + contact.getAddress().getCity() +
                    "/" + contact.getAddress().getCountry() + "," + contact.getBirthDate();
            writer.write(string);
            writer.newLine();
            writer.flush();
        }
        writer.close();
    }

    public void readFile(Set<Contact> setList, String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;
        String header = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] strings = line.split(",");
            String[] company = strings[4].split("/");
            String[] phoneNo = strings[5].split("/");
            String[] address = strings[7].split("/");

            setList.add(new Contact(Integer.parseInt(strings[0]), strings[1], strings[2], strings[3],
                    new Company(company[0], company[1], new Address(company[2], company[3], Integer.parseInt(company[4]),
                            Integer.parseInt(company[5]), company[6], company[7])),
                    new PhoneNumber(phoneNo[0], phoneNo[1]), Group.lookup(strings[6], Group.MY_CONTACTS),
                    new Address(address[0], address[1], Integer.parseInt(address[2]), Integer.parseInt(address[3]),
                            address[4], address[5]), LocalDate.now()));
        }
        reader.close();
    }
}

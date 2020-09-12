package ro.jademy.contactlist.services;

import au.com.anthonybruno.Gen;
import com.github.javafaker.Faker;
import ro.jademy.contactlist.models.*;
import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class IOService {

    private static final String CONTACTS_FILE = "contacts.csv";

    public static void generateContacts() {

        Faker faker = Faker.instance(new Locale("en-GB"));
        Gen.start()
                .addField("ID", () -> faker.number().numberBetween(99, 199))
                .addField("FIRST_NAME", () -> faker.name().firstName())
                .addField("LAST_NAME", () -> faker.name().lastName())
                .addField("E-MAIL", () -> faker.internet().emailAddress())
                .addField("COMPANY", () -> new Company(faker.company().name(), faker.job().title(), new Address(
                        faker.address().streetName(),
                        faker.address().streetAddressNumber(),
                        Integer.parseInt(faker.number().digit()),   // doorNo
                        faker.number().numberBetween(0, 8),         // floorNo
                        faker.address().cityName(),
                        faker.country().name())))
                .addField("PHONE_NUMBER", () -> new PhoneNumber(
                        faker.regexify("+44"),
                        faker.phoneNumber().cellPhone()))
                .addField("GROUP", () -> Group.MY_CONTACTS)
                .addField("ADDRESS", () -> new Address(
                        faker.address().streetName(),
                        faker.address().streetAddressNumber(),
                        Integer.parseInt(faker.number().digit()),  // doorNo
                        faker.number().numberBetween(0, 8),         // floorNo
                        faker.address().cityName(),
                        faker.country().name()))
                .addField("BIRTHDATE", () -> faker.date().birthday(20, 50)
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .generate(100)
                .asCsv()
                .toFile(CONTACTS_FILE);
    }

    public static Set<Contact> readFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(CONTACTS_FILE));
        Set<Contact> contactSet = new TreeSet<>();

        String line;
        String header = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] strings = line.split(",");
            String[] company = strings[4].split("/");
            String[] phoneNo = strings[5].split("/");
            String[] address = strings[7].split("/");

            contactSet.add(new Contact(Integer.parseInt(strings[0]), strings[1], strings[2], strings[3],
                    new Company(company[0], company[1], new Address(company[2], company[3], Integer.parseInt(company[4]),
                            Integer.parseInt(company[5]), company[6], company[7])),
                    new PhoneNumber(phoneNo[0], phoneNo[1]), Group.lookup(strings[6], Group.MY_CONTACTS),
                    new Address(address[0], address[1], Integer.parseInt(address[2]), Integer.parseInt(address[3]),
                            address[4], address[5]), LocalDate.now()));
        }
        reader.close();
        return contactSet;
    }
}


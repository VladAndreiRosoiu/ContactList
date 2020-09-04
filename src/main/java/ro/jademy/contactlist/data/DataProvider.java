package ro.jademy.contactlist.data;

import com.github.javafaker.Faker;
import ro.jademy.contactlist.models.*;

import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class DataProvider {

    private static Faker faker = new Faker(new Locale("en-GB"));


    public static Set<Contact> contacts() {
        Set<Contact> contactSet = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            contactSet.add(new Contact(i, faker.name().firstName(), faker.name().lastName()
                    , faker.internet().emailAddress(), new Company(faker.company().name()),
                    new PhoneNumber(faker.phoneNumber().cellPhone()), Group.MY_CONTACTS,
                    new Address(faker.address().streetName(), faker.address().streetAddressNumber(),
                            faker.address().city()), faker.date().birthday(20, 50)));
        }
        return contactSet;
    }
}

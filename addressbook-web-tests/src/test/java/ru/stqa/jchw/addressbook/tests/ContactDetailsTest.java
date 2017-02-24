package ru.stqa.jchw.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

import java.util.stream.Stream;

import static org.testng.Assert.assertTrue;

public class ContactDetailsTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withAddress("144A Mira str., Apt. 1, Moscow 123456, Russia.").
                    withMobilePhone("8 (999) 11-11-111").withEmail("111@111.com"));
        }
    }

    @Test
    public void testDetailsContact () {
        ContactData contact = app.contact().all().iterator().next();
        String contactDetailsText = app.contact().initFromDetailsForm(contact);

        assertTrue(contactDetailsText.contains(contact.getFirstname() + " " + contact.getLastname()), "Contact details text doesn't contain name");
        assertTrue(contactDetailsText.contains(contact.getAddress()), "Contact details text doesn't contain address");

        String[] phones = contact.getAllPhones().split("\n");
        String[] emails = contact.getAllEmails().split("\n");

        Stream.of(phones)
                .forEach(phone -> assertTrue(cleaned(contactDetailsText).contains(phone), "Contact details text doesn't contain phone " + phone));
        Stream.of(emails)
                .forEach(email -> assertTrue(contactDetailsText.contains(email), "Contact details text doesn't contain email " + email));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}

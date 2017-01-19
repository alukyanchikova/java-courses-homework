package ru.stqa.jchw.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("Ivanov", "Ivanov",
                "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                "111@111.com", "test"));
    }
}

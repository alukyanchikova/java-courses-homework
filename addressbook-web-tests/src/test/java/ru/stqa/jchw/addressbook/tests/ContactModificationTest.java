package ru.stqa.jchw.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ivanova", "Ivanka",
                "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                "111@111.com", null), false);
        app.getContactHelper().submitContactModification();
    }
}

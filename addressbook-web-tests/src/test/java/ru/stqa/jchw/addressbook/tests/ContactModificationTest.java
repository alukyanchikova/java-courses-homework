package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        int numberOfElementsBefore = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivanov", "Ivanov",
                    "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                    "111@111.com", "test"), true);
        }
        app.getContactHelper().initContactModification(numberOfElementsBefore - 1);
        app.getContactHelper().fillContactForm(new ContactData("Ivanova", "Ivanka",
                "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                "111@111.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        int numberOfElementsAfter = app.getContactHelper().getContactCount();
        Assert.assertEquals(numberOfElementsAfter, numberOfElementsBefore);
    }
}

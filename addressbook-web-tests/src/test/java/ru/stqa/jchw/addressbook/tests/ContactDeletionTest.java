package ru.stqa.jchw.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivanov", "Ivanov",
                    "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                    "111@111.com", "test"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().clickOnDelete();
        app.getContactHelper().acceptDeletion();
    }
}

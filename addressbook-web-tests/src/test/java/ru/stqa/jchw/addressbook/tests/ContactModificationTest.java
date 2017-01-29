package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov",
                    "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                    "111@111.com", "test"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int lastContactIndex = before.size() - 1;
        app.getContactHelper().initContactModification(lastContactIndex);
        ContactData contact = new ContactData("Ann", "Antonova",
                "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                "111@111.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(lastContactIndex);
        before.add(contact);


        Comparator<ContactData> byLastAndFirstName = (o1, o2) -> {
           int lestNameCompareResult = o1.getLastname().compareTo(o2.getLastname());
           if (lestNameCompareResult == 0) {
               return o1.getFirstname().compareTo(o2.getFirstname());
           }
           return lestNameCompareResult;
        };

        before.sort(byLastAndFirstName);
        after.sort(byLastAndFirstName);
        Assert.assertEquals(before, after);
    }
}
package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoHomePage();
        if (app.getContactHelper().getContactList().size() == 0) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov",
                    "144A Mira str., Apt. 1, Moscow 123456, Russia.", "8 (999) 11-11-111",
                    "111@111.com", "test"));
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int indexContactDeletion = before.size() - 1;
        app.getContactHelper().deleteContact(indexContactDeletion);
        List<ContactData> after = app.getContactHelper().getContactList();
        //оставить
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(indexContactDeletion);
        Assert.assertEquals(before, after);
    }


}

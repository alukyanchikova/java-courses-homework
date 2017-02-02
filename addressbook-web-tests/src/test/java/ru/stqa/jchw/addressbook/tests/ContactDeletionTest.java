package ru.stqa.jchw.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jchw.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().createContact(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withAddress("144A Mira str., Apt. 1, Moscow 123456, Russia.").
                    withMobilePhone("8 (999) 11-11-111").withtEmail("111@111.com").withGroup("test"));
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int indexContactDeletion = before.size() - 1;
        app.contact().delete(indexContactDeletion);
        List<ContactData> after = app.contact().list();
        //оставить
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(indexContactDeletion);
        Assert.assertEquals(before, after);
    }


}
